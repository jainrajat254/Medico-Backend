package com.example.Medico.common.service;

import com.example.Medico.common.jwt.JWTService;
import com.example.Medico.common.model.LoginCredentials;
import com.example.Medico.doctor.model.DoctorDetails;
import com.example.Medico.doctor.repository.DoctorRepository;
import com.example.Medico.doctor.responses.DoctorLoginResponse;
import com.example.Medico.user.model.Users;
import com.example.Medico.user.repository.UserRepository;
import com.example.Medico.user.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Object login(LoginCredentials credentials) {
        String email = credentials.getEmail();
        String password = credentials.getPassword();
        String role = credentials.getRole();

        Users user = userRepository.findByEmail(email);
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword()) && "USER".equalsIgnoreCase(role)) {
            String token = jwtService.generateToken(email);
            return new UserResponse(token,
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getAge(),
                    user.getGender(),
                    user.getBloodGroup(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getPassword());
        }

        // Authenticate doctor
        DoctorDetails doctorDetails = doctorRepository.findByEmail(email);
        if (doctorDetails != null && bCryptPasswordEncoder.matches(password, doctorDetails.getPassword()) && "DOCTOR".equalsIgnoreCase(role)) {
            String token = jwtService.generateToken(email);
            return new DoctorLoginResponse(
                    token,
                    doctorDetails.getId(),
                    doctorDetails.getUid(),
                    doctorDetails.getFirstName(),
                    doctorDetails.getLastName(),
                    doctorDetails.getDob(),
                    doctorDetails.getGender(),
                    doctorDetails.getState(),
                    doctorDetails.getDistrict(),
                    doctorDetails.getZipCode(),
                    doctorDetails.getAddress(),
                    doctorDetails.getWorkspaceName(),
                    doctorDetails.getFee(),
                    doctorDetails.getWorkingTime(),
                    doctorDetails.getMedicalRegNo(),
                    doctorDetails.getSpecialization(),
                    doctorDetails.getQualification(),
                    doctorDetails.getExperience(),
                    doctorDetails.getPhone(),
                    doctorDetails.getEmail(),
                    doctorDetails.isAvailableForOnlineConsultation(),
                    doctorDetails.getPassword()
            );

        }

        throw new BadCredentialsException("Invalid Username or Password");
    }

}

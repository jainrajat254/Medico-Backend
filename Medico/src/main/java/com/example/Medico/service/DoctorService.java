package com.example.Medico.service;

import com.example.Medico.jwt.JWTService;
import com.example.Medico.model.Doctor;
import com.example.Medico.model.LoginCredentials;
import com.example.Medico.model.PersonalInfo;
import com.example.Medico.model.Users;
import com.example.Medico.repository.DoctorRepository;
import com.example.Medico.responses.DoctorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Doctor register(Doctor doctor) {
        doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
        return doctorRepository.save(doctor);
    }

    public DoctorResponse login(LoginCredentials credentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
        if (authentication.isAuthenticated()) {
            Optional<Doctor> doctor = Optional.ofNullable(doctorRepository.findByEmail(credentials.getEmail()));

            if ((doctor.isPresent())) {
                Doctor doctorEntity = doctor.get();
                String token = jwtService.generateToken(credentials.getEmail());
                return new DoctorResponse(token,
                        doctorEntity.getId(),
                        doctorEntity.getFirstName(),
                        doctorEntity.getLastName(),
                        doctorEntity.getEmail()
                );
            } else {
                throw new BadCredentialsException("Invalid Username or Password");
            }
        } else {
            throw new BadCredentialsException("Invalid Username or Password");
        }
    }

    public void addPhoto(MultipartFile file, String uid) throws IOException {
        Doctor doctor = doctorRepository.findByUid(uid)
                .orElseThrow(() -> new RuntimeException("Doctor not found with UID: " + uid));

        doctor.setProfilePicture(file.getBytes()); // Save the photo as a byte array
        doctorRepository.save(doctor); // Save the doctor with the new photo
    }

    // Method to get doctor details
    public Doctor getPersonalInfo(String uid) {
        return doctorRepository.findByUid(uid)
                .orElseThrow(() -> new RuntimeException("Doctor not found with UID: " + uid));
    }
}

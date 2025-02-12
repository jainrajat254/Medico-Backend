package com.example.Medico.service;

import com.example.Medico.DTO.DocAddressDetailsDTO;
import com.example.Medico.DTO.DocMedicalDetailsDTO;
import com.example.Medico.DTO.EditDocDTO;
import com.example.Medico.jwt.JWTService;
import com.example.Medico.model.Doctor;
import com.example.Medico.model.LoginCredentials;
import com.example.Medico.model.Users;
import com.example.Medico.repository.DoctorRepository;
import com.example.Medico.repository.UserRepository;
import com.example.Medico.responses.DoctorResponse;
import com.example.Medico.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorService {

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

        // Authenticate user first
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
        Doctor doctor = doctorRepository.findByEmail(email);
        if (doctor != null && bCryptPasswordEncoder.matches(password, doctor.getPassword()) && "DOCTOR".equalsIgnoreCase(role)) {
            String token = jwtService.generateToken(email);
            return new DoctorResponse(
                    token,
                    doctor.getId(),
                    doctor.getUid(),
                    doctor.getFirstName(),
                    doctor.getLastName(),
                    doctor.getDob(),
                    doctor.getGender(),
                    doctor.getState(),
                    doctor.getDistrict(),
                    doctor.getZipCode(),
                    doctor.getAddress(),
                    doctor.getWorkspaceName(),
                    doctor.getFee(),
                    doctor.getWorkingTime(),
                    doctor.getMedicalRegNo(),
                    doctor.getSpecialization(),
                    doctor.getQualification(),
                    doctor.getExperience(),
                    doctor.getPhone(),
                    doctor.getEmail(),
                    doctor.isAvailableForOnlineConsultation(),
                    doctor.getPassword()
            );

        }

        throw new BadCredentialsException("Invalid Username or Password");
    }


    public Doctor register(Doctor doctor) {
        doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
        return doctorRepository.save(doctor);
    }

    public void addPhoto(MultipartFile file, UUID id) throws IOException {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with UID: " + id));

        doctor.setProfilePicture(file.getBytes()); // Save the photo as a byte array
        doctorRepository.save(doctor); // Save the doctor with the new photo
    }

    @Transactional(readOnly = true)
    public Doctor getPersonalInfo(UUID id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with UID: " + id));
    }

    public Doctor editDocPersonalDetails(EditDocDTO userDTO, UUID id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Doctor not found"));
        doctor.setUid(userDTO.getUid());
        doctor.setDob(userDTO.getDob());
        doctor.setPhone(userDTO.getPhone());
        doctor.setEmail(userDTO.getEmail());
        return doctorRepository.save(doctor);
    }

    public Doctor editDocAddressDetails(DocAddressDetailsDTO docDTO, UUID id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Doctor not found"));
        doctor.setWorkspaceName(docDTO.getWorkspaceName());
        doctor.setAddress(docDTO.getAddress());
        doctor.setState(docDTO.getState());
        doctor.setDistrict(docDTO.getDistrict());
        doctor.setZipCode(docDTO.getZipCode());
        return doctorRepository.save(doctor);
    }

    public Doctor editDocMedicalDetails(DocMedicalDetailsDTO docDTO, UUID id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Doctor not found"));
        doctor.setMedicalRegNo(docDTO.getMedicalRegNo());
        doctor.setQualification(docDTO.getQualification());
        doctor.setSpecialization(docDTO.getSpecialization());
        doctor.setExperience(docDTO.getExperience());
        doctor.setFee(docDTO.getFee());
        doctor.setAvailableForOnlineConsultation(docDTO.isAvailableForOnlineConsultation());
        return doctorRepository.save(doctor);
    }
}

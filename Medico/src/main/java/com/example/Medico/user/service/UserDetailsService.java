package com.example.Medico.user.service;

import com.example.Medico.user.model.UserDetails;
import com.example.Medico.user.model.Users;
import com.example.Medico.user.repository.UserDetailsRepository;
import com.example.Medico.user.repository.UserRepository;
import com.example.Medico.user.responses.UserDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public UserDetails addPersonalInfo(UserDetailsResponse userDetailsResponse, UUID id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        UserDetails userDetails = new UserDetails(
                user,
                userDetailsResponse.getHeight(),
                userDetailsResponse.getWeight(),
                userDetailsResponse.getDob(),
                userDetailsResponse.getEmergencyContactName(),
                userDetailsResponse.getEmergencyContactRelation(),
                userDetailsResponse.getEmergencyContactPhone(),
                userDetailsResponse.getState(),
                userDetailsResponse.getDistrict(),
                userDetailsResponse.getCity(),
                userDetailsResponse.getCurrentAddress(),
                userDetailsResponse.getPermanentAddress(),
                userDetailsResponse.getKnownHealthConditions(),
                userDetailsResponse.getFamilyMedicalHistory(),
                userDetailsResponse.getInsuranceProvider(),
                userDetailsResponse.getPolicyNumber(),
                userDetailsResponse.getGroupNumber(),
                userDetailsResponse.getCoverageDetails()
        );
        return userDetailsRepository.save(userDetails);
    }

    public UserDetails addPhoto(MultipartFile file, UUID id) throws IOException {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDetails userDetails = userDetailsRepository.findByUsers(user);
        if (userDetails == null) {
            userDetails = new UserDetails();
            userDetails.setUsers(user);  // Corrected here
        }

        byte[] photoBytes = file.getBytes();
        userDetails.setPhoto(photoBytes);

        return userDetailsRepository.save(userDetails);
    }

    public UserDetails getPersonalInfo(UUID id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userDetailsRepository.findByUsers(user);
    }
}

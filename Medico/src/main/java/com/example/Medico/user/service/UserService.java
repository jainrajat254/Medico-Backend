package com.example.Medico.user.service;

import com.example.Medico.user.dto.EditUserDetails;
import com.example.Medico.common.dto.EditPassword;
import com.example.Medico.common.jwt.JWTService;
import com.example.Medico.user.model.UserDetails;
import com.example.Medico.user.repository.UserDetailsRepository;
import com.example.Medico.user.repository.UserRepository;
import com.example.Medico.user.model.Users;
import com.example.Medico.user.responses.UserDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTService jwtService;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Users editDetails(EditUserDetails userDTO, UUID id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setAge(userDTO.getAge());
        user.setBloodGroup(userDTO.getBloodGroup());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }

    public void editPassword(EditPassword request, UUID id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!bCryptPasswordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect current password.");
        }
        user.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }


    public UserDetails extraDetails(UserDetailsResponse response, UUID id) {
        UserDetails existingUserDetails = userDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        // Update fields only if they are not null or empty in the response
        if (response.getHeight() != null && !response.getHeight().isEmpty()) {
            existingUserDetails.setHeight(response.getHeight());
        }
        if (response.getWeight() != null && !response.getWeight().isEmpty()) {
            existingUserDetails.setWeight(response.getWeight());
        }
        if (response.getDob() != null) {
            existingUserDetails.setDob(response.getDob());
        }
        if (response.getEmergencyContactName() != null && !response.getEmergencyContactName().isEmpty()) {
            existingUserDetails.setEmergencyContactName(response.getEmergencyContactName());
        }
        if (response.getEmergencyContactRelation() != null && !response.getEmergencyContactRelation().isEmpty()) {
            existingUserDetails.setEmergencyContactRelation(response.getEmergencyContactRelation());
        }
        if (response.getEmergencyContactPhone() != null && !response.getEmergencyContactPhone().isEmpty()) {
            existingUserDetails.setEmergencyContactPhone(response.getEmergencyContactPhone());
        }
        if (response.getState() != null && !response.getState().isEmpty()) {
            existingUserDetails.setState(response.getState());
        }
        if (response.getDistrict() != null && !response.getDistrict().isEmpty()) {
            existingUserDetails.setDistrict(response.getDistrict());
        }
        if (response.getCity() != null && !response.getCity().isEmpty()) {
            existingUserDetails.setCity(response.getCity());
        }
        if (response.getCurrentAddress() != null && !response.getCurrentAddress().isEmpty()) {
            existingUserDetails.setCurrentAddress(response.getCurrentAddress());
        }
        if (response.getPermanentAddress() != null && !response.getPermanentAddress().isEmpty()) {
            existingUserDetails.setPermanentAddress(response.getPermanentAddress());
        }
        if (response.getKnownHealthConditions() != null && !response.getKnownHealthConditions().isEmpty()) {
            existingUserDetails.setKnownHealthConditions(response.getKnownHealthConditions());
        }
        if (response.getFamilyMedicalHistory() != null && !response.getFamilyMedicalHistory().isEmpty()) {
            existingUserDetails.setFamilyMedicalHistory(response.getFamilyMedicalHistory());
        }
        if (response.getInsuranceProvider() != null && !response.getInsuranceProvider().isEmpty()) {
            existingUserDetails.setInsuranceProvider(response.getInsuranceProvider());
        }
        if (response.getPolicyNumber() != null && !response.getPolicyNumber().isEmpty()) {
            existingUserDetails.setPolicyNumber(response.getPolicyNumber());
        }
        if (response.getGroupNumber() != null && !response.getGroupNumber().isEmpty()) {
            existingUserDetails.setGroupNumber(response.getGroupNumber());
        }
        if (response.getCoverageDetails() != null && !response.getCoverageDetails().isEmpty()) {
            existingUserDetails.setCoverageDetails(response.getCoverageDetails());
        }
        if (response.getPhoto() != null) {
            existingUserDetails.setPhoto(response.getPhoto());
        }

        // Save the updated UserDetails
        return userDetailsRepository.save(existingUserDetails);
    }

}

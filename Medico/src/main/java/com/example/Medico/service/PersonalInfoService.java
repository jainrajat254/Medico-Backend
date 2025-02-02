package com.example.Medico.service;

import com.example.Medico.model.PersonalInfo;
import com.example.Medico.model.Users;
import com.example.Medico.repository.PersonalInfoRepository;
import com.example.Medico.repository.UserRepository;
import com.example.Medico.responses.PersonalInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class PersonalInfoService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonalInfoRepository personalInfoRepository;

    public PersonalInfo addPersonalInfo(PersonalInfoResponse personalInfoResponse, UUID id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        PersonalInfo personalInfo = new PersonalInfo(
                user,
                personalInfoResponse.getHeight(),
                personalInfoResponse.getWeight(),
                personalInfoResponse.getDob(),
                personalInfoResponse.getEmergencyContactName(),
                personalInfoResponse.getEmergencyContactRelation(),
                personalInfoResponse.getEmergencyContactPhone(),
                personalInfoResponse.getCurrentAddress(),
                personalInfoResponse.getPermanentAddress(),
                personalInfoResponse.getKnownHealthConditions(),
                personalInfoResponse.getFamilyMedicalHistory(),
                personalInfoResponse.getInsuranceProvider(),
                personalInfoResponse.getPolicyNumber(),
                personalInfoResponse.getGroupNumber(),
                personalInfoResponse.getCoverageDetails()
        );
        return personalInfoRepository.save(personalInfo);
    }

    public PersonalInfo addPhoto(MultipartFile file, UUID id) throws IOException {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PersonalInfo personalInfo = personalInfoRepository.findByUsers(user);
        if (personalInfo == null) {
            personalInfo = new PersonalInfo();
            personalInfo.setUsers(user);  // Corrected here
        }

        byte[] photoBytes = file.getBytes();
        personalInfo.setPhoto(photoBytes);

        return personalInfoRepository.save(personalInfo);
    }

    public PersonalInfo getPersonalInfo(UUID id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return personalInfoRepository.findByUsers(user);
    }
}

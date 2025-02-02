package com.example.Medico.repository;

import com.example.Medico.model.PersonalInfo;
import com.example.Medico.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, UUID> {

    PersonalInfo findByUsers_Id(UUID id);

    PersonalInfo findByUsers(Users user);
}

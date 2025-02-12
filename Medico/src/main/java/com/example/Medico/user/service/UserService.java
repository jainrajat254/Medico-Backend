package com.example.Medico.user.service;

import com.example.Medico.user.dto.EditUserDetails;
import com.example.Medico.common.dto.EditPassword;
import com.example.Medico.common.jwt.JWTService;
import com.example.Medico.user.repository.UserRepository;
import com.example.Medico.user.model.Users;
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

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

//    public UserResponse login(LoginCredentials credentials) {
//        Authentication authentication = authenticationManager.authenticate (
//                new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
//        if (authentication.isAuthenticated()) {
//            Optional<Users> user = Optional.ofNullable(userRepository.findByEmail(credentials.getEmail()));
//
//            if (user.isPresent()) {
//                Users userEntity = user.get();
//                String token = jwtService.generateToken(credentials.getEmail());
//                return new UserResponse(token,
//                        userEntity.getId(),
//                        userEntity.getFirstName(),
//                        userEntity.getLastName(),
//                        userEntity.getAge(),
//                        userEntity.getGender(),
//                        userEntity.getBloodGroup(),
//                        userEntity.getPhone(),
//                        userEntity.getEmail());
//            } else {
//                throw new BadCredentialsException("Invalid Username or Password");
//            }
//        } else
//            throw new BadCredentialsException("Invalid Username or Password");
//    }

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
        // Fetch user by ID
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Check if the current password matches the stored password
        if (!bCryptPasswordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect current password.");
        }

        // Update the password
        user.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }


}

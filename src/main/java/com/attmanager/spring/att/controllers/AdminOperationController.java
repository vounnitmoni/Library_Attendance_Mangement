package com.attmanager.spring.att.controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.attmanager.spring.att.models.Student;
import com.attmanager.spring.att.models.User;
import com.attmanager.spring.att.payload.request.ChangePasswordRequest;
import com.attmanager.spring.att.payload.response.MessageResponse;
import com.attmanager.spring.att.payload.response.UserPasswordResponse;
import com.attmanager.spring.att.repository.StudentRepository;
import com.attmanager.spring.att.repository.UserRepository;
import com.attmanager.spring.att.security.services.UserDetailsImpl;

@CrossOrigin(maxAge = 3600, origins = "*")
@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminOperationController {
    @Autowired UserRepository userRepository;
    @Autowired AuthenticationManager authenticationManager;
    @Autowired StudentRepository studentRepository;

    @PutMapping("/changepassword")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest cpRequest){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Boolean isMatch = encoder.matches(cpRequest.getOld_password(),userRepository.userPassword(userDetails.getUsername()));
        if(!isMatch){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Your old password is wrong!"));
        }else{
            if(!cpRequest.getNew_password().equalsIgnoreCase(cpRequest.getAgain_newpassword())){
                return ResponseEntity.badRequest().body(new MessageResponse("Error: New passwords are not match! "));
            }else{
                userRepository.findById(userDetails.getId()).map((user) -> {
                    user.setPassword(encoder.encode(cpRequest.getNew_password()));
                    return userRepository.save(user);
                });
                // User user = userRepository.findById(userDetails.getId());
            }
        }
        if(!cpRequest.getNew_password().equalsIgnoreCase(cpRequest.getAgain_newpassword())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: New passwords are not match!"));
        }


        return ResponseEntity.ok().body(new MessageResponse("Password is sucessfully changed!"));
    }

    @PutMapping("/updatestudentprofile/{id}")
    public ResponseEntity<?> updateProfile(@Valid @PathVariable String id, @RequestParam("file") MultipartFile file){
        studentRepository.findById(id).map((student)->{
            try {
                student.setProfilePic(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return studentRepository.save(student);
        });
        return ResponseEntity.ok().body(new MessageResponse("Success!"));
    }

    @GetMapping("/testuser")
    public ResponseEntity<?> test(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        UserPasswordResponse userPasswordResponse = new UserPasswordResponse(userDetails.getPassword());
        return ResponseEntity.ok().body(userPasswordResponse);
    }
}
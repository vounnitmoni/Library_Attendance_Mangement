package com.attmanager.spring.att.controllers;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attmanager.spring.att.models.StudentJoinLibrary;
import com.attmanager.spring.att.payload.request.JoinLibraryRequest;
import com.attmanager.spring.att.payload.response.JoinLibraryResponse;
import com.attmanager.spring.att.payload.response.ResponseJoinLibBadRequest;
import com.attmanager.spring.att.repository.StudentJoinLibraryRepository;
import com.attmanager.spring.att.repository.StudentRepository;

@RestController
@CrossOrigin(origins =  "*", maxAge=3600)
@RequestMapping("/api/v1")
public class GeneralController {
    @Autowired StudentJoinLibraryRepository studentJoinLibraryRepository;
    @Autowired StudentRepository studentRepository;
    @PostMapping("/joinlibrary")
    public ResponseEntity<?> joinLibrary(@Valid @RequestBody JoinLibraryRequest jRequest){
        LocalTime joinTime = LocalTime.now();
        LocalDate joinDate = LocalDate.now();
        BigInteger isExist = studentRepository.existsStudentById(jRequest.getSid());
        Boolean existen = false;
        StudentJoinLibrary sJoinLibrary = new StudentJoinLibrary(jRequest.getSid(),
                                                                       joinTime,
                                                                       joinDate);
        String department = studentRepository.studentDepartment(jRequest.getSid());
        // Optional<Student> = studentRepository.findById(jRequest.getSid());
        if(isExist == BigInteger.ZERO){
            return ResponseEntity.badRequest().body(new ResponseJoinLibBadRequest("There is no such an ID: " + jRequest.getSid()+ "!", existen));
        }else{
            existen = true;
        }
        studentJoinLibraryRepository.save(sJoinLibrary);                                            
        return ResponseEntity.ok().body(new JoinLibraryResponse(jRequest.getSid(), 
                                                                studentRepository.findStudentName(jRequest.getSid()),
                                                                department, 
                                                                studentRepository.findStudentProfile(jRequest.getSid()),
                                                                "You have succefully joined!",
                                                                existen));
    }
}


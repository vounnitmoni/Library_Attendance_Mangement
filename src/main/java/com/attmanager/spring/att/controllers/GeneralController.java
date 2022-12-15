package com.attmanager.spring.att.controllers;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attmanager.spring.att.models.StudentJoinLibrary;
import com.attmanager.spring.att.payload.request.JoinLibraryRequest;
import com.attmanager.spring.att.payload.response.MessageResponse;
import com.attmanager.spring.att.repository.StudentJoinLibraryRepository;

@RestController
@RequestMapping("/api/v1")
public class GeneralController {
    @Autowired StudentJoinLibraryRepository studentJoinLibraryRepository;
    
    @PostMapping("/joinlibrary")
    public ResponseEntity<?> joinLibrary(@Valid @RequestBody JoinLibraryRequest jRequest){
        LocalTime joinTime = LocalTime.now();
        LocalDate joinDate = LocalDate.now();
        StudentJoinLibrary sJoinLibrary = new StudentJoinLibrary(jRequest.getSid(),
                                                                    joinTime,
                                                                    joinDate);
        if(!studentJoinLibraryRepository.existsByStudentid(jRequest.getSid())){
            studentJoinLibraryRepository.save(sJoinLibrary);
            return ResponseEntity.ok(new MessageResponse("You have never join library before! But we appreciate on your first time of being here"));
        }else{
            if(studentJoinLibraryRepository.studentJoinDate(jRequest.getSid()).isEqual(joinDate)){
                return ResponseEntity.badRequest().body(new MessageResponse("You have already joined!"));
            }
        }
            studentJoinLibraryRepository.save(sJoinLibrary);
                                                              
        return ResponseEntity.ok(new MessageResponse("You have successfully joined!"));
    }
}

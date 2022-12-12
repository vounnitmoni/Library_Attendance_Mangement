package com.attmanager.spring.att.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attmanager.spring.att.models.StudentJoinLibrary;
import com.attmanager.spring.att.payload.request.ManualReportRequest;
import com.attmanager.spring.att.repository.StudentJoinLibraryRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    public static final long serialVersionUID = 1L;
    @Autowired StudentJoinLibraryRepository studentJoinLibraryRepository;
    
    @GetMapping("/todayjoins")
    public List<StudentJoinLibrary> sJoinLibrary(){
        LocalDate nowDate = LocalDate.now();
        return studentJoinLibraryRepository.joinInfoByDate(nowDate);
    }

    @PostMapping("/manualreport")
    public ResponseEntity<?> manualReport(@Valid @RequestBody ManualReportRequest mRequest){
        return ResponseEntity.ok().body(studentJoinLibraryRepository.findJoinInfoManually(mRequest.getFromDate(), mRequest.getToDate()));
    }


}

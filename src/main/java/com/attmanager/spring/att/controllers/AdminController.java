package com.attmanager.spring.att.controllers;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attmanager.spring.att.models.ERole;
import com.attmanager.spring.att.models.Role;
import com.attmanager.spring.att.models.StudentJoinLibrary;
import com.attmanager.spring.att.models.User;
import com.attmanager.spring.att.payload.request.ManualReportRequest;
import com.attmanager.spring.att.payload.request.SignupRequest;
import com.attmanager.spring.att.payload.response.MessageResponse;
import com.attmanager.spring.att.payload.response.ReportByDep;
import com.attmanager.spring.att.payload.response.ReportByDepAndYear;
import com.attmanager.spring.att.payload.response.StudentJoinLibraryByYear;
import com.attmanager.spring.att.payload.response.SuccessWithMessageResponse;
import com.attmanager.spring.att.repository.RoleRepository;
import com.attmanager.spring.att.repository.StudentJoinLibraryRepository;
import com.attmanager.spring.att.repository.UserRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    public static final long serialVersionUID = 1L;
    LocalDate nowDate = LocalDate.now();
    @Autowired StudentJoinLibraryRepository studentJoinLibraryRepository;
    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder encoder;
    @Autowired RoleRepository roleRepository;

    @GetMapping("/todayjoins")
    public ResponseEntity<?> joinToday(){
        List<StudentJoinLibrary> studentJoin = studentJoinLibraryRepository.joinInfoByDate(nowDate);
        if(studentJoin==null){
            return ResponseEntity.badRequest().body(new MessageResponse("No student joins yet!"));
        }else{
            return ResponseEntity.ok().body(studentJoinLibraryRepository.joinInfoByDate(nowDate));
        }
        
    }

    @PostMapping("/manualreport")
    public ResponseEntity<?> manualReport(@Valid @RequestBody ManualReportRequest mRequest){
        return ResponseEntity.ok().body(studentJoinLibraryRepository.findJoinInfoManually(mRequest.getFromDate(), mRequest.getToDate()));
    }

    @GetMapping("/reportbystudentyear")
    public ResponseEntity<?> reportstudentyear(){
        List<Object[]> report = studentJoinLibraryRepository.reporbystudentyear();
        List<StudentJoinLibraryByYear> result = new ArrayList<>((report.size()));
        for(Object[] objarr : report){
            result.add(new StudentJoinLibraryByYear((String) objarr[0], (BigInteger) objarr[1]));
        }
        return ResponseEntity.ok().body(result);
    }
    @PostMapping("/reportbystudentyearanddate")
    public ResponseEntity<?> reportByStudentYearandDate(@Valid @RequestBody ManualReportRequest mRequest){
        List<Object[]> report = studentJoinLibraryRepository.reporByStudentYearAndDate(mRequest.getFromDate(), mRequest.getToDate());
        List<StudentJoinLibraryByYear> result = new ArrayList<>((report.size()));
        for(Object[] objarr : report){
            result.add(new StudentJoinLibraryByYear((String) objarr[0], (BigInteger) objarr[1]));
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/reportbystudentdep")
    public ResponseEntity<?> reportByStudentDepartment(){
        List<Object[]> report = studentJoinLibraryRepository.reporByStudentDep();
        List<ReportByDep> result = new ArrayList<>((report.size()));
        for(Object[] objarr : report){
            result.add(new ReportByDep((String) objarr[0], (BigInteger) objarr[1]));
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/reportbystudentdepanddate")
    public ResponseEntity<?> reportByStudentDepAndDate(@Valid @RequestBody ManualReportRequest mRequest){
        List<Object[]> report = studentJoinLibraryRepository.reporByStudentDepAndDate(mRequest.getFromDate(), mRequest.getToDate());
        List<ReportByDep> result = new ArrayList<>((report.size()));
        for(Object[] objarr : report){
            result.add(new ReportByDep((String) objarr[0], (BigInteger) objarr[1]));
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/reportbystudentdepandyear")
    public ResponseEntity<?> reportByStudentDepAndYear(){
        List<Object[]> report = studentJoinLibraryRepository.reportByStudentDepAndYear();
        List<ReportByDepAndYear> result = new ArrayList<>((report.size()));
        for(Object[] objarr : report){
            result.add(new ReportByDepAndYear((String) objarr[0], (BigInteger) objarr[1]));
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(signUpRequest.getUsername(),
                            signUpRequest.getEmail(),
                            encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        
        strRoles.forEach(role -> {
            switch (role) {
            case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            }
        });
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new SuccessWithMessageResponse("User has successfully registered! ", true));
  }

}

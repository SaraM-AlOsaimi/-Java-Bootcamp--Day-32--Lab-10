package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-seeking-system/job-application")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping("/get/{user_id}")
    public ResponseEntity<?> getAllJobApplications(@PathVariable Integer user_id){
        List getAllJobApplications = jobApplicationService.getAllJobApplications(user_id);
        if(getAllJobApplications == null){
            return ResponseEntity.status(400).body(new ApiResponse("Nothing found"));
        }
        return ResponseEntity.status(200).body(getAllJobApplications);
    }


    @PostMapping("/apply/for/job")
    public ResponseEntity<?> applyForJob(@RequestBody @Valid JobApplication jobApplication, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Integer isApplied = jobApplicationService.applyForJob(jobApplication);
        if(isApplied == 1){
            return ResponseEntity.status(200).body(new ApiResponse("Applied successfully"));
        } else if(isApplied == 2){
            return ResponseEntity.status(400).body(new ApiResponse("There is no job post with the given ID"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("You are not registered in the system"));
    }

    @DeleteMapping("/delete/{job_application_id}")
    public ResponseEntity<?> withdrawJobApplication(@PathVariable Integer job_application_id){
        Boolean isDeleted = jobApplicationService.withdrawJobApplication(job_application_id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("The application has been withdrawn successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The is no apply request for the given ID"));
    }

}

package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-seeking-system-job-posts")
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllJobPosts(){
        return ResponseEntity.status(200).body(jobPostService.getAllJobPosts());
    }

    @PostMapping("/add/{user_id}")
    public ResponseEntity<?> addJobPost( @PathVariable Integer user_id,@RequestBody @Valid JobPost jobPost , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
       Boolean isEmployer = jobPostService.addJobPost(user_id,jobPost);
        if(isEmployer){
            return ResponseEntity.status(200).body(new ApiResponse("Job Post added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Only employers can add a job post"));
    }

    @PutMapping("/update/{user_id}/{id}")
    public ResponseEntity<?> updateJobPost(@PathVariable Integer user_id,@PathVariable Integer id, @RequestBody @Valid JobPost jobPost , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Integer isUpdated = jobPostService.updateJobPost(user_id,id,jobPost);
        if(isUpdated == 2){
            return ResponseEntity.status(200).body(new ApiResponse("Job Post Updated"));
        }
        if (isUpdated == 1){
            return ResponseEntity.status(400).body(new ApiResponse("JobPost not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Only employers can Update a job post"));
    }

    @DeleteMapping("/delete/{user_id}/{id}")
    public ResponseEntity<?> deleteJobPost(@PathVariable Integer user_id,@PathVariable Integer id){
        Integer isDeleted = jobPostService.deleteJobPost(user_id,id);
        if(isDeleted == 1){
            return ResponseEntity.status(400).body(new ApiResponse("JobPost not found"));
        }
        if(isDeleted == 2){
            return ResponseEntity.status(400).body(new ApiResponse("User not found"));
        }
        if(isDeleted == 3){
            return ResponseEntity.status(200).body(new ApiResponse("Job Post Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Only employers can Delete a job post"));
    }


}

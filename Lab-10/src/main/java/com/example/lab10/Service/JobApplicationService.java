package com.example.lab10.Service;

import com.example.lab10.Model.JobApplication;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.JobApplicationRepository;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;


    public List<JobApplication> getAllJobApplications(Integer user_id){
        User user = userRepository.getById(user_id);
        if(user != null){
         List<JobApplication> jobApplications = jobApplicationRepository.findAll();
         for(JobApplication jobApplication : jobApplications){
             if(jobApplication.getUser_id().equals(user_id)){
                 jobApplications.add(jobApplication);
                 return jobApplications;
             }
          }
        }
        return null;
    }


    public Integer applyForJob(JobApplication jobApplication){
       for (User user : userRepository.findAll()){
           if(user.getId().equals(jobApplication.getUser_id())){
               for(JobPost jobPost : jobPostRepository.findAll()){
                   if(jobPost.getId().equals(jobApplication.getJob_post_id())){
                      jobApplicationRepository.save(jobApplication);
                      return 1;
                   }
               }
               return 2;
           }
       }
       return 3;
    }


    public Boolean withdrawJobApplication(Integer job_application_id){
        for (JobApplication jobApplication : jobApplicationRepository.findAll()){
            if (jobApplication.getId().equals(job_application_id)){
                jobApplicationRepository.delete(jobApplication);
                return true;
            }
        }
        return false;
    }

}

package com.example.lab10.Service;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;

    public List<JobPost> getAllJobPosts(){
        return jobPostRepository.findAll();
    }

    public Boolean addJobPost(Integer user_id , JobPost jobPost){
        User user = userRepository.getById(user_id);
        if(user.getRole().equals("employer")){
            jobPostRepository.save(jobPost);
            return true;
        }
        return false;
    }

    public Integer updateJobPost(Integer user_id,Integer id , JobPost jobPost){
        JobPost oldJobPost = jobPostRepository.getById(id);
        if(oldJobPost == null){
            return 1;
        }
        User user = userRepository.getById(user_id);
        if(user.getRole().equals("employer")){
            oldJobPost.setTitle(jobPost.getTitle());
            oldJobPost.setDescription(jobPost.getDescription());
            oldJobPost.setLocation(jobPost.getLocation());
            oldJobPost.setSalary(jobPost.getSalary());
            oldJobPost.setPosting_date(jobPost.getPosting_date());
            jobPostRepository.save(oldJobPost);
            return 2;
        }
        return 3;
    }

    public Integer deleteJobPost(Integer user_id,Integer id){
        JobPost jobPost = jobPostRepository.getById(id);
        if(jobPost == null){
            return 1;
        }
        User user = userRepository.getById(user_id);
        if(user == null){
            return 2;
        }
        if(user.getRole().equals("employer")){
        jobPostRepository.delete(jobPost);
        return 3;
    }
        return 4;
}

}

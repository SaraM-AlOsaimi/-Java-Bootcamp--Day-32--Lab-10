package com.example.lab10.Service;

import com.example.lab10.Model.User;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public void addUser(User user){
        userRepository.save(user);
    }

    public Boolean updateUser(Integer id , User user){
        User oldUser = userRepository.getById(id);
        if(oldUser == null){
            return false;
        }
        oldUser.setAge(user.getAge());
        oldUser.setRole(user.getRole());
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        userRepository.save(oldUser);
        return true;
    }

    public Boolean deleteUser(Integer id){
        User user = userRepository.getById(id);
        if(user != null){
            userRepository.delete(user);
            return true;
        }
        return false;
    }


}

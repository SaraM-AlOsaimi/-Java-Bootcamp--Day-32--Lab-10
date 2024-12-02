package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.User;
import com.example.lab10.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-seeking-system-users")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id , @RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdated = userService.updateUser(id , user);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("User Updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        Boolean isDeleted = userService.deleteUser(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("User Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }


}

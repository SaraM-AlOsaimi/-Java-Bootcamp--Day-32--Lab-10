package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@Check(constraints = "age > 21")
@Check(constraints = "role='job_seeker' or role='employer'")
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // name:
    //- Cannot be null.
    //- Length must be more than 4 characters.
    //- Must contain only characters (no numbers).
    @NotEmpty(message = "Name is empty")
    @Size(min = 5, message = "Name length must be more than 4 characters")
    @Pattern(regexp = "^[A-Za-z]+$" , message = "Name must be only characters no numbers")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;

    //▪ email:
    //- Must be a valid email format.
    //- Must be unique.
    @NotBlank(message = "Email is blank")
    @Email(message = "Enter a valid email format")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    //▪ password:
    //- Cannot be null.
    @NotEmpty(message = "Password is empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String password;

    // ▪ age:
    //- Cannot be null.
    //- Must be a number.
    //- Must be more than 21.
    @NotNull(message = "Age is null")
    @Min(value = 22 , message = "age must be 22 or above")
    @Column(columnDefinition = "int not null")
    private Integer age;

    //▪ role:
    //- Cannot be null.
    //- Must be either "JOB_SEEKER" or "EMPLOYER" only.
    @NotEmpty(message = "Role is empty")
    @Pattern(regexp = "^job_seeker|employer$")
    @Column(columnDefinition = "varchar(10) not null")
    private String role;


}

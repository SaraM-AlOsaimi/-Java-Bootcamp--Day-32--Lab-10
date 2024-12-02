package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // userId:
    //- Cannot be null.
    @NotNull(message = "UserId is null")
    @Column(columnDefinition = "int not null")
    private Integer user_id;

    // ▪ jobPostId:
    //- Cannot be null.
    @NotNull(message = "jobPostId is null")
    @Column(columnDefinition = "int not null")
    private Integer job_post_id;


}

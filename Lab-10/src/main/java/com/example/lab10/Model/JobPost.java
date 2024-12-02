package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // title:
    //- Cannot be null.
    //- Length must be more than 4 characters.
    @NotEmpty(message = "Job Post title is empty")
    @Size(min = 5 , message = "title length must be more than 4 characters")
    @Column(columnDefinition = "varchar(50) not null")
    private String title;

    //▪ description:
    //- Cannot be null.
    @NotEmpty(message = "description is empty")
    @Column(columnDefinition = "varchar(200) not null")
    private String description;

    // ▪ location:
    //- Cannot be null.
    @NotEmpty(message = "Location is empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String location;

    // ▪ salary:
    //- Cannot be null.
    //- Must be a non-negative number.
    @NotNull(message = "Salary is null")
    @Positive(message = "salary must be a heighten than 0!")
    private Integer salary;

    //▪ postingDate
    private LocalDate posting_date;

}

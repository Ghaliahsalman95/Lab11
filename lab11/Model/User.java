package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data@AllArgsConstructor@NoArgsConstructor@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer ID;

    // @NotEmpty(message = "name not empty")
    //  @Column(columnDefinition = "name varchar(10) not null check ( length(name)>4 ) ")
    @Size(min = 4,message = "name must be more 4 character")
    private String name;
    //  @NotEmpty(message = "username not empty")
    //  @Column(columnDefinition = " varchar(10) not null unique")
    @Size(min = 4,message = "username must be more 6 character")
    private String username;
    //   @NotEmpty(message = "password not empty")
    // @Column(columnDefinition = "varchar(10) not null )")
    private String password;
    //  @NotEmpty(message = "email not empty")
    @Email(message = "email valid")
    //@Column(columnDefinition = "varchar(10) not null unique")
    private  String email;
    @NotNull
    @Column(columnDefinition = "DATETIME ")
    private LocalDate registrationDate;
}

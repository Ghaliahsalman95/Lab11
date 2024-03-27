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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer ID;
    // @NotEmpty(message = "title not empty")
    //  @Column(columnDefinition = "title varchar(20) not null check ( length(title)>10 ) ")
    @Size(min = 4,message = "title must be more 10 character")
    private String title;
    //  @NotEmpty(message = "content not empty")
    //  @Column(columnDefinition = " varchar(100) not null")
    @Size(min = 20,message = "content must be more 50 character")
    private String content;
    @Column(columnDefinition = "int not null")
    @NotNull(message = "user_id must be not null")
    private Integer userId;
    @Column(columnDefinition = "int not null")
    @NotNull(message = "category_id must be not null")
    private Integer categoryId;
    private LocalDate publishDate;




}

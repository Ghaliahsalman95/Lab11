package com.example.lab11.Model;

import jakarta.persistence.*;
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

public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer ID;
    @Column(columnDefinition = "int not null")
    @NotNull(message = "user_id must be not null")
    private Integer userId;
    @Column(columnDefinition = "int not null")
    @NotNull(message = "post_id must be not null")
    private Integer postId;
    //  @NotEmpty(message = "content not empty")
    //  @Column(columnDefinition = " varchar(100) not null")
    @Size(min = 20,message = "content must be more 50 character")
    private String content;
    private LocalDate commentDate;


}

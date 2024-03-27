package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Category {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer ID;
    @Column(columnDefinition = "varchar(10) not null unique")
    @NotNull(message = "Category Name  must not be empty")
    @Size(min = 3,message = "Category Name have to be more than 3 length long")
    private String name;
}

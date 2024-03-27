package com.example.lab11.Repositry;

import com.example.lab11.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositry extends JpaRepository<Category,Integer> {
@Query("select c from Category c where c.ID=?1")
    Category findByID(Integer id);
    //Category findByID(Integer id); without query

}

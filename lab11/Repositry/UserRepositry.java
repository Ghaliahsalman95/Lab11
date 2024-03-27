package com.example.lab11.Repositry;

import com.example.lab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositry extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.ID=?1")
    User findByID(Integer id);
    //    //User findByID(Integer id); without query
    @Query("select u from User u where u.username=?1 and u.password=?2")
    User findByUsernameAndPassword(String username,String passord);
}

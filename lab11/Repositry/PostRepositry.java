package com.example.lab11.Repositry;

import com.example.lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PostRepositry extends JpaRepository<Post,Integer> {

    @Query("select p from Post p where p.ID=?1")
    Post findByID(Integer id);
    //Post findByID(Integer id); without query
    @Query("select p from Post  p where p.userId=?1")
    List<Post> getAllPostOfuserID(Integer user_id);
    //@Query("select p from Post p where p.publish_date")
    @Query("select p from Post p where p.publishDate<=?1")
    List<Post> getPostByPublishDateBefore(LocalDate post_date);
    @Query("select p from Post p where p.categoryId=?1")
    List<Post> findPostsByCategoryId(Integer category_id);
    @Query("select p from Post p where p.title=?1")
    List<Post> getByTitle(String title);
    List<Post> findPostsByPublishDateBefore(LocalDate date);
List<Post> getPostsByTitle(String title);
  //  List<Post> findPostsByPublish_dateBefore(Date date);
}

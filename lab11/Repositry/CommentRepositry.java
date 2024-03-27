package com.example.lab11.Repositry;

import com.example.lab11.Model.Comment;
import com.example.lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepositry extends JpaRepository<Comment,Integer> {
    @Query("select c from Comment c where c.ID=?1")
    Comment findByID(Integer id);
    //Comment findByID(Integer id); without query

    @Query("select c from Comment  c where c.userId=?1")
    List<Comment> getAllCommentsOfuserID(Integer user_id);
    @Query("select c from Comment  c where c.postId=?1")
    List<Comment> getAllCommentOfPostID(Integer post_id);
      List<Comment> findCommentsByCommentDateAndAndUserIdAndPostId(LocalDate date,Integer user_id,Integer post_id);
}

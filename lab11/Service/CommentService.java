package com.example.lab11.Service;

import com.example.lab11.API.APIException;
import com.example.lab11.Model.Comment;
import com.example.lab11.Repositry.CommentRepositry;
import com.example.lab11.Repositry.PostRepositry;
import com.example.lab11.Repositry.UserRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {


    private final CommentRepositry commentRepositry;
    private final PostRepositry postRepositry;
    private final UserRepositry userRepositry;

    public List<Comment> getAll() {
        if (commentRepositry.findAll().isEmpty())
            throw new APIException("Empty list");
        return commentRepositry.findAll();
    }

    public void add(Comment comment) {

        if (userRepositry.findByID(comment.getUserId()) != null) {

            if (postRepositry.findByID(comment.getPostId()) != null) {
                comment.setCommentDate(LocalDate.now());
                commentRepositry.save(comment);
            } else throw new APIException("Comment without Post ");

        } else throw new APIException("Comment without user ");
    }

    public void delete(Integer id) {

        if (commentRepositry.findByID(id) != null)
            commentRepositry.delete(commentRepositry.findByID(id));

        throw new APIException("failed deleted because not Found ID");
    }

    public void update(Integer id, Comment comment) {

        if (commentRepositry.findByID(id) != null) {
            if (userRepositry.findByID(comment.getUserId()) != null) {

                if (postRepositry.findByID(comment.getPostId()) != null) {
                    Comment retrivecomment = commentRepositry.findByID(id);
                    retrivecomment.setContent(comment.getContent());
                    retrivecomment.setUserId(comment.getUserId());
                    retrivecomment.setCommentDate(comment.getCommentDate());
                    retrivecomment.setPostId(comment.getPostId());
                    commentRepositry.save(retrivecomment);
                } else throw new APIException("Comment without Post ");

            } else throw new APIException("Comment without user ");

        }
        throw new APIException("failed updated because not Found ID" + id);
    }

//----------------------------------1

    public List<Comment> getAllCommentOfPostAtDateAtUser_id(Integer post_id, LocalDate comment_date, Integer user_id) {
        if (commentRepositry.findCommentsByCommentDateAndAndUserIdAndPostId(comment_date, user_id, post_id).isEmpty())
            throw new APIException("No Comment Of Post At Date" + comment_date);
        return commentRepositry.findCommentsByCommentDateAndAndUserIdAndPostId(comment_date, user_id, post_id);
    }

    //--------------------------------------------2


}

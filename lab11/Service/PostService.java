package com.example.lab11.Service;

import com.example.lab11.API.APIException;
import com.example.lab11.Model.Comment;
import com.example.lab11.Model.Post;
import com.example.lab11.Repositry.CategoryRepositry;
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
public class PostService {

    private final PostRepositry postRepositry;
    private final CommentRepositry commentRepositry;
    private final UserRepositry userRepositry;
    private final CategoryRepositry categoryRepositry;

    public List<Post> getAll() {
        if (postRepositry.findAll().isEmpty())
            throw new APIException("Empty list");
        return postRepositry.findAll();
    }

    public void add(Post post) {

        if (userRepositry.findByID(post.getUserId()) != null) {
            if (categoryRepositry.findByID(post.getCategoryId()) != null) {
               post.setPublishDate(LocalDate.now());
                postRepositry.save(post);
            } else throw new APIException("Post without category ");
        } else throw new APIException("Post without user ");
    }

    public void delete(Integer id) {

        if (postRepositry.findByID(id) != null)
            postRepositry.delete(postRepositry.findByID(id));

        throw new APIException("failed deleted because not Found ID");
    }

    public void update(Integer id, Post post) {

        if (postRepositry.findByID(id) != null) {
            if (userRepositry.findByID(post.getUserId()) != null) {
                if (categoryRepositry.findByID(post.getCategoryId()) != null) {

                    Post retrivePost = postRepositry.findByID(id);
                    retrivePost.setCategoryId(post.getCategoryId());
                    retrivePost.setContent(post.getContent());
                    retrivePost.setTitle(post.getTitle());
                    retrivePost.setUserId(post.getUserId());
                  //retrivePost.setPublishDate(post.getPublishDate());
                    retrivePost.setPublishDate(LocalDate.now());
                    postRepositry.save(retrivePost);
                } else throw new APIException("Post without user ");

            } else throw new APIException("Post without user ");
        }else throw new APIException("failed updated because not Found ID" + id);

    }


    //-----------------------------3

    public List<Comment> getAllComents(Integer post_id) {
        if (commentRepositry.getAllCommentOfPostID(post_id).isEmpty())
            throw new APIException("Empty comment of Post id" + post_id);
        return commentRepositry.getAllCommentOfPostID(post_id);

    }

    //--------------------------4
    public List<Post> getallPostBefore(LocalDate date_post) {
        if (postRepositry.findPostsByPublishDateBefore(date_post).isEmpty())
            throw new APIException("Empty posts before date by date " + date_post);
        return postRepositry.findPostsByPublishDateBefore(date_post);
    }

    //-------------------------------7
    public List<Post> getTitle(String title) {
        if (postRepositry.getPostsByTitle(title).isEmpty())
        {  throw new APIException("Empty posts by title " + title);}

        return postRepositry.getPostsByTitle(title);
    }


}

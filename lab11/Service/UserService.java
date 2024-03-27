package com.example.lab11.Service;

import com.example.lab11.API.APIException;
import com.example.lab11.Model.Comment;
import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Repositry.CommentRepositry;
import com.example.lab11.Repositry.PostRepositry;
import com.example.lab11.Repositry.UserRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service@RequiredArgsConstructor
public class UserService {

    private final UserRepositry userRepositry;
    private final PostRepositry postRepositry;
private final CommentRepositry commentRepositry;

    public List<User> getAll(){
        return userRepositry.findAll();
    }

    public void add(User user){
        user.setRegistrationDate(LocalDate.now());
        userRepositry.save(user);
    }
    public void delete(Integer id){

        if(userRepositry.findByID(id)!=null)
            userRepositry.delete(userRepositry.findByID(id));

        throw new APIException("failed deleted because not Found ID");
    }
    public void update(Integer id,User user){

        if (userRepositry.findByID(id)!=null)
        {
            User retriveUser=userRepositry.findByID(id);
            retriveUser.setUsername(user.getUsername());
            retriveUser.setName(user.getName());
            retriveUser.setEmail(user.getEmail());
            retriveUser.setPassword(user.getPassword());
            retriveUser.setRegistrationDate(user.getRegistrationDate());
            userRepositry.save(retriveUser);
        }throw  new APIException("ailed updated because not Found ID"+id);
    }
//////////////////////////////////////////////////////////////////

    //------------------------1
    public List<Post> getallPost(Integer user_id){
        if (postRepositry.getAllPostOfuserID(user_id).isEmpty()){
            throw new APIException("Empty post user id"+user_id);
        }
        return postRepositry.getAllPostOfuserID(user_id);
    }
//----------------------------------2

    public List<Comment> getallComment(Integer user_id){
        if (commentRepositry.getAllCommentsOfuserID(user_id).isEmpty()){
            throw new APIException("Empty Comment user id"+user_id);
        }
        return commentRepositry.getAllCommentsOfuserID(user_id);
    }

    public User login(String username,String password){
        if (userRepositry.findByUsernameAndPassword(username,password)!=null)
            return userRepositry.findByUsernameAndPassword(username,password);
        throw new APIException("Not Allowed login");
    }

}

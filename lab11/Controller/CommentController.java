package com.example.lab11.Controller;

import com.example.lab11.API.APIResponse;
import com.example.lab11.Model.Comment;
import com.example.lab11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;


    @GetMapping("/get-all")
    public ResponseEntity getall() {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Comment comment, Errors errors) {

        if (errors.hasErrors())
            ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        commentService.add(comment);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.update(id, comment);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Updated Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        commentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Deleted Successfully"));

    }

    //-----------------------------------FirstExtra-----------------------
    @GetMapping("/get-all-comment/{post_id}/{comment_date}/{user_id}")
    public ResponseEntity getAllCommentOfPostAtDateAtUser_id(@PathVariable Integer post_id, @PathVariable LocalDate comment_date, @PathVariable Integer user_id){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentOfPostAtDateAtUser_id(post_id,comment_date,user_id));


    }
}

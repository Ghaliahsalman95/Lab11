package com.example.lab11.Controller;

import com.example.lab11.API.APIResponse;
import com.example.lab11.Model.Post;
import com.example.lab11.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
    
    private final PostService postService;


    @GetMapping("/get-all")
    public ResponseEntity getall(){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Post post, Errors errors){

        if (errors.hasErrors())
            ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        postService.add(post);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Added Successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody @Valid Post post,Errors errors)
    { if (errors.hasErrors())
    {ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());}
        postService.update(id,post);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Updated Successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        postService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Deleted Successfully"));
    }

    //--------------------------Fifth
    @GetMapping("/get-all-comment/{post_id}")
    public ResponseEntity getAllComents(@PathVariable Integer post_id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllComents(post_id));
    }

    //--------------------sixth
    @GetMapping("get-date/{date}")
    public ResponseEntity getall(@PathVariable LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getallPostBefore(date));
    }

    //-------------------seventh
    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getTitle(@PathVariable String title){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getTitle(title));
    }

}

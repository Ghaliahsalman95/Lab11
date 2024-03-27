package com.example.lab11.Controller;

import com.example.lab11.API.APIResponse;
import com.example.lab11.Model.User;
import com.example.lab11.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;


    @GetMapping("/get-all")
    public ResponseEntity getall(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid User user, Errors errors){

        if (errors.hasErrors())
            ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        userService.add(user);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Added Successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody @Valid User user,Errors errors)
    { if (errors.hasErrors())
    {ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());}
        userService.update(id,user);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Updated Successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Deleted Successfully"));
    }

    ///---------------------------------ThirdExtra
    @GetMapping("get-all-post/{user_id}")
    public ResponseEntity getallPost(@PathVariable Integer user_id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getallPost(user_id));
    }
    ///---------------------------------FourthExtra

    @GetMapping("get-all-comment/{user_id}")

    public ResponseEntity getallComment(@PathVariable Integer user_id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getallComment(user_id));
    }
@GetMapping("login/{username}/{password}")
public ResponseEntity login(@PathVariable String username,@PathVariable String password){
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(username,password));
}
}

package com.example.lab11.Controller;

import com.example.lab11.API.APIResponse;
import com.example.lab11.Model.Category;
import com.example.lab11.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryConyroller {

    private final CategoryService categoryService;
    @GetMapping("/get-all")
    public ResponseEntity getall(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Category category, Errors errors){

        if (errors.hasErrors())
            ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        categoryService.add(category);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Added Successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody @Valid Category category,Errors errors)
    { if (errors.hasErrors())
    {ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());}
        categoryService.update(id,category);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Updated Successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Deleted Successfully"));

    }

    //-----------------------------------SecondExtra-----------------------
    @GetMapping("/get-all-post-category/{category_id}")
    public ResponseEntity getAllpostCategory(@PathVariable Integer category_id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllpostCategory(category_id));
    }
}

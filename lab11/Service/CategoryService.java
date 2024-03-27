package com.example.lab11.Service;

import com.example.lab11.API.APIException;
import com.example.lab11.Model.Category;
import com.example.lab11.Model.Post;
import com.example.lab11.Repositry.CategoryRepositry;
import com.example.lab11.Repositry.PostRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service@RequiredArgsConstructor
public class CategoryService {
    
    private final CategoryRepositry categoryRepositry;
    private final PostRepositry postRepositry;

    public List<Category> getAll(){
        if (categoryRepositry.findAll().isEmpty())
            throw new APIException("Empty list");
        return categoryRepositry.findAll();
    }

    public void add(Category category){
        categoryRepositry.save(category);
    }
    public void delete(Integer id){

        if(categoryRepositry.findByID(id)!=null)
            categoryRepositry.delete(categoryRepositry.findByID(id));

        throw new APIException("failed deleted because not Found ID");
    }
    public void update(Integer id,Category category){

        if (categoryRepositry.findByID(id)!=null)
        {
            Category retriveCategory=categoryRepositry.findByID(id);
            retriveCategory.setName(category.getName());
            categoryRepositry.save(retriveCategory);
        }throw  new APIException("failed updated because not Found ID"+id);
    }


    //----------------------------------8
public List<Post> getAllpostCategory(Integer category_id){
        if (postRepositry.findPostsByCategoryId(category_id).isEmpty())
            throw new APIException("Empty posts by category_id "+category_id);
return postRepositry.findPostsByCategoryId(category_id);
}


}

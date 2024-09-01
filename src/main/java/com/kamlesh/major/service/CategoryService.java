package com.kamlesh.major.service;

import com.kamlesh.major.model.Category;
import com.kamlesh.major.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public  void addCategory(Category category){
        categoryRepository.save(category);

    }
    public void removeCategoryById(int id){
        categoryRepository.deleteById(id);
    }
    public Optional getCategoryById(int id){
        return categoryRepository.findById(id) ;
    }
}

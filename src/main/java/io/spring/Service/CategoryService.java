package io.spring.Service;

import io.spring.Entity.Category;
import io.spring.Entity.News;
import io.spring.Repository.CategoryRepository;
import io.spring.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository ;

    public List<Category> fetchAll(){
        return categoryRepository.findAll();
    }

    public Category findById(long id){
        return categoryRepository.findById(id).get();
    }

    public void save(Category category){
        categoryRepository.save(category);
    }

    public void delete(long id){
        categoryRepository.deleteById(id);
    }

    public List<Category> fetchCateChild(int parent_id){
        return categoryRepository.CateListChild(parent_id);
    }

}

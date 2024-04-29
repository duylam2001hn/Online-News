package io.spring.Repository;

import io.spring.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("select c from Category c where c.parent_id = ?1")
    public List<Category> CateListChild(int parent_id);


}

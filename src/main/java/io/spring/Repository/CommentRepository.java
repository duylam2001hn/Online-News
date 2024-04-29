package io.spring.Repository;

import io.spring.Entity.Category;
import io.spring.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("select c from Comment c where c.news.id =?1")
    public List<Comment> commentPerNews(Long idNews);
    
}

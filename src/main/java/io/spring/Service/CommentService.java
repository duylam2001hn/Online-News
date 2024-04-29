package io.spring.Service;

import io.spring.Entity.Comment;
import io.spring.Entity.News;
import io.spring.Repository.CommentRepository;
import io.spring.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> fetchAll(){
        return commentRepository.findAll();
    }

    public Comment findById(long id){
        return commentRepository.findById(id).get();
    }

    public void save(Comment comment){
        commentRepository.save(comment);
    }

    public void delete(long id){
        commentRepository.deleteById(id);
    }


    public List<Comment> commentsPerNews(Long idNews) {
        return commentRepository.commentPerNews(idNews);
    }
}

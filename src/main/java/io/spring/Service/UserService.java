package io.spring.Service;

import io.spring.Entity.News;
import io.spring.Entity.User;
import io.spring.Repository.NewsRepository;
import io.spring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> LoginAdmin(String email, String password){
        return userRepository.LoginAdmin(email,password);
    }

    public List<User> fetchAll(){
        return userRepository.findAll();
    }

    public User findById(long id){
        return userRepository.findById(id).get();
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void delete(long id){
        userRepository.deleteById(id);
    }


}

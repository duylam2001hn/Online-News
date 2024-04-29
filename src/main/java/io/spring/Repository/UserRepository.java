package io.spring.Repository;


import io.spring.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where (u.email =?1 and u.password =?2) and u.status=0")
    List<User> LoginAdmin (String email, String password);

    
}

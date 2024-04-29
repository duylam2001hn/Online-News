package io.spring.Repository;

import io.spring.Entity.Category;
import io.spring.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findCustomerByEmailAndPassword(String Email, String Password);
}

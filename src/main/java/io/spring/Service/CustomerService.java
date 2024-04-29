package io.spring.Service;

import io.spring.Entity.Customer;
import io.spring.Entity.News;
import io.spring.Repository.CustomerRepository;
import io.spring.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> fetchAll(){
        return customerRepository.findAll();
    }

    public Customer findByLogin(String email, String password){return customerRepository.findCustomerByEmailAndPassword(email,password);}
    public Customer findById(long id){
        return customerRepository.findById(id).get();
    }

    public void save(Customer customer){
        customerRepository.save(customer);
    }

    public void delete(long id){
        customerRepository.deleteById(id);
    }


}

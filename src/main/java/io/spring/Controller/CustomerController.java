package io.spring.Controller;

import io.spring.Entity.Customer;
import io.spring.Repository.CustomerRepository;
import io.spring.Service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Table;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/customer")
public class CustomerController {

    List<Customer> customerList = new ArrayList<>();
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/list")
    public String getList(Model model, HttpSession httpSession){

        customerList = customerService.fetchAll();
        model.addAttribute("customerList",customerList);

        checkSession(httpSession,model);
        return "main/admin/customer/list";
    }

    public int checkSession(HttpSession httpSession, Model mav){
        if(httpSession.getAttribute("UserLogin") != null){

            mav.addAttribute("userLog",httpSession.getAttribute("UserLogin"));
            return 1;
        }

        return 0;
    }
}

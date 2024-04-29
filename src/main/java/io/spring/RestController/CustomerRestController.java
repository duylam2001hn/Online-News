package io.spring.RestController;

import io.spring.Entity.Customer;
import io.spring.Entity.JsonResponse;
import io.spring.Service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
public class CustomerRestController {


    private CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {

        this.customerService = customerService;
    }

//    @GetMapping("/report/check")
//    public JsonResponse checkReport(@RequestParam String listIdCheck){
//        JsonResponse js = new JsonResponse();
//
//        String [] listId = listIdCheck.split(",");
//
//        for (String item: listId) {
//            Report report = reportService.findById(Long.valueOf(item));
//            report.setStatus(1);
//            reportService.save(report);
//        }
//
//        js.setStatus("Check");
//
//        return js;
//    }
//
//    @GetMapping(value = "/report/list")
//    public List<Report> getListReport(){
//        return reportService.fetchAll();
//    }


    @GetMapping("/customer/lock")
    public JsonResponse lockAccount(@RequestParam String listIdCheck){
        JsonResponse js = new JsonResponse();

        String [] listId = listIdCheck.split(",");

        for (String item: listId) {
            Customer customer = customerService.findById(Long.valueOf(item));
            customer.setStatus(1);
            customerService.save(customer);
        }

        js.setStatus("lock");

        return js;
    }

    @GetMapping(value = "/customer/list")
    public List<Customer> getListCustomer(){
        return customerService.fetchAll();
    }


}

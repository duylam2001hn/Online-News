package io.spring.RestController;

import io.spring.Entity.Comment;
import io.spring.Entity.Customer;
import io.spring.Entity.JsonResponse;
import io.spring.Entity.Report;
import io.spring.Repository.CommentRepository;
import io.spring.Repository.ReportRepository;
import io.spring.Service.CommentService;
import io.spring.Service.CustomerService;
import io.spring.Service.ReportService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/home")
public class HomeRestController {

    private CommentService commentService;
    private ReportService reportService;

    private CustomerService customerService;

    public HomeRestController(CommentService commentService, ReportService reportService, CustomerService customerService) {
        this.commentService = commentService;
        this.reportService = reportService;
        this.customerService = customerService;
    }

    //    @RequestMapping(value = "/customer/register")
//    public JsonResponse Register(@Valid @RequestBody Customer customer, BindingResult br){
//        JsonResponse js = new JsonResponse();
//            if(br.hasErrors()){
//                js.setStatus("Error");
//                js.setResult(br.getAllErrors());
//            }
//
//            js.setResult("success");
//
//            return js;
//    }

    @PostMapping(value = "/comment/")
    public JsonResponse SendComment(@Valid @RequestBody Comment comment, BindingResult rs){
        JsonResponse js = new JsonResponse();

        commentService.save(comment);
        js.setStatus("success");


        return js;
    }

    @PostMapping(value = "/report/")
    public JsonResponse SendComment(@Valid @RequestBody Report report, BindingResult rs){
        JsonResponse js = new JsonResponse();

        reportService.save(report);
        js.setStatus("success");


        return js;
    }

    @GetMapping(value = "/comment/list/{idNews}")
    public List<Comment> getListComment(@PathVariable Long idNews){
        List<Comment> commentList = commentService.commentsPerNews(idNews);
        return commentList;
    }


    @PostMapping(value = "/customer/login")
    public JsonResponse Login(@Valid @RequestBody Customer customer, BindingResult rs,HttpSession httpSession){
        JsonResponse js = new JsonResponse();


        if( rs.hasFieldErrors("email") && rs.hasFieldErrors("password")){
            js.setResult(rs.getAllErrors());
            js.setStatus("Error");
            System.out.println(js.getResult());
            System.out.println(js.getStatus());
            return js ;
        }

        String passwordHash = PasswordHash(customer.getPassword());

        List<Customer> customerList = customerService.fetchAll();

        List<Customer> checkCustomer = new ArrayList<>();
        for (Customer item: customerList) {
            if(item.getEmail().equals(customer.getEmail()) && item.getPassword().equals(passwordHash)){
                checkCustomer.add(item);

            }
        }

        System.out.println(checkCustomer.size());
        if(checkCustomer.size()>0){
//            httpSession.setAttribute("CustomerLogin",checkCustomer.get(0).getEmail());
            js.setStatus("Success");
        }else {
            js.setStatus("Not Exist");

        }




        return js;

    }

    public String PasswordHash(String password){

        String salt ="jgkas;@abc%bcd";
        String result = null;
        password = password+salt;
        try {
            byte[] dataBytes = password.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void checkSession(HttpSession httpSession, Model model){
        if(httpSession.getAttribute("CustomerLogin") != null){
            model.addAttribute("customerLog",httpSession.getAttribute("CustomerLogin"));
        }
    }
}

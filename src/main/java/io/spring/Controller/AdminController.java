package io.spring.Controller;

import io.spring.Entity.News;
import io.spring.Entity.User;
import io.spring.Entity.UserCount;
import io.spring.Repository.NewsRepository;
import io.spring.Repository.UserRepository;
import io.spring.Service.NewsService;
import io.spring.Service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

@Controller
@RequestMapping("/Admin")
public class AdminController {

    private NewsService newsService;
    private UserService userService;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public AdminController(NewsService newsService, UserService userService,
                           NewsRepository newsRepository,
                           UserRepository userRepository) {
        this.newsService = newsService;
        this.userService = userService;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/home")
        public String HomeAdmin(HttpSession httpSession,Model model){

        if(checkSession(httpSession,model)==0){
            return "redirect:/Admin/login";
        }


            List<News> newsListAll = newsService.ListNewsMoreView();


            List<News> ListFiveViews = new ArrayList<>();
        for (News news:newsListAll) {
            if(ListFiveViews.size()<4){
                ListFiveViews.add(news);
            }
        }

            List<User> userList = userService.fetchAll();

        Map<String,Integer> map = new HashMap<>();
        for (User user: userList) {

           map.put(user.getEmail(),newsService.CountByUser(user.getId()));
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());

        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<UserCount> ChartBarData = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : entries) {
            UserCount userCount = new UserCount();
            if(ChartBarData.size()<4){
                userCount.setEmail(entry.getKey());
                userCount.setTotalNews(entry.getValue());
                ChartBarData.add(userCount);
            }
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println(ChartBarData.size());

            model.addAttribute("dataPie",ListFiveViews);

        model.addAttribute("dataBar",ChartBarData);

        System.out.println(httpSession.getAttribute("UserLogin"));
        User user = userService.findById((Long) httpSession.getAttribute("UserId"));
        System.out.println(user.getRole());

            return "main/admin/home";
        }
    @RequestMapping("/login")
    public String LoginForm(Model model ,HttpSession httpSession){

        if(checkSession(httpSession,model)==1){
            return "redirect:/Admin/home";
        }

        User user = new User();


        model.addAttribute("user",user);
        return "main/admin/User/Login";
    }


    @PostMapping(value = "/login")
    public String Login(@Valid @ModelAttribute("user") User user, BindingResult br, Model model, HttpSession httpSession){



        if(br.hasErrors()){

            return "main/admin/User/Login";
        }




        String passwordHash = PasswordHash(user.getPassword());



        List<User> userList = userService.LoginAdmin(user.getEmail(), passwordHash);


        if(userList.size()<1){
            model.addAttribute("ErrorExist","Tài khoản hoặc mật khẩu không đúng");
            return "main/admin/User/Login";
        }


        for (User user1:userList) {
            httpSession.setAttribute("UserId",user1.getId());
            httpSession.setAttribute("UserLogin",user1.getEmail());
            httpSession.setAttribute("UserRole",user1.getRole());

            System.out.println(user1.getRole());
        }



        return "redirect:/Admin/home";
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

    @GetMapping(value = "/list")
    public String ListAdmin(Model model,HttpSession httpSession){

        if(checkSession(httpSession,model)==0){
            return "redirect:/Admin/login";
        }

        List<User> userList = userService.fetchAll();

        model.addAttribute("listAdmin",userList);



        return "main/admin/User/list";
    }

    @GetMapping(value = "/add")
    public String AddAdmin(Model model,HttpSession httpSession){

        if(checkSession(httpSession,model)==0){
            return "redirect:/Admin/login";
        }


        User user = new User();

        model.addAttribute("user",user);

        return "main/admin/User/add";
    }

    @GetMapping(value = "/get/{id}")
    public String getUser(Model model, HttpSession httpSession, @PathVariable Long id){

        if(checkSession(httpSession,model)==0){
            return "redirect:/Admin/login";
        }

        User user = userService.findById(id);

        model.addAttribute("user",user);
        return "main/admin/User/edit";
    }

    @PostMapping(value = "/save")
    public String SaveAdmin(Model model, @Valid @ModelAttribute User user, BindingResult br, HttpSession httpSession, @RequestParam String form){
        if(br.hasErrors()){

            model.addAttribute("userLog",httpSession.getAttribute("UserLogin"));

            if(form.equals("edit")){
                return "main/admin/User/edit";
            }else {
                return "main/admin/User/add";
            }

        }

        String passwordHash = PasswordHash(user.getPassword());

        user.setPassword(passwordHash);

        userService.save(user);

        return "redirect:/Admin/list";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("UserLogin");
        httpSession.removeAttribute("UserId");
        httpSession.removeAttribute("UserRole");
        return "redirect:/Admin/login";
    }


    public int checkSession(HttpSession httpSession, Model mav){
        if(httpSession.getAttribute("UserLogin") != null){

            mav.addAttribute("userLog",httpSession.getAttribute("UserLogin"));
            return 1;
        }

        return 0;
    }

}

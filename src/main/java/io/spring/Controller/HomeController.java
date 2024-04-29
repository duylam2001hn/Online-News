package io.spring.Controller;

import io.spring.Entity.*;
import io.spring.Repository.NewsRepository;
import io.spring.Service.CategoryService;
import io.spring.Service.CommentService;
import io.spring.Service.CustomerService;
import io.spring.Service.NewsService;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

@Controller
//@SessionAttributes("customerLogin")
@RequestMapping("/News01")
public class HomeController {


    private Hashtable<String,Integer> countPage = new Hashtable<>();
    private List<News> newsList = new ArrayList<>();
    private List<Category> categoryChildList = new ArrayList<>();

    private List<Category> categoryParentList = new ArrayList<>();

    private CategoryService categoryService;
    private NewsService newsService;

    private CustomerService customerService;

    private CommentService commentService;
    private final NewsRepository newsRepository;


    public HomeController(CategoryService categoryService, NewsService newsService, CustomerService customerService,CommentService commentService,
                          NewsRepository newsRepository) {
        this.categoryService = categoryService;
        this.newsService = newsService;
        this.customerService = customerService;
        this.commentService = commentService;
        this.newsRepository = newsRepository;
    }

    @GetMapping(value = "/home")
    public ModelAndView Home(HttpSession session){

        ModelAndView mav = new ModelAndView("main/web/home");
        newsList = newsService.ListNewsNewest();

        checkSession(session,mav);


        System.out.println(session.getAttribute("CustomerLogin"));

        List<Category> categoryListParent = categoryService.fetchCateChild(0);

        List<News> newsListFlag0 = newsService.fetchLimitForHome(10);

        List<News> dataListNew = new ArrayList<>();
        for (Category category:categoryListParent) {
            List<News> newsListLimit = newsService.fetchLimit(category.getId(),3);
            for (News news: newsListLimit) {
                dataListNew.add(news);
            }
        }



        System.out.println(newsListFlag0.size());

        System.out.println(dataListNew.size());


        List<News> newsList5 = newsService.ListNewsForHome(5);
        List<News> newsList7 = newsService.ListNewsForHome(7);
        List<News> newsList3 = newsService.ListNewsForHome(3);

        List<News> bookList = new ArrayList<>();


        for (News news:newsList) {
            if(news.getCategory().getParent_id()==2){
                if(bookList.size()<8){
                    bookList.add(news);
                }

            }
        }

        addCategory(mav);

        System.out.println(bookList.size());



        mav.addObject("news",dataListNew);
        mav.addObject("newsList5",newsList5);
        mav.addObject("newsList7",newsList7);
        mav.addObject("newsList3",newsList3);
        mav.addObject("bookList",bookList);
        mav.addObject("newsListFlag0",newsListFlag0);
        return mav;
    }

// Page CateMaster and CateChild
    @GetMapping(value = "/master/{link_name}")
    public ModelAndView homeTheThao(@PathVariable String link_name,HttpSession httpSession){

        ModelAndView mav = new ModelAndView("main/web/NewsCategoryMaster/MasterPage");

        categoryParentList = categoryService.fetchCateChild(0);

        Category categoryMaster = new Category();

        for (Category category:categoryParentList) {

            if(category.getLink_name().equals(link_name)){
                categoryChildList = categoryService.fetchCateChild(Math.toIntExact(category.getId()));
                GetPerPageMaster(mav, Math.toIntExact(category.getId()));
                categoryMaster = category;
            }

        }





//        ModelAndView mav = new ModelAndView("main/web/NewsCategoryMaster/TheThao");
//        categoryChildList = categoryService.fetchCateChild(1);
        addCategory(mav);



        mav.addObject("CategoryMaster",categoryMaster);
        mav.addObject("cateChild",categoryChildList);
        return mav;
    }

    @GetMapping(value = "/{link_cate}")
    public ModelAndView getPageCateChild(@PathVariable String link_cate, HttpSession httpSession){


        ModelAndView mav = new ModelAndView("main/web/News/NewsCategoryChild");



        addCategory(mav);
        newsList = newsService.ListNewsByLinkName(link_cate);

        List<Category> categoryList = categoryService.fetchAll();

        Category categoryChild = new Category();

        for (Category category:categoryList) {
            if(category.getLink_name().equals(link_cate)){
                GetPerPageChild(mav, category.getId());
                categoryChild = category;
            }
        }



        mav.addObject("CategoryMaster",categoryChild);

        return mav;
    }




    //Common
    public void addCategory(ModelAndView mav){
        List<Category> categoryList = categoryService.fetchCateChild(0);
        mav.addObject("category",categoryList);
    }


    public void GetPerPageMaster(ModelAndView mav, int cate_parent){

        List<News> newsFeatureListFour = newsService.ListNewsFeatureCateParent(cate_parent,4);

        List<News> newsFeatureListTwo = newsService.ListNewsFeatureCateParent(cate_parent,2);
        List<News> newsFeatureBig = newsService.ListNewsFeatureCateParent(cate_parent,1);


        List<News> NewsListNewest = newsService.ListNewsCateParent(cate_parent);

        List<News> NewsListMoreView = newsService.ListNewsMoreViewCateParent(cate_parent);


        List<News> NewsListLimit = new ArrayList<>();
        for (News news:NewsListMoreView) {
            if(NewsListLimit.size()<4){
                NewsListLimit.add(news);
            }
        }


        mav.addObject("news",NewsListNewest);
        mav.addObject("newsRight",NewsListLimit);

        mav.addObject("newsFeatureListFour",newsFeatureListFour);
        mav.addObject("newsFeatureBig",newsFeatureBig);
        mav.addObject("newsFeatureListTwo",newsFeatureListTwo);

    }

    public void GetPerPageChild(ModelAndView mav, Long cateId){

        List<News> newsFeatureListFour = newsService.ListNewsFeatureCateChild(cateId,12);

        List<News> newsFeatureListTwo = newsService.ListNewsFeatureCateChild(cateId,11);
        List<News> newsFeatureBig = newsService.ListNewsFeatureCateChild(cateId,10);

        List<News> NewsListExist = newsService.FetchNewsChild(cateId);


        System.out.println(NewsListExist.size());

        mav.addObject("news",NewsListExist);

        mav.addObject("newsFeatureListFour",newsFeatureListFour);
        mav.addObject("newsFeatureBig",newsFeatureBig);
        mav.addObject("newsFeatureListTwo",newsFeatureListTwo);

    }


//Per Page Detail
    @GetMapping(value = "/get/{id}")
    public ModelAndView getNews(@PathVariable String id, HttpSession session){
        ModelAndView mav = new ModelAndView("main/web/News/NewsDetail");

        News news = newsService.findById(Long.valueOf(id));
        int old_count = news.getView();
        int new_count = old_count+=1;


        news.setView(new_count);

        List<Comment> commentList = commentService.commentsPerNews(Long.valueOf(id));

        if (commentList.size()>0){

        }

        Report report = new Report();
        Comment comment = new Comment();

        checkSession(session,mav);
        addCategory(mav);

        mav.addObject("ListNews",newsService.ListNewsMoreView());
        mav.addObject("News",news);

        mav.addObject("report",report);
        mav.addObject("comment",comment);
        mav.addObject("CommentList",commentList);
        return mav;
    }



// Customer
    @GetMapping(value = "/customer/{form}")
    public ModelAndView LoginView(@PathVariable String form){


        ModelAndView mav = new ModelAndView("main/web/Customer/Login");
        Customer customer = new Customer();
        addCategory(mav);
        System.out.println(form);
        if(form.equals("register")){
            mav.addObject("checkForm",form);
        } else {
            mav.addObject("checkForm",form);
        }

        mav.addObject("customer",customer);
        mav.addObject("ListNews",newsService.ListNewsMoreView());
        return mav;
    }




    @PostMapping (value = "/customer/register")
    public ModelAndView Register(@Valid @ModelAttribute("customer") Customer customer, BindingResult br, String ConfirmPass){
        String check ="";
        String form= "register";
        ModelAndView mav = new ModelAndView("main/web/Customer/Login");
        addCategory(mav);
        mav.addObject("ListNews",newsService.ListNewsMoreView());


        //redirect mà muốn sendata dùng RedirectAttribute sau String ConfirmPass
        //            rattrs.addFlashAttribute("checkForm",form);
//            rattrs.addFlashAttribute("BindingResult",br.hasErrors());
        System.out.println(customer.getEmail());

        List<Customer> customerList = customerService.fetchAll();


        if(br.hasErrors()){

            mav.addObject("checkForm",form);

            return  mav;
        }


        if(customer.getPassword().equals(ConfirmPass)){

            for (Customer cus:customerList) {
                if(cus.getEmail().equals(customer.getEmail())){
                    check="Email này đã được đăng ký";
                    System.out.println(cus.getEmail());
                    System.out.println(cus.getEmail().equals(customer.getEmail()));

                    mav.addObject("checkUniqueEmail",check);

                }else{
                    mav.addObject("checkSuccess","Đăng ký thành công");
                    String passwordHash= PasswordHash(customer.getPassword());
                    customer.setPassword(passwordHash);
                    customerService.save(customer);
//
                }
                mav.addObject("checkForm",form);
                return mav;

            }
        }else {
            check = "Confirm Password không giống nhau";
            mav.addObject("checkUniqueEmail",check);
            mav.addObject("checkForm",form);

        }


        System.out.println(check);

        return mav;

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


    @PostMapping(value = "/customer/login")
    public String Login(@Valid @ModelAttribute("customer") Customer customer, BindingResult br, Model model, HttpSession httpSession, RedirectAttributes redirectAttributes){



        if(br.hasFieldErrors("email") && br.hasFieldErrors("password")){
            model.addAttribute("checkForm","login");
            List<Category> categoryList = categoryService.fetchCateChild(0);
            model.addAttribute("category",categoryList);
            model.addAttribute("customer",customer);
            return "main/web/Customer/Login";
        }

        String password_hash= PasswordHash(customer.getPassword());

        System.out.println(customer.getEmail());

        int check=0;
        List<Customer> customerList = customerService.fetchAll();

        List<Customer> checkCustomer = new ArrayList<>();
        for (Customer item: customerList) {
            if(item.getEmail().equals(customer.getEmail()) && item.getPassword().equals(password_hash)){
                checkCustomer.add(item);

            }
        }

        System.out.println(checkCustomer.size());
        if(checkCustomer.size()>0){
            for (Customer item: checkCustomer) {
                httpSession.setAttribute("CustomerLogin",item.getEmail());
                httpSession.setAttribute("CustomerId",item.getId());
            }

        }else {
            String checkLog = "Tài khoản hoặc mật khẩu không đúng";
            model.addAttribute("checkForm","login");
            List<Category> categoryList = categoryService.fetchCateChild(0);
            model.addAttribute("category",categoryList);
            model.addAttribute("checkLogin",checkLog);
            return "main/web/Customer/Login";
        }



        return "redirect:/News01/home";
    }

    @GetMapping(value = "/customer/logout")
    public String logout(HttpSession httpSession){
            httpSession.removeAttribute("CustomerLogin");
            return "redirect:/News01/home";
    }

    @GetMapping("/search")
    public ModelAndView SearchNews(Model model,HttpSession httpSession, @RequestParam String search){
        ModelAndView mav = new ModelAndView("/main/web/News/NewsSearch");

        newsList = newsService.SearhNews(search);
        addCategory(mav);
        mav.addObject("listNews",newsList);
        checkSession(httpSession,mav);
        return mav ;
    }


    public void checkSession(HttpSession httpSession, ModelAndView mav){
        if(httpSession.getAttribute("CustomerLogin") != null){
            mav.addObject("customerLog",httpSession.getAttribute("CustomerLogin"));
        }
    }



}

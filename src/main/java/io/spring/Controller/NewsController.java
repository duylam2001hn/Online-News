package io.spring.Controller;


import io.spring.Entity.Category;
import io.spring.Entity.News;
import io.spring.Entity.User;
import io.spring.Repository.UserRepository;
import io.spring.Service.CategoryService;
import io.spring.Service.NewsService;

import io.spring.Service.UserService;
import io.spring.Validate.ValidateFileType;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

@Controller
@RequestMapping("/admin/news")
public class NewsController {

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";
    NewsService newsService;
    CategoryService categoryService;

    UserService userService;


    List<News> newsList = new ArrayList<>();
    private final UserRepository userRepository;

    public NewsController(NewsService newsService, CategoryService categoryService, UserService userService,
                          UserRepository userRepository) {
        this.newsService = newsService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/add")
    public String addNews(Model model,HttpSession httpSession){

        List<Category> categoryParentList = categoryService.fetchCateChild(0);
        List<Category> categoryDefault = categoryService.fetchCateChild(1);
        News news = new News();
        model.addAttribute("categoryParentList",categoryParentList);
        model.addAttribute("categoryDefault",categoryDefault);
        model.addAttribute("news",news);

        if(checkSession(httpSession,model)==0){
            return "redirect:/Admin/login";
        };


        return "/main/admin/new/add";
    }


    @PostMapping(value = "/save")
    public String saveNews(@Valid @ModelAttribute("news") News news, BindingResult br, @RequestPart("photo") MultipartFile multipartFile, HttpSession httpSession, Model model, @RequestParam String form)  {



        Long timeNow = System.nanoTime();


        if(br.hasErrors()){
            addDataForError(model);
            model.addAttribute("news",news);
            model.addAttribute("userLog",httpSession.getAttribute("UserLogin"));
            System.out.println(form);
            if(form.equals("edit")){

                return "/main/admin/new/edit_new";
            }else {

                return "/main/admin/new/add";
            }

//            mav.setViewName("/main/admin/new/add");
//            addDataForError(mav);

        }

        if(!multipartFile.isEmpty()){
            String file_name = multipartFile.getOriginalFilename();

            int DotIndex = file_name.lastIndexOf(".");

            String File_extension = file_name.substring(DotIndex);
            String New_file_name = file_name.substring(0,DotIndex)+timeNow + File_extension;



            ValidateFileType validateFileType = new ValidateFileType();

            int Result = validateFileType.IsValidImage(New_file_name);

            if(Result == 1){
                Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY,New_file_name);

                try{
                    Files.write(fileNameAndPath, multipartFile.getBytes());
                }catch (IOException e){
                    System.out.println(e);
                    addDataForError(model);
                    model.addAttribute("news",news);
                    return "/main/admin/new/add";
                }

                String LastThumbnailURL = "/uploads/"+New_file_name;
                news.setThumbnail(LastThumbnailURL);


                System.out.println(httpSession.getAttribute("UserLogin"));
                    List<User> userList = userService.fetchAll();

                    for (User user:userList) {

                           if(user.getId().equals(httpSession.getAttribute("UserId"))){

                               news.setUser(user);
                           }


                    }


                    newsService.save(news);


            }

        }
        else{


            model.addAttribute("thumbnailError","Hình ảnh đại diện không được rỗng");
            addDataForError(model);
            model.addAttribute("news",news);
            model.addAttribute("userLog",httpSession.getAttribute("UserLogin"));
            if(form.equals("edit")){
                return "/main/admin/new/edit_new";
            }else {
                return "/main/admin/new/add";
            }
        }



        return "redirect:/admin/news/list" ;
    }

    @GetMapping(value = "/get/{form}/{id}")
    public String getNews(@PathVariable Long id,@PathVariable String form ,Model model, HttpSession httpSession){

        List<Category> categoryParentList = categoryService.fetchCateChild(0);
        List<Category> categoryDefault = categoryService.fetchCateChild(1);
        News news = newsService.findById(id);
        int Role = (int) httpSession.getAttribute("UserRole");
        System.out.println(Role);
        if( Role ==0){
            if(news.getUser().getId() != httpSession.getAttribute("UserId") ){
                if(form.equals("home")){
                    return "redirect:/admin/news/home";
                }else {
                    return "redirect:/admin/news/list";
                }

            }
        }

        model.addAttribute("categoryParentList",categoryParentList);
        model.addAttribute("categoryDefault",categoryDefault);
        model.addAttribute("news",news);

        if(checkSession(httpSession,model)==0){
            return "redirect:/Admin/login";
        }

        return "/main/admin/new/edit_new";
    }


    @GetMapping(value = "/list")
    public String ListNews(Model model,HttpSession httpSession){
        List<Category> categoryDefault = categoryService.fetchCateChild(1);
        model.addAttribute("categoryDefault",categoryDefault);

        if(checkSession(httpSession,model)==0){
            return "redirect:/Admin/login";
        }
        return listByPages(1,model,"CateMaster",httpSession);
    }



    @GetMapping("/page/{form}/{currentPage}")
    public String listByPages(@PathVariable Integer currentPage,Model model,@PathVariable String form,HttpSession httpSession){



        if(checkSession(httpSession,model)==0){
            return "redirect:/Admin/login";
        }


        Page<News> PageNews = newsService.pageNewFindHome(currentPage);
        if(form.equals("CateMaster")){
            PageNews = newsService.pageNewFindPerCateMaster(currentPage);
        }


        Long totalItem = PageNews.getTotalElements();
        int totalPage = PageNews.getTotalPages();
        List<Integer> pageTotal = new ArrayList<>();



        for (int i=1;i<=totalPage;i++) {
            pageTotal.add(i);
        }

        List<News> newsListPage = PageNews.getContent();


        List<Category> cateParent = categoryService.fetchCateChild(0);
        model.addAttribute("newsList",newsListPage);
        model.addAttribute("cateParent",cateParent);

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItem",totalItem);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("pageTotal",pageTotal);

        System.out.println(form);

        if(form.equals("CateMaster")){

            TotalPageCateParent(model,form);

            return "/main/admin/new/list";
        }  else {
            TotalPageCateParent(model,form);

        }



        return "/main/admin/new/listForHome";
    }

    @GetMapping(value = "/home")
    public String ListNewsForHome(Model model,HttpSession httpSession){

//            if((news.getFlag() ==0 || news.getFlag() ==3 || news.getFlag()==5 || news.getFlag() ==7) && news.getStatus()==0 ){

        if(checkSession(httpSession,model)==0){
            return "redirect:/Admin/login";
        }
        return listByPages(1,model,"home",httpSession) ;
    }









    public void addDataForError(Model model){
        List<Category> categoryParentList = categoryService.fetchCateChild(0);
        List<Category> categoryDefault = categoryService.fetchCateChild(1);

        model.addAttribute("categoryParentList",categoryParentList);
        model.addAttribute("categoryDefault",categoryDefault);
    }


    public void TotalPageCateParent(Model model,String form){
        List<Category> categoryListParent =categoryService.fetchCateChild(0);



        Dictionary<Integer,Integer> TotalPageCate = new Hashtable<>();

        for (Category category:categoryListParent ){
            Page<News> newsPageCate = newsService.pageNewCateParentPerCate(1, Math.toIntExact(category.getId()));
            if(form.equals("home")){
                newsPageCate = newsService.pageNewCateParentPerCateHome(1,Math.toIntExact(category.getId()));
            }

            int total = newsPageCate.getTotalPages();
            TotalPageCate.put(Math.toIntExact(category.getId()),total);
        }

        int totalPageSport = 0;
        int totalPageBooks = 0;
        int totalPageBuss = 0;
        int totalPageTech = 0;
        int totalPageEntertain = 0;
        int totalPageLife = 0;
        int totalPageSocial =0;
        int totalPageHealthy = 0;

         totalPageSport = TotalPageCate.get(1);
         totalPageBooks = TotalPageCate.get(2);
         totalPageBuss =  TotalPageCate.get(3);
         totalPageTech = TotalPageCate.get(4);
         totalPageEntertain = TotalPageCate.get(5);
         totalPageLife = TotalPageCate.get(6);
         totalPageSocial = TotalPageCate.get(7);
         totalPageHealthy = TotalPageCate.get(41);



        model.addAttribute("totalPageSport",totalPageSport);
        model.addAttribute("totalPageBooks",totalPageBooks);
        model.addAttribute("totalPageBuss",totalPageBuss);
        model.addAttribute("totalPageTech",totalPageTech);
        model.addAttribute("totalPageEntertain",totalPageEntertain);
        model.addAttribute("totalPageLife",totalPageLife);
        model.addAttribute("totalPageSocial",totalPageSocial);
        model.addAttribute("totalPageHealthy",totalPageHealthy);
    }


    public int checkSession(HttpSession httpSession, Model mav){
        if(httpSession.getAttribute("UserLogin") != null){

            mav.addAttribute("userLog",httpSession.getAttribute("UserLogin"));
            return 1;
        }

        return 0;
    }
}

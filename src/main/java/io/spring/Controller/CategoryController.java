package io.spring.Controller;

import io.spring.Entity.Category;
import io.spring.Entity.User;
import io.spring.Service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    private List<Category> categoryList = new ArrayList<>();
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/list")
    public String listCategory(HttpSession httpSession, Model model){

        if(checkSession(httpSession,model)==0){
            return "redirect:/Admin/login";
        }

        categoryList = categoryService.fetchAll();
        model.addAttribute("categoryList",categoryList);
        return "main/admin/category/list";
    }


    @GetMapping(value = "/add")
    public String add(HttpSession httpSession, Model model){

        if(checkSession(httpSession,model)==0){
            return "redirect:/Admin/login";
        }
        categoryList = categoryService.fetchAll();
        Category category = new Category();
        model.addAttribute("category",category);
        model.addAttribute("categoryList",categoryList);
        return "main/admin/category/add";
    }

    @GetMapping(value = "/get/{id}")
    public String getCate(@PathVariable String id, HttpSession httpSession, Model model){

        if(checkSession(httpSession,model)==0){
            return "redirect:/Admin/login";
        }

        categoryList = categoryService.fetchAll();
        Category category = categoryService.findById(Long.valueOf(id));
        model.addAttribute("category",category);
        model.addAttribute("categoryList",categoryList);

        return "main/admin/category/edit";
    }


    @PostMapping (value = "/save")
    public String save(@Valid @ModelAttribute("category") Category category, BindingResult br, String form, Model model){
        System.out.println(form);
        categoryList = categoryService.fetchAll();

        model.addAttribute("categoryList",categoryList);
        if (br.hasErrors()){
            if(form.equals("add")){
                return "main/admin/category/add";
            }
            else{
                return "main/admin/category/edit";
            }


        }

            categoryService.save(category);








        return "redirect:/admin/category/list";
    }

    public int checkSession(HttpSession httpSession, Model mav){
        if(httpSession.getAttribute("UserLogin") != null){

            mav.addAttribute("userLog",httpSession.getAttribute("UserLogin"));
            return 1;
        }

        return 0;
    }
}

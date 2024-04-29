package io.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/news/")
public class CkfinderController {

        @GetMapping("/ImagesSave")
        public ModelAndView ServerImages(
                @RequestParam(value = "CKEditor") String CKEditor,
                @RequestParam(value = "CKEditorFuncNum") String CKEditorFuncNum,
                @RequestParam(value = "langCode") String langCode){
            //?CKEditor=content&CKEditorFuncNum=0&langCode=en
            System.out.println("1: "+CKEditor);
            System.out.println("2: "+CKEditorFuncNum);
            System.out.println("3: "+langCode);
            ModelAndView mav = new ModelAndView("main/ckfinder/ckfinder");
            return mav;
        }
}

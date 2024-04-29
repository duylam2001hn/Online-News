package io.spring.RestController;

import com.google.gson.Gson;
import io.spring.Entity.Category;
import io.spring.Entity.JsonResponse;
import io.spring.Entity.News;
import io.spring.Repository.NewsRepository;
import io.spring.Service.CategoryService;
import io.spring.Service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/news")
public class NewsRestController {
    JsonResponse js = new JsonResponse();
    private NewsService newsService;

    private CategoryService categoryService;

    private List<News> newsList = new ArrayList<>();
    private final NewsRepository newsRepository;

    public NewsRestController(NewsService newsService, CategoryService categoryService,
                              NewsRepository newsRepository) {
        this.newsService = newsService;
        this.categoryService = categoryService;
        this.newsRepository = newsRepository;
    }


    @GetMapping(value = "/category/{id}")
    public JsonResponse CategoryChild(@PathVariable String id) {

        Gson gson = new Gson();

        JsonResponse js = new JsonResponse();

        List<Category> categoryChildList = categoryService.fetchCateChild(Integer.valueOf(id));

        String html = "";

        for (Category item:categoryChildList) {
            html+="<option value="+item.getId()+">"+item.getName()+"</option>";

        }


        js.setStatus("Success");
        js.setResult(html);

        return js;
    }



    @GetMapping(value = "/{cate}/featureBig/{id}")
    public JsonResponse featureBig(@PathVariable String id ,@PathVariable String cate){

        JsonResponse js = new JsonResponse();


        long idNews = Long.valueOf(id);
        newsList = newsService.fetchAll();
        for ( News item : newsList) {
            if(item.getStatus()==0){
                if(item.getId().equals(idNews)){
                    js.setStatus("Exist");
                    News perNew = newsService.findById(idNews);
                    if(cate.equals("child")){
                        perNew.setFlag(10);
                    }else {
                        perNew.setFlag(1);
                    }

                    newsService.save(perNew);
                }
            }
        }


        return js;
    }

    @GetMapping(value = "/{cate}/featureSmall/")
    public JsonResponse featureSmall(@RequestParam String listNews,@PathVariable String cate){

        JsonResponse js = new JsonResponse();
        System.out.println(listNews);
        String [] listId = listNews.split(",");

       
        newsList = newsService.fetchAll();

        List<Long> idListNewsExist = new ArrayList<>();


        for ( String i: listId) {

            for (News item : newsList) {
                if(item.getStatus() ==0){
                    if(Long.valueOf(i) == item.getId()){
                        News perNew = newsService.findById(item.getId());
                        if(cate.equals("child")){
                            perNew.setFlag(11);
                        }else {
                            perNew.setFlag(2);
                        }
                        newsService.save(perNew);
                        js.setStatus("Exist2");
                        //                    newsService.save(perNew);
                    }
                }else{
                    js.setStatus("NotExist");
                    js.setResult("Có bản tin đã bị xóa.");
                }


            }
        }


        return js;
    }

    @GetMapping(value = "/{cate}/featureFour/")
    public JsonResponse featureFour(@RequestParam String listNews, @PathVariable String cate){


        System.out.println(listNews);
        String [] listId = listNews.split(",");


        newsList = newsService.fetchAll();

        List<Long> idListNewsExist = new ArrayList<>();


        for (String id :listId) {
            for (News item : newsList) {
                if (item.getStatus() == 0) {
                    if (Long.valueOf(id) == item.getId()) {
                        js.setStatus("Exist4");
                        News perNew = newsService.findById(item.getId());
                        if(cate.equals("child")){
                            perNew.setFlag(12);
                        }else {
                            perNew.setFlag(4);
                        }

                        newsService.save(perNew);
                    }
                } else {
                    js.setStatus("NotExist");
                    js.setResult("Có bản tin không tồn tại");
                }
            }

        }

        return js;
    }



//    @GetMapping(value = "/loadList/")
//    public JsonResponse getNewsList(){
//        JsonResponse js = new JsonResponse();
//        String html ="";
//
//
//        return js ;
//    }


    @GetMapping(value = "/list/")
    public List<News> getList(){
        List<News> newsListAll = newsService.fetchAll();
        List<News> newsListData = new ArrayList<>();
        for (News item:newsListAll) {
            if(item.getStatus()==0){
                newsListData.add(item);
            }
        }

        return newsListData;
    }




    @GetMapping(value = "/list/cate/{id}")
    public JsonResponse getListByCate(@PathVariable Integer id){
//        List<News> dataList = new ArrayList<>();
        JsonResponse jsonResponse = new JsonResponse();


        Page<News> newsPage = newsService.pageNewCateParentPerCate(1,id);

        int totalPage = newsPage.getTotalPages();
        Long totalItem = newsPage.getTotalElements();

        List<Integer> ListPage = new ArrayList<>();


        System.out.println(totalPage);
        System.out.println(totalItem);

        for(int i=1;i<=totalPage;i++){
            ListPage.add(i);
        }

        newsList = newsPage.getContent();

//        for (News news: newsList) {
//
//            if(news.getFlag() ==0 || news.getFlag() ==1 || news.getFlag()==2 || news.getFlag() ==4 ){
//                dataList.add(news);
//            }
//        }
        jsonResponse.setResult(newsList);
        jsonResponse.setResult1(totalPage);
        jsonResponse.setResult2(totalItem);
        jsonResponse.setResult3(ListPage);

        return jsonResponse;
    }





    @GetMapping("/page/{form}")
    public List<News> listByPagesPerCate(@RequestParam String idCatePage,@PathVariable String form){

        System.out.println(idCatePage);

        String [] listId = idCatePage.split(",");

        List<Integer> idListCateAndPage = new ArrayList<>();
        for (String id:listId) {
           idListCateAndPage.add(Integer.valueOf(id));
        }

        System.out.println(idListCateAndPage.size());

        Page<News> PageNews = newsService.pageNewCateParentPerCate(idListCateAndPage.get(1), idListCateAndPage.get(0)); ;

        if(form.equals("home")){
            PageNews = newsService.pageNewCateParentPerCateHome(idListCateAndPage.get(1), idListCateAndPage.get(0));
        }




        newsList = PageNews.getContent();

        System.out.println(newsList.size());
        return newsList;
    }


    @PostMapping(value = "/search/{form}")
    public List<News> searchNew(@RequestParam String input,@PathVariable String form) {



        System.out.println(form);
        System.out.println(input);

        String search = input.trim().replaceAll("\\s\\s+", " ");

        System.out.println(search);
//        newsList = newsService.fetchAll();
//        String query = input.toLowerCase();
//        List<News> resultsList = new ArrayList<>();
//        for(News n: newsList){
//            if( n.getContent().toLowerCase().contains(query) ||
//                    n.getTitle().toLowerCase().contains(query) ||
//                    n.getShortDescription().toLowerCase().contains(query)
//            ){
//                resultsList.add(n);
//            }
//        }
        newsList = newsService.SearhNews(search);

        List<News> dataList = new ArrayList<>();
        for (News news :newsList) {
            if(form.equals("forHome")){
                if(news.getFlag()==0 || news.getFlag()==3 || news.getFlag()==5 || news.getFlag()==7 ){
                    dataList.add(news);
                }

            }else {
                if(news.getFlag()==0 || news.getFlag()==1 || news.getFlag()==2 || news.getFlag()==4 || news.getFlag() ==10 || news.getFlag() ==11 || news.getFlag() ==12 ){
                    dataList.add(news);
                }
            }
        }


        System.out.println(dataList.size());
        return dataList;
    }

    @GetMapping("/setNull")
    public JsonResponse setNull(@RequestParam String listIdCheck){


        String [] listId = listIdCheck.split(",");

        for (String item: listId) {
            News news = newsService.findById(Long.valueOf(item));
            news.setFlag(0);
            newsService.save(news);
        }

        js.setStatus("Check");

        return js;
    }

    @GetMapping("/feature3Home/")
    public JsonResponse set3FeatureHome(@RequestParam String listNews){


        String [] listId = listNews.split(",");

        newsList = newsService.fetchAll();

        for (String id :listId) {
            for (News item : newsList) {
                if (item.getStatus() == 0) {
                    if (Long.valueOf(id) == item.getId()) {
                        js.setStatus("Exist3");
                        News perNew = newsService.findById(item.getId());
                        perNew.setFlag(6);
                        newsService.save(perNew);
                    }
                } else {
                    js.setStatus("NotExist");
                    js.setResult("Có bản tin không tồn tại");
                }
            }

        }



        return js;
    }



    @GetMapping("/delete")
    public JsonResponse checkReport(@RequestParam String listIdCheck){


        String [] listId = listIdCheck.split(",");

        for (String item: listId) {
            News news = newsService.findById(Long.valueOf(item));
            news.setStatus(1);
            newsService.save(news);
        }

        js.setStatus("Check");

        return js;
    }


    @GetMapping(value = "/list/{id}")
    public List<News> getListData(@PathVariable Integer id, @RequestParam String form, @RequestParam Integer currentPage){


        Page<News> newsPage = newsService.pageNewCateParentPerCate(currentPage,id);

        if(form.equals("home")){
            newsPage = newsService.pageNewCateParentPerCateHome(currentPage,id);
        }

        newsList = newsPage.getContent();



        return newsList;
    }

    @GetMapping(value = "/list/cate/forHome/{id}")
    public JsonResponse getListByCateForHome(@PathVariable Integer id){

        JsonResponse jsonResponse = new JsonResponse();


        Page<News> newsPage = newsService.pageNewCateParentPerCateHome(1,id);

        int totalPage = newsPage.getTotalPages();
        Long totalItem = newsPage.getTotalElements();

        List<Integer> ListPage = new ArrayList<>();


        System.out.println(totalPage);
        System.out.println(totalItem);

        for(int i=1;i<=totalPage;i++){
            ListPage.add(i);
        }

        newsList = newsPage.getContent();

        jsonResponse.setResult(newsList);
        jsonResponse.setResult1(totalPage);
        jsonResponse.setResult2(totalItem);
        jsonResponse.setResult3(ListPage);

        return jsonResponse;


    }

    @GetMapping(value = "/featureBigForHome/{id}")
    public JsonResponse featureBigForHome(@PathVariable String id){

        JsonResponse js = new JsonResponse();


        long idNews = Long.valueOf(id);
        newsList = newsService.fetchAll();
        for ( News item : newsList) {
            if(item.getStatus()==0){
                if(item.getId().equals(idNews)){
                    js.setStatus("Exist");
                    News perNew = newsService.findById(idNews);
                    perNew.setFlag(7);
                    newsService.save(perNew);
                }
            }
        }


        return js;
    }

    @GetMapping(value = "/featureSmallForHome/")
    public JsonResponse featureSmallForHome(@RequestParam String listNews){

        JsonResponse js = new JsonResponse();
        System.out.println(listNews);
        String [] listId = listNews.split(",");


        newsList = newsService.fetchAll();

        List<Long> idListNewsExist = new ArrayList<>();


        for ( String i: listId) {

            for (News item : newsList) {
                if(item.getStatus() ==0){
                    if(Long.valueOf(i) == item.getId()){
                        News perNew = newsService.findById(item.getId());
                        perNew.setFlag(3);
                        newsService.save(perNew);
                        js.setStatus("Exist2");
                        //                    newsService.save(perNew);
                    }
                }else{
                    js.setStatus("NotExist");
                    js.setResult("Có Bản tin không tồn tại");
                }


            }
        }


        return js;
    }


    @GetMapping(value = "/featureFive/")
    public JsonResponse featureFive(@RequestParam String listNews){


        System.out.println(listNews);
        String [] listId = listNews.split(",");


        newsList = newsService.fetchAll();

        List<Long> idListNewsExist = new ArrayList<>();


        for (String id :listId) {
            for (News item : newsList) {
                if (item.getStatus() == 0) {
                    if (Long.valueOf(id) == item.getId()) {
                        js.setStatus("Exist4");
                        News perNew = newsService.findById(item.getId());
                        perNew.setFlag(5);
                        newsService.save(perNew);
                    }
                } else {
                    js.setStatus("NotExist");
                    js.setResult("Có bản tin không tồn tại");
                }
            }

        }

        return js;
    }
}

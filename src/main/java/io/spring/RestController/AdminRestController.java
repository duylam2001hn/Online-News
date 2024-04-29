package io.spring.RestController;

import io.spring.Entity.*;
import io.spring.Service.NewsService;
import io.spring.Service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private NewsService newsService;
    private UserService userService;

    public AdminRestController(NewsService newsService, UserService userService) {
        this.newsService = newsService;
        this.userService = userService;
    }


    @GetMapping("/lock")
    public JsonResponse lockAccount(@RequestParam String listIdCheck){
        JsonResponse js = new JsonResponse();

        String [] listId = listIdCheck.split(",");

        for (String item: listId) {
            User user = userService.findById(Long.valueOf(item));


            user.setStatus(1);

            if(user.getRole()!=1){
                userService.save(user);
            }

        }

        js.setStatus("lock");

        return js;
    }






    @PostMapping(value = "/DataChart/time")
    public JsonResponse sendData( @RequestBody TimeLimit timelimit) throws ParseException {

        JsonResponse js = new JsonResponse();
        List<News> newsList = newsService.listNewsExist();

        System.out.println("Thời gian bắt đầu: "+timelimit.getStart_time());
        System.out.println("Thời gian kết thúc: "+timelimit.getEnd_time());

        Date StartDate=new SimpleDateFormat("yyyy-MM-dd").parse(timelimit.getStart_time());
        Date EndDate=new SimpleDateFormat("yyyy-MM-dd").parse(timelimit.getEnd_time());




        List<User> userList = userService.fetchAll();

        Map<String,Integer> map = new HashMap<>();

        String hello = null;





        for (User user: userList) {

            Integer count=0;

            for (News item : newsList) {


                Date InsertDate = new SimpleDateFormat("yyyy-MM-dd").parse(item.getInsertTime());


                if (StartDate.compareTo(InsertDate) <= 0 && EndDate.compareTo(InsertDate) >= 0) {
                    hello = "Hello";




                        if (item.getUser().getId() == user.getId()) {

                            count += 1;
                            //                        map.put(user.getEmail(),newsService.CountByUser(item.getUser().getId()));
                        }


                    map.put(user.getEmail(),count);

                    //                map.put(item.getUser().getEmail(),countNews);
                    //                newsService.CountByUser(item.getUser().getId());
                    js.setStatus("ok");
                }
            }

//            for(User user:userList){
//                for (News news:newsList) {
//                    if(news.getUser().getId() == user.getId()){
//                        map.put(user.getEmail(),count);
//                    }
//                }
//                count=0;
//
//            }
        }


        System.out.println("admin1@gmail.com: "+map.get("admin1@gmail.com"));

        System.out.println("Count : "+map.size());

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

        }


        System.out.println("UserList Size "+userList.size());
        System.out.println(ChartBarData.size());
        js.setResult(ChartBarData);


        return js;
    }
}

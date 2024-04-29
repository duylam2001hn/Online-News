package io.spring.Service;

import io.spring.Entity.News;
import io.spring.Repository.NewsRepository;
import io.spring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class NewsService{

    @Autowired
    NewsRepository newsRepository;
    @Autowired
    private UserRepository userRepository;

    public Page<News> pageNewFindPerCateMaster(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1,5);
        return newsRepository.findAllNewsInPerCategoryMaster(pageable);
    }



    public Page<News> pageNewFindHome(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1,5);
        return newsRepository.findAllNewsInHome(pageable);
    }


    public Page<News> pageNewCateParentPerCate(int pageNumber, int cateParent){
        Pageable pageable = PageRequest.of(pageNumber-1,5);
        return newsRepository.NewsListCateParentPagePerCate(cateParent ,pageable);
    }

    public Page<News> pageNewCateParentPerCateHome(int pageNumber, int cateParent){
        Pageable pageable = PageRequest.of(pageNumber-1,5);
        return newsRepository.NewsListCateParentPagePerCateForHome(cateParent ,pageable);
    }




    public List<News> fetchAll(){
        return newsRepository.findAll();
    }

    public List<News> fetchLimit(Long cate_id,int limit){
        return newsRepository.NewsLimitHomePerCate(cate_id,limit);

    }


    public List<News> FetchNewsChild(Long cate_id){
        return newsRepository.FetchNewsChild(cate_id);
    }

    public List<News> fetchLimitForHome(int limit){
        return newsRepository.NewsLimitForHome(limit);
    }

    public List<News> ListNewsCateParent(int cateParent){
        return newsRepository.NewsListCateParent(cateParent);
    }


    public List<News> ListNewsMoreViewCateParent(int cateParent){
        return newsRepository.ListNewsMoreViewCateParent(cateParent);
    }

    public List<News> ListNewsFeatureCateParent(int cateParent,int flag){
        return newsRepository.NewsListFeatureParent(cateParent,flag);
    }

    public List<News> ListNewsFeatureCateChild(Long cateId,int flag){
        return newsRepository.NewsListFeatureChild(cateId,flag);
    }

    public List<News> ListNewsMoreView(){
        return newsRepository.ListNewsMoreView();
    }

    public List<News> ListNewsByLinkName(String link_name){return newsRepository.ListNewsByCateLinkName(link_name);}

    public List<News> SearhNews(String input){
        return newsRepository.searchNews(input);
    }

    public List<News> ListNewsForHome(int flag){
        return newsRepository.ListNewsForHome(flag);
    }

    public List<News> ListNewsNewest(){
        return newsRepository.ListNewsNewest();
    }


    public List<News> ListNewsForCateParent(int parent_id){return newsRepository.ListNewestNewsForCateParent(parent_id);}

    public int CountByUser(Long userId){
        return newsRepository.CountNewsByUser(userId);
    }


    public News findById(long id){
        return newsRepository.findById(id).get();
    }

    public void save(News news){
        newsRepository.save(news);
    }

    public void delete(long id){
        newsRepository.deleteById(id);
    }

    public List<News> listNewsExist(){
       return newsRepository.listNewsExist();
    }


}

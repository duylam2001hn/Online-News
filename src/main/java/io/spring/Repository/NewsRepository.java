package io.spring.Repository;

import io.spring.Entity.Category;
import io.spring.Entity.News;
import io.spring.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News,Long> {

    @Query(value = "select n from News n where (n.flag=0 or n.flag=2 or n.flag =4 or n.flag=1 or n.flag =10 or n.flag=11 or n.flag =12 ) and n.status = 0")
    Page<News> findAllNewsInPerCategoryMaster(Pageable pageable);
    @Query(value = "select n from News n where (n.flag=0 or n.flag=10 or n.flag =11 or n.flag=12  ) and n.status = 0")
    Page<News> findAllNewsInPerCategoryChild(Pageable pageable);

    @Query(value = "select n from News n where (n.flag=0 or n.flag=3 or n.flag =5 or n.flag=7 or n.flag =6 ) and n.status = 0")
    Page<News> findAllNewsInHome(Pageable pageable);

    @Query(value = "select n from News n where (n.flag=0 or n.flag=2 or n.flag =4 or n.flag=1 or n.flag=10 or n.flag =11 or n.flag=12 ) and n.status=0 and n.category.parent_id=?1")
    Page<News> NewsListCateParentPagePerCate(int cateParent, Pageable pageable);

    @Query(value = "select n from News n where (n.flag=0 or n.flag=3 or n.flag =5 or n.flag=7 or n.flag=6 ) and n.status=0 and n.category.parent_id=?1")
    Page<News> NewsListCateParentPagePerCateForHome(int cateParent, Pageable pageable);

    @Query(value = "select n from News n where (n.flag=0 or n.flag=10 or n.flag =11 or n.flag=12) and n.status=0 and n.category.id=?1")
    Page<News> NewsListCateChildPagePerCate(Long cateId, Pageable pageable);

@Query(value = "select * from News n inner join category c On n.cate_id = c.id where( c.parent_id = ?1 and n.flag =6) and n.status=0 order by insert_time desc limit ?2",nativeQuery = true)
List<News> NewsLimitHomePerCate(Long idCategory, int limit);

    @Query(value = "select n from News n where (n.category.id = ?1 and n.flag=0) and n.status=0 order by n.InsertTime desc")
    List<News> FetchNewsChild(Long idCategory);

@Query(value = "select * from News n order by insert_time desc limit ?1",nativeQuery = true)
List<News> NewsLimitForHome (int limit);
@Query(value = "select n from News n where n.status = 0 order by n.InsertTime desc ")
List<News> ListNewsNewest();


    @Query(value = "select n from News n where n.category.parent_id = ?1 and n.flag =?2 and n.status=0")
    List<News> NewsListFeatureParent(int idCategory, int flag);

    @Query(value = "select n from News n where n.category.id = ?1 and n.flag =?2 and n.status=0")
    List<News> NewsListFeatureChild(Long idCategory, int flag);


    @Query(value = "select n from News n where n.category.parent_id=?1 and n.status=0 order by n.InsertTime desc ")
    List<News> NewsListCateParent(int cateParent);

    @Query(value = "select n from News n where n.status=0 and (n.title like concat('%',:input,'%') or n.content like concat('%',:input,'%') or n.shortDescription like concat('%',:input,'%') or n.category.name like concat('%',:input,'%') )")
    List<News> searchNews(@Param("input")String input);


    @Query(value = "select n from News n where n.status=0 order by n.view desc")
    List<News> ListNewsMoreView();

    @Query(value = "select n from News n where n.flag = ?1")
    List<News> ListNewsForHome(int flag);

    @Query(value = "select n from News n where n.category.parent_id =?1 and n.status=0 order by n.InsertTime desc")
    List<News> ListNewestNewsForCateParent(int ParentId);

    @Query(value = "select n from News n where n.category.parent_id=?1 and  n.status=0 order by n.view desc")
    List<News> ListNewsMoreViewCateParent(int cateParent);


    @Query(value = "select n from News n where n.category.link_name =?1 and n.status =0")
    List<News> ListNewsByCateLinkName(String link_name);

    @Query(value = "select count(n.user.id) from News n where n.user.id = ?1 and n.status =0 ")
    int CountNewsByUser(Long userId);

    @Query(value = "select n from News n where n.status = 0")
    List<News> listNewsExist();
}

package io.spring.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "news")
public class News extends Common {

    @NotEmpty(message = "Đây là trường bắt buộc")
    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail")
    private String thumbnail;

    @NotEmpty(message = "Đây là trường bắt buộc")
    @Column(name = "shortdescription")
    private String shortDescription;

    @NotEmpty(message = "Đây là trường bắt buộc")
    @Column(name = "content")
    private String content;


    @Column(name="status")
    private int status;

    @Column(name = "view")
    private int view;

    @Column(name = "flag")
    private int flag;

    @ManyToOne(targetEntity = Category.class, fetch= FetchType.LAZY)
    @JoinColumn(name = "cate_id")
    private Category category;

    @ManyToOne(targetEntity = User.class , fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public News() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

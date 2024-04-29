package io.spring.Entity;

import javax.persistence.criteria.CriteriaBuilder;

public class UserCount {
    private String email;
    private Integer totalNews;

    public UserCount() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTotalNews() {
        return totalNews;
    }

    public void setTotalNews(Integer totalNews) {
        this.totalNews = totalNews;
    }
}

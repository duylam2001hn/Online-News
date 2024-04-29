package io.spring.Entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;


@MappedSuperclass
public abstract class Common {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "insert_time")
    private String InsertTime;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsertTime() {
        return InsertTime;
    }

    public void setInsertTime(String insertTime) {
        InsertTime = insertTime;
    }


}

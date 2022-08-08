package com.example.review;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CollectionId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Video {

    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO) Integer id;
    private String title;
    public Video() {
    }
    public Video(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}



package com.techtalentsouth.TechTalentBlog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String title;
    private String author;
    private String blogEntry;

    public BlogPost(String title, String author, String blogEntry) {
        this.title = title;
        this.author = author;
        this.blogEntry = blogEntry;
    }

}

package com.blog9.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="title",nullable=false)
    private String title;

    @Column(name="description",nullable=false)
    private String description;

    @Column(name="content",nullable = false)
    private String content;
    @OneToMany(mappedBy = "post",cascade=CascadeType.ALL, orphanRemoval=true)

    List<Comments> comments=new ArrayList<>();


}

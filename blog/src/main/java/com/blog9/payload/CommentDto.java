package com.blog9.payload;

import com.blog9.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String name;
    private String email;
    private String body;

}

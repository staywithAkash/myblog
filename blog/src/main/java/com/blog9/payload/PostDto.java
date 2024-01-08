package com.blog9.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class PostDto {
    private long id;
    @Size(min=3,message = "Title should be atleast 3 character")
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotEmpty
    private String content;

}

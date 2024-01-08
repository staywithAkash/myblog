package com.blog9.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private List<PostDto> posts;
    private int pageNo;
    private int pageSize;
    private boolean lastPage;
    private int totalPages;
}

package com.blog9.service;

import com.blog9.payload.PostDto;
import com.blog9.payload.PostResponse;

import java.util.List;

public interface SavePost {

    PostDto createPost(PostDto postdto);

    void deleteById(long id);

    PostDto getPostById(long id);

    void updatePostById(long id,PostDto postDto);

    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
}

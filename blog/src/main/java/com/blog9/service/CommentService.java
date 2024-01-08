package com.blog9.service;

import com.blog9.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long id);
    public void deleteById(long commentId);
    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto updateComment(long id, CommentDto commentDto);
}

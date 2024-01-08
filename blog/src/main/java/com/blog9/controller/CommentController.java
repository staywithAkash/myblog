package com.blog9.controller;

import com.blog9.payload.CommentDto;
import com.blog9.service.CommentService;
import com.blog9.service.impl.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

//    http://localhost:8080/api/comments?postId=1
    @PostMapping()
    public ResponseEntity<CommentDto> createComment(
            @RequestParam long postId,
            @RequestBody CommentDto commentDto
    ){
        CommentDto commentDto1=commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/comments?postId=&commentId=
    @DeleteMapping
    public ResponseEntity<String> deleteById(
            @RequestParam() long commentId
    ){
        commentService.deleteById(commentId);
        return new ResponseEntity<>("comment is deleted",HttpStatus.OK);
    }
    //http://localhost:8080/api/comments?postId=1
    @GetMapping
    public List<CommentDto> getCommentsByPostId(
            @RequestParam() long postId
    ){
        List<CommentDto> dto=commentService.getCommentsByPostId(postId);
        return dto;
    }
    //http://localhost:8080/api/comments?postId=1
    @PutMapping
    public ResponseEntity<CommentDto> updateComment(
            @RequestParam long commentId,
            @RequestBody CommentDto commentDto
    ){
        CommentDto c=commentService.updateComment(commentId,commentDto);
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
}

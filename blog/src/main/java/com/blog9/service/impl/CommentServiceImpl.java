package com.blog9.service.impl;

import com.blog9.entity.Comments;
import com.blog9.entity.Post;
import com.blog9.exception.ResourceNotFound;
import com.blog9.payload.CommentDto;
import com.blog9.repository.CommentRepository;
import com.blog9.repository.PostRepository;
import com.blog9.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepo;
    private PostRepository postRepo;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepo,
                              PostRepository postRepo,
                              ModelMapper modelMapper) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.modelMapper=modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long id) {
        Post post=postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Post does not exist with id "+id)
        );
        Comments comments=mapToEntity(commentDto);
        comments.setPost(post);
        Comments c=commentRepo.save(comments);
        return mapToDto(c);
    }

    CommentDto mapToDto(Comments c) {
//        CommentDto commentDto=new CommentDto();
//        commentDto.setBody(c.getBody());
//        commentDto.setName(c.getName());
//        commentDto.setEmail(c.getEmail());
        CommentDto commentDto=modelMapper.map(c,CommentDto.class);
        return commentDto;
    }

    Comments mapToEntity(CommentDto commentDto) {
//        Comments comments=new Comments();
//        comments.setBody(commentDto.getBody());
//        comments.setName(commentDto.getName());
//        comments.setEmail(commentDto.getEmail());
        Comments comments=modelMapper.map(commentDto,Comments.class);
        return comments;
    }

    public void deleteById(long commentId) {
        Comments comment=commentRepo.findById(commentId).orElseThrow(
                ()->new ResourceNotFound("Comment does not exist")
        );
        commentRepo.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comments> comment=commentRepo.findByPostId(postId);
        List<CommentDto> commentDto=comment.stream().map(com->mapToDto(com)).collect(Collectors.toList());

        return commentDto;
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto) {
        Comments com=commentRepo.findById(id).get();
        Comments comments = mapToEntity(commentDto);
        comments.setId(com.getId());
        comments.setPost(com.getPost());
        Comments c=commentRepo.save(comments);

        return mapToDto(c);
    }
}

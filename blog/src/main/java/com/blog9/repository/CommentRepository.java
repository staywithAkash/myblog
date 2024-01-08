package com.blog9.repository;

import com.blog9.entity.Comments;
import com.blog9.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments,Long> {
List<Comments> findByPostId(long postId);
}

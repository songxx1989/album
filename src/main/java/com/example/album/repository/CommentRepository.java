package com.example.album.repository;

import com.example.album.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByPid(Long pid);
}

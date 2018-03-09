package com.example.album.controller;

import com.example.album.entity.Comment;
import com.example.album.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/add")
    public String addComment(Comment comment) {
        comment.setCtime(new Date());
        commentRepository.save(comment);
        return "redirect:/pictures/" + comment.getPid();
    }
}

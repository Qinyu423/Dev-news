package com.sda.demo.controllers;

import com.sda.demo.models.Comment;
import com.sda.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CommentsController {

    CommentRepository commentRepository;

    @Autowired
    public CommentsController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/comment")
    public Comment getCommentByAuthorName(@RequestParam String authorName) {
        Optional<Comment> searchedCommentByAuthorName = commentRepository.findByAuthorName(authorName);
        return searchedCommentByAuthorName.get();
    }

    @PutMapping("/comment/{id}")
    public Comment updatedComment(@PathVariable Long id, @RequestBody Comment updatedComment) {
        updatedComment.setId(id);
        commentRepository.save(updatedComment);
        return updatedComment;
    }
    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable Long id) {
        Optional<Comment> searchedCommentById = commentRepository.findById(id);
        commentRepository.delete(searchedCommentById.get());

    }
}

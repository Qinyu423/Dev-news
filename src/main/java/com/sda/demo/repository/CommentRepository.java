package com.sda.demo.repository;

import com.sda.demo.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByAuthorName(String authorName);
}

package com.sda.demo.repository;

import com.sda.demo.models.Comment;
import com.sda.demo.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAllById(Long articleId);

    Optional<Topic> findByName(String name);
}

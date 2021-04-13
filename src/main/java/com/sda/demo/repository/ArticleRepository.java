package com.sda.demo.repository;

import com.sda.demo.models.Article;
import com.sda.demo.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllById(Long topicId);
}

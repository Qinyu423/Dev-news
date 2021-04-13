package com.sda.demo.controllers;

import com.sda.demo.models.Article;
import com.sda.demo.models.Comment;
import com.sda.demo.models.Topic;
import com.sda.demo.repository.ArticleRepository;
import com.sda.demo.repository.CommentRepository;
import com.sda.demo.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class ArticlesController {


    ArticleRepository articleRepository;
    CommentRepository commentRepository;
    TopicRepository topicRepository;

    @Autowired
    public ArticlesController(ArticleRepository articleRepository, CommentRepository commentRepository, TopicRepository topicRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
        this.topicRepository = topicRepository;
    }                                                                    


    @GetMapping("/articles")
    public List<Article> listAllArticles() {
        List<Article> article = articleRepository.findAll();
        return article;
    }


    @PostMapping("/articles")
    public Article createArticle(@RequestBody Article createdNewArticle) {
        articleRepository.save(createdNewArticle);
        return createdNewArticle;
    }
    @GetMapping("/articles/{id}")
    public Article getArticleById(@PathVariable Long id) {
        Optional<Article> searchedArticleById = articleRepository.findById(id);
        return searchedArticleById.get();
    }

    @PutMapping("/articles/{id}")
    public Article updatedArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {
        updatedArticle.setId(id);
        articleRepository.save(updatedArticle);
        return updatedArticle;
    }
     @DeleteMapping("/articles/{id}")
     public void deleteArticle(@PathVariable Long id) {
         Optional<Article> searchedArticleById = articleRepository.findById(id);
           articleRepository.delete(searchedArticleById.get());
     }

     @PostMapping("/articles/{articleId}/comments")
     public Article commentedArticle(@PathVariable Long articleId, @RequestBody Comment commentAddedToArticle) {
        Optional<Article> searchedArticleById = articleRepository.findById(articleId);
        Article commentedArticle = searchedArticleById.get();
        commentedArticle.addComment(commentAddedToArticle);
        commentAddedToArticle.setArticle(commentedArticle);
        commentRepository.save(commentAddedToArticle);
        articleRepository.save(commentedArticle);
        return commentedArticle;

     }


     @GetMapping("/articles/{articleId}/comments")
     public List<Comment> listAllComments(@PathVariable Long articleId) {
         List<Comment> comment = commentRepository.findAllById(articleId);
         commentRepository.findAllById(articleId);
         return comment;

     }

     @GetMapping("/articles/{articleId}/topics")
     public List<Topic> listAllTopics(@PathVariable Long articleId) {
         List<Topic> topic = topicRepository.findAllById(articleId);
         topicRepository.findAllById(articleId);
         return topic;

     }

     @PostMapping("/articles/{articleId}/topics")
     public Topic addTopicToArticle(@PathVariable Long articleId, @RequestBody Topic topicToAdd) {
        Optional<Article> searchedArticleById = articleRepository.findById(articleId);
        Optional<Topic> searchedTopicById = topicRepository.findByName(topicToAdd.getName());
        Article foundArticle = searchedArticleById.get();
        Topic foundTopic = searchedTopicById.get();
        foundArticle.addTopic(foundTopic);
        topicToAdd.addArticle(foundArticle);
        topicRepository.save(foundTopic);
        articleRepository.save(foundArticle);
        return topicToAdd;

     }

}

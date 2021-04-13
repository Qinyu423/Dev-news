package com.sda.demo.controllers;

import com.sda.demo.models.Article;
import com.sda.demo.models.Topic;
import com.sda.demo.repository.ArticleRepository;
import com.sda.demo.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TopicsController {
    TopicRepository topicRepository;
    ArticleRepository articleRepository;

    @Autowired
    public TopicsController(TopicRepository topicRepository, ArticleRepository articleRepository) {
        this.topicRepository = topicRepository;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/topics")
    public List<Topic> listAllTopics() {
        List<Topic> topic = topicRepository.findAll();
        return topic;
    }

    @PostMapping("/topics")
    public Topic createTopic(@RequestBody Topic createdNewTopic) {
        topicRepository.save(createdNewTopic);
        return createdNewTopic;
    }

    @PutMapping("/topics/{id}")
    public Topic updatedTopic(@PathVariable Long id, @RequestBody Topic updatedTopic) {
        updatedTopic.setId(id);
        topicRepository.save(updatedTopic);
        return updatedTopic;
    }

    @DeleteMapping("/topics/{id}")
    public void deleteTopic(@PathVariable Long id) {
        Optional<Topic> searchedTopicById = topicRepository.findById(id);
        topicRepository.delete(searchedTopicById.get());
    }


    @GetMapping("/topics/{topicId}/articles")
    public List<Article> listAllArticles(@PathVariable Long topicId) {
        List<Article> article = articleRepository.findAllById(topicId);
        articleRepository.findAllById(topicId);
        return article;

    }

    @DeleteMapping("/articles/{articleId}/topics/{topicId}")
    public void deleteTopicForGivenArticle(@PathVariable Long articleId, @PathVariable Long topicId) {
       Optional<Article> searchedArticle = articleRepository.findById(articleId);
       Optional<Topic> searchedTopic = topicRepository.findById(topicId);
       Article foundArticle = searchedArticle.get();
       Topic foundTopic = searchedTopic.get();
       List<Topic> listOfTopics = foundArticle.getTopicList();
       if (listOfTopics.contains(foundTopic)) {
           listOfTopics.remove(foundTopic);
       }
    }





}
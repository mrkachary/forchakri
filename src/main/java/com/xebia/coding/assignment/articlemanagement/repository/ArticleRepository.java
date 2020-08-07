package com.xebia.coding.assignment.articlemanagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.xebia.coding.assignment.articlemanagement.model.Article;


public interface ArticleRepository extends CrudRepository<Article, Long> {
	Optional<Article> findBySlug(String slug);
	 
}

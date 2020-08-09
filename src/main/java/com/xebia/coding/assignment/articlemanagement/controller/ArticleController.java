package com.xebia.coding.assignment.articlemanagement.controller;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xebia.coding.assignment.articlemanagement.exception.ResourceNotFoundException;
import com.xebia.coding.assignment.articlemanagement.model.Article;
import com.xebia.coding.assignment.articlemanagement.response.ReadTimeResponse;
import com.xebia.coding.assignment.articlemanagement.service.ArticleService;


@RestController
@RequestMapping(("/article"))
public class ArticleController {

	/**
	 * API to save the article
	 * 
	 */
	@Autowired
	ArticleService articleService;
	@PostMapping
	private ResponseEntity<Article> saveArticle(@RequestBody Article article) throws ResourceNotFoundException
	{
		if(article.getTitle()==null || article.getTitle().isEmpty())
		{
			throw new ResourceNotFoundException("Title of article is missing and is mandatory");
		}
		if(article.getDescription()==null || article.getDescription().isEmpty())
		{
			throw new ResourceNotFoundException("Description of article is missing and mandatory");
		}
		if(article.getBody()==null || article.getBody().isEmpty())
		{
			throw new ResourceNotFoundException("Body of the article is missing and mandatory");
		}
		if(article.getTitle()!=null && !article.getTitle().isEmpty()) {
			article.setSlug(Arrays.asList(article.getTitle().split(" ")).stream().map(x->x.toLowerCase()).collect(Collectors.joining("-")));
		}
		Article savedArticle = articleService.saveArticles(article);
		return	ResponseEntity.ok().body(savedArticle);
	}

	/**
	 * getting an article by slug id
	 * 
	 * @param slug
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/slug/{slug}")
	private ResponseEntity<Article> getArticleBySlug(@PathVariable String slug) throws ResourceNotFoundException
	{		
		Optional<Article> article = articleService.getArticleBySlugId(slug);
		if(article.isPresent()) {
			return ResponseEntity.ok().body(article.get());
		} else {
				return new ResponseEntity<Article>(HttpStatus.BAD_REQUEST);
		}
	}
	/**
	 * API to update by Slug Id
	 * 
	 * @param article
	 * @param slug
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/slug/{slug}")
	private ResponseEntity<Article> updateArticleBySlug(@RequestBody Article article, @PathVariable String slug) throws ResourceNotFoundException
	{		
		Article updatedArticle = articleService.updateArticle(article,slug);
		return ResponseEntity.ok().body(updatedArticle);
	}

	/**
	 * delete by slug id
	 * 
	 * @param slug
	 * @return
	 * @throws ResourceNotFoundException 
	 */
	@DeleteMapping("/slug/{slug}")
	private void deleteArticle(@PathVariable String slug) throws ResourceNotFoundException
	{		
		articleService.deleteArticle(slug);
	}
	/**
	 * 
	 * @param slug
	 * @return
	 * @throws ResourceNotFoundException 
	 * @throws SQLException 
	 */
	@GetMapping("/readspeed/slug/{slug}")
	private ResponseEntity<ReadTimeResponse> getReaderSpeed(@PathVariable String slug) throws ResourceNotFoundException, SQLException {
		ReadTimeResponse readerTime = articleService.getReaderTime(slug);
		return ResponseEntity.ok().body(readerTime);
	}
}

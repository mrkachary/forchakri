package com.xebia.coding.assignment.articlemanagement.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.xebia.coding.assignment.articlemanagement.exception.ResourceNotFoundException;
import com.xebia.coding.assignment.articlemanagement.model.Article;
import com.xebia.coding.assignment.articlemanagement.repository.ArticleRepository;
import com.xebia.coding.assignment.articlemanagement.response.ReadTimeResponse;
import com.xebia.coding.assignment.articlemanagement.response.TimeToRead;


@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;


	@Autowired
	Environment environment;

	/**
	 * generate slug id and save
	 * @param article
	 * @return
	 */
	public Article saveArticles(Article article) {

		article.setSlug(Arrays.asList(article.getTitle().split(" ")).stream().map(x->x.toLowerCase()).collect(Collectors.joining("-")));
		article.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		article.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		Optional<Article> existingArticle = articleRepository.findBySlug(article.getSlug());
		//version if same slug id is found.
		if(existingArticle.isPresent())
		{
			article.setSlug(article.getSlug()+new Timestamp(System.currentTimeMillis()));
		}
		return articleRepository.save(article);	
	}

	/**
	 * update updated date, generate slug id, and other fields based on slug id. 
	 * 
	 * @param article
	 * @param slug
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Article updateArticle(Article article, String slug) throws ResourceNotFoundException{

		Article existingArticle = articleRepository.findBySlug(slug)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this slug id :: " + slug));

		existingArticle.setSlug(Arrays.asList(article.getTitle().split(" ")).stream().map(x->x.toLowerCase()).collect(Collectors.joining("-")));
		existingArticle.setTitle(article.getTitle());
		existingArticle.setDescription(article.getDescription());
		existingArticle.setBody(article.getBody());
		existingArticle.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		return  articleRepository.save(existingArticle);

	}

	/**
	 * 
	 * @param slug
	 * @return
	 * @throws ResourceNotFoundException 
	 */
	public void deleteArticle(String slug) throws ResourceNotFoundException {
		Article existingArticle = articleRepository.findBySlug(slug)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this slug id :: " + slug));
		articleRepository.delete(existingArticle);
	}

	/**
	 * to count speed of reader number of words per minute
	 * 
	 * @param slug
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public ReadTimeResponse getReaderTime(String slug) throws ResourceNotFoundException {

		double totalWords =0L;
		Article existingArticle = articleRepository.findBySlug(slug)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this slug id :: " + slug));
		//counting all words of title, description, and body
		totalWords = Arrays.asList(existingArticle.getTitle().split(" ")).stream().count();
		totalWords = totalWords+Arrays.asList(existingArticle.getDescription().split(" ")).stream().count();
		totalWords = totalWords+Arrays.asList(existingArticle.getBody().split(" ")).stream().count();

		//fetch average speed  configured value
		Long speedPerMin = Long.valueOf(environment.getProperty("average-words-reading-speed-per-minute"));
		//calculate reading speed
		double timeTaken = totalWords/speedPerMin;
		ReadTimeResponse response = new ReadTimeResponse();
		TimeToRead timeToRead = new TimeToRead();

		String valuesString = Double.toString(timeTaken);
		String nums[] = valuesString.split("\\.");
		int mins = Integer.parseInt(nums[0]);
		
		int seconds = 0;
		if(nums.length>1) {
			seconds = (int) (Double.parseDouble("."+nums[1]) * 60);
		}
		timeToRead.setMins(mins);
		timeToRead.setSeconds(seconds);
		response.setTimeToRead(timeToRead);
		response.setArticleId(slug);
		return response;
	}
}

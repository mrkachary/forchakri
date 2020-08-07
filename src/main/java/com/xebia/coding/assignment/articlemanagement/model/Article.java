package com.xebia.coding.assignment.articlemanagement.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Entity
@Data
@Getter
@Setter
@Table
public class Article {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="slug")
	private String slug;

	@Column(name="title")
	private String title;

	@Column(name="description")
	private String description;

	@Column(name="body", columnDefinition="CLOB NOT NULL") 
	@Nationalized
	private String body;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	public Article() {
		
	}
	public Article(Long id, String slug, String title, String description, String body) {
		super();
		this.id = id;
		this.slug = slug;
		this.title = title;
		this.description = description;
		this.body = body;
		this.createdAt = new Timestamp(System.currentTimeMillis());
		this.updatedAt =  new Timestamp(System.currentTimeMillis());
	}
}

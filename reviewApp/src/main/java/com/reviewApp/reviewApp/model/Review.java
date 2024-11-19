package com.reviewApp.reviewApp.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.GeneratorType;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ReviewDetails")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Column(name="review")
	@JsonProperty(namespace = "review")
	private String review;
	@JsonProperty(namespace = "review_source")
	private String review_source;
	@JsonProperty(namespace = "author")
	private String author;
	@JsonProperty(namespace = "rating")
	private Double rating;
	@JsonProperty(namespace = "title")
	private String title;
	@JsonProperty(namespace = "product_name")
	private String product_name;
	@JsonProperty(namespace = "reviewed_date")
	private LocalDateTime reviewed_date;

}

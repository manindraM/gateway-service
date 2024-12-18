package com.example.tmdb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.tmdb.model.Rating;
import com.example.tmdb.model.RatingRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/public")
@Slf4j
public class PublicController {
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Value("${ratings-service.url}")
	private String ratingsServiceUrl;
	
	
	@PostMapping
	public ResponseEntity<Object> addRating(@RequestBody RatingRequest ratingRequest){
		Rating rating;
		
		try {
			rating = restTemplate.postForObject(ratingsServiceUrl, ratingRequest, Rating.class);
			return ResponseEntity.ok(rating);
		}
		catch(HttpStatusCodeException ex) {
			log.error("Error adding rating: {}", ex.getMessage());
			return ResponseEntity.status(ex.getStatusCode())
					.contentType(MediaType.APPLICATION_JSON)
					.body(ex.getResponseBodyAsString());
		}
	}

}

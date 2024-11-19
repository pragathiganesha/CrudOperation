package com.reviewApp.reviewApp.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.reviewApp.reviewApp.repo.ReviewRepo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
@AutoConfigureMockMvc

public class ReviewControllerTest {
	@Mock
	private ReviewRepo repo;
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void addReview() {
		try {
			Path path = Paths.get("src", "test", "resources", "Request.json");
			String rateCountJson = new String(Files.readAllBytes(path));
			mockMvc.perform(MockMvcRequestBuilders.post("/add")
					.contentType(MediaType.APPLICATION_JSON)
					.content(rateCountJson))
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$.rating").value(1))
					.andExpect(jsonPath("$.product_name").value("Amazon Alexa"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void getAllReview() {
		try {
			
			 mockMvc.perform((RequestBuilder) ((ResultActions) MockMvcRequestBuilders.get("/").
					contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.rating").value(1))
					.andExpect(jsonPath("$.product_name").value("Amazon Alexa")));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	@Test
	public void getFilterReviewDetails() {
		try {
					
			
			mockMvc.perform((RequestBuilder) ((ResultActions) MockMvcRequestBuilders.get("/filter?rating=1&store=Amazon").
					contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.rating").value(1))
					.andExpect(jsonPath("$.product_name").value("Amazon Alexa")));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void getEachRatingCount() {
		try {
					
			mockMvc.perform((RequestBuilder) ((ResultActions) MockMvcRequestBuilders.get("/rating/store=Amazon").
					contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.rating").value(1))
					.andExpect(jsonPath("$.product_name").value("Amazon Alexa")));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

	@Test
	public void getAverageRatingPerStore() {
		try {
					
			mockMvc.perform((RequestBuilder) ((ResultActions) MockMvcRequestBuilders.get("average/store=Amazon").
					contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.rating").value(1))
					.andExpect(jsonPath("$.product_name").value("Amazon Alexa")));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}

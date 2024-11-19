package com.reviewApp.reviewApp.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reviewApp.reviewApp.model.RateCount;
import com.reviewApp.reviewApp.model.Review;
import com.reviewApp.reviewApp.repo.ReviewRepo;

@RestController

public class ReviewController {
	@Autowired
	private ReviewRepo repo;
	
	/**
	 * Add reviews
	 * @param reviewBody
	 * @return
	 */

	@PostMapping("/add")
	public ResponseEntity<Review> save(@RequestBody Review reviewBody) {
		try {
			Review reviewObj = repo.save(reviewBody);
			return new ResponseEntity<Review>(reviewObj, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Review>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
/**
 * 
 * Retreive all reviews
 * @return
 */
	
	  @GetMapping("/") 
	  public ResponseEntity<?> getAllReview() {
		  try {
			List<Review> reviewList=new ArrayList<>();
  
			  repo.findAll().forEach(reviewList::add);
			  if(reviewList.isEmpty()) {
				 return new ResponseEntity<String>("Data is not available",HttpStatus.NO_CONTENT);
			  }
			  return new ResponseEntity<List<Review>>( reviewList,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Review>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	  
	  }
	  
	  /**
	   * Filter data by date,store,rating
	   * 
	   * @param startDate
	   * @param endDate
	   * @param product_name
	   * @param rating
	   * @return
	   */
		@GetMapping("/filter")
		public ResponseEntity<?> getFilterReviewDetails(@RequestParam(required = false) String date,
				@RequestParam(required = false) String store, @RequestParam(required = false) Double rating) {

			try {
				// Parse dates if endDate
				LocalDateTime dateFilter = (date != null)
						? LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
						: null;
//				 String s="2017-12-22 01:14:56";
//		        	System.out.print(s.length());
//		        	
//		        	if (date.length()<11)
//					date=date.concat(" 00:00:00");
//
//		        LocalDateTime localDateTime = LocalDateTime.parse(date,  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

				List<Review> reviewList = repo.filterByCriteria(dateFilter, store, rating);
				if (reviewList.isEmpty())
					return new ResponseEntity<String>("No data for this filter", HttpStatus.NO_CONTENT);

				return new ResponseEntity<List<Review>>(reviewList, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Review>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		
		/**
		 * Average rating og given store name
		 * @param storeName
		 * @return
		 */
		@GetMapping("average/{store}")
		public ResponseEntity<?> getAverageRatingPerStore(@PathVariable("store") String storeName) {
			try {
				Double rating = repo.getAverageRatingPerStore(storeName);
				if (rating == null)
					return new ResponseEntity<String>("No rating for " + storeName + "", HttpStatus.NO_CONTENT);
				return new ResponseEntity<String>("Average rating of " + storeName + " is " + rating, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Review>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
		  
		  
		  /**
		   * 
		   * @param storeName
		   * @return
		   */
			@GetMapping("rating/{storeRate}")
			public ResponseEntity<?> getEachRatingCount(@PathVariable("storeRate") String storeName) {
				try {
					List<List<Number>> rating = repo.ratingCount(storeName);
					if (rating.isEmpty())
						return new ResponseEntity<String>("No rating for " + storeName + "", HttpStatus.NO_CONTENT);
					List<RateCount> ratingCount = new ArrayList<>();
					for (List<Number> rate : rating) {
						if (rate.size() == 2)
							ratingCount.add(new RateCount(rate.get(0).doubleValue(), rate.get(1).intValue()));

					}

					return new ResponseEntity<>(ratingCount, HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<Review>(HttpStatus.INTERNAL_SERVER_ERROR);
				}

			}

	 }



package com.reviewApp.reviewApp.repo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reviewApp.reviewApp.model.Review;
@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
	
	
	
	   @Query("SELECT p FROM Review p WHERE " +
	            "(:date IS NULL OR p.reviewed_date = :date) AND " +
	            "(:store IS NULL OR p.product_name = :store) AND " +
	            "(:rating IS NULL OR p.rating = :rating)")
	
	    List<Review> filterByCriteria(@Param("date") LocalDateTime date,
	                                   @Param("store") String store,
	                                   @Param("rating") Double rating);
	
	
	
	 
//	 List<Review> filterByCriteria(@Param("date") LocalDateTime date,
//             @Param("store") String store,
//             @Param("rating") Double rating);
//	   
//	 
//	       
	 @Query("SELECT AVG(rating) from  Review where product_name like '%Amazon%'")
	 Double getAverageRatingPerStore(@Param ("product")String _nameoreName);

	 
	 @Query("select rating, COUNT(*)AS RATING_COUNT  from Review "
	 	 		+ "where product_name like '%Amazon%' group by rating order by rating")
	 List<List<Number>> ratingCount(@Param ("store")String store);

	
}

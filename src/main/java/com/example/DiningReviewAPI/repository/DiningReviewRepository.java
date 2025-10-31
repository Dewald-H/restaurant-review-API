package com.example.DiningReviewAPI.repository;

import com.example.DiningReviewAPI.model.DiningReview;
import com.example.DiningReviewAPI.model.Restaurant;
import com.example.DiningReviewAPI.model.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiningReviewRepository extends JpaRepository<DiningReview, Long>{
    // Fetch all reviews pending admin approval
    List<DiningReview> findByStatus(ReviewStatus status);

    // Fetch all approved reviews for a specific restaurant
    List<DiningReview> findByRestaurantAndStatus(Restaurant restaurant, ReviewStatus status);

    // Optional: fetch reviews submitted by a specific user
    List<DiningReview> findByDisplayName(String displayName);
}
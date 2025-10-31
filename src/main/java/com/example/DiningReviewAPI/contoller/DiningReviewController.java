package com.example.DiningReviewAPI.contoller;

import com.example.DiningReviewAPI.model.DiningReview;
import com.example.DiningReviewAPI.model.Restaurant;
import com.example.DiningReviewAPI.model.ReviewStatus;
import com.example.DiningReviewAPI.repository.DiningReviewRepository;
import com.example.DiningReviewAPI.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class DiningReviewController {

    private final DiningReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    public DiningReviewController(DiningReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping
    public ResponseEntity<DiningReview> submitReview(@RequestBody DiningReview review) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(review.getRestaurant().getId());
        if (optionalRestaurant.isEmpty()) return ResponseEntity.badRequest().build();

        review.setRestaurant(optionalRestaurant.get());
        review.setStatus(ReviewStatus.PENDING);
        DiningReview saved = reviewRepository.save(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/user/{displayName}")
    public ResponseEntity<List<DiningReview>> getReviewsByUser(@PathVariable String displayName) {
        return ResponseEntity.ok(reviewRepository.findByDisplayName(displayName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiningReview> getReviewById(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

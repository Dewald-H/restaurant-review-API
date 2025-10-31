package com.example.DiningReviewAPI.contoller;

import com.example.DiningReviewAPI.model.DiningReview;
import com.example.DiningReviewAPI.model.ReviewStatus;
import com.example.DiningReviewAPI.model.Restaurant;
import com.example.DiningReviewAPI.model.AdminReview;
import com.example.DiningReviewAPI.repository.DiningReviewRepository;
import com.example.DiningReviewAPI.repository.RestaurantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/admin/reviews")
public class AdminController {

    private final DiningReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    public AdminController(DiningReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/pending")
    public ResponseEntity<List<DiningReview>> getPendingReviews() {
        return ResponseEntity.ok(reviewRepository.findByStatus(ReviewStatus.PENDING));
    }

    @PostMapping("/{id}")
    public ResponseEntity<DiningReview> handleReviewAction(
            @PathVariable Long id,
            @RequestBody AdminReview action
    ) {
        Optional<DiningReview> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isEmpty()) return ResponseEntity.notFound().build();

        DiningReview review = optionalReview.get();
        review.setStatus(action.isAccepted() ? ReviewStatus.ACCEPTED : ReviewStatus.REJECTED);
        reviewRepository.save(review);

        // If accepted, recompute restaurant scores
        if (action.isAccepted()) {
            Restaurant restaurant = review.getRestaurant();
            List<DiningReview> approvedReviews =
                    reviewRepository.findByRestaurantAndStatus(restaurant, ReviewStatus.ACCEPTED);

            // Example: recompute scores
            restaurant.setPeanutScore(approvedReviews.stream()
                    .map(DiningReview::getPeanutScore)
                    .filter(score -> score != null)
                    .mapToDouble(Integer::doubleValue)
                    .average().orElse(Double.NaN));
            restaurant.setEggScore(approvedReviews.stream()
                    .map(DiningReview::getEggScore)
                    .filter(score -> score != null)
                    .mapToDouble(Integer::doubleValue)
                    .average().orElse(Double.NaN));
            restaurant.setDairyScore(approvedReviews.stream()
                    .map(DiningReview::getDairyScore)
                    .filter(score -> score != null)
                    .mapToDouble(Integer::doubleValue)
                    .average().orElse(Double.NaN));

            // Compute overall score across non-null category scores
            double overall = Stream.of(restaurant.getPeanutScore(), restaurant.getEggScore(), restaurant.getDairyScore())
                    .filter(score -> score != null && !Double.isNaN(score))
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(Double.NaN);
            restaurant.setOverallScore(Double.isNaN(overall) ? null : overall);

            restaurantRepository.save(restaurant);
        }

        return ResponseEntity.ok(review);
    }
}

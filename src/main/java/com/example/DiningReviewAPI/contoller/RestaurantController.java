package com.example.DiningReviewAPI.contoller;

import com.example.DiningReviewAPI.model.Restaurant;
import com.example.DiningReviewAPI.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Optional<Restaurant> exists = restaurantRepository.findByNameAndZipcode(
                restaurant.getName(), restaurant.getZipcode()
        );
        if (exists.isPresent()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Restaurant saved = restaurantRepository.save(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long id) {
        return restaurantRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(
            @RequestParam String zipCode,
            @RequestParam String allergy
    ) {
        List<Restaurant> results;
        switch (allergy.toLowerCase()) {
            case "peanut":
                results = restaurantRepository.findByZipcodeAndPeanutScoreIsNotNullOrderByPeanutScoreDesc(zipCode);
                break;
            case "egg":
                results = restaurantRepository.findByZipcodeAndEggScoreIsNotNullOrderByEggScoreDesc(zipCode);
                break;
            case "dairy":
                results = restaurantRepository.findByZipcodeAndDairyScoreIsNotNullOrderByDairyScoreDesc(zipCode);
                break;
            default:
                return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(results);
    }
}

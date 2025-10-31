package com.example.DiningReviewAPI.repository;

import com.example.DiningReviewAPI.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
    // Fetch a restaurant by name and zip code (to prevent duplicates)
    Optional<Restaurant> findByNameAndZipcode(String name, String zipcode);

    // Fetch restaurants by zip code, where at least one allergy score is not null
    List<Restaurant> findByZipcodeAndPeanutScoreIsNotNullOrderByPeanutScoreDesc(String zipcode);
    List<Restaurant> findByZipcodeAndEggScoreIsNotNullOrderByEggScoreDesc(String zipcode);
    List<Restaurant> findByZipcodeAndDairyScoreIsNotNullOrderByDairyScoreDesc(String zipcode);
}
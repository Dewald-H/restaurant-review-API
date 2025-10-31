package com.example.DiningReviewAPI.repository;

import com.example.DiningReviewAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    // Fetch user by display name (for profile fetch and validation)
    Optional<User> findByDisplayName(String displayName);

    // Optional: check if display name already exists
    boolean existsByDisplayName(String displayName);
}
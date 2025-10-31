package com.example.DiningReviewAPI.contoller;

import com.example.DiningReviewAPI.model.User;
import com.example.DiningReviewAPI.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (userRepository.existsByDisplayName(user.getDisplayName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{displayName}")
    public ResponseEntity<User> updateUser(@PathVariable String displayName, @RequestBody User updates) {
        Optional<User> optionalUser = userRepository.findByDisplayName(displayName);
        if (optionalUser.isEmpty()) return ResponseEntity.notFound().build();

        User user = optionalUser.get();
        user.setCity(updates.getCity());
        user.setState(updates.getState());
        user.setZipcode(updates.getZipcode());
        user.setInterestedInPeanutAllergy(updates.isInterestedInPeanutAllergy());
        user.setInterestedInEggAllergy(updates.isInterestedInEggAllergy());
        user.setInterestedInDairyAllergy(updates.isInterestedInDairyAllergy());

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{displayName}")
    public ResponseEntity<User> getUser(@PathVariable String displayName) {
        return userRepository.findByDisplayName(displayName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

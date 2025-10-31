package com.example.DiningReviewAPI.model;

import jakarta.persistence.*;

@Entity
public class DiningReview {

    @Id
    @GeneratedValue
    private Long id;

    private String displayName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private Integer peanutScore;
    private Integer eggScore;
    private Integer dairyScore;
    private String commentary;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private ReviewStatus status = ReviewStatus.PENDING;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }

    public Integer getPeanutScore() { return peanutScore; }
    public void setPeanutScore(Integer peanutScore) { this.peanutScore = peanutScore; }

    public Integer getEggScore() { return eggScore; }
    public void setEggScore(Integer eggScore) { this.eggScore = eggScore; }

    public Integer getDairyScore() { return dairyScore; }
    public void setDairyScore(Integer dairyScore) { this.dairyScore = dairyScore; }

    public String getCommentary() { return commentary; }
    public void setCommentary(String commentary) { this.commentary = commentary; }

    public ReviewStatus getStatus() { return status; }
    public void setStatus(ReviewStatus status) { this.status = status; }
}

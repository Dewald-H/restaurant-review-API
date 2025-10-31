package com.example.DiningReviewAPI.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String zipcode;
    private Double peanutScore;
    private Double eggScore;
    private Double dairyScore;
    private Double overallScore;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiningReview> reviews = new ArrayList<>();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public Double getPeanutScore() { return peanutScore; }
    public void setPeanutScore(Double peanutScore) { this.peanutScore = peanutScore; }

    public Double getEggScore() { return eggScore; }
    public void setEggScore(Double eggScore) { this.eggScore = eggScore; }

    public Double getDairyScore() { return dairyScore; }
    public void setDairyScore(Double dairyScore) { this.dairyScore = dairyScore; }

    public Double getOverallScore() { return overallScore; }
    public void setOverallScore(Double overallScore) { this.overallScore = overallScore; }

    public List<DiningReview> getReviews() { return reviews; }
    public void setReviews(List<DiningReview> reviews) { this.reviews = reviews; }
}

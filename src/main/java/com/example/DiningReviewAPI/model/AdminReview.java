package com.example.DiningReviewAPI.model;

public class AdminReview {

    private Boolean accepted;

    // No-arg constructor
    public AdminReview() {}

    // All-args constructor
    public AdminReview(Boolean accepted) {
        this.accepted = accepted;
    }

    // Getter
    public Boolean isAccepted() {
        return accepted;
    }

    // Setter
    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}

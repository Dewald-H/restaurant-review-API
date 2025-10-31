package com.example.DiningReviewAPI.model;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String displayName;
    private String city;
    private String state;
    private String zipcode;
    private Boolean interestedInPeanutAllergy;
    private Boolean interestedInEggAllergy;
    private Boolean interestedInDairyAllergy;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public Boolean isInterestedInPeanutAllergy() { return interestedInPeanutAllergy; }
    public void setInterestedInPeanutAllergy(Boolean interestedInPeanutAllergy) { this.interestedInPeanutAllergy = interestedInPeanutAllergy; }

    public Boolean isInterestedInEggAllergy() { return interestedInEggAllergy; }
    public void setInterestedInEggAllergy(Boolean interestedInEggAllergy) { this.interestedInEggAllergy = interestedInEggAllergy; }

    public Boolean isInterestedInDairyAllergy() { return interestedInDairyAllergy; }
    public void setInterestedInDairyAllergy(Boolean interestedInDairyAllergy) { this.interestedInDairyAllergy = interestedInDairyAllergy; }
}


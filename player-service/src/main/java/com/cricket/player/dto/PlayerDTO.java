package com.cricket.player.dto;

import com.cricket.player.model.BattingStyle;
import com.cricket.player.model.BowlingStyle;
import com.cricket.player.model.PlayingRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PlayerDTO {
    private Long id;
    
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;
    
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    
    @NotBlank(message = "Country is required")
    @Size(max = 50, message = "Country must not exceed 50 characters")
    private String country;
    
    private PlayingRole playingRole;
    private BattingStyle battingStyle;
    private BowlingStyle bowlingStyle;
    private Integer jerseyNumber;
    private Boolean isActive;
    
    // Constructors
    public PlayerDTO() {}
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public PlayingRole getPlayingRole() {
        return playingRole;
    }
    
    public void setPlayingRole(PlayingRole playingRole) {
        this.playingRole = playingRole;
    }
    
    public BattingStyle getBattingStyle() {
        return battingStyle;
    }
    
    public void setBattingStyle(BattingStyle battingStyle) {
        this.battingStyle = battingStyle;
    }
    
    public BowlingStyle getBowlingStyle() {
        return bowlingStyle;
    }
    
    public void setBowlingStyle(BowlingStyle bowlingStyle) {
        this.bowlingStyle = bowlingStyle;
    }
    
    public Integer getJerseyNumber() {
        return jerseyNumber;
    }
    
    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
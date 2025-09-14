package com.cricket.player.model;

import com.cricket.common.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "players")
public class Player extends BaseEntity {
    
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Past(message = "Date of birth must be in the past")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @NotBlank(message = "Country is required")
    @Size(max = 50, message = "Country must not exceed 50 characters")
    @Column(name = "country", nullable = false)
    private String country;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "playing_role")
    private PlayingRole playingRole;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "batting_style")
    private BattingStyle battingStyle;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "bowling_style")
    private BowlingStyle bowlingStyle;
    
    @Column(name = "jersey_number")
    private Integer jerseyNumber;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    // Constructors
    public Player() {}
    
    public Player(String firstName, String lastName, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }
    
    // Getters and Setters
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
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
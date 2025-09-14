package com.cricket.player.controller;

import com.cricket.common.dto.ApiResponse;
import com.cricket.player.dto.PlayerDTO;
import com.cricket.player.model.PlayingRole;
import com.cricket.player.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@Tag(name = "Player Management", description = "APIs for managing cricket players")
public class PlayerController {
    
    private final PlayerService playerService;
    
    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    
    @PostMapping
    @Operation(summary = "Create a new player", description = "Create a new cricket player")
    public ResponseEntity<ApiResponse<PlayerDTO>> createPlayer(@Valid @RequestBody PlayerDTO playerDTO) {
        PlayerDTO createdPlayer = playerService.createPlayer(playerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Player created successfully", createdPlayer));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get player by ID", description = "Retrieve a player by their ID")
    public ResponseEntity<ApiResponse<PlayerDTO>> getPlayerById(
            @Parameter(description = "Player ID") @PathVariable Long id) {
        PlayerDTO player = playerService.getPlayerById(id);
        return ResponseEntity.ok(ApiResponse.success(player));
    }
    
    @GetMapping
    @Operation(summary = "Get all players", description = "Retrieve all players with pagination and filtering")
    public ResponseEntity<ApiResponse<Page<PlayerDTO>>> getAllPlayers(
            @Parameter(description = "Country filter") @RequestParam(required = false) String country,
            @Parameter(description = "Playing role filter") @RequestParam(required = false) PlayingRole playingRole,
            @Parameter(description = "Active status filter") @RequestParam(required = false) Boolean isActive,
            @PageableDefault(size = 20) Pageable pageable) {
        
        Page<PlayerDTO> players;
        if (country != null || playingRole != null || isActive != null) {
            players = playerService.getPlayersWithFilters(country, playingRole, isActive, pageable);
        } else {
            players = playerService.getAllPlayers(pageable);
        }
        
        return ResponseEntity.ok(ApiResponse.success(players));
    }
    
    @GetMapping("/country/{country}")
    @Operation(summary = "Get players by country", description = "Retrieve all players from a specific country")
    public ResponseEntity<ApiResponse<List<PlayerDTO>>> getPlayersByCountry(
            @Parameter(description = "Country name") @PathVariable String country) {
        List<PlayerDTO> players = playerService.getPlayersByCountry(country);
        return ResponseEntity.ok(ApiResponse.success(players));
    }
    
    @GetMapping("/role/{role}")
    @Operation(summary = "Get players by role", description = "Retrieve all players with a specific playing role")
    public ResponseEntity<ApiResponse<List<PlayerDTO>>> getPlayersByRole(
            @Parameter(description = "Playing role") @PathVariable PlayingRole role) {
        List<PlayerDTO> players = playerService.getPlayersByRole(role);
        return ResponseEntity.ok(ApiResponse.success(players));
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search players by name", description = "Search players by first name or last name")
    public ResponseEntity<ApiResponse<List<PlayerDTO>>> searchPlayersByName(
            @Parameter(description = "Search term") @RequestParam String name) {
        List<PlayerDTO> players = playerService.searchPlayersByName(name);
        return ResponseEntity.ok(ApiResponse.success(players));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update player", description = "Update an existing player")
    public ResponseEntity<ApiResponse<PlayerDTO>> updatePlayer(
            @Parameter(description = "Player ID") @PathVariable Long id,
            @Valid @RequestBody PlayerDTO playerDTO) {
        PlayerDTO updatedPlayer = playerService.updatePlayer(id, playerDTO);
        return ResponseEntity.ok(ApiResponse.success("Player updated successfully", updatedPlayer));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete player", description = "Delete a player by ID")
    public ResponseEntity<ApiResponse<Void>> deletePlayer(
            @Parameter(description = "Player ID") @PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok(ApiResponse.success("Player deleted successfully", null));
    }
    
    @PutMapping("/{id}/activate")
    @Operation(summary = "Activate player", description = "Activate a player")
    public ResponseEntity<ApiResponse<PlayerDTO>> activatePlayer(
            @Parameter(description = "Player ID") @PathVariable Long id) {
        PlayerDTO player = playerService.activatePlayer(id);
        return ResponseEntity.ok(ApiResponse.success("Player activated successfully", player));
    }
    
    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate player", description = "Deactivate a player")
    public ResponseEntity<ApiResponse<PlayerDTO>> deactivatePlayer(
            @Parameter(description = "Player ID") @PathVariable Long id) {
        PlayerDTO player = playerService.deactivatePlayer(id);
        return ResponseEntity.ok(ApiResponse.success("Player deactivated successfully", player));
    }
    
    @GetMapping("/stats/country/{country}/count")
    @Operation(summary = "Get player count by country", description = "Get the total number of players from a specific country")
    public ResponseEntity<ApiResponse<Long>> getPlayerCountByCountry(
            @Parameter(description = "Country name") @PathVariable String country) {
        long count = playerService.getPlayerCountByCountry(country);
        return ResponseEntity.ok(ApiResponse.success(count));
    }
    
    @GetMapping("/stats/role/{role}/count")
    @Operation(summary = "Get player count by role", description = "Get the total number of players with a specific role")
    public ResponseEntity<ApiResponse<Long>> getPlayerCountByRole(
            @Parameter(description = "Playing role") @PathVariable PlayingRole role) {
        long count = playerService.getPlayerCountByRole(role);
        return ResponseEntity.ok(ApiResponse.success(count));
    }
}
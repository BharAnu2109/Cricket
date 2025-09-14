package com.cricket.player.repository;

import com.cricket.player.model.Player;
import com.cricket.player.model.PlayingRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    
    List<Player> findByCountry(String country);
    
    List<Player> findByPlayingRole(PlayingRole playingRole);
    
    List<Player> findByIsActive(Boolean isActive);
    
    Optional<Player> findByJerseyNumber(Integer jerseyNumber);
    
    @Query("SELECT p FROM Player p WHERE " +
           "(:country IS NULL OR p.country = :country) AND " +
           "(:playingRole IS NULL OR p.playingRole = :playingRole) AND " +
           "(:isActive IS NULL OR p.isActive = :isActive)")
    Page<Player> findPlayersWithFilters(@Param("country") String country,
                                      @Param("playingRole") PlayingRole playingRole,
                                      @Param("isActive") Boolean isActive,
                                      Pageable pageable);
    
    @Query("SELECT p FROM Player p WHERE " +
           "LOWER(p.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
           "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Player> findByNameContaining(@Param("name") String name);
    
    long countByCountry(String country);
    
    long countByPlayingRole(PlayingRole playingRole);
}
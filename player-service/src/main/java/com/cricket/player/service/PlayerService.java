package com.cricket.player.service;

import com.cricket.common.exception.ResourceNotFoundException;
import com.cricket.player.dto.PlayerDTO;
import com.cricket.player.model.Player;
import com.cricket.player.model.PlayingRole;
import com.cricket.player.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlayerService {
    
    private final PlayerRepository playerRepository;
    
    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player player = convertToEntity(playerDTO);
        Player savedPlayer = playerRepository.save(player);
        return convertToDTO(savedPlayer);
    }
    
    @Transactional(readOnly = true)
    public PlayerDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", id));
        return convertToDTO(player);
    }
    
    @Transactional(readOnly = true)
    public Page<PlayerDTO> getAllPlayers(Pageable pageable) {
        Page<Player> players = playerRepository.findAll(pageable);
        return players.map(this::convertToDTO);
    }
    
    @Transactional(readOnly = true)
    public Page<PlayerDTO> getPlayersWithFilters(String country, PlayingRole playingRole, 
                                                Boolean isActive, Pageable pageable) {
        Page<Player> players = playerRepository.findPlayersWithFilters(country, playingRole, isActive, pageable);
        return players.map(this::convertToDTO);
    }
    
    @Transactional(readOnly = true)
    public List<PlayerDTO> getPlayersByCountry(String country) {
        List<Player> players = playerRepository.findByCountry(country);
        return players.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<PlayerDTO> getPlayersByRole(PlayingRole playingRole) {
        List<Player> players = playerRepository.findByPlayingRole(playingRole);
        return players.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<PlayerDTO> searchPlayersByName(String name) {
        List<Player> players = playerRepository.findByNameContaining(name);
        return players.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public PlayerDTO updatePlayer(Long id, PlayerDTO playerDTO) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", id));
        
        updatePlayerFields(player, playerDTO);
        Player updatedPlayer = playerRepository.save(player);
        return convertToDTO(updatedPlayer);
    }
    
    public void deletePlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", id));
        playerRepository.delete(player);
    }
    
    public PlayerDTO activatePlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", id));
        player.setIsActive(true);
        Player updatedPlayer = playerRepository.save(player);
        return convertToDTO(updatedPlayer);
    }
    
    public PlayerDTO deactivatePlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", id));
        player.setIsActive(false);
        Player updatedPlayer = playerRepository.save(player);
        return convertToDTO(updatedPlayer);
    }
    
    @Transactional(readOnly = true)
    public long getPlayerCountByCountry(String country) {
        return playerRepository.countByCountry(country);
    }
    
    @Transactional(readOnly = true)
    public long getPlayerCountByRole(PlayingRole playingRole) {
        return playerRepository.countByPlayingRole(playingRole);
    }
    
    private Player convertToEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        updatePlayerFields(player, playerDTO);
        return player;
    }
    
    private void updatePlayerFields(Player player, PlayerDTO playerDTO) {
        player.setFirstName(playerDTO.getFirstName());
        player.setLastName(playerDTO.getLastName());
        player.setDateOfBirth(playerDTO.getDateOfBirth());
        player.setCountry(playerDTO.getCountry());
        player.setPlayingRole(playerDTO.getPlayingRole());
        player.setBattingStyle(playerDTO.getBattingStyle());
        player.setBowlingStyle(playerDTO.getBowlingStyle());
        player.setJerseyNumber(playerDTO.getJerseyNumber());
        
        if (playerDTO.getIsActive() != null) {
            player.setIsActive(playerDTO.getIsActive());
        }
    }
    
    private PlayerDTO convertToDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setFirstName(player.getFirstName());
        playerDTO.setLastName(player.getLastName());
        playerDTO.setDateOfBirth(player.getDateOfBirth());
        playerDTO.setCountry(player.getCountry());
        playerDTO.setPlayingRole(player.getPlayingRole());
        playerDTO.setBattingStyle(player.getBattingStyle());
        playerDTO.setBowlingStyle(player.getBowlingStyle());
        playerDTO.setJerseyNumber(player.getJerseyNumber());
        playerDTO.setIsActive(player.getIsActive());
        return playerDTO;
    }
}
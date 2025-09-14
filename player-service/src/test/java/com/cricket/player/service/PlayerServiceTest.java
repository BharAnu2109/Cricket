package com.cricket.player.service;

import com.cricket.common.exception.ResourceNotFoundException;
import com.cricket.player.dto.PlayerDTO;
import com.cricket.player.model.Player;
import com.cricket.player.model.PlayingRole;
import com.cricket.player.model.BattingStyle;
import com.cricket.player.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    private Player testPlayer;
    private PlayerDTO testPlayerDTO;

    @BeforeEach
    void setUp() {
        testPlayer = new Player();
        testPlayer.setId(1L);
        testPlayer.setFirstName("Virat");
        testPlayer.setLastName("Kohli");
        testPlayer.setCountry("India");
        testPlayer.setDateOfBirth(LocalDate.of(1988, 11, 5));
        testPlayer.setPlayingRole(PlayingRole.BATSMAN);
        testPlayer.setBattingStyle(BattingStyle.RIGHT_HANDED);
        testPlayer.setJerseyNumber(18);
        testPlayer.setIsActive(true);

        testPlayerDTO = new PlayerDTO();
        testPlayerDTO.setId(1L);
        testPlayerDTO.setFirstName("Virat");
        testPlayerDTO.setLastName("Kohli");
        testPlayerDTO.setCountry("India");
        testPlayerDTO.setDateOfBirth(LocalDate.of(1988, 11, 5));
        testPlayerDTO.setPlayingRole(PlayingRole.BATSMAN);
        testPlayerDTO.setBattingStyle(BattingStyle.RIGHT_HANDED);
        testPlayerDTO.setJerseyNumber(18);
        testPlayerDTO.setIsActive(true);
    }

    @Test
    void createPlayer_ShouldReturnPlayerDTO() {
        // Given
        when(playerRepository.save(any(Player.class))).thenReturn(testPlayer);

        // When
        PlayerDTO result = playerService.createPlayer(testPlayerDTO);

        // Then
        assertNotNull(result);
        assertEquals(testPlayerDTO.getFirstName(), result.getFirstName());
        assertEquals(testPlayerDTO.getLastName(), result.getLastName());
        assertEquals(testPlayerDTO.getCountry(), result.getCountry());
        verify(playerRepository).save(any(Player.class));
    }

    @Test
    void getPlayerById_ShouldReturnPlayerDTO_WhenPlayerExists() {
        // Given
        when(playerRepository.findById(anyLong())).thenReturn(Optional.of(testPlayer));

        // When
        PlayerDTO result = playerService.getPlayerById(1L);

        // Then
        assertNotNull(result);
        assertEquals(testPlayer.getId(), result.getId());
        assertEquals(testPlayer.getFirstName(), result.getFirstName());
        verify(playerRepository).findById(1L);
    }

    @Test
    void getPlayerById_ShouldThrowException_WhenPlayerNotExists() {
        // Given
        when(playerRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ResourceNotFoundException.class, 
                    () -> playerService.getPlayerById(1L));
        verify(playerRepository).findById(1L);
    }

    @Test
    void getAllPlayers_ShouldReturnPageOfPlayerDTOs() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Player> playersPage = new PageImpl<>(List.of(testPlayer));
        when(playerRepository.findAll(pageable)).thenReturn(playersPage);

        // When
        Page<PlayerDTO> result = playerService.getAllPlayers(pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testPlayer.getFirstName(), result.getContent().get(0).getFirstName());
        verify(playerRepository).findAll(pageable);
    }

    @Test
    void updatePlayer_ShouldReturnUpdatedPlayerDTO() {
        // Given
        testPlayerDTO.setFirstName("Updated Name");
        when(playerRepository.findById(anyLong())).thenReturn(Optional.of(testPlayer));
        when(playerRepository.save(any(Player.class))).thenReturn(testPlayer);

        // When
        PlayerDTO result = playerService.updatePlayer(1L, testPlayerDTO);

        // Then
        assertNotNull(result);
        verify(playerRepository).findById(1L);
        verify(playerRepository).save(any(Player.class));
    }

    @Test
    void deletePlayer_ShouldDeletePlayer_WhenPlayerExists() {
        // Given
        when(playerRepository.findById(anyLong())).thenReturn(Optional.of(testPlayer));

        // When
        assertDoesNotThrow(() -> playerService.deletePlayer(1L));

        // Then
        verify(playerRepository).findById(1L);
        verify(playerRepository).delete(testPlayer);
    }

    @Test
    void activatePlayer_ShouldSetPlayerAsActive() {
        // Given
        testPlayer.setIsActive(false);
        when(playerRepository.findById(anyLong())).thenReturn(Optional.of(testPlayer));
        when(playerRepository.save(any(Player.class))).thenReturn(testPlayer);

        // When
        PlayerDTO result = playerService.activatePlayer(1L);

        // Then
        assertNotNull(result);
        assertTrue(result.getIsActive());
        verify(playerRepository).findById(1L);
        verify(playerRepository).save(testPlayer);
    }

    @Test
    void deactivatePlayer_ShouldSetPlayerAsInactive() {
        // Given
        when(playerRepository.findById(anyLong())).thenReturn(Optional.of(testPlayer));
        when(playerRepository.save(any(Player.class))).thenReturn(testPlayer);

        // When
        PlayerDTO result = playerService.deactivatePlayer(1L);

        // Then
        assertNotNull(result);
        assertFalse(result.getIsActive());
        verify(playerRepository).findById(1L);
        verify(playerRepository).save(testPlayer);
    }
}
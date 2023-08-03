package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.implementations.RatingServiceImpl;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

    @InjectMocks
    RatingServiceImpl ratingService;

    @Mock
    RatingRepository ratingRepository;

    @Mock
    Rating mockRating;

    @BeforeEach
    void setUp(){
        mockRating = new Rating();
        mockRating.setMoodysRating("moody");
        mockRating.setSandPRating("sandp");
        mockRating.setFitchRating("fitch");
        mockRating.setOrderNumber(3);
    }

    @Test
    void testSaveRating() {
       //Act
        ratingService.saveRating(mockRating);

        //Assert
        verify(ratingRepository, times(1)).save(mockRating);
    }

    @Test
    void testUpdateRating() {
        //Arrange
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(mockRating));

        //Act
        ratingService.updateRating(mockRating, 1);

        //Assert
        verify(ratingRepository, times(1)).save(mockRating);
    }

    @Test
    void testGetAllRatings() {
        //Arrange
        when(ratingRepository.findAll()).thenReturn(Collections.singletonList(mockRating));

        //Act
        List<Rating> result = ratingService.getAllRatings();

        //Assert
        verify(ratingRepository, times(1)).findAll();
        assertThat(result).isNotNull().hasSize(1).contains(mockRating);
    }

    @Test
    void testGetRatingById() {
        //Arrange
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(mockRating));

        //Act
        Rating result = ratingService.getRatingById(1);

        //Assert
        verify(ratingRepository, times(1)).findById(1);
        assertThat(result).isNotNull().isEqualTo(mockRating);
    }

    @Test
    void testDeleteRatingById() {
       //Act
        ratingService.deleteRatingById(1);

        //Assert
        verify(ratingRepository, times(1)).deleteById(1);
    }

}

package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.RatingService;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatingControllerTest {

    @InjectMocks
    RatingController ratingController;
    @Mock
    RatingService ratingService;
    @Mock
    UserService userService;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;
    @Mock
    Principal principal;

    Rating mockRating;

    private static final String REDIRECT_RATING_LIST = "redirect:/rating/list";

    @BeforeEach
    void setUp(){
        mockRating = new Rating();
        mockRating.setMoodysRating("moody");
        mockRating.setSandPRating("sandp");
        mockRating.setFitchRating("fitch");
        mockRating.setOrderNumber(3);
    }

    @Test
    void testHome() {
        //Arrange
        when(ratingService.getAllRatings()).thenReturn(Collections.singletonList(mockRating));
        when(principal.getName()).thenReturn("username");
        when(userService.getUserByUsername(anyString())).thenReturn(new User());
        //Act
        String viewName = ratingController.home(model, principal);
        //Assert
        verify(model, times(1)).addAttribute("ratings", Collections.singletonList(mockRating));
        verify(model, times(1)).addAttribute(eq("currentUser"), any(User.class));
        assertThat(viewName).isEqualTo("rating/list");
    }

    @Test
    void testAddRatingForm() {
        //Act
        String viewName = ratingController.addRatingForm(mockRating, model);
        //Assert
        verify(model, times(1)).addAttribute(eq("rating"), any(Rating.class));
        assertThat(viewName).isEqualTo("rating/add");
    }

    @Test
    void testValidate() {
        //Arrange
        when(bindingResult.hasErrors()).thenReturn(false);
        //Act
        String viewName = ratingController.validate(mockRating, bindingResult);
        //Assert
        verify(ratingService, times(1)).saveRating(mockRating);
        assertThat(viewName).isEqualTo(REDIRECT_RATING_LIST);
    }

    @Test
    void testShowUpdateForm() {
        //Arrange
        when(ratingService.getRatingById(anyInt())).thenReturn(mockRating);
        //Act
        String viewName = ratingController.showUpdateForm(1, model);
        //Assert
        verify(model, times(1)).addAttribute("rating", mockRating);
        assertThat(viewName).isEqualTo("rating/update");
    }

    @Test
    void testUpdateRating() {
        //Arrange
        when(bindingResult.hasErrors()).thenReturn(false);
        //Act
        String viewName = ratingController.updateRating(1, mockRating, bindingResult);
        //Assert
        verify(ratingService, times(1)).updateRating(mockRating, 1);
        assertThat(viewName).isEqualTo(REDIRECT_RATING_LIST);
    }

    @Test
    void testDeleteRating() {
        //Act
        String viewName = ratingController.deleteRating(1);
        //Assert
        verify(ratingService, times(1)).deleteRatingById(1);
        assertThat(viewName).isEqualTo(REDIRECT_RATING_LIST);
    }

}

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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.security.Principal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RatingControllerTest {

    @InjectMocks
    RatingController ratingController;
    @Mock
    RatingService ratingService;
    @Mock
    UserService userService;

    @Mock
    Model model;
    @Mock
    Principal principal;

    Rating mockRating;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();

        mockRating = new Rating();
        mockRating.setMoodysRating("AAA");
        mockRating.setSandPRating("AAA");
        mockRating.setFitchRating("AAA");
        mockRating.setOrderNumber(2);
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testHome() throws Exception {
        //Arrange
        when(principal.getName()).thenReturn("mockUsername");
        when(userService.getUserByUsername(anyString())).thenReturn(new User());

        // Act  & Assert
        mockMvc.perform(get("/rating/list").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/list"));

        verify(userService, times(1)).getUserByUsername(anyString());
        verify(ratingService, times(1)).getAllRatings();
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testAddRatingForm() throws Exception {
        //Arrange
        String viewName = ratingController.addRatingForm(mockRating, model);

        // Assert & Act
        mockMvc.perform(get("/rating/add")).andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testValidate() throws Exception {
        // Assert & act
        mockMvc.perform(post("/rating/validate")
                        .flashAttr("rating", mockRating))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/rating/list"));

        verify(ratingService, times(1)).saveRating(mockRating);
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testShowUpdateForm() throws Exception {
        //Arrange
        int id = 1;
        when(ratingService.getRatingById(anyInt())).thenReturn(mockRating);

        // Act & Assert
        mockMvc.perform(get("/rating/update/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"));

        verify(ratingService, times(1)).getRatingById(id);
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testUpdateRating() throws Exception {
        //Arrange
        int id = 1;

        // Act & Assert
        mockMvc.perform(post("/rating/update/" + id)
                        .flashAttr("rating", mockRating))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/rating/list"));

        verify(ratingService, times(1)).updateRating(mockRating, 1);
    }

    @Test
    void testDeleteRating() throws Exception {
        // Arrange
        int id = 1;

        //Act & Assert
        mockMvc.perform(get("/rating/delete/" + id))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/rating/list"));
        verify(ratingService, times(1)).deleteRatingById(id);
    }

}

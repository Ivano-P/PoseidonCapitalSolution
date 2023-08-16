package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.CurveService;
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
class CurveControllerTest {

    @InjectMocks
    CurveController curveController;

    @Mock
    CurveService curveService;

    @Mock
    UserService userService;

    @Mock
    Model model;

    @Mock
    Principal principal;

    private MockMvc mockMvc;

    CurvePoint mockCurvePoint;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(curveController).build();

        mockCurvePoint = new CurvePoint();
        mockCurvePoint.setCurveId(5);
        mockCurvePoint.setTerm(10.00);
        mockCurvePoint.setValue(10.00);
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testHome() throws Exception {
        // Arrange
        when(principal.getName()).thenReturn("mockUsername");
        when(userService.getUserByUsername("mockUsername")).thenReturn(new User());

        // Act  & Assert
        mockMvc.perform(get("/curvePoint/list").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"));

        verify(userService, times(1)).getUserByUsername(anyString());
        verify(curveService, times(1)).getAllCurvePoints();
    }


    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testAddBidForm() throws Exception {
        // Act
        String viewName = curveController.addBidForm(mockCurvePoint, model);

        // Assert & Act
        mockMvc.perform(get("/curvePoint/add")).andExpect(status().isOk())
                        .andExpect(view().name("curvePoint/add"));
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testValidate_NoErrors() throws Exception {
        // Assert & act
        mockMvc.perform(post("/curvePoint/validate")
                        .flashAttr("curvePoint", mockCurvePoint))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/curvePoint/list"));

        verify(curveService, times(1)).saveCurvePoint(mockCurvePoint);
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testShowUpdateForm() throws Exception {
        // Arrange
        int id = 1;
        when(curveService.getCurvePointById(id)).thenReturn(mockCurvePoint);

        // Act & Assert
        mockMvc.perform(get("/curvePoint/update/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));

        verify(curveService, times(1)).getCurvePointById(id);
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testUpdateBid_NoErrors() throws Exception {
        // Arrange
        int id = 1;

        // Act & Assert
        mockMvc.perform(post("/curvePoint/update/" + id)
                        .flashAttr("curvePoint", mockCurvePoint))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/curvePoint/list"));

        verify(curveService, times(1)).updateCurvePoint(mockCurvePoint, id);
    }

    @Test
    void testDeleteBid() throws Exception {
        // Arrange
        int id = 1;

        //Act & Assert
        mockMvc.perform(get("/curvePoint/delete/" + id))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/curvePoint/list"));
        verify(curveService, times(1)).deleteCurvePointById(id);
    }



}

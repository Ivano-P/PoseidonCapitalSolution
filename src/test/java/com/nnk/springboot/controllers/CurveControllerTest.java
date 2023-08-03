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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurveControllerTest {

    @InjectMocks
    CurveController curveController;

    @Mock
    CurveService curveService;

    @Mock
    UserService userService;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;

    @Mock
    Principal principal;

    CurvePoint mockCurvePoint;

    private static final String REDIRECT_CURVE_POINT_LIST = "redirect:/curvePoint/list";
    @BeforeEach
    void setUp() {
        mockCurvePoint = new CurvePoint();
        mockCurvePoint.setCurveId(5);
        mockCurvePoint.setTerm(10.00);
        mockCurvePoint.setValue(10.00);
    }

    @Test
    void testHome() {
        // Arrange
        when(curveService.getAllCurvePoints()).thenReturn(Collections.singletonList(mockCurvePoint));
        when(principal.getName()).thenReturn("mockUsername");
        when(userService.getUserByUsername("mockUsername")).thenReturn(new User());

        // Act
        String viewName = curveController.home(model, principal);

        // Assert
        assertThat(viewName).isEqualTo("curvePoint/list");
        verify(model, times(1)).addAttribute("curvePoints", Collections.singletonList(mockCurvePoint));
        verify(model, times(1)).addAttribute("currentUser", new User());
    }


    @Test
    void testAddBidForm() {
        // Act
        String viewName = curveController.addBidForm(mockCurvePoint, model);

        // Assert
        assertThat(viewName).isEqualTo("curvePoint/add");
    }

    @Test
    void testValidate_NoErrors() {
        // Arrange
        when(bindingResult.hasErrors()).thenReturn(false);

        // Act
        String viewName = curveController.validate(mockCurvePoint, bindingResult);

        // Assert
        assertThat(viewName).isEqualTo(REDIRECT_CURVE_POINT_LIST);
        verify(curveService, times(1)).saveCurvePoint(mockCurvePoint);
    }

    @Test
    void testShowUpdateForm() {
        // Arrange
        int id = 1;
        when(curveService.getCurvePointById(id)).thenReturn(mockCurvePoint);

        // Act
        String viewName = curveController.showUpdateForm(id, model);

        // Assert
        assertThat(viewName).isEqualTo("curvePoint/update");
        verify(curveService, times(1)).getCurvePointById(id);
    }

    @Test
    void testUpdateBid_NoErrors() {
        // Arrange
        int id = 1;
        when(bindingResult.hasErrors()).thenReturn(false);

        // Act
        String viewName = curveController.updateBid(id, mockCurvePoint, bindingResult);

        // Assert
        assertThat(viewName).isEqualTo(REDIRECT_CURVE_POINT_LIST);
        verify(curveService, times(1)).updateCurvePoint(mockCurvePoint, id);
    }

    @Test
    void testDeleteBid() {
        // Arrange
        int id = 1;

        // Act
        String viewName = curveController.deleteBid(id);

        // Assert
        assertThat(viewName).isEqualTo(REDIRECT_CURVE_POINT_LIST);
        verify(curveService, times(1)).deleteCurvePointById(id);
    }



}

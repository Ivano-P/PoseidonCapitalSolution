package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.InvalidAddBidListException;
import com.nnk.springboot.exceptions.InvalidUpdateBidListException;
import com.nnk.springboot.services.BidListService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BidListControllerTest {

    @InjectMocks
    BidListController bidListController;

    @Mock
    BidListService bidListService;

    @Mock
    UserService userService;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;

    @Mock
    Principal principal;

    BidList mockBid;

    @BeforeEach
    void setUp() {
        mockBid = new BidList();
        mockBid.setAccount("testAccount");
        mockBid.setType("mockType");
        mockBid.setBidQuantity(5.00);
    }

    @Test
    void testHome() {
        //Arrange
        when(principal.getName()).thenReturn("testUser");
        when(userService.getUserByUsername(anyString())).thenReturn(new User());

        //Act
        String viewName = bidListController.home(model, principal);

        //Assert
        assertThat(viewName).isEqualTo("bidList/list");
        verify(bidListService, times(1)).getAllBids();
        verify(userService, times(1)).getUserByUsername(anyString());
    }


    @Test
    void testAddBidForm(){
        //Arrange
        when(principal.getName()).thenReturn("testUser");
        when(userService.getUserByUsername(anyString())).thenReturn(new User());

        //Act
        String viewName = bidListController.addBidForm(mockBid ,principal, model);

        //Assert
        assertThat(viewName).isEqualTo("bidList/add");
        verify(userService, times(1)).getUserByUsername(anyString());
    }

    @Test
    void testValidate_NoErrors() {
        // Arrange
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        // Act
        String viewName = bidListController.validate(mockBid, result);

        // Assert
        assertThat(viewName).isEqualTo("redirect:/bidList/list");
        verify(bidListService, times(1)).saveBidList(mockBid);
    }

    @Test
    void testValidate_HasErrors() throws Exception {
        // Arrange
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // Act and Assert
        assertThrows(InvalidAddBidListException.class,
                () -> bidListController.validate(mockBid, result));

        verify(bidListService, never()).saveBidList(any(BidList.class));
    }

    @Test
    void testShowUpdateForm() {
        // Arrange
        int id = 1;
        when(bidListService.getBidById(id)).thenReturn(mockBid);

        // Act
        String viewName = bidListController.showUpdateForm(id, model);

        // Assert
        assertThat(viewName).isEqualTo("bidList/update");
        verify(bidListService, times(1)).getBidById(id);
    }

    @Test
    void testUpdateBid() {
        // Arrange
        int id = 1;
        when(bindingResult.hasErrors()).thenReturn(false);

        // Act
        String viewName = bidListController.updateBid(id, mockBid, bindingResult);

        // Assert
        assertThat(viewName).isEqualTo("redirect:/bidList/list");
        verify(bidListService, times(1)).updateBidList(mockBid, id);
    }

    @Test
    void testUpdateBidWithError() {
        // Arrange
        int id = 1;
        when(bindingResult.hasErrors()).thenReturn(true);

        //Act and Assert
        assertThrows(InvalidUpdateBidListException.class,
                () -> bidListController.updateBid(id, mockBid, bindingResult));
        verify(bidListService, never()).updateBidList(mockBid, id);
    }

    @Test
    void testDeleteBid() {
        // Arrange
        int id = 1;

        doNothing().when(bidListService).deleteBidListById(id);

        // Act
        String viewName = bidListController.deleteBid(id);

        // Assert
        assertThat(viewName).isEqualTo("redirect:/bidList/list");
        verify(bidListService, times(1)).deleteBidListById(id);
    }


}

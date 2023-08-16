package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.BidListService;
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

import java.security.Principal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BidListControllerTest {

    @InjectMocks
    BidListController bidListController;

    @Mock
    BidListService bidListService;

    @Mock
    UserService userService;

    @Mock
    Principal principal;

    BidList mockBid;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bidListController).build();

        mockBid = new BidList();
        mockBid.setAccount("testAccount");
        mockBid.setType("mockType");
        mockBid.setBidQuantity(5.00);
    }

    @Test
    @WithMockUser(username = "testUser")
    void testHome() throws Exception {
        //Arrange
        when(principal.getName()).thenReturn("testUser");
        when(userService.getUserByUsername(anyString())).thenReturn(new User());

        // Act & Assert
        mockMvc.perform(get("/bidList/list").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"));

        verify(bidListService, times(1)).getAllBids();
        verify(userService, times(1)).getUserByUsername(anyString());
    }

    @Test
    @WithMockUser(username = "testUser")
    void testAddBidForm() throws Exception{
        //Arrange
        when(principal.getName()).thenReturn("testUser");
        when(userService.getUserByUsername(anyString())).thenReturn(new User());

        //Act & Assert
        mockMvc.perform(get("/bidList/add").principal(principal)).andExpect(status().isOk());
        verify(userService, times(1)).getUserByUsername(anyString());
    }

    @Test
    @WithMockUser(username = "testUser")
    void testValidate_NoErrors() throws Exception {
    // Act & Assert
        mockMvc.perform(post("/bidList/validate")
                        .flashAttr("bidList", mockBid))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/bidList/list"));

        verify(bidListService, times(1)).saveBidList(mockBid);
    }

    @Test
    @WithMockUser(username = "testUser")
    void testShowUpdateForm() throws Exception {
        // Arrange
        int id = 1;
        when(bidListService.getBidById(id)).thenReturn(mockBid);

        // Act & Assert
        mockMvc.perform(get("/bidList/update/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));

        verify(bidListService, times(1)).getBidById(id);
    }

    @Test
    @WithMockUser(username = "testUser")
    void testUpdateBid() throws Exception {
        //Arrange
        int id = 1;

        //Act & Assert
        mockMvc.perform(post("/bidList/update/" + id)
                        .flashAttr("bidList", mockBid))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/bidList/list"));

        verify(bidListService, times(1)).updateBidList(mockBid, id);
    }

    @Test
    @WithMockUser(username = "testUser")
    void testDeleteBid() throws Exception {
        //Arrange
        int id = 1;
        doNothing().when(bidListService).deleteBidListById(id);

        //Act & Assert
        mockMvc.perform(get("/bidList/delete/" + id))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/bidList/list"));

        verify(bidListService, times(1)).deleteBidListById(id);
    }


}

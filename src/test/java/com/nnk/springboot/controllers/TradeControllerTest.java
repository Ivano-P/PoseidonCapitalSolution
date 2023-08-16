package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.TradeService;
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
class TradeControllerTest {

    @InjectMocks
    public TradeController tradeController;

    @Mock
    UserService userService;
    @Mock
    TradeService tradeService;

    @Mock
    Model model;
    @Mock
    Principal principal;

   Trade mockTrade;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tradeController).build();

        mockTrade = new Trade();
        mockTrade.setAccount("testAccount");
        mockTrade.setType("testType");
        mockTrade.setBuyQuantity(10.00);
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testHome() throws Exception {
        //Arrange
        when(principal.getName()).thenReturn("mockUsername");
        when(userService.getUserByUsername(anyString())).thenReturn(new User());

        // Act  & Assert
        mockMvc.perform(get("/trade/list").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/list"));

        verify(userService, times(1)).getUserByUsername(anyString());
        verify(tradeService, times(1)).getAllTrades();
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testAddTradeForm() throws Exception {
        //Arrange
        String result = tradeController.addTradeForm(mockTrade, model);

        // Assert & Act
        mockMvc.perform(get("/trade/add")).andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testValidate() throws Exception {
        // Assert & act
        mockMvc.perform(post("/trade/validate")
                        .flashAttr("trade", mockTrade))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/trade/list"));
        verify(tradeService, times(1)).saveTrade(mockTrade);
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testShowUpdateForm() throws Exception {
        //Arrange
        int id = 1;
        when(tradeService.getTradeById(anyInt())).thenReturn(mockTrade);

        // Act & Assert
        mockMvc.perform(get("/trade/update/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"));
        verify(tradeService, times(1)).getTradeById(anyInt());
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testUpdateTrade() throws Exception {
        //Arrange
        int id = 1;

        // Act & Assert
        mockMvc.perform(post("/trade/update/" + id)
                        .flashAttr("trade", mockTrade))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/trade/list"));
        verify(tradeService, times(1)).updateTrade(mockTrade, 1);
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testDeleteTrade() throws Exception {
        //Arrange
        int id = 1;

        //Act & Assert
        mockMvc.perform(get("/trade/delete/" + id))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/trade/list"));
        verify(tradeService, times(1)).deleteTradeById(anyInt());
    }

}

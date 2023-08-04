package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TradeControllerTest {

    @InjectMocks
    public TradeController tradeController;

    @Mock
    UserService userService;
    @Mock
    TradeService tradeService;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;
    @Mock
    Principal principal;

   Trade mockTrade;

    @BeforeEach
    void setUp() {
        mockTrade = new Trade();
        mockTrade.setAccount("testAccount");
        mockTrade.setType("testType");
        mockTrade.setBuyQuantity(10.00);
    }

    @Test
    void testHome() {
        //Act
        String result = tradeController.home(model, principal);

        //Assert
        verify(tradeService, times(1)).getAllTrades();
        verify(userService, times(1)).getUserByUsername(principal.getName());
        assertThat(result).isEqualTo("trade/list");
    }

    @Test
    void testAddTradeForm() {
        //Act
        String result = tradeController.addTradeForm(mockTrade, model);

        //Assert
        assertThat(result).isEqualTo("trade/add");
    }

    @Test
    void testValidate() {
        //Arrange
        when(bindingResult.hasErrors()).thenReturn(false);

        //Act
        String result = tradeController.validate(mockTrade, bindingResult);

        //Assert
        verify(tradeService, times(1)).saveTrade(mockTrade);
        assertThat(result).isEqualTo("redirect:/trade/list");
    }

    @Test
    void testShowUpdateForm() {
        //Arrange
        when(tradeService.getTradeById(anyInt())).thenReturn(mockTrade);

        //Act
        String result = tradeController.showUpdateForm(1, model);

        //Assert
        verify(tradeService, times(1)).getTradeById(anyInt());
        assertThat(result).isEqualTo("trade/update");
    }

    @Test
    void testUpdateTrade() {
        //Arrange
        when(bindingResult.hasErrors()).thenReturn(false);

        //Act
        String result = tradeController.updateTrade(1, mockTrade, bindingResult);

        //Assert
        verify(tradeService, times(1)).updateTrade(mockTrade, 1);
        assertThat(result).isEqualTo("redirect:/trade/list");
    }

    @Test
    void testDeleteTrade() {
        //Act
        String result = tradeController.deleteTrade(1);

        //Assert
        verify(tradeService, times(1)).deleteTradeById(anyInt());
        assertThat(result).isEqualTo("redirect:/trade/list");
    }

}

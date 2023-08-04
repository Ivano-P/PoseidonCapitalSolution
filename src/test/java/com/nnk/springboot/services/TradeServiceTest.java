package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.implementations.TradeServiceImpl;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TradeServiceTest {

    @InjectMocks
    public TradeServiceImpl tradeService;
    @Mock
    TradeRepository tradeRepository;

    Trade mockTrade;

    @BeforeEach
    void setUp() {
        mockTrade = new Trade();
        mockTrade.setAccount("testAccount");
        mockTrade.setType("testType");
        mockTrade.setBuyQuantity(10.00);
    }

    @Test
    void testSaveTrade() {
        //Act
        tradeService.saveTrade(mockTrade);

        //Assert
        verify(tradeRepository, times(1)).save(mockTrade);
    }

    @Test
    void testUpdateTrade() {
        //Arrange
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.of(mockTrade));

        //Act
        tradeService.updateTrade(mockTrade, 1);

        //Assert
        verify(tradeRepository, times(1)).save(mockTrade);
    }

    @Test
    void testGetAllTrades() {
        //Arrange
        when(tradeRepository.findAll()).thenReturn(Collections.singletonList(mockTrade));

        //Act
        List<Trade> result = tradeService.getAllTrades();

        //Assert
        verify(tradeRepository, times(1)).findAll();
        assertThat(result).contains(mockTrade);
    }

    @Test
    void testGetTradeById() {
        //Arrange
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.of(mockTrade));

        //Act
        Trade result = tradeService.getTradeById(1);

        //Assert
        verify(tradeRepository, times(1)).findById(anyInt());
        assertThat(result).isEqualTo(mockTrade);
    }

    @Test
    void testGetTradeById_NotFound() {
        //Arrange
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.empty());

        //Act Assert
        assertThrows(NoSuchElementException.class, () -> tradeService.getTradeById(1));
    }

    @Test
    void testDeleteTradeById() {
        //Act
        tradeService.deleteTradeById(1);

        //Assert
        verify(tradeRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void testDeleteTradeById_NotFound() {
        //Act
        tradeService.deleteTradeById(1);

        //Assert
        verify(tradeRepository, times(1)).deleteById(anyInt());
    }
}

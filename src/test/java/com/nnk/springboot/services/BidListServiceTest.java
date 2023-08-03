package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.implementations.BidListServiceImpl;
import com.nnk.springboot.repositories.BidListRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BidListServiceTest {

    @InjectMocks
    BidListServiceImpl bidListService;

    @Mock
    BidListRepository bidListRepository;

    BidList mockBid;

    @BeforeEach
    void setUp() {
        mockBid = new BidList();
        mockBid.setAccount("testAccount");
        mockBid.setType("mockType");
        mockBid.setBidQuantity(5.00);
    }

    @Test
    void updateBidList() {
        // Act
        bidListService.saveBidList(mockBid);

        // Assert
        verify(bidListRepository, times(1)).save(mockBid);
    }

    @Test
    void getAllBids() {
        // Arrange
        when(bidListRepository.findAll()).thenReturn(Collections.singletonList(mockBid));

        // Act
        List<BidList> bidLists = bidListService.getAllBids();

        // Assert
        assertThat(bidLists).containsExactly(mockBid);
    }

    @Test
    void getBidById() {
        // Arrange
        int id = 1;
        when(bidListRepository.findById(id)).thenReturn(Optional.of(mockBid));

        // Act
        BidList bidList = bidListService.getBidById(id);

        // Assert
        assertThat(bidList).isEqualTo(mockBid);
    }

    @Test
    void deleteBidList() {
        // Act
        bidListService.deleteBidList(mockBid);

        // Assert
        verify(bidListRepository, times(1)).delete(mockBid);
    }

    @Test
    void deleteBidListById() {
        // Arrange
        int id = 1;

        doNothing().when(bidListRepository).deleteById(id);

        // Act
        bidListService.deleteBidListById(id);

        // Assert
        verify(bidListRepository, times(1)).deleteById(id);
    }

}

package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BidListService {
    void updateBidList(BidList bidList);
    List<BidList> getAllBids();

    BidList getBidById(int id);

    BidList getBidListByBid(BidList bid);

    void deleteBidList(BidList bidList);

    void deleteBidListById(int id);

}

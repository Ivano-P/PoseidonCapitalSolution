package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;

import java.util.List;

public interface BidListService {
    void saveBidList(BidList bidList);
    void updateBidList(BidList updatedBidlist, int bidListToUpdateId);
    List<BidList> getAllBids();

    BidList getBidById(int id);

    void deleteBidList(BidList bidList);

    void deleteBidListById(int id);

}

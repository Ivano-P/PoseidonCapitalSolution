package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;

import java.util.List;

/**
 * Service interface for operations related to BidList.
 */
public interface BidListService {

    /**
     * Persists a given BidList entity.
     *
     * @param bidList the bid list to save
     */
    void saveBidList(BidList bidList);

    /**
     * Updates an existing BidList entity identified by its ID.
     *
     * @param updatedBidlist the updated bid list
     * @param bidListToUpdateId the ID of the bid list to update
     */
    void updateBidList(BidList updatedBidlist, int bidListToUpdateId);

    /**
     * Retrieves all BidList entities.
     *
     * @return a list of all bids
     */
    List<BidList> getAllBids();

    /**
     * Retrieves a BidList entity by its ID.
     *
     * @param id the ID of the bid list to retrieve
     * @return the bid list corresponding to the given ID
     */
    BidList getBidById(int id);

    /**
     * Deletes a given BidList entity.
     *
     * @param bidList the bid list to delete
     */
    void deleteBidList(BidList bidList);

    /**
     * Deletes a BidList entity identified by its ID.
     *
     * @param id the ID of the bid list to delete
     */
    void deleteBidListById(int id);

}

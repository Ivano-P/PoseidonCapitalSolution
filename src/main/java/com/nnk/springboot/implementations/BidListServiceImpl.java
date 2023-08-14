package com.nnk.springboot.implementations;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Implementation for {@link BidListService}.
 * Provides CRUD operations for {@link BidList} objects.
 *
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
@Service
public class BidListServiceImpl implements BidListService {

    /** The repository responsible for managing bid list data. */
    private final BidListRepository bidListRepository;

    /**
     * Saves a given {@link BidList}.
     *
     * @param bidList the bid list to be saved.
     */
    public void saveBidList(BidList bidList) {
        log.info("updateBidList method called with: {}", bidList);
        bidListRepository.save(bidList);
    }

    /**
     * Updates an existing {@link BidList} identified by the provided ID.
     *
     * @param updatedBidlist      the updated bid list data.
     * @param bidListToUpdateId   the ID of the bid list to be updated.
     */
    public void updateBidList(BidList updatedBidlist, int bidListToUpdateId){
        log.info("updateBidList method called with: {}, {}", updatedBidlist, bidListToUpdateId);
        BidList bidListToUpdate = getBidById(bidListToUpdateId);
        bidListToUpdate.setAccount(updatedBidlist.getAccount());
        bidListToUpdate.setType(updatedBidlist.getType());
        bidListToUpdate.setBidQuantity(updatedBidlist.getBidQuantity());
        saveBidList(bidListToUpdate);
    }

    /**
     * Fetches all available {@link BidList}.
     *
     * @return a list of all bid lists.
     */
    public List<BidList> getAllBids() {
        log.info("getAllBids method called");
        return bidListRepository.findAll();
    }

    /**
     * Retrieves a {@link BidList} by its ID.
     *
     * @param id the ID of the bid list to be retrieved.
     * @return the bid list if found, otherwise throw NoSuchElementException.
     */
    public BidList getBidById(int id) {
        log.info("getBidById method called with: {}", id);
        Optional<BidList> bidListOptional = bidListRepository.findById(id);

        return bidListOptional.orElseThrow(NoSuchElementException::new);
    }


    /**
     * Deletes a given {@link BidList}.
     *
     * @param bidList the bid list to be deleted.
     */
    public void deleteBidList(BidList bidList) {
        log.info("deleteBidList method called with: {}", bidList);
        bidListRepository.delete(bidList);
    }

    /**
     * Deletes {@link BidList} by its ID.
     *
     * @param id the ID of the bid list to be deleted.
     */
    public void deleteBidListById(int id) {
        log.info("deleteBidListById method called with: {}", id);
        bidListRepository.deleteById(id);
    }

}

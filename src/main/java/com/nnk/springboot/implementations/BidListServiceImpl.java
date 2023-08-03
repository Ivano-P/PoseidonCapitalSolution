package com.nnk.springboot.implementations;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Log4j2
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BidListServiceImpl implements BidListService {

    private final BidListRepository bidListRepository;

    public void updateBidList(BidList bidList) {
        log.info("updateBidList method called with: {}", bidList);
        bidListRepository.save(bidList);
    }

    public List<BidList> getAllBids() {

        log.info("getAllBids method called");
        return bidListRepository.findAll();
    }

    public BidList getBidById(int id) {
        log.info("getBidById method called with: {}", id);
        Optional<BidList> bidListOptional = bidListRepository.findById(id);

        return bidListOptional.orElse(null);
    }

    public BidList getBidListByBid(BidList bid) {
        log.info("getBidListByBid method called with: {}", bid);
        Optional<BidList> bidListOptional = bidListRepository.findByBid(bid);

        return bidListOptional.orElse(null);
    }


    public void deleteBidList(BidList bidList) {
        log.info("deleteBidList method called with: {}", bidList);
        bidListRepository.delete(bidList);
    }

    public void deleteBidListById(int id) {
        log.info("deleteBidListById method called with: {}", id);
        bidListRepository.deleteById(id);
    }

}

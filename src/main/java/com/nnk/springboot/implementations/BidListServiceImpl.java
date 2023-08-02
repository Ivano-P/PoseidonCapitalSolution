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

    public void updateBidList(BidList bidList){
        bidListRepository.save(bidList);
    }

    public List<BidList> getAllBids(){
        return bidListRepository.findAll();
    }

    public BidList getBidById(int id){
        Optional<BidList> bidListOptional = bidListRepository.findById(id);

        return bidListOptional.orElse(null);
    }

    public BidList getBidListByBid(BidList bid){
        Optional<BidList> bidListOptional = bidListRepository.findByBid(bid);

        return bidListOptional.orElse(null);
    }


    public void deleteBidList(BidList bidList){
        bidListRepository.delete(bidList);
    }

    public void deleteBidListById(int id){
        bidListRepository.deleteById(id);
    }

}

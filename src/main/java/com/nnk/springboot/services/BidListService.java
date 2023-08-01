package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BidListService {
    List<BidList> getAllBids();


}

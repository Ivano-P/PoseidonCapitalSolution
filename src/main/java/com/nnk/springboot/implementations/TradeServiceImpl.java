package com.nnk.springboot.implementations;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class TradeServiceImpl implements TradeService {

    private final TradeRepository tradeRepository;

    public void saveTrade(Trade trade) {
        log.info("saveTrade method called with: {}", trade);
        tradeRepository.save(trade);
    }

    public void updateTrade(Trade updatedTrade, int tradeToUpdateId) {
        log.info("updateTrade method called with: {}, {}", updatedTrade, tradeToUpdateId);
        Trade tradeToUpdate = getTradeById(tradeToUpdateId);
        tradeToUpdate.setAccount(updatedTrade.getAccount());
        tradeToUpdate.setType(updatedTrade.getType());
        tradeToUpdate.setBuyQuantity(updatedTrade.getBuyQuantity());
        saveTrade(tradeToUpdate);
    }


    public List<Trade> getAllTrades() {
        log.info("getAllTrades method called");
        return tradeRepository.findAll();
    }


    public Trade getTradeById(int id) {
        log.info("getTradeById method called with: {}", id);
        Optional<Trade> tradeOptional = tradeRepository.findById(id);
        return tradeOptional.orElseThrow(NoSuchElementException::new);
    }


    public void deleteTradeById(int id) {
        log.info("deleteTradeById method called with: {}", id);
        tradeRepository.deleteById(id);
    }
}

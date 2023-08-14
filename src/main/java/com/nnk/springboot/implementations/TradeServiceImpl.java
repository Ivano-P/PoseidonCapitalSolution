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

/**
 * Implementation of the {@link TradeService} interface.
 * Manages CRUD operations for the {@link Trade} domain.
 *
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class TradeServiceImpl implements TradeService {

    /** The repository responsible for managing Trade data. */
    private final TradeRepository tradeRepository;

    /**
     * Saves a given {@link Trade} to the database.
     *
     * @param trade the trade entity to save.
     */
    public void saveTrade(Trade trade) {
        log.info("saveTrade method called with: {}", trade);
        tradeRepository.save(trade);
    }

    /**
     * Updates an existing trade using the provided updated trade details.
     *
     * @param updatedTrade the updated trade details.
     * @param tradeToUpdateId the ID of the trade to update.
     */
    public void updateTrade(Trade updatedTrade, int tradeToUpdateId) {
        log.info("updateTrade method called with: {}, {}", updatedTrade, tradeToUpdateId);
        Trade tradeToUpdate = getTradeById(tradeToUpdateId);
        tradeToUpdate.setAccount(updatedTrade.getAccount());
        tradeToUpdate.setType(updatedTrade.getType());
        tradeToUpdate.setBuyQuantity(updatedTrade.getBuyQuantity());
        saveTrade(tradeToUpdate);
    }


    /**
     * Fetches all trades from the database.
     *
     * @return a list of all trade entities.
     */
    public List<Trade> getAllTrades() {
        log.info("getAllTrades method called");
        return tradeRepository.findAll();
    }

    /**
     * Retrieves a specific {@link Trade} using its ID.
     *
     * @param id the ID of the trade to retrieve.
     * @return the corresponding trade entity.
     * @throws NoSuchElementException if no trade is found for the given ID.
     */
    public Trade getTradeById(int id) {
        log.info("getTradeById method called with: {}", id);
        Optional<Trade> tradeOptional = tradeRepository.findById(id);
        return tradeOptional.orElseThrow(NoSuchElementException::new);
    }


    /**
     * Deletes a trade from the database using its ID.
     *
     * @param id the ID of the trade to delete.
     */
    public void deleteTradeById(int id) {
        log.info("deleteTradeById method called with: {}", id);
        tradeRepository.deleteById(id);
    }
}

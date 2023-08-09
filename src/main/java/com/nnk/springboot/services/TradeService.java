package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;

import java.util.List;

/**
 * Service interface for operations related to Trade.
 */
public interface TradeService {


    /**
     * Persists a given Trade entity.
     *
     * @param trade the trade to save
     */
    void saveTrade(Trade trade);

    /**
     * Updates an existing Trade entity identified by its ID.
     *
     * @param updatedTrade the updated trade
     * @param tradeToUpdateId the ID of the trade to update
     */
    void updateTrade(Trade updatedTrade, int tradeToUpdateId);

    /**
     * Retrieves all Trade entities.
     *
     * @return a list of all trades
     */
    List<Trade> getAllTrades();

    /**
     * Retrieves a Trade entity by its ID.
     *
     * @param id the ID of the trade to retrieve
     * @return the trade corresponding to the given ID
     */
    Trade getTradeById(int id);

    /**
     * Deletes a Trade entity identified by its ID.
     *
     * @param id the ID of the trade to delete
     */
    void deleteTradeById(int id);

}

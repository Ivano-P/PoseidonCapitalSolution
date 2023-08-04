package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;

import java.util.List;

public interface TradeService {

    void saveTrade(Trade trade);
    void updateTrade(Trade updatedTrade, int tradeToUpdateId);
    List<Trade> getAllTrades();
    Trade getTradeById(int id);
    void deleteTradeById(int id);

}

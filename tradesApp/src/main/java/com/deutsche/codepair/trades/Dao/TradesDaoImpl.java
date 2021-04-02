package com.deutsche.codepair.trades.Dao;

import com.deutsche.codepair.trades.pojo.Trades;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TradesDaoImpl implements TradesDao{
    @Override
    public void save(Trades trades) {
        trades.setCreatedDate(LocalDate.now());
        tradeMaps.put(trades.getTradeId(),trades);
    }

    @Override
    public List<Trades> findAll() {
         return tradeMaps.values().stream().
                 collect(Collectors.toList());
    }

    @Override
    public Trades findTrade(String tradeId) {
        return tradeMaps.get(tradeId);
    }
}

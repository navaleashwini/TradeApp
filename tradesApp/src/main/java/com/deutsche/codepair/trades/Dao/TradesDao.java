package com.deutsche.codepair.trades.Dao;

import com.deutsche.codepair.trades.pojo.Trades;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface TradesDao {


    public  static Map<String,Trades> tradeMaps =new ConcurrentHashMap<>();


    public void save(Trades trades);

    List<Trades> findAll();

    Trades findTrade(String tradeId);
}

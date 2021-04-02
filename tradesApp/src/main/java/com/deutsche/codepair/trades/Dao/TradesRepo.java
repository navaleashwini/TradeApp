package com.deutsche.codepair.trades.Dao;

import com.deutsche.codepair.trades.pojo.Trades;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradesRepo extends JpaRepository<Trades,String> {
}

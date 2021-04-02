package com.deutsche.codepair.trades.controller;

import com.deutsche.codepair.trades.exception.TradesExceptions;
import com.deutsche.codepair.trades.pojo.Trades;
import com.deutsche.codepair.trades.service.TradesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradesController {
    @Autowired
    TradesService tradesService;
    
    @GetMapping("/tradeApp")
    public List<Trades> findAllTrades(){
        return tradesService.findAll();
    }
    
    @PostMapping("/tradeApp")
    public ResponseEntity<String> tradeValidateStore(@RequestBody Trades trades){
       if(tradesService.isValid(trades)) {
           tradesService.persist(trades);
       }else{
  
           throw new TradesExceptions(trades.getTradeId()+"  Trade Id is not found");
       }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    
}

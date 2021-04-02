package com.deutsche.codepair.tradesTest;

import com.deutsche.codepair.trades.controller.TradesController;
import com.deutsche.codepair.trades.exception.TradesExceptions;
import com.deutsche.codepair.trades.pojo.Trades;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TradesAppTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private TradesController tradesController;

	private Trades createTrade(String tradeId,int version,LocalDate  maturityDate){
		Trades trades = new Trades();
		trades.setTradeId(tradeId);
		trades.setBookId(tradeId+"B1");
		trades.setVersion(version);
		trades.setCounterParty(tradeId+"Cpty");
		trades.setMaturityDate(maturityDate);
		trades.setExpiredFlag("Y");
		return trades;
	}

	public static LocalDate getLocalDate(int year,int month, int day){
		LocalDate localDate = LocalDate.of(year,month,day);
		return localDate;
	}




}

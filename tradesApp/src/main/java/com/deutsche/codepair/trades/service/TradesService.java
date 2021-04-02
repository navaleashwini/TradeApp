package com.deutsche.codepair.trades.service;

import com.deutsche.codepair.trades.Dao.TradesDao;
import com.deutsche.codepair.trades.Dao.TradesRepo;
import com.deutsche.codepair.trades.pojo.Trades;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TradesService {

	private static final Logger log = LoggerFactory
			.getLogger(TradesService.class);

	@Autowired
	TradesDao tradesDao;

	@Autowired
	TradesRepo tradesRepo;

	public boolean isValid(Trades trades) {
		if (checkMaturityDate(trades)) {

			Optional<Trades> exsitingTrade = tradesRepo.findById(trades
					.getTradeId());
			if (exsitingTrade.isPresent()) {
				return checkVersion(trades, exsitingTrade.get());
			} else {
				return true;
			}
		}
		return false;
	}

	private boolean checkVersion(Trades trades, Trades oldTrade) {
		// validation 1 During transmission if the
		// lower version is being received by the store it will reject the trade
		// and throw an exception.
		if (trades.getVersion() >= oldTrade.getVersion()) {
			return true;
		}
		return false;
	}

	// 2. Store should not allow the trade which has less maturity date then
	// today date
	private boolean checkMaturityDate(Trades trades) {
		return trades.getMaturityDate().isBefore(LocalDate.now()) ? false
				: true;
	}

	public void persist(Trades trades) {
		trades.setCreatedDate(LocalDate.now());
		tradesRepo.save(trades);
	}

	public List<Trades> findAll() {
		return tradesRepo.findAll();

	}

	public void updateExpiryFlag() {
		tradesRepo.findAll().stream().forEach(t -> {
			if (!checkMaturityDate(t)) {
				t.setExpiredFlag("Y");
				log.info("Trade which need to updated", t);
				tradesRepo.save(t);
			}
		});
	}

}

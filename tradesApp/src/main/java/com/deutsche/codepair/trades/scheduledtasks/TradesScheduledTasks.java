
package com.deutsche.codepair.trades.scheduledtasks;

import com.deutsche.codepair.trades.service.TradesService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TradesScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(TradesScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	TradesService tradesService;

	@Scheduled(cron = "${trade.expiry.schedule}")
	public void showCurrentTime() {
		log.info("Current time is", dateFormat.format(new Date()));
		tradesService.updateExpiryFlag();
	}
}
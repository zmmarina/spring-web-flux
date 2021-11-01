package com.zm.stockquotesapi;

import java.util.Date;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.zm.stockquotesapi.entities.Quote;
import com.zm.stockquotesapi.repositories.QuoteRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@EnableScheduling
@SpringBootApplication
public class StockQuotesApiApplication {
	
	@Autowired
	private QuoteRepository quoteRepository;

	public static void main(String[] args) {SpringApplication.run(StockQuotesApiApplication.class, args);}
		
		@Scheduled(fixedDelay = 1000)
		public void generateData() {
			log.info(quoteRepository.findFirstBySymbolOrderByTimestampDesc("TEST")
					.map(this:: generateNewData)
					.orElseGet(this::initializeData));
		}
		
		private Quote initializeData() {
			return quoteRepository.save(Quote.builder()
					.symbol("TEST")
					.openValue(0.22222)
					.closeValue(0.22222)
					.timestamp(new Date())
					.build());
		}
		
		private Quote generateNewData(Quote quote) {
			return quoteRepository.save(Quote.builder()
					.symbol("TEST")
					.openValue(quote.getOpenValue() * new RandomDataGenerator().nextUniform(-0.10, 0.10))
					.closeValue(quote.getCloseValue() * new RandomDataGenerator().nextUniform(-0.10, 0.10))
					.timestamp(new Date())
					.build());
		}
		
	}



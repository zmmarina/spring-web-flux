package com.zm.stockquotesapi.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.zm.stockquotesapi.entities.Quote;

@Repository
public interface QuoteRepository extends PagingAndSortingRepository<Quote, Long> {	
	
	@RestResource(rel = "quotes", path = "quotes")
	Page<Quote> findAllBySymbol(@Param("symbol") String symbol, Pageable page);

	Optional<Quote> findFirstBySymbolOrderByTimestampDesc(String string);
}

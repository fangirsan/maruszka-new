package com.maruszka.bootstrap;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.maruszka.model.Batch;
import com.maruszka.model.BeerType;
import com.maruszka.model.Country;
import com.maruszka.model.Hop;
import com.maruszka.model.Malt;
import com.maruszka.model.Producer;
import com.maruszka.model.Yeast;
import com.maruszka.model.Enums.ProducerType;
import com.maruszka.model.Enums.YeastFlocculation;
import com.maruszka.model.Enums.YeastType;
import com.maruszka.services.BatchService;
import com.maruszka.services.BeerTypeService;
import com.maruszka.services.CountryService;
import com.maruszka.services.HopService;
import com.maruszka.services.MaltService;
import com.maruszka.services.ProducerService;
import com.maruszka.services.YeastService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Transactional
public class DataLoader implements CommandLineRunner{

	private final MaltService maltService;
	private final ProducerService producerService;
	private final CountryService countryService;
	private final HopService hopService;
	private final YeastService yeastService;
	private final BeerTypeService beerTypeService;
	private final BatchService batchService;
	
	public DataLoader(MaltService maltService, ProducerService maltProducerService, CountryService countryService,
			HopService hopService, YeastService yeastService, BeerTypeService beerTypeService, BatchService batchService) {
		this.maltService = maltService;
		this.producerService = maltProducerService;
		this.countryService = countryService;
		this.hopService = hopService;
		this.yeastService = yeastService;
		this.beerTypeService = beerTypeService;
		this.batchService = batchService;
	}

	@Override
	public void run(String... args) throws Exception {

		int maltCount = maltService.findAll().size();
		
		if (maltCount == 0) {
			log.info("Loading data...");
			loadData();
		}
	}

	private void loadData() {
		
		// Country
		Country country = new Country();
		country.setCountryCode("PL");
		country.setCountryName("Poland");
		countryService.save(country);
		
		Country country2 = Country.builder().id(2L).countryCode("DK").countryName("Denmark").build();
		countryService.save(country2);
		
		Country country3 = Country.builder().id(3L).countryCode("USA").countryName("United States of America").build();
		countryService.save(country3);
		log.info("Countries loadaed...");
		
		// Producer
		Producer producer = Producer.builder()
				.id(1L)
				.producerName("Malt Europ")
				.product(ProducerType.Malt)
				.build();
		producerService.save(producer);
		
		Producer producer2 = Producer.builder()
				.id(2L)
				.producerName("Fermentis")
				.product(ProducerType.Hop)
				.build();
		producerService.save(producer2);
		log.info("Producers loadaed...");
		
		// Malt
		Malt malt = Malt.builder()
				.id(1L)
				.maltName("Pale Ale")
				.maltFilling(100)
				.maltEbc(3)
				.maltUsage("All")
				.country(countryService.findByCountryName("Poland"))
				.producer(producerService.findByProducerName("Malt Europ"))
				.build();
		maltService.save(malt);
		
		Malt malt2 = Malt.builder()
				.maltName("Strzegom")
				.maltFilling(100)
				.maltEbc(3)
				.maltUsage("All")
				.country(countryService.findByCountryName("Poland"))
				.producer(producerService.findByProducerName("Malt Europ"))
				.build();
		maltService.save(malt2);
		log.info("Malts loadaed...");
		
		// Hop
		Hop hop = Hop.builder()
				.hopName("Citra")
				.bitteringHop(true)
				.aromaHop(true)
				.alphaAcidMin(new BigDecimal("12.3"))
				.alphaAcidMax(new BigDecimal("15.3"))
				.country(countryService.findByCountryName("United States of America"))
				.build();
		hopService.save(hop);
		log.info("Hops loadaed...");
		
		// Yeast
		Yeast yeast = Yeast.builder()
				.yeastName("US-05")
				.alcoholToleracne(9)
				.flocculation(YeastFlocculation.MEDIUM)
				.fermentationTempMin(15)
				.fermentationTempMax(24)
				.yeastType(YeastType.DRY)
				.producer(producerService.findByProducerName("Fermentis"))
				.build();
		yeastService.save(yeast);
		log.info("Yeasts loadaed...");
		
		// BeerType
		BeerType beerType = BeerType.builder()
				.beerType("Stout")
				.build();
		beerTypeService.save(beerType);
		
		beerType = BeerType.builder()
				.beerType("Russian Imperial Stout")
				.build();
		beerTypeService.save(beerType);
		log.info("BeerType loaded...");
		
		// Batch
		Set<Hop> hops = new HashSet<Hop>();
		hops.add(hopService.findByHopName("Citra"));
		
		Set<Malt> malts = new HashSet<Malt>();
		malts.add(maltService.findByMaltName("Pale Ale"));
		malts.add(maltService.findById(2L));
		
		Batch batch = Batch.builder()
				.batchNumber(1)
				.beerType(beerTypeService.findByBeerType("Stout"))
				.hops(hops)
				.yeast(yeastService.findByYeastName("US-05"))
				.malts(malts)
				.build();
		batchService.save(batch);
		
		batch = Batch.builder()
				.batchNumber(2)
				.beerType(beerTypeService.findById(2L))
				.hops(hops)
				.yeast(yeastService.findByYeastName("US-05"))
				.malts(malts)
				.build();
		batchService.save(batch);
		log.info("Batch loaded...");
		
		log.info("Loading data complete");
		
		Batch beer = batchService.findBatchByBeerType(beerTypeService.findByBeerType("Stout"));
		log.info(beer.toString());
		
	}
	
}
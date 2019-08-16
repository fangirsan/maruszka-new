package com.maruszka.bootstrap;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import com.maruszka.model.enums.YeastFermentationType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.maruszka.model.Batch;
import com.maruszka.model.BeerType;
import com.maruszka.model.Country;
import com.maruszka.model.Hop;
import com.maruszka.model.Malt;
import com.maruszka.model.Producer;
import com.maruszka.model.Yeast;
import com.maruszka.model.enums.ProducerType;
import com.maruszka.model.enums.YeastFlocculation;
import com.maruszka.model.enums.YeastType;
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
class DataLoader implements CommandLineRunner{

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
    public void run(String... args) {

        int maltCount = maltService.findAll().size();

        if (maltCount == 0) {
            log.info("Loading data...");
            loadData();
        }
    }

    public void loadData() {

        // Country
        Country country = new Country();
        country.setCountryCode("PL");
        country.setCountryName("Poland");
        countryService.save(country);

        Country country2 = Country.builder().id(2L).countryCode("DK").countryName("Denmark").build();
        countryService.save(country2);

        Country country3 = Country.builder().id(3L).countryCode("USA").countryName("United States of America").build();
        countryService.save(country3);

        Country country4 = Country.builder().id(4L).countryCode("DE").countryName("Germany").build();
        countryService.save(country4);

        Country unspecified = Country.builder().id(4L).countryCode("N/A").countryName("N/A").build();
        countryService.save(unspecified);

        log.info("Countries loaded...");

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
                .product(ProducerType.Yeast)
                .build();
        producerService.save(producer2);

        Producer nullProducer = Producer.builder()
                .id(3L)
                .producerName("N/A")
                .build();
        producerService.save(nullProducer);
        log.info("Producers loaded...");

        // Malt
        Malt malt = Malt.builder()
                .maltName("Pale Ale")
                .maltFilling(100)
                .maltEbc(3)
                .maltUsage("All")
                .country(countryService.findByCountryName("Poland"))
                .producer(producerService.findByProducerName("Malt Europ"))
                .build();

        Malt malt2 = Malt.builder()
                .maltName("Strzegom")
                .maltFilling(100)
                .maltEbc(3)
                .maltUsage("All")
                .country(countryService.findByCountryName("Poland"))
                .producer(producerService.findByProducerName("Malt Europ"))
                .build();
        maltService.save(malt2);

        Malt malt3 = Malt.builder()
                .maltName("Jęczmień palony")
                .maltFilling(10)
                .maltEbc(1200)
                .maltUsage("Dark beers")
                .country(countryService.findByCountryName("Poland"))
                .producer(producerService.findByProducerName("Malt Europ"))
                .build();
        maltService.save(malt3);
        log.info("Malts loaded...");

        // Hop
        Hop hop = Hop.builder()
                .hopName("Citra")
                .bitterHop(true)
                .aromaHop(true)
                .alphaAcidMin(new BigDecimal("12.3"))
                .alphaAcidMax(new BigDecimal("15.3"))
                .country(countryService.findById(3L))
                .build();
        hopService.save(hop);

        Hop hop2 = Hop.builder()
                .hopName("Magnum")
                .bitterHop(true)
                .aromaHop(false)
                .alphaAcidMin(new BigDecimal("10"))
                .alphaAcidMax(new BigDecimal("15"))
                .country(countryService.findById(1L))
                .build();
        hopService.save(hop2);

        Hop cascade = Hop.builder()
                .hopName("Cascade")
                .bitterHop(false)
                .aromaHop(true)
                .alphaAcidMin(new BigDecimal("5"))
                .alphaAcidMax(new BigDecimal("9"))
                .country(countryService.findById(3L))
                .build();
        hopService.save(cascade);
        log.info("Hops loaded...");

        // Yeast
        Yeast yeast = Yeast.builder()
                .yeastName("SafAle US-05")
                .alcoholTolerance(new BigDecimal(9))
                .flocculation(YeastFlocculation.Medium)
                .fermentationTempMin(new BigDecimal(15))
                .fermentationTempMax(new BigDecimal(24))
                .yeastType(YeastType.Dry)
                .producer(producerService.findByProducerName("Fermentis"))
                .yeastFermentationType(YeastFermentationType.TOP)
                .build();
        yeastService.save(yeast);

        Yeast yeast2 = Yeast.builder()
                .yeastName("SafAle T-58")
                .alcoholTolerance(new BigDecimal(10))
                .flocculation(YeastFlocculation.Medium)
                .fermentationTempMin(new BigDecimal(15))
                .fermentationTempMax(new BigDecimal(20))
                .yeastType(YeastType.Dry)
                .producer(producerService.findByProducerName("Fermentis"))
                .yeastFermentationType(YeastFermentationType.TOP)
                .build();
        yeastService.save(yeast2);

        Yeast nullYeast = Yeast.builder()
                .yeastName("N/A")
                .build();
        yeastService.save(nullYeast);
        log.info("Yeasts loaded...");

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
                .yeast(yeastService.findById(2L))
                .malts(malts)
                .build();
        batch.getMalts().add(malt);
        batchService.save(batch);

        batch = Batch.builder()
                .batchNumber(2)
                .beerType(beerTypeService.findById(2L))
                .hops(hops)
                .yeast(yeastService.findById(1L))
                .malts(malts)
                .build();
        batchService.save(batch);
        log.info("Batch loaded...");

        log.info("Loading data complete");

        Batch beer = batchService.findBatchByBeerType(beerTypeService.findByBeerType("Stout"));
        log.info(beer.toString());

    }

}

package com.maruszka.bootstrap;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import com.maruszka.model.*;
import com.maruszka.model.enums.YeastFermentationType;
import com.maruszka.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.maruszka.model.enums.ProducerType;
import com.maruszka.model.enums.YeastFlocculation;
import com.maruszka.model.enums.YeastType;

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
    private final BeerStyleService beerStyleService;
    private final BatchService batchService;
    private final AdditiveService additiveService;

    public DataLoader(MaltService maltService, ProducerService maltProducerService, CountryService countryService,
                      HopService hopService, YeastService yeastService, BeerStyleService beerStyleService, BatchService batchService, AdditiveService additiveService) {
        this.maltService = maltService;
        this.producerService = maltProducerService;
        this.countryService = countryService;
        this.hopService = hopService;
        this.yeastService = yeastService;
        this.beerStyleService = beerStyleService;
        this.batchService = batchService;
        this.additiveService = additiveService;
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
        BeerStyle ris = BeerStyle.builder()
                .beerStyle("Russian Imperial Stout")
                .originalBLG1(new BigDecimal(18.2))
                .originalBLG2(new BigDecimal(27))
                .finalBLG1(new BigDecimal(4.6))
                .finalBLG2(new BigDecimal(7.6))
                .ebc1(60)
                .ebc2(80)
                .abv1(new BigDecimal(8))
                .abv2(new BigDecimal(12))
                .ibu1(50)
                .ibu2(90)
                .build();
        beerStyleService.save(ris);

        BeerStyle dryStout = BeerStyle.builder()
                .beerStyle("Dry Stout")
//                .originalBLG1(new BigDecimal(9))
//                .originalBLG2(new BigDecimal(12.4))
//                .finalBLG1(new BigDecimal(1.8))
//                .finalBLG2(new BigDecimal(2.8))
//                .ebc(35)
//                .abv(new BigDecimal(4.5))
                .build();
        beerStyleService.save(dryStout);

        BeerStyle nullBeerStyle = BeerStyle.builder()
                .beerStyle("N/A")
                .build();
        beerStyleService.save(nullBeerStyle);
        log.info("BeerType loaded...");

        // Additive
        Additive curacao = Additive.builder()
                .additiveName("Curacao")
                .build();
        additiveService.save(curacao);

        Additive lactose = Additive.builder()
                .additiveName("Lactose")
                .build();
        additiveService.save(lactose);
        log.info("Additives loaded...");

        // Batch
        Set<Hop> hops = new HashSet<Hop>();
        hops.add(hopService.findByHopName("Citra"));

        Set<Malt> malts = new HashSet<Malt>();
        malts.add(maltService.findByMaltName("Pale Ale"));
        malts.add(maltService.findById(2L));

        Set<Additive> additives = new HashSet<Additive>();
        additives.add(additiveService.findByAdditiveName("Curacao"));

        Batch batch1 = Batch.builder()
                .batchNumber(1)
                .beerStyle(beerStyleService.findById(1L))
                .hops(hops)
                .yeast(yeastService.findById(2L))
                .malts(malts)
                .additives(additives)
                .build();
        batch1.getMalts().add(malt);
        batchService.save(batch1);

        Batch batch2 = Batch.builder()
                .batchNumber(2)
                .beerStyle(beerStyleService.findById(2L))
                .hops(hops)
                .yeast(yeastService.findById(1L))
                .malts(malts)
                .additives(additives)
                .build();
        batchService.save(batch2);
        log.info("Batch loaded...");

        log.info("Loading data complete");

        Set<Batch> batches = batchService.findAll();
        for (Batch tempBatch : batches) {
            log.info(tempBatch.toString());
        }


    }

}

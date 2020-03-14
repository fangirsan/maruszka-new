package com.maruszka.bootstrap;

import com.maruszka.dao.BatchDao;
import com.maruszka.model.*;
import com.maruszka.model.association.BatchIngredient;
import com.maruszka.model.enums.ProducerType;
import com.maruszka.model.enums.YeastFermentationType;
import com.maruszka.model.enums.YeastFlocculation;
import com.maruszka.model.enums.YeastType;
import com.maruszka.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;

@Slf4j
@Component
@Transactional
//@Profile("dev")
class DataLoader implements CommandLineRunner{

    private final MaltService maltService;
    private final ProducerService producerService;
    private final CountryService countryService;
    private final HopService hopService;
    private final YeastService yeastService;
    private final BeerStyleService beerStyleService;
    private final BatchService batchService;
    private final AdditiveService additiveService;
    private final BatchIngredientService batchIngredientService;
    private final MashTemperatureService mashTemperatureService;
    private final BatchMashTemperatureService batchMashTemperatureService;
    private final BatchDao batchDao;

    @Autowired
    public DataLoader(MaltService maltService, ProducerService maltProducerService, CountryService countryService,
                      HopService hopService, YeastService yeastService, BeerStyleService beerStyleService,
                      BatchService batchService, AdditiveService additiveService,
                      BatchIngredientService batchIngredientService, MashTemperatureService mashTemperatureService,
                      BatchMashTemperatureService batchMashTemperatureService, BatchDao batchDao) {
        this.maltService = maltService;
        this.producerService = maltProducerService;
        this.countryService = countryService;
        this.hopService = hopService;
        this.yeastService = yeastService;
        this.beerStyleService = beerStyleService;
        this.batchService = batchService;
        this.additiveService = additiveService;
        this.batchIngredientService = batchIngredientService;
        this.mashTemperatureService = mashTemperatureService;
        this.batchMashTemperatureService = batchMashTemperatureService;
        this. batchDao = batchDao;
    }

    @Override
    public void run(String... args) {

        int maltCount = maltService.findAll().size();

        if (maltCount == 0) {
            log.info("Loading initial data...");
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
                .name("Pale Ale")
                .maltFilling(100)
                .maltEbc(3)
                .maltUsage("All")
                .country(countryService.findByCountryName("Poland"))
                .producer(producerService.findByProducerName("Malt Europ"))
                .build();
        maltService.save(malt);

        Malt malt2 = Malt.builder()
                .name("Strzegom")
                .maltFilling(100)
                .maltEbc(3)
                .maltUsage("All")
                .country(countryService.findByCountryName("Poland"))
                .producer(producerService.findByProducerName("Malt Europ"))
                .build();
        maltService.save(malt2);

        Malt malt3 = Malt.builder()
                .name("Jęczmień palony")
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
                .name("Citra")
                .bitterHop(true)
                .aromaHop(true)
                .alphaAcidMin(new BigDecimal("12.3"))
                .alphaAcidMax(new BigDecimal("15.3"))
                .country(countryService.findById(3L))
                .build();
        hopService.save(hop);

        Hop hop2 = Hop.builder()
                .name("Magnum")
                .bitterHop(true)
                .aromaHop(false)
                .alphaAcidMin(new BigDecimal("10"))
                .alphaAcidMax(new BigDecimal("15"))
                .country(countryService.findById(1L))
                .build();
        hopService.save(hop2);

        Hop cascade = Hop.builder()
                .name("Cascade")
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
                .name("SafAle US-05")
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
                .name("SafAle T-58")
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
                .name("N/A")
                .build();
        yeastService.save(nullYeast);
        log.info("Yeasts loaded...");

        // BeerType
        BeerStyle ris = BeerStyle.builder()
                .beerStyleName("Russian Imperial Stout")
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
                .beerStyleName("Dry Stout")
                .originalBLG1(new BigDecimal(9))
                .originalBLG2(new BigDecimal(12.4))
                .finalBLG1(new BigDecimal(1.8))
                .finalBLG2(new BigDecimal(2.8))
                .ebc1(35)
                .ebc2(45)
                .abv1(new BigDecimal(4.5))
                .abv2(new BigDecimal(4.5))
                .ibu1(50)
                .ibu2(70)
                .build();
        beerStyleService.save(dryStout);

        BeerStyle nullBeerStyle = BeerStyle.builder()
                .beerStyleName("N/A")
                .originalBLG1(new BigDecimal(10))
                .originalBLG2(new BigDecimal(10))
                .finalBLG1(new BigDecimal(10))
                .finalBLG2(new BigDecimal(10))
                .ebc1(1)
                .ebc2(1)
                .abv1(new BigDecimal(1))
                .abv2(new BigDecimal(1))
                .ibu1(1)
                .ibu2(1)
                .build();
        beerStyleService.save(nullBeerStyle);
        log.info("BeerType loaded...");

        // Additive
        Additive curacao = Additive.builder()
                .name("Curacao")
                .build();
        additiveService.save(curacao);

        Additive lactose = Additive.builder()
                .name("Lactose")
                .build();
        additiveService.save(lactose);
        log.info("Additives loaded...");

        Additive water = Additive.builder()
                .name("Water")
                .build();
        additiveService.save(water);

        // Malt Conversion Rest
        MashTemperature mcs = MashTemperature.builder()
                .restName("Acid rest")
                .temp1(35)
                .temp2(45)
                .build();
        mashTemperatureService.save(mcs);

        mcs = MashTemperature.builder()
                .restName("Ferulic Acid rest")
                .temp1(43)
                .temp2(45)
                .build();
        mashTemperatureService.save(mcs);

        mcs = MashTemperature.builder()
                .restName("Protein rest")
                .temp1(44)
                .temp2(59)
                .build();
        mashTemperatureService.save(mcs);

        mcs = MashTemperature.builder()
                .restName("Saccharification rest")
                .temp1(61)
                .temp2(71)
                .build();
        mashTemperatureService.save(mcs);

        mcs = MashTemperature.builder()
                .restName("Mashout")
                .temp1(77)
                .temp2(78)
                .build();
        mashTemperatureService.save(mcs);
        log.info("Malt conversion rest loaded...");

        // Batches
        BatchComments batchComments = new BatchComments();
        batchComments.setComment("Test");
        Batch batch1 = Batch.builder()
                .batchNumber(1)
                .creationDate(LocalDate.now())
                .designation("DS")
                .beerStyle(beerStyleService.findByBeerStyleName("Dry Stout"))
                .boilingTime(90)
                .mashingWaterAmount(15)
                .lauteringWaterAmount(15)
                .maturationDate(LocalDate.now().plusDays(5))
                .bottlingDate(LocalDate.now().plusDays(10))
                .blg1(new BigDecimal(20.5))
                .blg2(new BigDecimal(5.5))
                .volume(new BigDecimal(18.5))
                .build();
        batch1.setBatchComments(batchComments);
        batchService.save(batch1);
        batchIngredientService.addIngredient(batch1, maltService.findByName("Strzegom"), 4000, "Whole mash conversion");
        batchIngredientService.addIngredient(batch1, maltService.findByName("Jęczmień palony"), 100, "10 minutes before end of mash conversion");
        batchIngredientService.addIngredient(batch1, hopService.findByName("Citra"), 30, "120 minutes");
        batchIngredientService.addIngredient(batch1, hopService.findByName("Cascade"), 30, "10 minutes");
        batchIngredientService.addIngredient(batch1, additiveService.findByName("Lactose"), 30, "Added at the start of maturing");
        batchIngredientService.addIngredient(batch1, yeastService.findByName("SafAle US-05"), 0, "Re-hydrated");
        batchMashTemperatureService.addMashTemperature(batch1, mashTemperatureService.findByName("Mashout"), 10, 2);
        batchMashTemperatureService.addMashTemperature(batch1, mashTemperatureService.findByName("Saccharification rest"), 45, 1);
//        batchService.calculateEfficiency(batch1);

        Batch batch2 = Batch.builder()
                .batchNumber(2)
                .creationDate(LocalDate.now())
                .beerStyle(beerStyleService.findByBeerStyleName("Russian Imperial Stout"))
                .blg1(new BigDecimal(20.5))
                .blg2(new BigDecimal(5.5))
                .build();
        batchService.save(batch2);
        batchIngredientService.addIngredient(batch2, maltService.findByName("Pale Ale"), 2500, "Whole mash conversion");
        batchIngredientService.addIngredient(batch2, maltService.findByName("Jęczmień palony"), 50, "Whole mash conversion");
        batchMashTemperatureService.addMashTemperature(batch2, mashTemperatureService.findByName("Mashout"), 10, 1);
        log.info("Batch loaded...");

        log.info("Loading data complete");

//        Set<Batch> batches = batchService.findAll();
//        batches.stream().forEach(batch -> log.info(batch.toString()));
//
//        Set<Malt> ingredientSet = batchService.getIngredientSetByClass(batch1, Malt.class);
//        for (Malt malts : ingredientSet) {
//            log.info(malts.getName());
//        }
//
//        HashMap<Malt, Integer> ingredientMap = (HashMap<Malt, Integer>) batchService.getIngredientMapByClass(batch1, Malt.class);
//            for (Map.Entry<Malt, Integer> mapEntry : ingredientMap.entrySet()) {
//                log.info(mapEntry.getKey().getName() + " - " + mapEntry.getValue());
//            }
//
//
//        Set<Batch> batchesByBeerStyle = batchService.findAllByBeerTypeLike(beerStyleService.findByBeerStyleName("Russian Imperial Stout"));
//        batchesByBeerStyle.stream().forEach(batch -> log.info(batch.getBatchNumber().toString()));
//
//        Set<BatchMashTemperature> tempTemperatures = batch1.getMashTemperature();
//        tempTemperatures.stream().forEach(t -> {
//            log.info(t.getMashTemperature().getRestName());
//        });

        Set<BatchIngredient> allByBatchId = batchIngredientService.findAllByBatchId(1L);
        BatchForm batchForm = batchDao.composeBatchForm(1L);

//        Set<BatchIngredient> allBatchIngredientsFtomBatch = batchService.getI
        log.info("end...");
        
    }

}

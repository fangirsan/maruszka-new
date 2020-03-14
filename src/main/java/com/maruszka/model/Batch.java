package com.maruszka.model;

import com.maruszka.constraints.bigDecimal.BigDecimalNotNullConstraint;
import com.maruszka.model.association.BatchIngredient;
import com.maruszka.model.association.BatchMashTemperature;
import com.maruszka.model.enums.LauteringType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "batch")
@Table(name="batch")
public class Batch extends BaseEntity {

    @NotNull
    @Column(name="batch_number")
    private Integer batchNumber;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @Column(name = "fermentation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fermentationDate;

    @Column(name = "maturation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate maturationDate;

    @Column(name = "bottling_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bottlingDate;

    @Column(name = "designation")
    private String designation;

    @OneToOne(cascade = CascadeType.ALL)
    private BatchComments batchComments;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="beer_style_id")
    private BeerStyle beerStyle;

    @Max(20)
    private Integer mashingWaterAmount;

    @Max(20)
    private Integer lauteringWaterAmount;

    @Enumerated(EnumType.STRING)
    private LauteringType lauteringType;

    private Integer boilingTime;

    @DecimalMin(value = "0", inclusive = false)
    @Digits(integer=3, fraction=1)
//    @BigDecimalNotNullConstraint
    @Column(name = "blg1", precision = 3, scale = 1)
    private BigDecimal blg1;

    @DecimalMin(value = "0", inclusive = false)
    @Digits(integer=3, fraction=1)
//    @BigDecimalNotNullConstraint
    @Column(name = "blg2", precision = 3, scale = 1)
    private BigDecimal blg2;

    @DecimalMin(value = "0", inclusive = false)
    @Digits(integer=3, fraction=1)
    @Column(name="volume", precision = 3, scale = 1)
    private BigDecimal volume;

    @Column(name = "efficiency")
    private BigDecimal efficiency;
//    https://www.piwo.org/forums/topic/373-wydajnosc-zacierania/

    @Column(name = "tinseth_ibu")
    private BigDecimal TinsethIbu;

    @Column(name = "rager_ibu")
    private BigDecimal RagerIbu;

    @Column(name = "calculated_ibu")
    private BigDecimal calculatedIbu;

    @Column(name = "alcohol_volume")
    private BigDecimal alcoholVolume;

    @Column(name = "priming_material")
    private String primingMaterial;

    private int amountOfPrimingMaterial;

//    public void setCreationDate(String creationDate) {
//        this.creationDate = LocalDate.parse(creationDate).atStartOfDay();
//    }

    @OneToMany(mappedBy = "batch")
    private Set<BatchIngredient> ingredients = new HashSet<>();

    @OneToMany(mappedBy = "batch")
    private Set<BatchMashTemperature> mashTemperature = new HashSet<>();

    @Builder
    public Batch(Long id, Integer batchNumber, LocalDate creationDate, LocalDate maturationDate, LocalDate bottlingDate, String designation, BatchComments batchComments,
                 BeerStyle beerStyle, Integer boilingTime, Integer mashingWaterAmount, Integer lauteringWaterAmount,
                 BigDecimal blg1, BigDecimal blg2, BigDecimal volume) {
        super(id);
        this.batchNumber = batchNumber;
        this.creationDate = creationDate;
        this.maturationDate = maturationDate;
        this.bottlingDate = bottlingDate;
        this.designation = designation;
        this.batchComments = batchComments;
        this.beerStyle = beerStyle;
        this.boilingTime = boilingTime;
        this.mashingWaterAmount = mashingWaterAmount;
        this.lauteringWaterAmount = lauteringWaterAmount;
        this.blg1= blg1;
        this.blg2 = blg2;
        this.volume = volume;
    }

    public void setBatchComments(BatchComments comments) {
        this.batchComments = comments;
        batchComments.setBatch(this);
    }

}

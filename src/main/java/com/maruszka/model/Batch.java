package com.maruszka.model;

import com.maruszka.model.association.BatchIngredient;
import com.maruszka.model.association.BatchMashTemperature;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "batch")
@Table(name="batch")
public class Batch extends BaseEntity {

    @Column(name="batch_number")
    private Integer batchNumber;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "fermentation_date")
    private LocalDate fermentationDate;

    @Column(name = "maturation_date")
    private LocalDate maturationDate;

    @Column(name = "bottling_date")
    private LocalDate bottlingDate;

    @Column(name = "designation")
    private String designation;

    @OneToOne(cascade = CascadeType.ALL)
    private BatchComments batchComments;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="beer_style_id")
    private BeerStyle beerStyle;

    @Column(name = "blg1")
    private BigDecimal blg1;

    @Column(name = "blg2")
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

    @OneToMany(mappedBy = "batch")
    private Set<BatchIngredient> ingredients = new HashSet<>();

    @OneToMany(mappedBy = "batch")
    private Set<BatchMashTemperature> mashTemperature = new HashSet<>();

    @Builder
    public Batch(Long id, Integer batchNumber, LocalDate creationDate, String designation, BatchComments batchComments, BeerStyle beerStyle) {
        super(id);
        this.batchNumber = batchNumber;
        this.creationDate = creationDate;
        this.designation = designation;
        this.batchComments = batchComments;
        this.beerStyle = beerStyle;
    }

    public void setBatchComments(BatchComments comments) {
        this.batchComments = comments;
        batchComments.setBatch(this);
    }

    private String getIngredientName() {
        String ingredientList = null;
        for (BatchIngredient ing : ingredients) {
            if (ing.getIngredient() instanceof Malt) {
                if (ingredientList == null) {
                    ingredientList = ing.getIngredient().getName();
                } else {
                    ingredientList += ing.getIngredient().getName();
                }
            }
        }
        return ingredientList;
    }
}

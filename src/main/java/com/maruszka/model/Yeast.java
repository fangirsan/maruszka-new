package com.maruszka.model;

import com.maruszka.model.enums.YeastFermentationType;
import com.maruszka.model.enums.YeastFlocculation;
import com.maruszka.model.enums.YeastType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
@Table(name="yeast")
public class Yeast extends Ingredient {

//    @NotBlank
//    @Column(name="yeast_name", unique = true)
//    private String yeastName;

    @Enumerated(EnumType.STRING)
    @Column(name="yeast_type")
    private YeastType yeastType;

    @Column(name="fermentation_temp_min", precision = 3, scale = 1)
    private BigDecimal fermentationTempMin;

    @Column(name="fermentation_temp_max", precision = 3, scale = 1)
    private BigDecimal fermentationTempMax;

    @Column(name="alcohol_tolerance", precision = 2, scale = 0)
    private BigDecimal alcoholTolerance;

    @Enumerated
    @Column(name="flocculation")
    private YeastFlocculation flocculation;

    @Enumerated
    @Column(name="fermentation_type")
    private YeastFermentationType yeastFermentationType;

    @ManyToOne
    @JoinColumn(name="producer_id")
    private Producer producer;

    @Builder
    public Yeast(Long id, String name, YeastType yeastType, BigDecimal fermentationTempMin, BigDecimal fermentationTempMax,
                 BigDecimal alcoholTolerance, YeastFlocculation flocculation, Producer producer, YeastFermentationType yeastFermentationType) {
        super(id);
        this.name = name;
        this.yeastType = yeastType;
        this.fermentationTempMin = fermentationTempMin;
        this.fermentationTempMax = fermentationTempMax;
        this.alcoholTolerance = alcoholTolerance;
        this.flocculation = flocculation;
        this.producer = producer;
        this.yeastFermentationType = yeastFermentationType;
    }


}

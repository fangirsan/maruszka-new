package com.maruszka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.maruszka.model.enums.YeastFermentationType;
import com.maruszka.model.enums.YeastFlocculation;
import com.maruszka.model.enums.YeastType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
@Table(name="yeast")
public class Yeast extends BaseEntity {

    @NotBlank
    @Column(name="yeast_name", unique = true)
    private String yeastName;

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
    public Yeast(Long id, String yeastName, YeastType yeastType, BigDecimal fermentationTempMin, BigDecimal fermentationTempMax,
                 BigDecimal alcoholTolerance, YeastFlocculation flocculation, Producer producer, YeastFermentationType yeastFermentationType) {
        super(id);
        this.yeastName = yeastName;
        this.yeastType = yeastType;
        this.fermentationTempMin = fermentationTempMin;
        this.fermentationTempMax = fermentationTempMax;
        this.alcoholTolerance = alcoholTolerance;
        this.flocculation = flocculation;
        this.producer = producer;
        this.yeastFermentationType = yeastFermentationType;
    }


}

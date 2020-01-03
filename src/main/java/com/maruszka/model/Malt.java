package com.maruszka.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "Malt")
@ToString
//@Table(name="malt")
@DiscriminatorValue("Malt")
public class Malt extends Ingredient {

//    @NotBlank(message="{NotBlank.malt.maltName}")
//    @Column(name="malt_name", unique = true)
//    private String maltName;

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="producer_id")
    private Producer producer;

    @Column(name="malt_filling")
    private int maltFilling;

    @Column(name="malt_ebc")
    private int maltEbc;

    @Column(name="malt_usage")
    private String maltUsage;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="country_id")
    private Country country;

//    @ManyToMany(mappedBy="malts")
//    private Set<Batch> batches;

//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinTable(name="ingredient")
//    private Set<Batch> batches;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "malt")
//    private Batch batch;


    @Builder
//    public Malt(Long id, String maltName, Country country, Producer producer, int maltFilling, int maltEbc, String maltUsage) {
    public Malt(Long id, String maltName, Country country, Producer producer, int maltFilling, int maltEbc, String maltUsage) {
        super(id);
//        this.ingredientType = IngredientType.MALT;
        this.maltName = maltName;
        this.producer = producer;
        this.maltFilling = maltFilling;
        this.maltEbc = maltEbc;
        this.maltUsage = maltUsage;
        this.country = country;
    }

}

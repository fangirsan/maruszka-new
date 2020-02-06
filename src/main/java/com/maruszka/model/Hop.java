package com.maruszka.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
@Table(name="hop")
public class Hop extends Ingredient {

//    @NotBlank
//    @Column(name="hop_name", unique = true)
//    private String hopName;

    @Column(name="alpha_acid_min", precision = 3, scale = 1)
    private BigDecimal alphaAcidMin;

    @Column(name="alpha_acid_max", precision = 3, scale = 1)
    private BigDecimal alphaAcidMax;

    @Column(name="bitter_hop")
    private boolean bitterHop;

    @Column(name="aroma_hop")
    private boolean aromaHop;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="country_id")
    private Country country;

    @Builder
    public Hop(Long id, String name, BigDecimal alphaAcidMin, BigDecimal alphaAcidMax, boolean bitterHop, boolean aromaHop,
               Country country) {
//        super(id)
        this.id = id;
        this.name = name;
        this.alphaAcidMin = alphaAcidMin;
        this.alphaAcidMax = alphaAcidMax;
        this.bitterHop = bitterHop;
        this.aromaHop = aromaHop;
        this.country = country;
    }

}

package com.maruszka.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@Entity
@ToString
@Table(name="batch")
public class Batch extends BaseEntity {

    @Column(name="batch_number")
    private Integer batchNumber;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="beerType")
    private BeerStyle beerStyle;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="yeast_id")
    private Yeast yeast;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "batch_hop",
            joinColumns = @JoinColumn(name = "batch_id"),
            inverseJoinColumns = @JoinColumn(name = "hop_id"))
    private Set<Hop> hops = new HashSet<>();

    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name="batch_malt",
            joinColumns = @JoinColumn(name="batch_id"),
            inverseJoinColumns = @JoinColumn(name="malt_id"))
    private Set<Malt> malts = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name="batch_additive",
            joinColumns = @JoinColumn(name="batch_id"),
            inverseJoinColumns = @JoinColumn(name="additive_id"))
    private Set<Additive> additives = new HashSet<>();

    @Builder
    public Batch(Long id, Integer batchNumber, BeerStyle beerStyle, Yeast yeast, Set<Hop> hops, Set<Malt> malts, Set<Additive> additives) {
        super(id);
        this.batchNumber = batchNumber;
        this.beerStyle = beerStyle;
        this.yeast = yeast;
        this.hops = hops;
        this.malts = malts;
        this.additives = additives;
    }

}

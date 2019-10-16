package com.maruszka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name="ingredients")
public class Ingredients extends BaseEntity{

    private Long batchId;
    private Long maltId;

    @Column(name="amount")
    private int amount;

    @ManyToOne
//    @PrimaryKeyJoinColumn(name="maltId", referencedColumnName="id")
    @JoinColumn(name="maltId", updatable = false, insertable = false, referencedColumnName = "id")
    private Malt malt;

    @ManyToOne
//    @PrimaryKeyJoinColumn(name="batchId", referencedColumnName="id")
    @JoinColumn(name="batchId", updatable = false, insertable = false, referencedColumnName = "id")
    private Batch batch;

    @Column(name = "type")
    private Enum ingredientType;




}

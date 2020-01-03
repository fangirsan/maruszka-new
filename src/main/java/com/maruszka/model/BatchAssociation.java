package com.maruszka.model;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name="batch_ingredient")
@IdClass(BatchAssociationId.class)
public class BatchAssociation {

    @Id
    private Long ingredientId;

    @Id
    private Long batchId;

    @Id
    @Column(name="amount")
    private int amount;

    @Id
    @Column(name = "way_of_serving")
    private String wayOfServing;

    @ManyToOne
//    @PrimaryKeyJoinColumn(name="INGREDIENTID", referencedColumnName = "ID")
    @JoinColumn(name = "ingredientId", updatable = false, insertable = false, referencedColumnName = "id")
//    @JoinColumn(name = "ingredientId", updatable = false, insertable = false)
    private Ingredient ingredient;

    @ManyToOne
//    @PrimaryKeyJoinColumn(name="BATCHID", referencedColumnName = "ID")
    @JoinColumn(name = "batchId", updatable = false, insertable = false, referencedColumnName = "id")
//    @JoinColumn(name = "batchId", updatable = false, insertable = false)
    private Batch batch;

}

package com.maruszka.model.association;

import com.maruszka.model.Batch;
import com.maruszka.model.Ingredient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name="batch_ingredient")
@IdClass(BatchIngredientId.class)
public class BatchIngredient {

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
    @JoinColumn(name = "ingredientId", updatable = false, insertable = false, referencedColumnName = "id")
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "batchId", updatable = false, insertable = false, referencedColumnName = "id")
    private Batch batch;

}

package com.maruszka.model.association;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class BatchIngredientId implements Serializable {

    private Long ingredientId;
    private Long batchId;
    private int amount;
    private String wayOfServing;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchIngredientId that = (BatchIngredientId) o;
        return getAmount() == that.getAmount() &&
                getIngredientId().equals(that.getIngredientId()) &&
                getBatchId().equals(that.getBatchId()) &&
                getWayOfServing().equals(that.getWayOfServing());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIngredientId(), getBatchId(), getAmount(), getWayOfServing());
    }
}

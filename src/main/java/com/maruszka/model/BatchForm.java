package com.maruszka.model;

import com.maruszka.model.association.BatchIngredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BatchForm {

    private Batch batch;
    private BatchIngredient batchIngredient;

}

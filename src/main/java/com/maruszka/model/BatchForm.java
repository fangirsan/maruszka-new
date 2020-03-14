package com.maruszka.model;

import com.maruszka.model.association.BatchIngredient;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class BatchForm {

    private Batch batch;
    private Set<BatchIngredient> batchIngredient;

}

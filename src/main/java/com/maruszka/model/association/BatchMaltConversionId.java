package com.maruszka.model.association;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
public class BatchMaltConversionId implements Serializable {

    private Long maltConversionId;
    private Long batchId;
    private Integer minutes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchMaltConversionId that = (BatchMaltConversionId) o;
        return maltConversionId.equals(that.maltConversionId) &&
                batchId.equals(that.batchId) &&
                minutes.equals(that.minutes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maltConversionId, batchId, minutes);
    }
}

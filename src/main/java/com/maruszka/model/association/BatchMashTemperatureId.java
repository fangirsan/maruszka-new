package com.maruszka.model.association;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
public class BatchMashTemperatureId implements Serializable {

    private Long mashTemperatureId;
    private Long batchId;
    private Integer minutes;
    private Integer sequence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchMashTemperatureId that = (BatchMashTemperatureId) o;
        return mashTemperatureId.equals(that.mashTemperatureId) &&
                batchId.equals(that.batchId) &&
                minutes.equals(that.minutes) &&
                sequence.equals(that.sequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mashTemperatureId, batchId, minutes, sequence);
    }
}

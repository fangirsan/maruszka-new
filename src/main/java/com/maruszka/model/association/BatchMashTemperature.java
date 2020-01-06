package com.maruszka.model.association;

import com.maruszka.model.Batch;
import com.maruszka.model.MashTemperature;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name="batch_mash_temperature")
@IdClass(BatchMashTemperatureId.class)
public class BatchMashTemperature {

    @Id
    private Long mashTemperatureId;

    @Id
    private Long batchId;

    @Id
    @Column
    private Integer minutes;

    @Id
    @Column
    private Integer sequence;

    @ManyToOne
    @JoinColumn(name = "mashTemperatureId", updatable = false, insertable = false, referencedColumnName = "id")
    private MashTemperature mashTemperature;

    @ManyToOne
    @JoinColumn(name = "batchId", updatable = false, insertable = false, referencedColumnName = "id")
    private Batch batch;

}

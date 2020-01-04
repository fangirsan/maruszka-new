package com.maruszka.model.association;

import com.maruszka.model.Batch;
import com.maruszka.model.MaltConversionRest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name="batch_malt_conversion")
@IdClass(BatchMaltConversionId.class)
public class BatchMaltConversionRest {

    @Id
    private Long maltConversionId;

    @Id
    private Long batchId;

    @Id
    @Column
    private Integer minutes;

//    @ManyToOne
//    @JoinColumn(name = "maltConversionRestId", updatable = false, insertable = false, referencedColumnName = "id")
//    private MaltConversionRest maltConversionRest;

    @ManyToOne
    @JoinColumn(name = "batchId", updatable = false, insertable = false, referencedColumnName = "id")
    private Batch batch;

}

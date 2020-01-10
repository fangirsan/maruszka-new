package com.maruszka.model;

import com.maruszka.model.enums.ProducerType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@Entity
//@ToString
@Table(name="producer")
public class Producer extends BaseEntity{

    @NotBlank(message="{NotBlank.producer.producerName}")
    @Column(name="producer_name")
    private String producerName;

    @Enumerated(EnumType.STRING)
    @Column(name="product")
    private ProducerType product;

    @Builder
    public Producer(Long id, String producerName, ProducerType product) {
        super(id);
        this.producerName = producerName;
        this.product = product;
    }

    public String toString() {
        return producerName;
    }

}

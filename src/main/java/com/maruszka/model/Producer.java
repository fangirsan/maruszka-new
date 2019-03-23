package com.maruszka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.maruszka.model.Enums.ProducerType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
@Table(name="producer")
public class Producer extends BaseEntity{

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
	
	
}

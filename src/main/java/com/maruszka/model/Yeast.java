package com.maruszka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.maruszka.model.Enums.YeastFlocculation;
import com.maruszka.model.Enums.YeastType;

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
@Table(name="yeast")
public class Yeast extends BaseEntity {

	@Column(name="yeast_name")
	private String yeastName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="yeast_type")
	private YeastType yeastType; 
	
	@Column(name="fermentation_temp_min")
	private Integer fermentationTempMin;
	
	@Column(name="fermentation_temp_max")
	private Integer fermentationTempMax;
	
	@Column(name="alcohol_tolerance")
	private Integer alcoholToleracne;
	
	@Enumerated
	@Column(name="flocculation")
	private YeastFlocculation flocculation;
	
	@ManyToOne
	@JoinColumn(name="producer_id")
	private Producer producer;

	@Builder
	public Yeast(Long id, String yeastName, YeastType yeastType, Integer fermentationTempMin, Integer fermentationTempMax,
			Integer alcoholToleracne, YeastFlocculation flocculation, Producer producer) {
		super(id);
		this.yeastName = yeastName;
		this.yeastType = yeastType;
		this.fermentationTempMin = fermentationTempMin;
		this.fermentationTempMax = fermentationTempMax;
		this.alcoholToleracne = alcoholToleracne;
		this.flocculation = flocculation;
		this.producer = producer;
	}
	
	
}

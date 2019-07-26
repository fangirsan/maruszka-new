package com.maruszka.model;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
@Table(name="malt")
public class Malt  extends BaseEntity{

	@NotBlank(message="{NotBlank.malt.maltName}")
	@Column(name="malt_name", unique = true)
	private String maltName;

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="producer_id")
	private Producer producer;

	@Column(name="malt_filling")
	private int maltFilling;

//	@NotNull
//	@Min(1)
//	@Max(2000)
	@Column(name="malt_ebc")
	private int maltEbc;

	@Column(name="malt_usage")
	private String maltUsage;

//	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="country_id")
	private Country country;

	@ManyToMany(mappedBy="malts")
	private Set<Batch> batches;

	
	@Builder
	public Malt(Long id, String maltName, Country country, Producer producer, int maltFilling, int maltEbc, String maltUsage) {
		super(id);
		this.maltName = maltName;
		this.producer = producer;
		this.maltFilling = maltFilling;
		this.maltEbc = maltEbc;
		this.maltUsage = maltUsage;
		this.country = country;
	}


//	@Override
//	public String toString() {
//		return "Malt [maltName=" + maltName + ", producer=" + producer + ", maltFilling=" + maltFilling + ", maltEbc="
//				+ maltEbc + ", maltUsage=" + maltUsage + ", country=" + country + ", batches=" + batches + "]";
//	}

	
}

package com.maruszka.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name="malt")
public class Malt extends BaseEntity {

	@Column(name="malt_name")
	private String maltName;

	@ManyToOne(fetch=FetchType.EAGER,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="producer_id")
	private Producer producer;

	@Column(name="malt_filling")
	private int maltFilling;

	@Column(name="malt_ebc")
	private int maltEbc;

	@Column(name="malt_usage")
	private String maltUsage;

	@ManyToOne(fetch=FetchType.EAGER,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="country_id")
	private Country country;

	@ManyToMany(mappedBy="malts")
	private Set<Batch> batches;

	@Builder
	public Malt(Long id, String maltName, String producerName, Country country, Producer producer, int maltFilling, int maltEbc, String maltUsage) {
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

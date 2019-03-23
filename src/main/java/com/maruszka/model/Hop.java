package com.maruszka.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="hop")
public class Hop extends BaseEntity{

	@Column(name="hop_name")
	private String hopName;
	
	@Column(name="alpha_acid_min")
	private BigDecimal alphaAcidMin;
	
	@Column(name="alpha_acid_max")
	private BigDecimal alphaAcidMax;
	
	@Column(name="bittering_hop")
	private boolean bitteringHop;
	
	@Column(name="aroma_hop")
	private boolean aromaHop;
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;
	
	@ManyToMany(mappedBy="hops")
	private Set<Batch> batches;

	@Builder
	public Hop(Long id, String hopName, BigDecimal alphaAcidMin, BigDecimal alphaAcidMax, boolean bitteringHop, boolean aromaHop,
			Country country) {
		super(id);
		this.hopName = hopName;
		this.alphaAcidMin = alphaAcidMin;
		this.alphaAcidMax = alphaAcidMax;
		this.bitteringHop = bitteringHop;
		this.aromaHop = aromaHop;
		this.country = country;
	}
	
}

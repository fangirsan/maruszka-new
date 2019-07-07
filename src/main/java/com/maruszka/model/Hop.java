package com.maruszka.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
@Table(name="hop")
public class Hop extends BaseEntity{

	@NotBlank
	@Column(name="hop_name", unique = true)
	private String hopName;
	
	@Column(name="alpha_acid_min", precision = 3, scale = 1)
	private BigDecimal alphaAcidMin;
	
	@Column(name="alpha_acid_max", precision = 3, scale = 1)
	private BigDecimal alphaAcidMax;
	
	@Column(name="bitter_hop")
	private boolean bitterHop;
	
	@Column(name="aroma_hop")
	private boolean aromaHop;
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;
	
	@ManyToMany(mappedBy="hops")
	private Set<Batch> batches;

	@Builder
	public Hop(Long id, String hopName, BigDecimal alphaAcidMin, BigDecimal alphaAcidMax, boolean bitterHop, boolean aromaHop,
			Country country) {
		super(id);
		this.hopName = hopName;
		this.alphaAcidMin = alphaAcidMin;
		this.alphaAcidMax = alphaAcidMax;
		this.bitterHop = bitterHop;
		this.aromaHop = aromaHop;
		this.country = country;
	}

}

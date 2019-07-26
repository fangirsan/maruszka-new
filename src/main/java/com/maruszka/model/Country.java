package com.maruszka.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Builder
//@ToString
@Table(name="country")
public class Country extends BaseEntity {

	@NotBlank
	@Column(name="country_name", unique = true)
	private String countryName;
	
	@Column(name="country_code")
	private String countryCode;

	@Builder
	public Country(Long id, String countryName, String countryCode) {
		super(id);
		this.countryName = countryName;
		this.countryCode = countryCode;
	}
	
	public String toString( ) {
		return countryName;
	}
	
}

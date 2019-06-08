package com.maruszka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Builder
//@ToString
@Table(name="country")
public class Country extends BaseEntity {

	@Column(name="country_name")
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

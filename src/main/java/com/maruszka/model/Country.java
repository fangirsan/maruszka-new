package com.maruszka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@Builder
@Entity
@ToString
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
	
	
}

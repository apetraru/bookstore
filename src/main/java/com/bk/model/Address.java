package com.bk.model;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author ph
 */
@Entity
public class Address extends AbstractEntity implements Serializable {

	private String street;
	private String city;
	private String country;

	public Address(String street, String city, String country) {
		this.street = street;
		this.city = city;
		this.country = country;
	}

	protected Address() {
	}

	public Address getCopy() {
		return new Address(this.street, this.city, this.country);
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}
}

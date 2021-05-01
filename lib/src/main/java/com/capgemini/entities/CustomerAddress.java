package com.capgemini.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component /* it will detect our custom beans */
@Scope(scopeName = "prototype") /* keeping this as prototype */
@Entity /* Creating table */
@Table(name = "CUSTOMERADDRESS") /* Specifying table name */
public class CustomerAddress {

	@Id /* defining primary key */
	@GeneratedValue(strategy = GenerationType.IDENTITY) /* generating value automatically */
	@Column(name = "ADDRESS_ID", length = 10) /* specifying column name */
	private int addressId;

	/*
	 * specifying column name, giving length and giving constraint as not null
	 */
	@Column(name = "CITY", length = 25, nullable = false)
	private String city;

	/*
	 * specifying column name, giving length and giving constraint as not null
	 */
	@Column(name = "AREA", length = 25, nullable = false)
	private String area;

	/*
	 * specifying column name, giving length and giving constraint as not null
	 */
	@Column(name = "STATE", length = 25, nullable = false)
	private String state;

	/*
	 * specifying column name, giving length and giving constraint as not null
	 */
	@Column(name = "PINCODE", length = 6, nullable = false)
	private long pincode;

	/* creating parameterized constructor */
	public CustomerAddress(int addressId, String city, String state, long pincode, String area) {
		super();
		this.addressId = addressId;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.area = area;
	}

	/* creating default constructor */
	public CustomerAddress() {

	}

	/* creating getters and setters */
	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getPincode() {
		return pincode;
	}

	public void setPincode(long pincode) {
		this.pincode = pincode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	/* creating tostring method */
	@Override
	public String toString() {
		return "CustomerAddress [addressId=" + addressId + ", city=" + city + ", area=" + area + ", state=" + state
				+ ", pincode=" + pincode +  "]";
	}

}
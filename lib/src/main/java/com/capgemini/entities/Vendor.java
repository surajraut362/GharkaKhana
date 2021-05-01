package com.capgemini.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component /* it will detect our custom beans */
@Scope(scopeName = "prototype") /* keeping this as prototype */
@Entity /* Creating table */
@Table(name = "Vendor") /* specifying name of table */
public class Vendor {
	@Id /* defining primary key */
	@GeneratedValue(strategy = GenerationType.IDENTITY) /* generating value automatically */
	@Column(name = "Vendor_Id") /* specifying column name */
	private int vendorId;

	/*
	 * specifying column name, giving length and giving constraint as not null
	 */
	@Column(name = "Vendor_Name", length = 25, nullable = false)
	private String vendorName;

	/*
	 * specifying column name, giving length and giving constraint as not null
	 */
	@Column(name = "Vendor_Contact", length = 10, nullable = false)
	private long vendorContact;

	/*
	 * specifying column name, giving length and giving constraint as not null
	 */
	@Column(name = "Vendor_Username", length = 25, nullable = false)
	private String vendorUsername;

	/*
	 * specifying column name, giving length and giving constraint as not null
	 */
	@Column(name = "Vendor_Password", length = 10, nullable = false)
	private String vendorPassword;

	/* setting up dependency */
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Menu_Id") // assigning foreign key
	private Menu foodMenu;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "VENDOR_ADDRESS_ID")
	private VendorAddress vendorAddress;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "vendor")
	private List<Order> vendorOrder;

	
	public List<Order> getVendorOrder() {
		return vendorOrder;
	}

	public void setVendorOrder(List<Order> vendorOrder) {
		this.vendorOrder = vendorOrder;
	}

	public void setVendorPassword(String vendorPassword) {
		this.vendorPassword = vendorPassword;
	}

	/* creating getters and setters */
	public Menu getFoodMenu() {
		return foodMenu;
	}

	public void setFoodMenu(Menu foodMenu) {
		this.foodMenu = foodMenu;
	}

	public VendorAddress getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(VendorAddress vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	/* creating parameterized constructor */
	public Vendor(String vendorName, long vendorContact, String vendorUsername, String vendorPassword, Menu foodMenu,
			VendorAddress vendorAddress, List<Order> vendorOrder) {
		super();
		this.vendorName = vendorName;
		this.vendorContact = vendorContact;
		this.vendorUsername = vendorUsername;
		this.vendorPassword = vendorPassword;
		this.foodMenu = foodMenu;
		this.vendorAddress = vendorAddress;
		this.vendorOrder = vendorOrder;
		
	}

	/* creating default constructor */
	public Vendor() {

	}

	/* creating getters and setters */
	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public long getVendorContact() {
		return vendorContact;
	}

	public void setVendorContact(long vendorContact) {
		this.vendorContact = vendorContact;
	}

	public String getVendorUsername() {
		return vendorUsername;
	}

	public void setVendorUsername(String vendorUsername) {
		this.vendorUsername = vendorUsername;
	}

	public String getVendorPassword() {
		return vendorPassword;
	}

	public void setVedorPassword(String vendorPassword) {
		this.vendorPassword = vendorPassword;
	}

	/* creating to string method */
	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", vendorName=" + vendorName + ", vendorContact=" + vendorContact
				+ ", vendorUsername=" + vendorUsername + ", vendorPassword=" + vendorPassword + ", foodMenu=" + foodMenu
				+ ", vendorAddress=" + vendorAddress + ", vendorOrder=" + vendorOrder +   "]";
	}

}
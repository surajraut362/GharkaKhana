package com.capgemini.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component /* it will detect our custom beans */
@Scope(scopeName = "prototype") /* keeping this as prototype */
@Entity /* Creating table */
@Table(name = "Menu") /* Specifying table name */
public class Menu {

	@Id
	@Column(name = "MENU_ID")
	private int menuId;

	@OneToOne(cascade = CascadeType.ALL)
	private Vendor vendor;
	
	@OneToMany(mappedBy = "menu")
	private List<FoodItem> foodItem;
	
	/* creating constructor */
	public Menu() {

	}

	/* creating getters and setters */
	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public List<FoodItem> getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(List<FoodItem> foodItem) {
		this.foodItem = foodItem;
	}
	

}
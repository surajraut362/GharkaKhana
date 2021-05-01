package com.capgemini.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class FoodItem {
	 /* defining primary key */
		@Column(name = "FOOD_ID") /* specifying column name */
		private int foodId;

		/*
		 * specifying column name, giving length and giving constraint as not null
		 */
		@Column(name = "Food_Name", length = 25, nullable = false)
		private String foodName;
		
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name="MENU_Id")
		private Menu menu;
		
		@ManyToMany(mappedBy = "foodItems")
		public List<Order> orders;
		/*
		 * specifying column name, giving length and giving constraint as not null
		 */
		@Column(name = "Food_Price", length = 25, nullable = false)
		private double foodPrice;

		public FoodItem(int foodId, String foodName, double foodPrice) {
			super();
			this.foodId = foodId;
			this.foodName = foodName;
			this.foodPrice = foodPrice;
		}

		public int getFoodId() {
			return foodId;
		}

		public void setFoodId(int foodId) {
			this.foodId = foodId;
		}

		public String getFoodName() {
			return foodName;
		}

		public void setFoodName(String foodName) {
			this.foodName = foodName;
		}

		public double getFoodPrice() {
			return foodPrice;
		}

		public void setFoodPrice(double foodPrice) {
			this.foodPrice = foodPrice;
		}

		public Menu getMenu() {
			return menu;
		}

		public void setMenu(Menu menu) {
			this.menu = menu;
		}

		public List<Order> getOrders() {
			return orders;
		}

		public void setOrders(List<Order> orders) {
			this.orders = orders;
		}
}

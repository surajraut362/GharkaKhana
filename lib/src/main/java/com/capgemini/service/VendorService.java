package com.capgemini.service;

import java.util.List;

import com.capgemini.entities.FoodItem;
import com.capgemini.entities.Menu;
import com.capgemini.entities.Order;
import com.capgemini.exceptions.NoSuchFoodItemException;
 
public interface VendorService {
    public FoodItem addFood(FoodItem foodItem,int menuId);
    public FoodItem modifyFood(FoodItem foodItem) throws NoSuchFoodItemException;
    public boolean removeFood(int foodId) throws NoSuchFoodItemException;
    public String setOrderStatus(int orderId);
    public List<FoodItem> viewAllMenu();
//    public Order sendOrderToCustomer(int orderId);
    public void setOrderStatus();
    public List<Order> viewOrder();
    public FoodItem findFoodById(int foodId) throws NoSuchFoodItemException;

}
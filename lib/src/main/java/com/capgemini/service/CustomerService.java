package com.capgemini.service;

 

import java.util.List;

import com.capgemini.entities.Customer;
import com.capgemini.entities.FoodItem;
import com.capgemini.entities.Menu;
import com.capgemini.entities.Order;
import com.capgemini.exceptions.NoSuchCustomerException;
import com.capgemini.exceptions.NoSuchDishException;
import com.capgemini.exceptions.NoSuchOrderException;

 

public interface CustomerService {
    public Customer registerCustomer(Customer customer);
	public Order placeOrder(int customerId, List<FoodItem> foodItems);
    public boolean cancelOrder(int orderId) throws NoSuchOrderException;
    public List<Menu> viewDishesSortByPrice();
    public List<Menu> searchDishes(String foodName) throws NoSuchDishException;
    public Order viewOrderStatus(int orderId) throws NoSuchOrderException, NoSuchCustomerException;
    public List<FoodItem> viewMenu();
    public Order findOrderById(int orderId) throws NoSuchOrderException;
    public Order modifyOrder(int foodId) throws NoSuchOrderException;
    
    
}
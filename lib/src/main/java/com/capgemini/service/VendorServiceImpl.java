package com.capgemini.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Customer;
import com.capgemini.entities.FoodItem;
import com.capgemini.entities.Menu;
import com.capgemini.entities.Order;
import com.capgemini.exceptions.NoSuchFoodItemException;
import com.capgemini.repository.FoodItemRepository;
import com.capgemini.repository.MenuRepository;
import com.capgemini.repository.OrderRepository;
import com.capgemini.repository.VendorRepository;
import com.capgemini.utilities.GlobalResources;

@Service
public class VendorServiceImpl implements VendorService {

	Logger logger = GlobalResources.getLogger(VendorServiceImpl.class);

	/* Creating reference (it creates loosely coupled application) */
	@Autowired
	/* Creating reference (it creates loosely coupled application) */
	private FoodItemRepository foodItemRepository;

	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private VendorRepository vendorRepository;

	@Override
	/* Add Food to Menu by accepting values */
	public FoodItem addFood(FoodItem foodItem, int menuId) {// this method should't be included
		String methodName = "addFood()";
		logger.info(methodName + "called");
		FoodItem result = null;
		if (isValidFoodItem(foodItem))
			logger.info("Valid Menu");
		result = foodItemRepository.save(foodItem);
		return result;
	}

	@Override
	public FoodItem findFoodById(int foodId) throws NoSuchFoodItemException {
		String methodName = "findFoodById()";
		logger.info(methodName + "called");
		try {
			if (isvalidFoodId(foodId)) {
				logger.info("Valid Food Id");
				Optional<FoodItem> foodItem = foodItemRepository.findById(foodId);
				if (foodItem.get() != null)
					return foodItem.get();
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchFoodItemException("FoodItem with id " + foodId + " not found.");
		}

		return null;
	}

	@Override
	/* Modify Food to Menu */
	public FoodItem modifyFood(FoodItem foodItem) throws NoSuchFoodItemException {
		String methodName = "modifyFood()";
		logger.info(methodName + "called");
		FoodItem modifiableFoodItem = foodItemRepository.findById(foodItem.getFoodId()).get();
		if (modifiableFoodItem != null) {
			logger.info("Valid Food");
			modifiableFoodItem.setFoodName(foodItem.getFoodName());
			modifiableFoodItem.setFoodPrice(foodItem.getFoodPrice());
			return foodItemRepository.save(modifiableFoodItem);
		} else {
			throw new NoSuchFoodItemException("FoodItem with id  not found.");

		}
	}

	@Override
	/* Delete Food from Menu */
	public boolean removeFood(int foodId) throws NoSuchFoodItemException {
		String methodName = "removeFood()";
		logger.info(methodName + "called");
		try {
			if (isvalidFoodId(foodId)) {
				logger.info("Valid Food Id");
				FoodItem foodItem = findFoodById(foodId); /* calling method findMenuById */
				if (foodItem != null) {
					foodItemRepository.delete(foodItem);
					return true;
				}
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchFoodItemException(
					"Food with " + foodId + " is not found"); /* Throwing and handling exception */
		}

		return false;
	}

	@Override
	/* View Menu */
	public List<FoodItem> viewAllMenu() {
		String methodName = "viewAllMenu()";
		logger.info(methodName + "called");
		return foodItemRepository.findAll();
	}

	@Override
	/* Set Order Status */
	public void setOrderStatus() {
	}

	@Override
	/* View all Order */
	public List<Order> viewOrder() {
		String methodName = "viewOrder()";
		logger.info(methodName + "called");
		return orderRepository.findAll();
	}

	/*
	 * @Override public Order sendOrderToCustomer(int orderId) { // how to match
	 * order id with cust id Customer customer = new Customer();
	 * customer.getCustomerId(); customer.getFirstName();
	 * customer.getCustomerAddress(); customer.getContactNo(); return null; }
	 */

	@Override
	public String setOrderStatus(int orderId) {
		Optional<Order> order = orderRepository.findById(orderId);
		if (order.get() != null) {
			order.get().setOrderStatus("Completed");
		}
		return "Order with OrderId " + orderId + " Accepted and Successfully delivered to customer";
		// can also // specify //customer name // here } else return "Order not
		// Accepted";

	}

	public boolean isValidFoodItem(FoodItem foodItem) {
		boolean flag = true;
		String price = Double.toString(foodItem.getFoodPrice());
		if (!foodItem.getFoodName().matches("[A-Za-z]+"))
			flag = false;
		else if (!price.matches("\\d"))
			flag = false;
		return flag;
	}

	public boolean isvalidFoodId(int foodId) {
		boolean flag = true;
		if (foodId <= 0)
			flag = false;
		return flag;
	}

}
package com.capgemini.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Customer;
import com.capgemini.entities.FoodItem;
import com.capgemini.entities.Menu;
import com.capgemini.entities.Order;
import com.capgemini.exceptions.NoSuchCustomerException;
import com.capgemini.exceptions.NoSuchDishException;
import com.capgemini.exceptions.NoSuchOrderException;
import com.capgemini.repository.CustomerRepository;
import com.capgemini.repository.FoodItemRepository;
import com.capgemini.repository.MenuRepository;
import com.capgemini.repository.OrderRepository;
import com.capgemini.utilities.GlobalResources;

@Service
public class CustomerServiceImpl implements CustomerService {

	Logger logger = GlobalResources.getLogger(CustomerServiceImpl.class);

	@Autowired
	/* Creating reference (it creates loosely coupled application) */
	private CustomerRepository customerRepository;
	@Autowired
	private FoodItemRepository foodItemRepository;
	@Autowired
	private OrderRepository orderRepository;

	@Override
	/* Customer registers by giving information */
	public Customer registerCustomer(Customer customer) {
		String methodName = "registerCustomer()";
		logger.info(methodName + "called");
		Customer result = null;
		System.out.println(customer);
		if (isValidCustomer(customer)) {
			logger.info("Valid Customer");
			result = customerRepository.save(customer);
		}
		return result;
	}

	/* Place Order by selecting dishes from menu */
	@Override
	public Order placeOrder(int customerId, List<FoodItem> foodItems) {
		String methodName = "placeOrder()";
		logger.info(methodName + "called");
		Customer customer = customerRepository.findById(customerId).get();
		if(customer!=null)
		{
		Order order = new Order();
		order.setCustomer(customer);
		order.setFoodItems(foodItems);
		order.setVendor(foodItems.get(0).getMenu().getVendor());
		return orderRepository.save(order); /* inserting record in vendor table */
		}
		return null;

	}

	/* Cancel order by giving order id */
	@Override
	public boolean cancelOrder(int orderId) throws NoSuchOrderException {
		String methodName = "cancelOrder()";
		logger.info(methodName + "called");
		try {
			if (isvalidOrderId(orderId)) {
				logger.info("Valid Order Id");
				Order order = findOrderById(orderId); /* calling method findOrderById */
				if (order != null) {
					orderRepository.delete(order);
					return true;
				}
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchOrderException(
					"Order with " + orderId + " is not found"); /* Throwing and handling exception */
		}

		return false;
	}

	@Override
	public Order findOrderById(int orderId) throws NoSuchOrderException {
		String methodName = "findOrderById()";
		logger.info(methodName + "called");
		try {
			if (isvalidOrderId(orderId)) {
				logger.info("Valid Order Id");
				Optional<Order> order = orderRepository.findById(orderId); /* selecting order by id */
				if (order.get() != null) {
					return order.get();
				}
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchOrderException(
					"Order with " + orderId + " is not found"); /* Throwing and handling exception */
		}

		return null;
	}

	@Override
	/* View Dishes by Price in Ascending or Descending Order */
	public List<Menu> viewDishesSortByPrice() {
		String methodName = "viewDishesSortByPrice()";
		logger.info(methodName + "called");
		return foodItemRepository.getDishesBySortedAmount();
	}

	@Override
	/* Search Dishes by their names */
	public List<Menu> searchDishes(String foodName) throws NoSuchDishException {
		String methodName = "searchDishes()";
		logger.info(methodName + "called");
		return foodItemRepository.getDishesByName(foodName);
	}

	@Override
	/* View Status of Order by giving order id */
	public Order viewOrderStatus(int orderId) throws NoSuchOrderException, NoSuchCustomerException {
		String methodName = "searchDishes()";
		logger.info(methodName + "called");
		try {
			if (isvalidOrderId(orderId)) {
				logger.info("Valid Order Id");
				Optional<Order> order = orderRepository.findById(orderId); /* checking status by id */
				if (order.get() != null) {
					return order.get();
				}
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchCustomerException(
					"Customer with " + orderId + " is not found"); /* Throwing and handling exception */
		}

		return null;
	}

	@Override
	/* Update Order by selecting from menu */
	public Order modifyOrder(int orderId) throws NoSuchOrderException {
		String methodName = "modifyOrder()";
		logger.info(methodName + "called");
		try {
			if (isvalidOrderId(orderId)) {
				logger.info("Valid Order Id");
				Order order = findOrderById(orderId); /* calling method findVendorById */
				if (order != null) {
					orderRepository.save(order);
					return order;
				}
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchOrderException(
					"Order with " + orderId + " is not found");/* Throwing and handling exception */
		}
		return null;
	}

	@Override
	public List<FoodItem> viewMenu() {
		String methodName = "veiwMenu()";
		logger.info(methodName + "called");
		return foodItemRepository.findAll();
	}

	public boolean isValidCustomer(Customer customer) {
		boolean flag = true;
		String s = Long.toString(customer.getContactNo());
		if (!customer.getFirstName().matches("[A-Za-z]+"))
			flag = false;
		else if (!customer.getLastName().matches("[A-Za-z]+"))
			flag = false;
		else if (!customer.getUserName().matches("[A-Za-z]+"))
			flag = false;
		else if (!customer.getPassword().matches("[A-Za-z]+[@#$%&]"))
			flag = false;
		else if (!s.matches("\\d{10}"))
			flag = false;
		else if (!customer.getEmailId().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"))
			flag = false;
		return flag;
	}

	public boolean isvalidOrderId(int orderId) {
		boolean flag = true;
		if (orderId <= 0)
			flag = false;
		return flag;
	}

	public boolean isValidFoodId(int foodId) {
		if (foodItemRepository.getOne(foodId) != null) {
			return true;
		}
		return false;
	}

}
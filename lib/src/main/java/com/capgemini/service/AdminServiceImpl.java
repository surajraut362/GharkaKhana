package com.capgemini.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Admin;
import com.capgemini.entities.Customer;
import com.capgemini.entities.CustomerAddress;
import com.capgemini.entities.Order;
import com.capgemini.entities.Vendor;
import com.capgemini.entities.VendorAddress;
import com.capgemini.exceptions.NoSuchCustomerException;
import com.capgemini.exceptions.NoSuchOrderException;
import com.capgemini.exceptions.NoSuchVendorException;
import com.capgemini.repository.AdminRepository;
import com.capgemini.repository.CustomerRepository;
import com.capgemini.repository.OrderRepository;
import com.capgemini.repository.VendorRepository;
import com.capgemini.utilities.GlobalResources;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	/* Creating reference (it creates loosely coupled application) */
	private CustomerRepository customerRepository;

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private AdminRepository adminRepository;

	private Logger logger = GlobalResources.getLogger(AdminServiceImpl.class);

	@Override
	/* Adds vendor by accepting vendor object */
	public Vendor addVendor(Vendor vendor) {
		String methodName = "addVendor()";
		logger.info(methodName + "called");
		Vendor result = null;
		if (isValidVendor(vendor)) {
			System.out.println(vendor);
			result = vendorRepository.save(vendor); /* inserting record in vendor table */
		}
		return result;
	}

	@Override
	/* Deletes vendor by accepting Vendor Id */
	public boolean removeVendor(int vendorId) throws NoSuchVendorException {
		String methodName = "removeVendor()";
		logger.info(methodName + "called");

		try {
			if (isvalidVendorId(vendorId)) {
				Vendor vendor = findVendorById(vendorId); /* calling method findVendorById */
				if (vendor != null) {
					vendorRepository.delete(vendor);
					return true;
				}
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchVendorException(
					"Vendor with " + vendorId + " is not found"); /* Throwing and handling exception */
		}

		return false;
	}

	@Override
	/* Updates vendor by accepting vendor id */
	public Vendor modifyVendor(Vendor vendor) throws NoSuchVendorException {
		String methodName = "modifyVendor()";
		logger.info(methodName + "called");
		Vendor result = null;
		if (isValidVendor(vendor))
			result = vendorRepository.save(vendor);
		return result;
	}

	@Override
	/* View Order by sort Amount */
	public List<Order> findSortedOrderByAmount() {
		String methodName = "findSortedOrderByAmount()";
		logger.info(methodName + "called");
		return orderRepository.getOrderBySortedAmount();
	}

	@Override
	/* View Order by sort Date */
	public List<Order> findSortedOrderByDate() {
		String methodName = "findSortedOrderByDate()";
		logger.info(methodName + "called");
		return orderRepository.getOrderBySortedDate();
	}

	@Override
	/* View All Order */
	public List<Order> findAllOrder() {
		String methodName = "findAllOrder()";
		logger.info(methodName + "called");
		return orderRepository.findAll();
	}

	@Override
	public Customer findCustomerById(int customerId) throws NoSuchCustomerException {
		String methodName = "findCustomerById()";
		logger.info(methodName + "called");
		try {
			if (isvalidCustomerId(customerId)) {
				logger.info("valid customer id");
				Optional<Customer> customer = customerRepository.findById(customerId); /* selecting customer by id */
				if (customer.get() != null) {
					return customer.get();
				}
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchCustomerException(
					"Customer with " + customerId + " is not found"); /* Throwing and handling exception */
		}

		return null;
	}

	@Override
	public Vendor findVendorById(int vendorId) throws NoSuchVendorException {
		String methodName = "findVendorById()";
		logger.info(methodName + "called");
		try {
			if (isvalidVendorId(vendorId)) {
				logger.info("Valid vendor Id");
				Optional<Vendor> vendor = vendorRepository.findById(vendorId); /* selecting vendor by id */
				if (vendor.get() != null) {
					return vendor.get();
				}
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchVendorException(
					"Vendor with " + vendorId + " is not found"); /* Throwing and handling exception */
		}

		return null;
	}

	@Override
	public boolean removeOrderByAdmin(int orderId) throws NoSuchOrderException {
		String methodName = "removeOrderByAdmin()";
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
	/* Register Admin with their details */
	public Admin registerAdmin(Admin admin) {
		String methodName = "registerAdmin()";
		logger.info(methodName + "called");
		Admin result = null;
		if (isValidAdmin(admin))
			logger.info("Valid Admin");
		System.out.println(admin);
		result = adminRepository.save(admin);
		return result;
	}

	@Override
	/* Find All Registered Admins with their details */
	public List<Admin> findAllAdmins() {
		String methodName = "findAllAdmins()";
		logger.info(methodName + "called");
		return adminRepository.findAll();
	}

	@Override
	public List<Vendor> findAllVendors() {
		String methodName = "findAllVendors()";
		logger.info(methodName + "called");
		return vendorRepository.findAll();
	}

	@Override
	public List<Customer> findAllCustomer() {
		String methodName = "findAllCustomer()";
		logger.info(methodName + "called");
		return customerRepository.findAll();
	}

	/*
	 * @Override public boolean assignOrderToVendor() { VendorAddress vendorAddress
	 * = new VendorAddress(); CustomerAddress customerAddress = new
	 * CustomerAddress(); Order order1 = new Order(); Vendor vendor = new Vendor();
	 * 
	 * if (customerAddress.getArea() == vendorAddress.getArea()) {
	 * vendor.getVendorOrder().setOrderId(order1.getOrderId());
	 * order1.getVendor().setVendorId(vendor.getVendorId()); } return true;
	 * 
	 * }
	 */
	private boolean isValidAdmin(Admin admin) {
		boolean flag = true;
		if (!admin.getAdminName().matches("[A-Za-z]+"))
			flag = false;
		else if (!admin.getAdminUsername().matches("[A-Za-z]+"))
			flag = false;
		else if (!admin.getAdminPassword().matches("[A-Za-z]+[@#$%&]"))
			flag = false;
		return flag;
	}

	public boolean isValidVendor(Vendor vendor) {
		boolean flag = true;
		String s = Long.toString(vendor.getVendorContact());
		if (!vendor.getVendorName().matches("[A-Za-z]+"))
			flag = false;
		else if (!vendor.getVendorUsername().matches("[A-Za-z]+"))
			flag = false;
		else if (!vendor.getVendorPassword().matches("[A-Za-z]+[@#$%&]"))
			flag = false;
		else if (!s.matches("\\d{10}"))
			flag = false;
		return flag;
	}

	public boolean isvalidOrderId(int orderId) {
		boolean flag = true;
		if (orderId <= 0)
			flag = false;
		return flag;
	}

	public boolean isvalidVendorId(int vendorId) {
		boolean flag = true;
		if (vendorId <= 0)
			flag = false;
		return flag;
	}

	public boolean isvalidCustomerId(int customerId) {
		boolean flag = true;
		if (customerId <= 0)
			flag = false;
		return flag;
	}

}
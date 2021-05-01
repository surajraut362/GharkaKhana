package com.capgemini.service;

import java.util.List;

import com.capgemini.entities.Admin;
import com.capgemini.entities.Customer;
import com.capgemini.entities.Order;
import com.capgemini.entities.Vendor;
import com.capgemini.exceptions.NoSuchCustomerException;
import com.capgemini.exceptions.NoSuchOrderException;
import com.capgemini.exceptions.NoSuchVendorException;

public interface AdminService {
	public Vendor addVendor(Vendor vendor); // done

	public boolean removeVendor(int vendorId) throws NoSuchVendorException; // done

	public Vendor modifyVendor(Vendor vendor) throws NoSuchVendorException; // done

	public List<Order> findSortedOrderByAmount();// done

	public List<Order> findSortedOrderByDate();// done

	public List<Order> findAllOrder(); // done

	//public boolean assignOrderToVendor(); // done;

	public Customer findCustomerById(int customerId) throws NoSuchCustomerException; // done

	public Vendor findVendorById(int vendorId) throws NoSuchVendorException; // done

	public boolean removeOrderByAdmin(int orderId) throws NoSuchOrderException;// done

	public Order findOrderById(int orderId) throws NoSuchOrderException; // done

	public Admin registerAdmin(Admin admin); // done

	public List<Admin> findAllAdmins(); // done

	public List<Vendor> findAllVendors(); // done

	public List<Customer> findAllCustomer();// done
}
package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Customer;
import com.capgemini.entities.Menu;
import com.capgemini.entities.Order;
import com.capgemini.exceptions.NoSuchCustomerException;
import com.capgemini.exceptions.NoSuchDishException;
import com.capgemini.exceptions.NoSuchOrderException;
import com.capgemini.service.CustomerService;



@RestController
@RequestMapping(path="customers")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@PostMapping(path="/addCustomer",consumes=MediaType.APPLICATION_JSON_VALUE)
	/* http://localhost:9090/GharKaKhana-api/customers/addCustomer*/
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		System.out.println(customer);
		ResponseEntity<Customer> response= null;
		Customer result = service.registerCustomer(customer);
		if(result != null)
			response = new ResponseEntity<Customer>(result,HttpStatus.CREATED);
		else
			response = new ResponseEntity<Customer>(result,HttpStatus.BAD_REQUEST);
		return response;
	}
	
	//http://localhost:9090/GharKaKhana-api/customers/status/
	@GetMapping(path="status/{orderId}")
    public ResponseEntity<Order> viewOrderStatus(@PathVariable("orderId") int orderId) throws NoSuchOrderException, NoSuchCustomerException{
        ResponseEntity<Order> response = null;
        Order result = service.viewOrderStatus(orderId);
        if(result != null)
            response = new ResponseEntity<Order>(HttpStatus.ACCEPTED);
        else
            response = new ResponseEntity<Order>(HttpStatus.NOT_ACCEPTABLE);
        return response;
    }
	
	@DeleteMapping(path = "/cancel/{orderId}")
    //http://localhost:9090/GharKaKhana-api/customers/cancel/
    public ResponseEntity<Order> cancelOrder(@PathVariable("orderId") int orderId) throws NoSuchOrderException {
        ResponseEntity<Order> response = null;
        boolean result = service.cancelOrder(orderId);
        if (result == true)
            response = new ResponseEntity<Order>(HttpStatus.ACCEPTED);
        else
            response = new ResponseEntity<Order>(HttpStatus.NOT_ACCEPTABLE);
        return response;
    }
	
	@GetMapping(path="/searchDish/{foodName}")
	//http://localhost:9090/GharKaKhana-api/customers/searchDish/
	public ResponseEntity<List<Menu>> searchDishByName(@PathVariable("foodName") String foodName) throws NoSuchDishException{
		ResponseEntity<List<Menu>> response = null;
		List<Menu> result = service.searchDishes(foodName);
		if(result != null)
			response = new ResponseEntity<List<Menu>>(result,HttpStatus.OK);
		else
			response = new ResponseEntity<List<Menu>>(result,HttpStatus.BAD_REQUEST);
		return response;
	}
}

package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>  {

	@Query("SELECT o FROM Order o ORDER BY o.orderDate ASC")
	public List<Order> getOrderBySortedDate();
	
	@Query("SELECT o FROM Order o ORDER BY o.orderPrice ASC")
	public List<Order> getOrderBySortedAmount();
}

package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.entities.FoodItem;
import com.capgemini.entities.Menu;

public interface FoodItemRepository extends JpaRepository<FoodItem,Integer> {

	  @Query("SELECT m FROM FoodItem m ORDER BY m.foodPrice ASC")
	    List<Menu> getDishesBySortedAmount();
	  
	  @Query("SELECT m FROM FoodItem m Where m.foodName =:foodName")
	    List<Menu> getDishesByName(@Param("foodName") String foodName);
}

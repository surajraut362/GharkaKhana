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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.FoodItem;
import com.capgemini.entities.Menu;
import com.capgemini.exceptions.NoSuchFoodItemException;
import com.capgemini.exceptions.NoSuchOrderException;
import com.capgemini.service.VendorService;

@RestController
@RequestMapping(path = "vendors")
public class VendorController {
 
    @Autowired
    private VendorService adminService;

    // http://localhost:9090/GharKaKhana-api/vendors/findFoodId/   -GET
    @GetMapping(path = "findFoodId/{foodId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FoodItem> getFoodById(@PathVariable("foodId") int foodId) throws NoSuchOrderException, NoSuchFoodItemException {
        ResponseEntity<Menu> response = null;
        FoodItem result = adminService.findFoodById(foodId);
        return new ResponseEntity<>(result, HttpStatus.FOUND);
        
    }
    
    // http://localhost:9090/GharKaKhana-api/vendors/add     -POST
    @PostMapping(path = "/add/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FoodItem> addFood(@RequestBody FoodItem foodItem,@PathVariable("menuId") int menuId) {
     
        FoodItem result = adminService.addFood(foodItem,menuId);
        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
       
    }

    // http://localhost:9090/GharKaKhana-api/vendors/viewAllOrders    -GET
    @GetMapping(path="/viewAllOrders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FoodItem>> getAllMenu() {
        ResponseEntity<List<Menu>> response = null;
        List<FoodItem> result = adminService.viewAllMenu();
        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    // http://localhost:9090/GharKaKhana-api/vendors/updateFood/
    @PutMapping(path="updateFood/{foodId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<FoodItem> modifyFood(@RequestBody FoodItem foodItem) throws NoSuchOrderException, NoSuchFoodItemException {
        ResponseEntity<Menu> response = null;
        FoodItem result = adminService.modifyFood(foodItem);
        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    // http://localhost:9090/GharKaKhana-api/vendors/deleteMenu/    -DELETE
    @DeleteMapping(path = "/deleteMenu/{foodId}")
    public ResponseEntity<Boolean> deleteFood(@PathVariable("foodId") int foodId) throws NoSuchFoodItemException {
        ResponseEntity<Boolean> response = null;
        boolean result = adminService.removeFood(foodId);
        if (result)
            response = new ResponseEntity<Boolean>(result, HttpStatus.OK);
        return response;
    }

}
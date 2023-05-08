package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.FoodItem;

public interface FoodRepo extends JpaRepository<FoodItem, Integer> {
	@Query("Select f from FoodItem f where f.food_name LIKE %?1%"+"OR f.food_category LIKE %?1%")
public List<FoodItem> findByKeyword(String keyword);

}

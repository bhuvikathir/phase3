package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class FoodItem {
	@Id
	private int fid;
	private String food_name;
	private String food_category;
	private String food_price;
	private String file_path;
}

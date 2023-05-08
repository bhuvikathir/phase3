package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.FoodItem;
import com.example.demo.repo.FoodRepo;

@Service
public class FoodDAO {
@Autowired
FoodRepo repo;

public FoodItem insert(FoodItem u) {
	return repo.save(u);
}
public List<FoodItem> findByKeyword(String keyword){
	return repo.findByKeyword(keyword);
}
public FoodItem update(FoodItem u) {
	FoodItem uu=repo.findById(u.getFid()).orElse(null);
	uu.setFood_name(u.getFood_name());
	uu.setFood_price(u.getFood_price());
	uu.setFood_category(u.getFood_category());
	uu.setFile_path(u.getFile_path());
	return repo.save(uu);
}
public String delete(FoodItem u) {
	repo.delete(u);
	return "Deleted!!";
}
public List<FoodItem> getAllfood(){
	return repo.findAll();
}

}

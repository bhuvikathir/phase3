package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Purchase;
import com.example.demo.repo.PurchaseRepo;

@Service
public class PurchaseDAO {
	@Autowired
	PurchaseRepo repo;
	
	public Purchase insert(Purchase p) {
		return repo.save(p);
	}
	
	public List<Purchase> getall(){
		return repo.findAll();
	}
}

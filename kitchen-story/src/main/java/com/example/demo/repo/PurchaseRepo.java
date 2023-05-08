package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Purchase;

public interface PurchaseRepo  extends JpaRepository<Purchase, Integer> {

}

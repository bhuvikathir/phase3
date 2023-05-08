package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Admin;
import com.example.demo.model.BankDetails;
import com.example.demo.service.AdminDAO;
import com.example.demo.service.BankDAO;

@RestController
public class PostmanController {
	@Autowired
	AdminDAO dao;
	@Autowired
	BankDAO bdao;
	
	@PostMapping("/insertadminbypostman")
	public Admin insert(@RequestBody Admin e) {
		return dao.insert(e);
	}
	@PostMapping("/insertbankbypostman")
	public List<BankDetails> insertbank(@RequestBody List<BankDetails> bd){
		return bdao.insertAll(bd);
	}
	
	@PostMapping("/insertalladmin")
	public List<Admin> insertall(@RequestBody List<Admin> ad){
		return dao.insertAll(ad);
	}
	
	@DeleteMapping("/deleteadmin")
	public void deletebyid(Admin a) {
		dao.delete(a);
	}
}

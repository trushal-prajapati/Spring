package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.serviceimplements.Implement;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:5173")
public class Ccontroller {
	@Autowired
	private Implement imp;
	
	@PostMapping("/insert")
	public Customer insert(@RequestBody Customer c) {
		return imp.Insert(c);
	}
	
	@PutMapping("/update")
	public Customer Update(@RequestBody Customer c) {
		return imp.Update(c);
	}
	
	@PutMapping("/update/{id}")
	public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
	    return imp.updateCustomer(id, customer);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		imp.Delete(id);
		return "Record Deleted!";
	}
	
	@GetMapping("/single/{id}")
	public Customer Single(@PathVariable int id) {
		return imp.Single(id);
	}
	
	@GetMapping("/all")
	public List<Customer> all() {
		return imp.all();
	}
	
	
}

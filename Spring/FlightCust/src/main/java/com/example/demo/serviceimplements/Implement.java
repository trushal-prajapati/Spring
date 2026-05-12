package com.example.demo.serviceimplements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Customer;
import com.example.demo.service.service;

@Service
public class Implement {
	
	@Autowired
	private service cService;

	public Customer Insert(Customer c) {
		return cService.save(c);
	}
	public Customer Update(Customer c) {
		return cService.save(c);
	}
	public Customer updateCustomer(int id, Customer updatedCustomer) {

	    Customer existingCustomer = cService.findById(id)
	            .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));

	    existingCustomer.setCname(updatedCustomer.getCname());
	    existingCustomer.setCaddress(updatedCustomer.getCaddress());
	    existingCustomer.setCcity(updatedCustomer.getCcity());

	    return cService.save(existingCustomer);
	}
	public void Delete(int id) {
		cService.deleteById(id);
	}
	public Customer Single(int id) {
		return cService.findById(id).orElse(null);
	}
	public List<Customer> all(){
		List<Customer> lp= new ArrayList<>();
		
		cService.findAll().forEach(lp :: add);
		
		return lp;
	}
}

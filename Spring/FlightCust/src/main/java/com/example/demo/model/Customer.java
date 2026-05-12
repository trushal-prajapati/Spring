package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cust")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int custid;
	private String cname;
	private String caddress;
	private int ccity;

	public Customer(String cname, String caddress, int ccity) {
		this.cname = cname;
		this.caddress = caddress;
		this.ccity = ccity;
	}

	public String getCaddress() {
		return caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	public int getCcity() {
		return ccity;
	}

	public void setCcity(int ccity) {
		this.ccity = ccity;
	}

	public Customer() {}

	public int getCustid() {
		return custid;
	}

	public void setCustid(int custid) {
		this.custid = custid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
	
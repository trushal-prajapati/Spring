package com.example.demo.dTO;

public class CustomerDto {
	
	private int custid;
	private String cname;
	private String caddress;
	private int ccity;
	
	
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

}

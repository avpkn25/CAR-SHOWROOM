package com.klef.ep.services;

import java.util.List;

import javax.ejb.Remote;

import com.klef.ep.models.Customer;


@Remote
public interface CustomerService {
	
	public String addCustomer(Customer customer);
	public String updateCustomer(Customer customer);
	
	public Customer checkcustomerlogin(String email,String password);
	

}

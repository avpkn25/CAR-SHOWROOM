package com.klef.ep.services;

import java.util.List;

import javax.ejb.Remote;

import com.klef.ep.models.Customer;
import com.klef.ep.models.Seller;


@Remote
public interface SellerService {
	
	public String addSeller(Seller seller);
	public String updateSeller(Seller seller);
	
	public Seller checksellerlogin(String email,String password);
	
	public List<Customer> viewallcustomers();
	

}

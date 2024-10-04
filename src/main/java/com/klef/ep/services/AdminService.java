package com.klef.ep.services;

import java.util.List;

import javax.ejb.Remote;

import com.klef.ep.models.Admin;
import com.klef.ep.models.Contact;
import com.klef.ep.models.Customer;
import com.klef.ep.models.Seller;
import com.klef.ep.models.Vehicle;


@Remote
public interface AdminService {
	public Admin checkadminlogin(String username,String password);
	public String addAdmin(Admin admin);
	public List<Admin> viewalladmins();
	public long admincount();
	public String addSeller(Seller seller);
	public String updateSeller(Seller seller);
	public List<Seller> viewallsellers();
	public long sellercount();
	public String deleteSeller(String email);
	
	public List<Customer> viewallcustomers();
	public long customercount();
	
	public long contactcount();
	public String addContact(Contact contact);
	public List<Contact> viewcontactdetails();
	
	void AddVehicle(Vehicle vehicle);
	List<Vehicle> ViewAllVehicles();
}

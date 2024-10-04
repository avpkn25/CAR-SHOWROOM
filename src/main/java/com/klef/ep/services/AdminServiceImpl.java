package com.klef.ep.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.klef.ep.models.Admin;
import com.klef.ep.models.Contact;
import com.klef.ep.models.Customer;
import com.klef.ep.models.Seller;
import com.klef.ep.models.Vehicle;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AdminServiceImpl implements AdminService {

	@Override
	public Admin checkadminlogin(String username, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
	    EntityManager em = emf.createEntityManager();
	    
	    Query qry = em.createQuery("select a from Admin a where a.username=? and a.password=?  ");
	    qry.setParameter(1, username);
	    qry.setParameter(2, password);
	    
	    Admin admin = null;
	    if(qry.getResultList().size()>0)
        {
          admin = (Admin) qry.getSingleResult();
        }
	    em.close();
	    emf.close();
	    
	    return admin;
	}

	@Override
	public String addAdmin(Admin admin) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(admin); 
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return "Admin Added Successfully";
	}

	@Override
	public List<Admin> viewalladmins() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
	    EntityManager em = emf.createEntityManager();
	    
	    Query qry = em.createQuery("select a from Admin a");
	    List<Admin> adminlist = qry.getResultList();
	    
	    em.close();
	    emf.close();
		return adminlist;
	}

	@Override
	public String addSeller(Seller seller) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(seller); 
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return "Seller Added Successfully";
	}

	@Override
	public String updateSeller(Seller seller) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Seller e = em.find(Seller.class, seller.getEmail());
		
		e.setName(seller.getName());
		e.setPassword(seller.getPassword());
		e.setContact(seller.getContact());
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return "Seller Updated Successfully";
	}

	@Override
	public List<Seller> viewallsellers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
	    EntityManager em = emf.createEntityManager();
	    
	    Query qry = em.createQuery("select s from Seller s");
	    List<Seller> sellerlist = qry.getResultList();
	    
	    em.close();
	    emf.close();
		return sellerlist;
	}

	@Override
	public long sellercount() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
	    EntityManager em = emf.createEntityManager();
	    
	    Query qry = em.createQuery("select count(*) from Seller");
	    List list = qry.getResultList();
	    long count =(long) list.get(0);
	    em.close();
	    emf.close();
		return count;
	}

	@Override
	public List<Customer> viewallcustomers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
	    EntityManager em = emf.createEntityManager();
	    
	    Query qry = em.createQuery("select c from Customer c");
	    List<Customer> customerlist = qry.getResultList();
	    
	    em.close();
	    emf.close();
		return customerlist;
	}

	@Override
	public long admincount() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
	    EntityManager em = emf.createEntityManager();
	    
	    Query qry = em.createQuery("select count(*) from Admin");
	    List list = qry.getResultList();
	    long count =(long) list.get(0);
	    em.close();
	    emf.close();
		return count;
	}

	@Override
	public long customercount() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
	    EntityManager em = emf.createEntityManager();
	    
	    Query qry = em.createQuery("select count(*) from Customer");
	    List list = qry.getResultList();
	    long count =(long) list.get(0);
	    em.close();
	    emf.close();
		return count;
	}

	@Override
	public String deleteSeller(String email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Seller s = em.find(Seller.class, email);
		em.remove(s);
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return "Employee Deleted Successfully";
	}

	@Override
	public long contactcount() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
	    EntityManager em = emf.createEntityManager();
	    
	    Query qry = em.createQuery("select count(*) from Contact");
	    List list = qry.getResultList();
	    long count =(long) list.get(0);
	    em.close();
	    emf.close();
		return count;
	}

	@Override
	public List<Contact> viewcontactdetails() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
	    EntityManager em = emf.createEntityManager();
	    
	    Query qry = em.createQuery("select c from Contact c");
	    List<Contact> contactlist = qry.getResultList();
	    
	    em.close();
	    emf.close();
		return contactlist;
	}

	@Override
	public String addContact(Contact contact) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(contact); 
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return "Contact Added Successfully";
	}

	@Override
	public void AddVehicle(Vehicle vehicle) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(vehicle); 
		em.getTransaction().commit();
		
		em.close();
		emf.close();

		
	}

	@Override
	public List<Vehicle> ViewAllVehicles() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
	    EntityManager em = emf.createEntityManager();
	    
	    Query qry = em.createQuery("select v from Vehicle v");
	    List<Vehicle> vehiclelist = qry.getResultList();
	    
	    em.close();
	    emf.close();
		return vehiclelist;
	}

}

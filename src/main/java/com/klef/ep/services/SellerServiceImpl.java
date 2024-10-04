package com.klef.ep.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.klef.ep.models.Customer;
import com.klef.ep.models.Seller;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class SellerServiceImpl implements SellerService {

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
	public Seller checksellerlogin(String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
	    EntityManager em = emf.createEntityManager();
	    
	    Query qry = em.createQuery("select s from Seller s where s.email=? and s.password=?  ");
	    qry.setParameter(1, email);
	    qry.setParameter(2, password);
	    
	    Seller seller = null;
	    if(qry.getResultList().size()>0)
        {
          seller = (Seller) qry.getSingleResult();
        }
	    em.close();
	    emf.close();
	    
	    return seller;
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
	public String addSeller(Seller seller) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(seller); 
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return "Seller Registered Successfully";
	}


}

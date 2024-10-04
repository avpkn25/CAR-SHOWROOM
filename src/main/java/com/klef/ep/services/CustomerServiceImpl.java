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

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CustomerServiceImpl implements CustomerService {

	@Override
	public String updateCustomer(Customer customer) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Customer e = em.find(Customer.class, customer.getEmail());
		
		e.setName(customer.getName());
		e.setPassword(customer.getPassword());
		e.setContact(customer.getContact());
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return "Customer Updated Successfully";
	}

	@Override
	public Customer checkcustomerlogin(String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
	    EntityManager em = emf.createEntityManager();
	    
	    Query qry = em.createQuery("select c from Customer c where c.email=? and c.password=?  ");
	    qry.setParameter(1, email);
	    qry.setParameter(2, password);
	    
	    Customer customer = null;
	    if(qry.getResultList().size()>0)
        {
          customer = (Customer) qry.getSingleResult();
        }
	    em.close();
	    emf.close();
	    
	    return customer;
	}

	@Override
	public String addCustomer(Customer customer) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("csr");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(customer); 
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return "Customer Registered Successfully";
	}

}

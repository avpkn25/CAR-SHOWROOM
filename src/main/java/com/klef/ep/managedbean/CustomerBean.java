package com.klef.ep.managedbean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.klef.ep.models.Admin;
import com.klef.ep.models.Customer;
import com.klef.ep.services.CustomerService;

@ManagedBean(name="custbean",eager=true)
public class CustomerBean {
	
	@EJB(lookup="java:global/CARSHOWROOM/CustomerServiceImpl!com.klef.ep.services.CustomerService")
	CustomerService customerService;
	
	private String name;
	private String gender;
	private String email;
	private String password;
	private String contact;
	private Customer customer;
	
	@PostConstruct
    public void init() {
        loadcustomerdetails(); 
    }
	
	private List<Customer> custlist;
	public List<Customer> getCustlist() {
		return custlist;
	}
	public void setCustlist(List<Customer> custlist) {
		this.custlist = custlist;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public void validatecustomerlogin() throws IOException{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		
		Customer customer = customerService.checkcustomerlogin(email, password);
		
		if(customer!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
			this.customer = customer;
			response.sendRedirect("customerhome.jsf");
		}else {
			this.email = null;
			this.password = null;
			response.sendRedirect("customerloginfail.jsf");
		}
		
	}
	public String add() {
		Customer c = new Customer();
		c.setName(name);
		c.setGender(gender);
		c.setEmail(email);
		c.setPassword(password);
		c.setContact(contact);
		
		customerService.addCustomer(c);
		
		return "customerlogin.jsf";
	}
	public String add1() {
		Customer c = new Customer();
		c.setName(name);
		c.setGender(gender);
		c.setEmail(email);
		c.setPassword(password);
		c.setContact(contact);
		
		customerService.addCustomer(c);
		
		return "addcustomer.jsf?faces-redirect=true";
	}
	public void logout() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        response.sendRedirect("index.jsf?faces-redirect=true");
    }
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void loadcustomerdetails() {
        if (iscustomerLoggedIn()) {
            this.customer = (Customer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("customer");
        }
    }

	private boolean iscustomerLoggedIn() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        return session != null && session.getAttribute("customer") != null;
	}
}

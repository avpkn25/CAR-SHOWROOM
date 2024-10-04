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

import com.klef.ep.models.Seller;
import com.klef.ep.services.SellerService;

@ManagedBean(name="sellerbean",eager=true)
public class SellerBean {
	
	@EJB(lookup="java:global/CARSHOWROOM/SellerServiceImpl!com.klef.ep.services.SellerService")
	SellerService sellerService;
	
	private String name;
	private String gender;
	private String email;
	private String password;
	private String contact;
	private Seller seller;
	
	private List<Seller> sellerlist;
	
	@PostConstruct
    public void init() {
        loadsellerdetails(); 
    }

	public List<Seller> getSellerlist() {
		return sellerlist;
	}
	
	public void setSellerlist(List<Seller> sellerlist) {
		this.sellerlist = sellerlist;
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

	public void validatesellerlogin() throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		
		Seller seller = sellerService.checksellerlogin(email, password);
		
		if (seller != null) {
			HttpSession session = request.getSession();
			session.setAttribute("seller", seller);
			this.seller = seller;
			response.sendRedirect("sellerhome.jsf");
		} else {
			this.email = null;
			this.password = null;
			response.sendRedirect("sellerloginfail.jsf");
		}
	}
	
	public String addSeller() {
		Seller s = new Seller();
		s.setName(name);
		s.setGender(gender);
		s.setEmail(email);
		s.setPassword(password);
		s.setContact(contact);
		
		sellerService.addSeller(s);
		
		return "sellerlogin.jsf";
	}

	public String add() {
		Seller s = new Seller();
		s.setName(name);
		s.setGender(gender);
		s.setEmail(email);
		s.setPassword(password);
		s.setContact(contact);
		
		sellerService.addSeller(s);
		
		return "addseller.jsf?faces-redirect=true";
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

	public Seller getSeller() {
		return seller;
	}
	
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public void loadsellerdetails() {
        if (isSellerLoggedIn()) {
            this.seller = (Seller) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("seller");
        }
    }

	private boolean isSellerLoggedIn() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        return session != null && session.getAttribute("seller") != null;
	}
}

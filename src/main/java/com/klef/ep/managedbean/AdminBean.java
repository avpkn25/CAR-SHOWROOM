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
import com.klef.ep.models.Contact;
import com.klef.ep.models.Customer;
import com.klef.ep.models.Seller;
import com.klef.ep.services.AdminService;

@ManagedBean(name="adminbean",eager=true)
public class AdminBean {
	
	@EJB(lookup="java:global/CARSHOWROOM/AdminServiceImpl!com.klef.ep.services.AdminService")
	AdminService adminService;
	
	private String username;
	private String password;
	private Admin admin;
	
	private List<Admin> adminlist;
	
	private String contactname;
	private String contactemail;
	private String contactmobile;
	private String contactcomments;
	private boolean contactinvalidcheck;
	private List<Seller> sellerlist;
	private List<Customer> customerlist;
	
	@PostConstruct
    public void init() {
        loadadmindetails(); 
    }
	
	public List<Customer> getCustomerlist() {
		return adminService.viewallcustomers();
	}
	public void setCustomerlist(List<Customer> customerlist) {
		this.customerlist = customerlist;
	}
	public List<Seller> getSellerlist() {
		return adminService.viewallsellers();
	}
	public void setSellerlist(List<Seller> sellerlist) {
		this.sellerlist = sellerlist;
	}
	public boolean isContactinvalidcheck() {
		return contactinvalidcheck;
	}
	public void setContactinvalidcheck(boolean contactinvalidcheck) {
		this.contactinvalidcheck = contactinvalidcheck;
	}
	private List<Contact> contactlist;
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public String getContactemail() {
		return contactemail;
	}
	public void setContactemail(String contactemail) {
		this.contactemail = contactemail;
	}
	public String getContactmobile() {
		return contactmobile;
	}
	public void setContactmobile(String contactmobile) {
		this.contactmobile = contactmobile;
	}
	public String getContactcomments() {
		return contactcomments;
	}
	public void setContactcomments(String contactcomments) {
		this.contactcomments = contactcomments;
	}
	public List<Admin> getAdminlist() {
		return adminService.viewalladmins();
	}
	public void setAdminlist(List<Admin> adminlist) {
		this.adminlist = adminlist;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String toString() {
		  return "AdminBean [username=" + username + ", password=" + password + "]";
	}
	public void validateadminlogin() throws IOException{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		
		Admin admin = adminService.checkadminlogin(username, password);
		
		if(admin!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			this.admin = admin;
			response.sendRedirect("adminhome.jsf");
		}else {
			this.username = null;
			this.password = null;
			response.sendRedirect("adminloginfail.jsf");
		}
		
	}
	public long getadmincount() {
		return adminService.admincount();
	}
	public long getsellercount() {
		return adminService.sellercount();
	}
	public long getcustomercount() {
		return adminService.customercount();
	}
	
	public String addAdmin() {
		Admin a = new Admin();
		a.setUsername(username);
		a.setPassword(password);
		
		adminService.addAdmin(a);
		
		return "addadmin.jsf?faces-redirect=true";
	}
	public long getcontactcount() {
		return adminService.contactcount();
	}
	public List<Contact> getContactlist() {
		return adminService.viewcontactdetails();
	}
	public void setContactlist(List<Contact> contactlist) {
		this.contactlist = contactlist;
	}
	public String addContact() {
		Contact c = new Contact();
		c.setName(contactname);
		c.setEmail(contactemail);
		c.setMobile(contactmobile);
		c.setComments(contactcomments);
		
		adminService.addContact(c);
		
		return "index.jsf?faces-redirect=true#contact";
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
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public void loadadmindetails() {
        if (isadminLoggedIn()) {
            this.admin = (Admin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("admin");
        }
    }

	private boolean isadminLoggedIn() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        return session != null && session.getAttribute("admin") != null;
	}
public String deleteseller(String email) {
		
		adminService.deleteSeller(email);
		return "viewallsellers.jsf";
	}
	
}

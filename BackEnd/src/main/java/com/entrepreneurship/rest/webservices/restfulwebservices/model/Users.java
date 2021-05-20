/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/*
 * {@ code Users} class represents properties and behaviours of users objects.
<br>
Each users has id, username and email
<br>
@version 1.0
@author Tenisheva N.I.
 */

@Entity
@Table(name="users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username")
	private String username;	
	
	@Column(name = "email")
	private String email;	
	
	
	 @Transient
	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	 private List<Customer> customers = new ArrayList<Customer>();

	 public List<Customer> getCustomers() {
	     return (List<Customer>) customers;
	 }

	 public void setCustomers(List<Customer> customers) {
	     this.customers = (List<Customer>) customers;
	 }	 
	 
	 @Transient
	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	 private List<Contract> contracts = new ArrayList<Contract>();

	 public List<Contract> getContracts() {
	     return (List<Contract>) contracts;
	 }

	 public void setContracts(List<Contract> contracts) {
	     this.contracts = (List<Contract>) contracts;
	 }
	 
	 @Transient
	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	 private List<Act> acts = new ArrayList<Act>();
	 
	 @Transient
	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	 private List<PaymentOrder> paymentOrders = new ArrayList<PaymentOrder>();
	 
	 @Transient
	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	 private List<FundsReceipt> fundsReceipts = new ArrayList<FundsReceipt>();
	 
	 	 
	
	public List<Act> getActs() {
	     return (List<Act>) acts;
	 }

	 public void setActs(List<Act> acts) {
	     this.acts = (List<Act>) acts;
	 }
	 
	
	public Users() {
		
	}

	public Users(Long id, String username, String email) {
		super();
		this.id = id;
		this.username = username;
        this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PaymentOrder> getPaymentOrders() {
		return paymentOrders;
	}

	public void setPaymentOrders(List<PaymentOrder> paymentOrders) {
		this.paymentOrders = paymentOrders;
	}

	public List<FundsReceipt> getFundsReceipts() {
		return fundsReceipts;
	}

	public void setFundsReceipts(List<FundsReceipt> fundsReceipts) {
		this.fundsReceipts = fundsReceipts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Users))
			return false;
		Users other = (Users) obj;
		return this.id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + "]";
	}
	
	
	
}

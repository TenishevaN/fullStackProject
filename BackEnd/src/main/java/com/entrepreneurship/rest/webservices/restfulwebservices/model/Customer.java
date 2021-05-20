/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/*
 * {@ code Customer} class represents properties and behaviours of customer objects.
<br>
Each customer has id, name, inn, props, {@link Users user} and comment
<br>
@version 1.0
@author Tenisheva N.I.
 */

@Setter
@Getter
@Entity
@Table(name="CUSTOMER")
public class Customer {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	 private Long id;
	 
	 @Column(name = "name")
	 private String name;
	 
	 @Column(name = "inn")
	 private int inn;
	 
	 @Column(name = "comment")
	 private String comment;
	 
	 @Column(name = "props")
	 private String props;
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_username",  nullable = false)
	 private Users user;
	 
	 @Transient
	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	 private List<Contract> contracts = new ArrayList<Contract>();

	 public List<Contract> getContracts() {
	     return (List<Contract>) contracts;
	 }

	 public void setContracts(List<Contract> contracts) {
	     this.contracts = (List<Contract>) contracts;
	 }
	 
	 
	 public Customer() {
		 
	 }	 

	 public Customer(Long id, String name, int inn, String comment, String props, Users user) {
		super();
		this.id = id;
		this.name = name;
		this.inn = inn;
		this.comment = comment;
		this.props = props;
		this.user = user;
	}

	public Users getUser() {
	      return user;
	 }

	 public void setUser(Users user) {
	     this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInn() {
		return inn;
	}

	public void setInn(int inn) {
		this.inn = inn;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getProps() {
		return props;
	}

	public void setProps(String props) {
		this.props = props;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		return this.id == other.id;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", inn=" + inn + "]";
	}
	
	
	 
	 
	 
}

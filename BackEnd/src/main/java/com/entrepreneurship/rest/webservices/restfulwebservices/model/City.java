/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;

/*
 * {@ code City} class represents properties and behaviours of city objects.
<br>
Each city has id, name
<br>
@version 1.0
@author Tenisheva N.I.
 */

@Setter
@Getter
@Entity
@Table(name="CITY")
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;
	
	
	@Transient
	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "city")
	 private List<Contract> contracts = new ArrayList<Contract>();

	 public List<Contract> getContracts() {
	     return (List<Contract>) contracts;
	 }

	 public void setContracts(List<Contract> contracts) {
	     this.contracts = (List<Contract>) contracts;
	 }
	 
	
	public City() {
		
	}

	public City(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof City))
			return false;
		City other = (City) obj;
		return this.id == other.id;
	}

	@Override
	public String toString() {
		return "City [name=" + name + "]";
	}
	
	

	
	
	

}

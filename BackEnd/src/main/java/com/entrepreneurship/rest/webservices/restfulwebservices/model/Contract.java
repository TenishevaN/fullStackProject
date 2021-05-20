/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.enums.TermsOfPayment;
import java.util.*;

/*
 * {@ code Contract} class represents properties and behaviours of contract objects.
<br>
Each contract has id, contract number, data start, data end, rate, {@link TermsOfPayment terms of payment}, comment, {@link City city}, 
{@link Users user}, {@link Customer customer}, {@link Act act} and comment
<br>
@version 1.0
@author Tenisheva N.I.
 */

@Setter
@Getter
@Entity
@Table(name="CONTRACT")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "contract_number")
    private String contract_number;

    @Column(name = "data_start")
    private Date data_start;

    @Column(name = "data_end")
    private Date data_end;

    @Column(name = "rate")
    private double rate;

    @Column(name = "comment")
    private String comment;    
      
    @Column(name = "terms_of_payment")
    @Enumerated(EnumType.STRING)
    private TermsOfPayment terms_of_payment;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_username",  nullable = false)
	private Users user;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_city",  nullable = false)
	private City city;
    
    @ManyToOne(fetch = FetchType.EAGER)
   	@JoinColumn(name = "id_customer",  nullable = false)
   	private Customer customer;     
         

    @Transient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contract")
    private List<Act> acts = new ArrayList<Act>();

    public List<Act> getActs() {
        return (List<Act>) acts;
    }

    public void setActs(List<Act> acts) {
        this.acts = (List<Act>) acts;
    }
    
    public Contract() {
    	
    }
    
   

	public Contract(Long id, String contract_number, Date data_start, Date data_end, double rate, String comment,
			TermsOfPayment terms_of_payment) {
		super();
		this.id = id;
		this.contract_number = contract_number;
		this.data_start = data_start;
		this.data_end = data_end;
		this.rate = rate;
		this.comment = comment;
		this.terms_of_payment = terms_of_payment;		
	}
	
	
	public TermsOfPayment getTerms_of_payment() {
		return terms_of_payment;
	}

	public void setTerms_of_payment(TermsOfPayment terms_of_payment) {
		this.terms_of_payment = terms_of_payment;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContract_number() {
		return contract_number;
	}

	public void setContract_number(String contract_number) {
		this.contract_number = contract_number;
	}

	public Date getData_start() {
		return data_start;
	}

	public void setData_start(Date data_start) {
		this.data_start = data_start;
	}

	public Date getData_end() {
		return data_end;
	}

	public void setData_end(Date data_end) {
		this.data_end = data_end;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Contract))
			return false;
		Contract other = (Contract) obj;
		return this.id == other.id;
	}

	@Override
    public String toString() {
        return "Contract №" + contract_number;
    }

}

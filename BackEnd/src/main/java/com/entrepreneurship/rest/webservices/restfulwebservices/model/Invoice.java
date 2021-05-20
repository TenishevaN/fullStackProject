/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/*
 * {@ code Invoice} class represents properties and behaviours of invoice objects.
<br>
Each invoice has id, invoice number, {@link Contract contract}, creation date, sum, paid feature,
{@link Users user}, {@link Contract contract} and comment
<br>
@version 1.0
@author Tenisheva N.I.
 */

@Setter
@Getter
@Entity
@Table(name="INVOICE")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "invoice_number")
    private int invoice_number;
        
    @Column(name = "date")
    private Date date;

    @Column(name = "paid")
    private boolean paid;
    
    @Column(name = "sum")
    private double sum;

    @Column(name = "comment")
    private String comment;
    
         
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_username",  nullable = false)
	private Users user;
    
    @ManyToOne(fetch = FetchType.EAGER)
   	@JoinColumn(name = "id_contract",  nullable = false)
   	private Contract contract;  
              
    @Transient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice")
    private List<InvoiceTable> invoice_table = new ArrayList<InvoiceTable>();
    
    @Transient
	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice")
	 private List<FundsReceipt> fundsReceipt = new ArrayList<FundsReceipt>();
    
    public List<InvoiceTable> getInvoice_table() {
		return invoice_table;
	}

	public void setInvoice_table(List<InvoiceTable> invoice_table) {
		this.invoice_table = invoice_table;
	}


	public Invoice() {

    }    
	
	public Invoice(Long id, int invoice_number, Date date, boolean paid, double sum, String comment) {
		super();
		this.id = id;
		this.invoice_number = invoice_number;
		this.date = date;
		this.paid = paid;
		this.sum = sum;
		this.comment = comment;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   	public int getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(int invoice_number) {
		this.invoice_number = invoice_number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	
	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	
    public List<FundsReceipt> getFundsReceipt() {
		return fundsReceipt;
	}

	public void setFundsReceipt(List<FundsReceipt> fundsReceipt) {
		this.fundsReceipt = fundsReceipt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Invoice))
			return false;
		Invoice other = (Invoice) obj;
		return this.id == other.id;
	}

	@Override
    public String toString() {
        return "Act №" + invoice_number + " from ";
    }

}

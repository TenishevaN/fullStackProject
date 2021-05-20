/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/*
 * {@ code Funds receipt} class represents properties and behaviours of funds receipt objects.
<br>
Each funds receipt has id, date, {@link Invoice invoice}, sum, 
{@link Users user} and comment
<br>
@version 1.0
@author Tenisheva N.I.
 */


@Setter
@Getter
@Entity
@Table(name="FUNDS_RECEIPT")
public class FundsReceipt {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id;
	    
	    @Column(name = "date")
	    private Date date;

	    @Column(name = "sum")
	    private double sum;

	    @Column(name = "comment")
	    private String comment;
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	  	@JoinColumn(name = "id_username",  nullable = false)
	  	private Users user;
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	   	@JoinColumn(name = "id_invoice",  nullable = false)
	   	private Invoice invoice;

		public FundsReceipt(Long id, Date date, double sum, String comment) {
			super();
			this.id = id;
			this.date = date;
			this.sum = sum;
			this.comment = comment;
		}  
		
		public FundsReceipt() {
			
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public double getSum() {
			return sum;
		}

		public void setSum(double sum) {
			this.sum = sum;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public Invoice getInvoice() {
			return invoice;
		}

		public void setInvoice(Invoice invoice) {
			this.invoice = invoice;
		}
		
		

		public Users getUser() {
			return user;
		}

		public void setUser(Users user) {
			this.user = user;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof FundsReceipt))
				return false;
			FundsReceipt other = (FundsReceipt) obj;
			return this.id == other.id;
		}

		@Override
		public String toString() {
			return "FundsReceipt [date=" + date + ", sum=" + sum + "]";
		}
	    
	    
	

}

/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.enums.PaymentOperation;
import java.util.Date;
import java.util.Objects;

/*
 * {@ code PaymentOrder} class represents properties and behaviours of payment order objects.
<br>
Each payment order has id, creation date, {@link PaymentOperation tax operation}, sum, tax rate, paid feature, to pay
{@link Users user}, description and comment
<br>
@version 1.0
@author Tenisheva N.I.
 */

@Setter
@Getter
@Entity
@Table(name="PAYMENT_ORDER")
public class PaymentOrder {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id;

	    @Column(name = "date")
	    private Date date;
	    
	    @Column(name = "operation")
	    @Enumerated(EnumType.STRING)
	    private PaymentOperation operation;
	    
	    @Column(name = "paid")
	    private boolean paid;
	    
	    @Column(name = "sum")
	    private double sum;
	    
	    @Column(name = "rate")
	    private double rate;
	    
	    @Column(name = "to_pay")
	    private double to_pay;

	    @Column(name = "comment")
	    private String comment;
	    
	    @Column(name = "description")
	    private String description;
	    
	    @ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "id_username",  nullable = false)
		private Users user;
	    
	    public PaymentOrder() {
	    	
	    }

		public PaymentOrder(Long id, Date date, PaymentOperation operation, boolean paid, double sum, double rate,
				double to_pay, String comment, String description) {
			super();
			this.id = id;
			this.date = date;
			this.operation = operation;
			this.paid = paid;
			this.sum = sum;
			this.rate = rate;
			this.to_pay = to_pay;
			this.comment = comment;
			this.description = description;
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

	

		public PaymentOperation getOperation() {
			return operation;
		}

		public void setOperation(PaymentOperation operation) {
			this.operation = operation;
		}

		public boolean isPaid() {
			return paid;
		}

		public void setPaid(boolean paid) {
			this.paid = paid;
		}

		public double getSum() {
			return sum;
		}

		public void setSum(double sum) {
			this.sum = sum;
		}

		public double getRate() {
			return rate;
		}

		public void setRate(double rate) {
			this.rate = rate;
		}

		public double getTo_pay() {
			return to_pay;
		}

		public void setTo_pay(double to_pay) {
			this.to_pay = to_pay;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
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
			if (!(obj instanceof PaymentOrder))
				return false;
			PaymentOrder other = (PaymentOrder) obj;
			return this.id == other.id;
		}

		@Override
		public String toString() {
			return "PaymentOrder [date=" + date + ", operation=" + operation + ", paid=" + paid + ", to_pay=" + to_pay
					+ "]";
		}
		
		

	    
}

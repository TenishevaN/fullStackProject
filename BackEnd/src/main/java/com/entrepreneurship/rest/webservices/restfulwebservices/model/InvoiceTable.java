/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.model;

import com.entrepreneurship.rest.webservices.restfulwebservices.model.enums.MeasureJob;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;
import javax.persistence.*;

/*
 * {@ code InvoiceTable} class represents properties and behaviours of invoice table objects.
<br>
Each row has id, {@link Invoice invoice}, quantity, price, {@link MeasureJob measure}, description
<br>
@version 1.0
@author Tenisheva N.I.
 */

@Setter
@Getter
@Entity
@Table(name="INVOICE_TABLE")
public class InvoiceTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "id_invoice")
    private Long id_invoice;
    
    @Column(name = "quantity")
    private double quantity;

    @Column(name = "price")
    private double price;
    
    @Column(name = "sum")
    private double sum;

    @Column(name = "description")
    private String description;
    
    @Column(name = "measure")
    @Enumerated(EnumType.STRING)
    private MeasureJob measure;
    
     
	public InvoiceTable(Long id, Long id_invoice, double quantity, double price, String description,
			MeasureJob measure, double sum) {
		super();
		this.id = id;
		this.id_invoice = id_invoice;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
		this.measure = measure;
		this.sum = quantity * price;
	}

	public InvoiceTable() {

    }

	public Long getId() {
		return id;
	}

    public void setId(Long id) {
        this.id = id;
    }

	public Long getId_invoice() {
		return id_invoice;
	}

	public void setId_invoice(Long id_invoice) {
		this.id_invoice = id_invoice;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MeasureJob getMeasure() {
		return measure;
	}

	public void setMeasure(MeasureJob measure) {
		this.measure = measure;
	}
	
	public double getSum() {
		return sum;
	}
	
	public void setSum(double sum) {
		this.sum = quantity * price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof InvoiceTable))
			return false;
		InvoiceTable other = (InvoiceTable) obj;
		return this.id == other.id;
	}

	@Override
	public String toString() {
		return "Invoice_table [quantity=" + quantity + ", price=" + price + ", description=" + description + "]";
	}	

}

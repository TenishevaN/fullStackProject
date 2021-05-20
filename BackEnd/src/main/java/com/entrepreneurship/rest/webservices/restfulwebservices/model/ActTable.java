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
 * {@ code ActTable} class represents properties and behaviours of act table objects.
<br>
Each row has id, {@link Act act}, quantity, price, {@link MeasureJob measure}, description
<br>
@version 1.0
@author Tenisheva N.I.
 */

@Setter
@Getter
@Entity
@Table(name = "ACT_TABLE")
public class ActTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "id_act")
	private Long id_act;

	@Column(name = "quantity")
	private double quantity;

	@Column(name = "price")
	private double price;

	@Column(name = "description")
	private String description;

	@Column(name = "measure")
	@Enumerated(EnumType.STRING)
	private MeasureJob measure;

	public Long getId_act() {
		return id_act;
	}

	public void setId_act(Long id_act) {
		this.id_act = id_act;
	}

	public ActTable(Long id, double quantity, double price, String description, MeasureJob measure, Long id_act) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
		this.measure = measure;
		this.id_act = id_act;
	}

	public ActTable() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ActTable))
			return false;
		ActTable other = (ActTable) obj;
		return this.id == other.id;
	}

	@Override
	public String toString() {
		return "Act_table [quantity=" + quantity + ", price=" + price + ", description=" + description + "]";
	}

}

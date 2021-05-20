/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/*
 * {@ code Act} class represents properties and behaviours of act objects.
<br>
Each act has id, act number, {@link Invoice invoice}, creation date, sum, paid feature,
{@link Users user}, {@link Contract contract}, {@link Act act} and comment
<br>
@version 1.0
@author Tenisheva N.I.
 */

@Setter
@Getter
@Entity
@Table(name = "ACT")
public class Act {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "act_number")
	private int act_number;

	@Column(name = "id_invoice")
	private int id_invoice;

	@Column(name = "date")
	private Date date;

	@Column(name = "paid")
	private boolean paid;

	@Column(name = "sum")
	private double sum;

	@Column(name = "comment")
	private String comment;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_username", nullable = false)
	private Users user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_contract", nullable = false)
	private Contract contract;

	@Transient
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "act")
	private List<ActTable> act_table = new ArrayList<ActTable>();

	public List<ActTable> getAct_table() {
		return (List<ActTable>) act_table;
	}

	public void setAct_table(List<ActTable> acts) {
		this.act_table = (List<ActTable>) act_table;
	}

	public Act() {

	}

	public Act(Long id, int act_number, int id_invoice, Date date, boolean paid, double sum, String comment) {
		super();
		this.id = id;
		this.act_number = act_number;
		this.id_invoice = id_invoice;
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

	public int getAct_number() {
		return act_number;
	}

	public void setAct_number(int act_number) {
		this.act_number = act_number;
	}

	public Date getDate() {
		return date;
	}

	public void setData(Date data) {
		this.date = data;
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

	public int getId_invoice() {
		return id_invoice;
	}

	public void setId_invoice(int id_invoice) {
		this.id_invoice = id_invoice;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Act))
			return false;
		Act other = (Act) obj;
		return this.id == other.id;
	}

	@Override
	public String toString() {
		return "Act №" + act_number + " from ";
	}

}

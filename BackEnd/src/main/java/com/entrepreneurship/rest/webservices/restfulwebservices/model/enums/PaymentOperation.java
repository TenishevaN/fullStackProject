/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.model.enums;

import java.math.BigDecimal;

/*
 * {@ code PaymentOperation} class represents properties and behaviours of tax constants.
<br>
Each tax has rate and method  to calculate tax.
ERUs tax has addition value minWage for calculation.
<br>
@version 1.0
@author Tenisheva N.I.
 */
public enum PaymentOperation{
	INCOME_TAX(5), ERUs(22, 6000);
	private double rate;
	private double minWage;

	private PaymentOperation(double rate) {
		this.rate = rate;
	}
	
	private PaymentOperation(double rate, double minWage) {
		this.rate = rate;
		this.minWage = minWage;
	}

	public BigDecimal getTax(double totalSum) {
	
		switch(this) {
		case INCOME_TAX:
			return (!(totalSum == 0) & !(this.rate == 0)) ? (BigDecimal.valueOf(( totalSum * this.rate) / 100)) : BigDecimal.ZERO;
		case ERUs:
			return (!(this.minWage == 0) & !(this.rate == 0))? (BigDecimal.valueOf(( this.minWage * this.rate) / 100)) : BigDecimal.ZERO;			
		}
		
		return BigDecimal.ZERO;
	} 
	
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}


	public double getMinWage() {
		return minWage;
	}


	public void setMinWage(double minWage) {
		this.minWage = minWage;
	}
	
		
}

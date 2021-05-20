/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.serviceConstants;

public enum PathOfPrintTemplate {
	InvoicePathExls("./src/main/resources/templates/templateInvoice.xls");

	private String path;

	private PathOfPrintTemplate(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

}

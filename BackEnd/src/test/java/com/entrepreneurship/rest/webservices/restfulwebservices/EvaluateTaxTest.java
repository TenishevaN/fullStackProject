/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.IObjectFactory;
import org.testng.annotations.ObjectFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.enums.PaymentOperation;
import static com.entrepreneurship.rest.webservices.restfulwebservices.model.enums.PaymentOperation.*;


@PrepareForTest(PaymentOperation.class)
public class EvaluateTaxTest {

	@Mock
	public PaymentOperation paymentOperation;

	@ObjectFactory
	public IObjectFactory getObjectFactory() {
		return new org.powermock.modules.testng.PowerMockObjectFactory();
	}

	@Test
	public void testEvaluateIncomeTaxes() {
		assertEquals(400.00, INCOME_TAX.getTax(8000.00).doubleValue());
		assertEquals(1320.00, ERUs.getTax(0.00).doubleValue());
	}

	@RepeatedTest(10)
	void repeatedTest(TestInfo testInfo) throws InterruptedException {
		assertEquals(400.00, INCOME_TAX.getTax(8000.00).doubleValue());
		assertEquals(1320.00, ERUs.getTax(0.00).doubleValue());

	}

}

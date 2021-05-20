/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.City;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.Invoice;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.InvoiceTable;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.PaymentOrder;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.CityRepository;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.PaymentOrderRepository;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.UserDao;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.InvoiceService;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.InvoiceTableService;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.PaymentOrderService;
import com.entrepreneurship.rest.webservices.restfulwebservices.controller.CityController;
import com.entrepreneurship.rest.webservices.restfulwebservices.controller.ContractController;
import com.entrepreneurship.rest.webservices.restfulwebservices.controller.CustomerController;
import com.entrepreneurship.rest.webservices.restfulwebservices.controller.FundsReceiptController;
import com.entrepreneurship.rest.webservices.restfulwebservices.controller.PaymentOrderController;
import com.entrepreneurship.rest.webservices.restfulwebservices.controller.ActController;
import com.entrepreneurship.rest.webservices.restfulwebservices.controller.ActTableController;
import com.entrepreneurship.rest.webservices.restfulwebservices.controller.InvoiceController;
import com.entrepreneurship.rest.webservices.restfulwebservices.controller.InvoiceTableController;
import static com.entrepreneurship.rest.webservices.restfulwebservices.model.enums.PaymentOperation.*;
import static com.entrepreneurship.rest.webservices.restfulwebservices.serviceConstants.PathOfPrintTemplate.*;
import net.sf.jxls.transformer.XLSTransformer;




@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RestfulWebServicesApplicationTests {
	
	@Autowired
	private CityController cityController;

	@Autowired
	private ContractController contractController;
	
	@Autowired
	private CustomerController customerController;
	
	@Autowired
	private FundsReceiptController fundsReceiptController;
	
	@Autowired
	private PaymentOrderController paymentOrderController;
	
	@Autowired
	private ActController actController;
	
	@Autowired
	private ActTableController actTableController;
	
	@Autowired
	private InvoiceController invoiceController;
	
	@Autowired
	private InvoiceTableController invoiceTableController;

	@Autowired
	private PaymentOrderRepository paymentOrderRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private InvoiceTableService invoiceTableService;
	
	@Autowired
	private PaymentOrderService paymentOrderService;
	
	@Autowired
	private UserDao userDao;
	
	
	@Test
	public void controllerLoads() throws Exception {
		assertThat(actController).isNotNull();
		assertThat(invoiceController).isNotNull();
		assertThat(cityController).isNotNull();
		assertThat(contractController).isNotNull();
		assertThat(customerController).isNotNull();
		assertThat(fundsReceiptController).isNotNull();
		assertThat(paymentOrderController).isNotNull();
		assertThat(actTableController).isNotNull();
		assertThat(invoiceTableController).isNotNull();
	}

	@Test
	public void repositoryLoads() throws Exception {
		List<City> listCity = (List<City>) cityRepository.findAll();
		assertThat(listCity).isNotNull();
	}
	
	@Test
	public void operationOnDB() throws Exception {
		String username = "admin";	
		Date date_now = java.sql.Date.valueOf(LocalDate.now());	
	
		PaymentOrder paymentOrder = new PaymentOrder((long)100, date_now, INCOME_TAX, true, 1.00, 0.1, 1.00, "test", "test");
		paymentOrder.setUser(userDao.findByUsername(username));
		PaymentOrder createdPaymentOrder = paymentOrderService.save(paymentOrder);
		assertThat(createdPaymentOrder.getTo_pay()).isEqualTo(1.00);
		
		List<PaymentOrder> listPaymentOrder = (List<PaymentOrder>) paymentOrderRepository.findListPaymentOrdersByUser(username);
		assertThat(listPaymentOrder).isNotNull();
		
		paymentOrderService.deleteById(createdPaymentOrder.getId());
		
	}
	

	@Test
	public void loadTemplate() throws Exception {
		long id = 1;
		String userName = "nataliia";
		Map<String, Object> beans = new HashMap<String, Object>();
		Invoice invoice = invoiceService.getInvoice(userName, (long) id);
		List<InvoiceTable> invoice_table = invoiceTableService.getInvoiceTableById(id);
		beans.put("invoice", invoice);
		beans.put("invoice_table", invoice_table);
		XLSTransformer transformer = new XLSTransformer();
		try {
			transformer.transformXLS(InvoicePathExls.getPath(), beans, "D:\\Invoice.xls");
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.emailing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

import java.time.temporal.TemporalAdjusters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.UserDao;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.PaymentOrder;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.enums.PaymentOperation;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.PaymentOrderService;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.FundsReceiptService;
import static com.entrepreneurship.rest.webservices.restfulwebservices.model.enums.PaymentOperation.*;

/*
 * SendEmailManager automatically starts verifying tax documents. 
 * If  "Funds receipts" documents are registered for a previous month it checks whether "Payment orders" documents are created for taxes. 
 * If the tax documents are absent it creates new automatically.
 * It also checks whether these documents are paid or not. If they aren't paid it send an email automatically.
@version 1.0
@author Tenisheva N.I.
 */

@SpringBootApplication(scanBasePackages = { "com.entrepreneurship.rest.webservices.restfulwebservices",
		"com.entrepreneurship.rest.webservices.restfulwebservices.service",
		"com.entrepreneurship.rest.webservices.restfulwebservices.dao" })
@EnableJpaRepositories(basePackages = "com.entrepreneurship.rest.webservices.restfulwebservices.repository")
public class SendEmailManager implements CommandLineRunner {
	private boolean incomeTaxCreated;
	private boolean ERUsCreated;

	@Autowired
	private PaymentOrderService paymentOrderService;

	@Autowired
	private MailSenderService senderService;

	@Autowired
	private FundsReceiptService fundsReceiptService;

	@Autowired
	private UserDao userDao;

	private ResourceFormatter formatter;
	private static Map<String, ResourceFormatter> formatters = Map.of("en-GB", new ResourceFormatter(Locale.UK),
			"ru-RU", new ResourceFormatter(Locale.ROOT));

	private static final Logger loggerWarn = LoggerFactory.getLogger("logger.warn");
	private static final Logger loggerInfo = LoggerFactory.getLogger("logger.info");
	private static final Logger loggerError = LoggerFactory.getLogger("logger.error");

	@Override
	public void run(String... args) throws Exception {
		checkTaxPayment();
	}

	public static void main(String[] args) {
		SpringApplication.run(SendEmailManager.class, args);
	}

	private void checkTaxPayment() {

		formatter = formatters.get("en-GB");
		// formatter = formatters.getOrDefault(formatters.get("en-GB"),
		List<PaymentOrder> listPaymentTax = null;
		LocalDate day_today = LocalDate.now();
		LocalDate last_month = day_today.minusMonths(1);
		LocalDate last_month_from = last_month.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate last_month_to = last_month.with(TemporalAdjusters.lastDayOfMonth());
		Date sqlDate_last_month_from = java.sql.Date.valueOf(last_month_from);
		Date sqlDate_last_month_to = java.sql.Date.valueOf(last_month_to);

		try {
			listPaymentTax = paymentOrderService.checkPaymentTax(sqlDate_last_month_from, sqlDate_last_month_to);
			listPaymentTax.stream().filter(item -> !item.isPaid()).forEach(item -> {
				loggerWarn.warn("payment order id: " + item.getId() + " unpaid.");
				senderService.sendSimpleMail(userDao.findByUsername("admin").getEmail(),
						formatter.getText("email_pay_tax_alert"), formatter.formatLetterContent(item));
				if (item.getOperation() == INCOME_TAX)
					incomeTaxCreated = true;
				if (item.getOperation() == ERUs)
					ERUsCreated = true;
			});

		} catch (NullPointerException ne) {
			loggerError.info("Error in checking payment tax");
		}

		if (!incomeTaxCreated) {

			double totalSum = 0;
			try {
				totalSum = fundsReceiptService.getTotalSum(sqlDate_last_month_from, sqlDate_last_month_to);
			} catch (NullPointerException ne) {
				loggerInfo.info("There is no funds receipt documents for previous month");
				return;
			}
			if (totalSum > 0)
				createPaymentOrder(1, totalSum, INCOME_TAX.getRate(), PaymentOperation.INCOME_TAX.getTax(totalSum),
						INCOME_TAX, sqlDate_last_month_to);
		}
		;

		if (!ERUsCreated)
			createPaymentOrder(2, ERUs.getMinWage(), ERUs.getRate(), (PaymentOperation.ERUs.getTax(0)), ERUs,
					sqlDate_last_month_to);

	}

	private void createPaymentOrder(long id, double sum, double rate, BigDecimal to_pay, PaymentOperation operation,
			Date date) {

		PaymentOrder paymentOrder = new PaymentOrder(id, date, operation, false, sum, rate, to_pay.doubleValue(),
				"automatic", "description");

		paymentOrder.setUser(userDao.findByUsername("admin"));
		paymentOrderService.save(paymentOrder);

	}

	private static class ResourceFormatter {

		private Locale locale;
		private ResourceBundle resources;
		private NumberFormat moneyFormat;

		private ResourceFormatter(Locale varlocale) {
			locale = varlocale;
			resources = ResourceBundle.getBundle(
					"com.entrepreneurship.rest.webservices.restfulwebservices.emailing.bundle.resources", locale);
			moneyFormat = NumberFormat.getCurrencyInstance(locale);
		}

		private String formatLetterContent(PaymentOrder paymentOrder) {
			return MessageFormat.format(resources.getString("email_pay_tax"), paymentOrder.getOperation(),
					moneyFormat.format(paymentOrder.getTo_pay()));
		}

		private String getText(String key) {
			return resources.getString(key);
		}

	}

}

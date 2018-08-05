package com.invoice.api.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.invoice.api.constants.ItemCategory;
import com.invoice.api.helper.dto.request.InvoiceBeanDtoRequest;
import com.invoice.api.helper.dto.request.InvoiceBeanDtoRequest.Invoice;
import com.invoice.api.helper.dto.response.InvoiceBeanDtoResponse;

@ContextConfiguration("classpath:application-config-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class InvoiceBeanServiceTest {
	
	@Autowired
	private InvoiceBeanService invoiceBeanService;

	@Test
	public void testSaveInvoice() throws Exception {
		InvoiceBeanDtoRequest request = getInvoiceRequest();
		InvoiceBeanDtoResponse saveInvoice = invoiceBeanService.saveInvoice(request);
		Assert.assertNotNull(saveInvoice);
		Assert.assertEquals(new Double(160.0),saveInvoice.getTotal_tax());
		Assert.assertEquals(new Double(1100.0), saveInvoice.getTotal_price());
		Assert.assertEquals(new Double(1260.0), saveInvoice.getSelling_price());
		System.out.println(saveInvoice);
	}
	
	private InvoiceBeanDtoRequest getInvoiceRequest() {
		InvoiceBeanDtoRequest request = new InvoiceBeanDtoRequest();
		Set<Invoice> invoice_items =getInvoiceItems();
		request.setInvoiceItem(invoice_items);
		return request;
	}
	
	private Set<Invoice> getInvoiceItems() {
		Set<Invoice> invoice_items = new HashSet<InvoiceBeanDtoRequest.Invoice>();
		invoice_items.add(new Invoice("Dettol", ItemCategory.NON_MEDICAL, 20.0f, 100.0f, 5));
		invoice_items.add(new Invoice("Shampoo", ItemCategory.NON_MEDICAL, 10.0f, 60.0f, 10));
		return invoice_items;
	}
	
	
}

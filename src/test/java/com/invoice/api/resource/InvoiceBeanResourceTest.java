package com.invoice.api.resource;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import com.invoice.api.constants.ItemCategory;
import com.invoice.api.helper.dto.request.InvoiceBeanDtoRequest;
import com.invoice.api.helper.dto.request.InvoiceBeanDtoRequest.Invoice;

public class InvoiceBeanResourceTest extends BaseResourceTest {

	@Test
	public void testCreateInvoice() {
		InvoiceBeanDtoRequest request = getInvoiceRequest();
		Response response = target("/invoices/create")
							.request().post(Entity.entity(request, MediaType.APPLICATION_JSON));
		System.out.println(response);
		Assert.assertNotNull(response);
	}

	private InvoiceBeanDtoRequest getInvoiceRequest() {
		InvoiceBeanDtoRequest request = new InvoiceBeanDtoRequest();
		Set<Invoice> invoice_items = getInvoiceItems();
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

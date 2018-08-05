package com.invoice.api.dto.validator;

import com.invoice.api.constants.ItemCategory;
import com.invoice.api.helper.dto.request.InvoiceBeanDtoRequest;
import com.invoice.api.helper.dto.request.InvoiceBeanDtoRequest.Invoice;

public class DtoValidator {

	public void validate(InvoiceBeanDtoRequest request) throws Exception {
		for (Invoice invoice : request.getInvoiceItem()) {
			if (invoice == null || isEmpty(invoice.getItem_name()) || invoice.getItem_category() == null
					|| invoice.getPrice() == null || invoice.getQuantity() == null || invoice.getTax_rate() == null)
				throw new Exception("Validation failed");
			if (invoice.getItem_category() == ItemCategory.MEDICAL && invoice.getTax_rate() != 0)
				throw new Exception("Validation failed");
			if (invoice.getPrice() == 0 || invoice.getQuantity() == 0)
				throw new Exception("Validation failed");
		}
	}

	private static boolean isEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}
}

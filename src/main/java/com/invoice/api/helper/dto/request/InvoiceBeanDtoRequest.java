package com.invoice.api.helper.dto.request;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.invoice.api.constants.ItemCategory;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceBeanDtoRequest {

	private Set<Invoice> invoiceItem;

	public InvoiceBeanDtoRequest(Set<Invoice> invoiceItem) {
		super();
		this.invoiceItem = invoiceItem;
	}

	public InvoiceBeanDtoRequest() {
		super();
	}

	public static class Invoice {
		private Long invoice_id;
		
		private String item_name;

		private ItemCategory item_category;

		private float tax_rate;

		private float price;

		private float quantity;
		

		public Invoice(String item_name, ItemCategory item_category, float tax_rate, float price, float quantity) {
			super();
			this.item_name = item_name;
			this.item_category = item_category;
			this.tax_rate = tax_rate;
			this.price = price;
			this.quantity = quantity;
		}

		public Invoice() {
			super();
		}

		@Override
		public String toString() {
			return "Invoice [invoice_id=" + invoice_id + ", item_name=" + item_name + ", item_category=" + item_category
					+ ", tax_rate=" + tax_rate + ", price=" + price + ", quantity=" + quantity + "]";
		}

		public String getItem_name() {
			return item_name;
		}

		public void setItem_name(String item_name) {
			this.item_name = item_name;
		}

		public ItemCategory getItem_category() {
			return item_category;
		}

		public void setItem_category(ItemCategory item_category) {
			this.item_category = item_category;
		}

		public Float getTax_rate() {
			return tax_rate;
		}

		public Long getInvoice_id() {
			return invoice_id;
		}

		public void setInvoice_id(Long invoice_id) {
			this.invoice_id = invoice_id;
		}

		public void setTax_rate(float tax_rate) {
			this.tax_rate = tax_rate;
		}

		public Float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((item_name == null) ? 0 : item_name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Invoice other = (Invoice) obj;
			if (item_name == null) {
				if (other.item_name != null)
					return false;
			} else if (!item_name.equals(other.item_name))
				return false;
			return true;
		}

		public Float getQuantity() {
			return quantity;
		}

		public void setQuantity(float quantity) {
			this.quantity = quantity;
		}
	}

	public Set<Invoice> getInvoiceItem() {
		return invoiceItem;
	}

	public void setInvoiceItem(Set<Invoice> invoiceItem) {
		this.invoiceItem = invoiceItem;
	}

	@Override
	public String toString() {
		return "InvoiceRequestDto [invoiceItem=" + invoiceItem + "]";
	}
}

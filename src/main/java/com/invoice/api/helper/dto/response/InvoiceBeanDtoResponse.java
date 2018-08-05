package com.invoice.api.helper.dto.response;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.invoice.api.helper.dto.request.InvoiceBeanDtoRequest.Invoice;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceBeanDtoResponse {

	private Long invoice_id;
	private Set<InvoiceRecord> invoiceItems;
	private double total_tax;
	private double total_price;
	private double selling_price;
	private Date created_date;
	private Date last_modified;
	public InvoiceBeanDtoResponse() {
		super();
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getLast_modified() {
		return last_modified;
	}

	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}

	public InvoiceBeanDtoResponse(Long invoice_id, Set<InvoiceRecord> invoiceItems, double total_tax, double total_price,
			double selling_price) {
		super();
		this.invoice_id = invoice_id;
		this.invoiceItems = invoiceItems;
		this.total_tax = total_tax;
		this.total_price = total_price;
		this.selling_price = selling_price;
	}
	
	@Override
	public String toString() {
		return "InvoiceBeanDtoResponse [invoice_id=" + invoice_id + ", invoiceItems=" + invoiceItems + ", total_tax="
				+ total_tax + ", total_price=" + total_price + ", selling_price=" + selling_price + ", created_date="
				+ created_date + ", last_modified=" + last_modified + "]";
	}

	public Set<InvoiceRecord> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(Set<InvoiceRecord> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public Double getTotal_tax() {
		return total_tax;
	}

	public void setTotal_tax(double total_tax) {
		this.total_tax = total_tax;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public Double getSelling_price() {
		return selling_price;
	}

	public void setSelling_price(double selling_price) {
		this.selling_price = selling_price;
	}

	public static class InvoiceRecord {
		private Invoice invoice;
		private double total_tax;
		private double total_price;
		private double selling_price;

		public InvoiceRecord(Invoice invoice, double total_tax, double total_price, double selling_price) {
			super();
			this.invoice = invoice;
			this.total_tax = total_tax;
			this.total_price = total_price;
			this.selling_price = selling_price;
		}

		public InvoiceRecord() {
			super();
		}

		public Invoice getInvoice() {
			return invoice;
		}

		public void setInvoice(Invoice invoice) {
			this.invoice = invoice;
		}

		public double getTotal_tax() {
			return total_tax;
		}

		public void setTotal_tax(double total_tax) {
			this.total_tax = total_tax;
		}

		public double getTotal_price() {
			return total_price;
		}

		public void setTotal_price(double total_price) {
			this.total_price = total_price;
		}

		public double getSelling_price() {
			return selling_price;
		}

		public void setSelling_price(double selling_price) {
			this.selling_price = selling_price;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
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
			InvoiceRecord other = (InvoiceRecord) obj;
			if (invoice == null) {
				if (other.invoice != null)
					return false;
			} else if (!invoice.equals(other.invoice))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "InvoiceRecord [invoice=" + invoice + ", total_tax=" + total_tax + ", total_price=" + total_price
					+ ", selling_price=" + selling_price + "]";
		}
	}
	public Long getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(Long invoice_id) {
		this.invoice_id = invoice_id;
	}
}

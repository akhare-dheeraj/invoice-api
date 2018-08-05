package com.invoice.api.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INVOICES")
public class InvoiceBean extends AbstractBaseInvoiceBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7206318452944277541L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long invoice_id;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<InvoiceItemBean> invoice_items; 
	
	@Column(name = "total_tax")
	private Double total_tax;
	
	@Column(name = "total_price")
	private Double total_price;
	
	@Column(name = "selling_price")
	private Double selling_price;
	
	public InvoiceBean() {
		super();
	}

	public InvoiceBean(Set<InvoiceItemBean> invoice_items, Double total_tax, Double total_price, Double selling_price) {
		super();
		this.invoice_items = invoice_items;
		this.total_tax = total_tax;
		this.total_price = total_price;
		this.selling_price = selling_price;
	}

	 
	public Long getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(Long invoice_id) {
		this.invoice_id = invoice_id;
	}

	public Set<InvoiceItemBean> getInvoice_items() {
		return invoice_items;
	}

	public void setInvoice_items(Set<InvoiceItemBean> invoice_items) {
		this.invoice_items = invoice_items;
	}

	public Double getTotal_tax() {
		return total_tax;
	}

	public void setTotal_tax(Double total_tax) {
		this.total_tax = total_tax;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public Double getSelling_price() {
		return selling_price;
	}

	public void setSelling_price(Double selling_price) {
		this.selling_price = selling_price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((invoice_id == null) ? 0 : invoice_id.hashCode());
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
		InvoiceBean other = (InvoiceBean) obj;
		if (invoice_id == null) {
			if (other.invoice_id != null)
				return false;
		} else if (!invoice_id.equals(other.invoice_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InvoiceBean [invoice_id=" + invoice_id + ", invoice_items=" + invoice_items + ", total_tax=" + total_tax
				+ ", total_price=" + total_price + ", selling_price=" + selling_price + ", created_date=" + created_date
				+ ", modified_date=" + modified_date + "]";
	}

}

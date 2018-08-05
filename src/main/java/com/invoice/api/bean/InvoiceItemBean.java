package com.invoice.api.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.invoice.api.constants.ItemCategory;

@Entity
@Table(name = "INVOICE_ITEM")
public class InvoiceItemBean extends AbstractBaseInvoiceBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "invoice_item_id")
	private Long invoice_item_id;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "item_id")
	private ItemBean invoice_item;

	@Column(name = "quantity", nullable = false)
	private Float quantity;

	@Column(name = "price")
	private Float price;

	@Column(name = "tax_rate")
	private Float tax_rate = 20.0f;

	@Column(name = "item_category")
	private ItemCategory item_category;

	@Column(name = "total_price")
	private Double total_price;

	@Column(name = "total_tax")
	private Double total_tax;

	@Column(name = "selling_price")
	private Double selling_price;
	
	public InvoiceItemBean() {
		super();
	}

	public InvoiceItemBean(ItemBean invoice_item, Float quantity, Float price, Float tax_rate,
			ItemCategory item_category) {
		super();
		this.invoice_item = invoice_item;
		this.quantity = quantity;
		this.price = price;
		this.tax_rate = tax_rate;
		this.item_category = item_category;
	}

	public ItemBean getInvoice_item() {
		return invoice_item;
	}

	public void setInvoice_item(ItemBean invoice_item) {
		this.invoice_item = invoice_item;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Float getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(Float tax_rate) {
		this.tax_rate = tax_rate;
	}

	public ItemCategory getItem_category() {
		return item_category;
	}

	public void setItem_category(ItemCategory item_category) {
		this.item_category = item_category;
	}

	public Long getInvoice_item_id() {
		return invoice_item_id;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public Double getTotal_tax() {
		return total_tax;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public void setTotal_tax(Double total_tax) {
		this.total_tax = total_tax;
	}

	public void setSelling_price(Double selling_price) {
		this.selling_price = selling_price;
	}

	public Double getSelling_price() {
		return selling_price;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((invoice_item_id == null) ? 0 : invoice_item_id.hashCode());
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
		InvoiceItemBean other = (InvoiceItemBean) obj;
		if (invoice_item_id == null) {
			if (other.invoice_item_id != null)
				return false;
		} else if (!invoice_item_id.equals(other.invoice_item_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InvoiceItem [invoice_item_id=" + invoice_item_id + ", invoice_item=" + invoice_item + ", quantity="
				+ quantity + ", price=" + price + ", tax_rate=" + tax_rate + ", item_category=" + item_category
				+ ", total_price=" + total_price + ", total_tax=" + total_tax + ", selling_price=" + selling_price
				+ ", created_date=" + created_date + ", modified_date=" + modified_date + "]";
	}
	
}

package com.invoice.api.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.invoice.api.constants.ItemCategory;

@Entity
@Table(name = "ITEMS")
public class ItemBean extends AbstractBaseInvoiceBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8777759962053914547L;

	@Column(name = "item_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long item_id;

	@Column(name = "item_name", nullable = false)
	private String item_name;

	@Column(name = "item_price", nullable = false)
	private Float item_price;

	@Column(name = "item_category")
	private ItemCategory item_category = ItemCategory.NON_MEDICAL;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "item_tax", joinColumns = @JoinColumn(name = "tax_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	private Set<TaxBean> taxes;

	public ItemBean() {
		super();
	}

	public ItemBean(String item_name, Float item_price, ItemCategory item_category, Set<TaxBean> taxes) {
		super();
		this.item_name = item_name;
		this.item_price = item_price;
		this.item_category = item_category;
		this.taxes = taxes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item_id == null) ? 0 : item_id.hashCode());
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
		ItemBean other = (ItemBean) obj;
		if (item_id == null) {
			if (other.item_id != null)
				return false;
		} else if (!item_id.equals(other.item_id))
			return false;
		return true;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public Float getItem_price() {
		return item_price;
	}

	public void setItem_price(Float item_price) {
		this.item_price = item_price;
	}

	public ItemCategory getItem_category() {
		return item_category;
	}

	public void setItem_category(ItemCategory item_category) {
		this.item_category = item_category;
	}

	public Set<TaxBean> getTaxes() {
		return taxes;
	}

	public void setTaxes(Set<TaxBean> taxes) {
		this.taxes = taxes;
	}

	public Long getItem_id() {
		return item_id;
	}

	@Override
	public String toString() {
		return "ItemBean [item_id=" + item_id + ", item_name=" + item_name + ", item_price=" + item_price
				+ ", item_category=" + item_category + ", taxes=" + taxes + ", created_date=" + created_date
				+ ", modified_date=" + modified_date + "]";
	}

}

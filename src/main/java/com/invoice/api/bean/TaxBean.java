package com.invoice.api.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TAXES")
public class TaxBean extends AbstractBaseInvoiceBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5291362916685550586L;

	@Column(name = "tax_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tax_id;
	
	@Column(name = "tax_type")
	private String tax_type;
	
	@Column(name = "tax_rate")
	private Float tax_rate = 20.0f;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "medical_exempt")
	private Boolean medical_exempt = false;
	
	@ManyToMany(mappedBy = "taxes")
	private Set<ItemBean> items;
	
	public TaxBean() {
		super();
	}

	public TaxBean(String tax_type, Float tax_rate, String description, Boolean medical_exempt) {
		super();
		this.tax_type = tax_type;
		this.tax_rate = tax_rate;
		this.description = description;
		this.medical_exempt = medical_exempt;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tax_id == null) ? 0 : tax_id.hashCode());
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
		TaxBean other = (TaxBean) obj;
		if (tax_id == null) {
			if (other.tax_id != null)
				return false;
		} else if (!tax_id.equals(other.tax_id))
			return false;
		return true;
	}

	public String getTax_type() {
		return tax_type;
	}

	public void setTax_type(String tax_type) {
		this.tax_type = tax_type;
	}

	public Float getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(Float tax_rate) {
		this.tax_rate = tax_rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getMedical_exempt() {
		return medical_exempt;
	}

	public void setMedical_exempt(Boolean medical_exempt) {
		this.medical_exempt = medical_exempt;
	}

	public Long getTax_id() {
		return tax_id;
	}
	
	
	@Override
	public String toString() {
		return "TaxBean [tax_id=" + tax_id + ", tax_type=" + tax_type + ", tax_rate=" + tax_rate + ", description="
				+ description + ", medical_exempt=" + medical_exempt + ", created_date=" + created_date
				+ ", modified_date=" + modified_date + "]";
	}

	public Set<ItemBean> getItems() {
		return items;
	}

	public void setItems(Set<ItemBean> items) {
		this.items = items;
	}

}

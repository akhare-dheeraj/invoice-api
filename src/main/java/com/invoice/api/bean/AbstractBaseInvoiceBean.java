package com.invoice.api.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractBaseInvoiceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3648599584838788423L;

	
	@Column(name = "created_date")
	protected Date created_date;

	@Column(name = "modified_date")
	protected Date modified_date;

	public Date getCreated_date() {
		return created_date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getModified_date() {
		return modified_date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

}

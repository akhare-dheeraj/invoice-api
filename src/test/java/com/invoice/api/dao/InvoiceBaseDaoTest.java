package com.invoice.api.dao;

import java.io.Serializable;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.invoice.api.bean.TaxBean;


public class InvoiceBaseDaoTest extends AbstractTest{
	
	@Autowired
	private AbstractInvoiceBaseDao<TaxBean> baseDao;

	@Test
	public void testSaveBean() {
		TaxBean tax = new TaxBean("GST", 20.0f, "GST Taxes", false);
		baseDao.openSessionAndTransaction();
		Serializable taxId = baseDao.save(tax);
		baseDao.commitTransactionAndCloseSession();
		Assert.assertNotNull(taxId);
	}
	
	@Test
	public void testUpdate() {
		TaxBean tax = new TaxBean("GST", 20.0f, "GST Taxes", false);
		baseDao.openSessionAndTransaction();
		Serializable taxId = baseDao.save(tax);
		baseDao.commitTransactionAndCloseSession();
		baseDao.openSessionAndTransaction();
		tax.setTax_type("SALES");
		baseDao.update(tax);
		baseDao.commitTransactionAndCloseSession();
		baseDao.openSessionAndTransaction();
		TaxBean updatedTax = (TaxBean) baseDao.get(taxId);
		Assert.assertNotEquals("GST", updatedTax.getTax_type());
		baseDao.commitTransactionAndCloseSession();
		Assert.assertNotNull(taxId);
	}
	
	@Test
	public void testPersistBean() {
		TaxBean tax = new TaxBean("GST", 20.0f, "GST Taxes", false);
		baseDao.openSessionAndTransaction();
		baseDao.persist(tax);
		baseDao.commitTransactionAndCloseSession();
		Assert.assertNotNull(tax.getTax_id());
	}

}

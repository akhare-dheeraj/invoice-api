package com.invoice.api.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.invoice.api.bean.InvoiceBean;
import com.invoice.api.bean.InvoiceItemBean;
import com.invoice.api.bean.ItemBean;
import com.invoice.api.bean.TaxBean;
import com.invoice.api.constants.ItemCategory;

public class InvoiceBeanDaoTest extends AbstractTest{
	
	@Autowired
	private AbstractInvoiceBaseDao<InvoiceBean> invoiceDao;
	
	private Serializable id;
	
	@Before
	public void before() {
		invoiceDao.openSessionAndTransaction();
		InvoiceBean bean = getInvoiceBean();
		invoiceDao.persist(bean);
		id = invoiceDao.save(bean);
		invoiceDao.commitTransactionAndCloseSession();
		invoiceDao.openSessionAndTransaction();
	}
	
	@After
	public void after() {
			invoiceDao.commitTransactionAndCloseSession();
	}
	
	@Test
	public void testGet() {
		InvoiceBean bean = invoiceDao.get(id);
		Assert.assertNotNull(bean);
	}
	
	@Test
	public void testGetAllBeans() {
		Set<InvoiceBean> beans = invoiceDao.getAllBeans();
		Assert.assertNotNull(beans);
		Assert.assertNotEquals(0, beans.size());
	}
	
	@Test
	public void testDelete() {
		invoiceDao.delete(id);
		InvoiceBean bean = invoiceDao.get(id);
		Assert.assertNull(bean);
	}
	
	private InvoiceBean getInvoiceBean() {
		Set<InvoiceItemBean> invoice_items = new HashSet<InvoiceItemBean>();
		Set<TaxBean> tax_set = new HashSet<TaxBean>();
		tax_set.add(new TaxBean("GST", 20.0f, "GST taxes", false));
		invoice_items.add(new InvoiceItemBean(new ItemBean("Dettol", 40.0f, ItemCategory.NON_MEDICAL, tax_set), 3.0f, 400.0f, 20.0f, ItemCategory.NON_MEDICAL));
		InvoiceBean bean = new InvoiceBean(invoice_items, 500.0	, 1000.0, 1500.0);
		return bean;
	}
}

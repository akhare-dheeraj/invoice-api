package com.invoice.api.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.invoice.api.bean.InvoiceItemBean;
import com.invoice.api.bean.ItemBean;
import com.invoice.api.bean.TaxBean;
import com.invoice.api.constants.ItemCategory;

public class InvoiceItemBeanDaoTest extends AbstractTest{
	
	@Autowired
	private AbstractInvoiceBaseDao<InvoiceItemBean> invoiceItemDao;
	
	private Serializable id;
	
	@Before
	public void before() {
		invoiceItemDao.openSessionAndTransaction();
		InvoiceItemBean bean = getInvoiceItem();
		invoiceItemDao.persist(bean);
		id = invoiceItemDao.save(bean);
		invoiceItemDao.commitTransactionAndCloseSession();
		invoiceItemDao.openSessionAndTransaction();
	}
	
	@After
	public void after() {
		invoiceItemDao.commitTransactionAndCloseSession();
	}
	
	@Test
	public void testGet() {
		InvoiceItemBean bean = invoiceItemDao.get(id);
		Assert.assertNotNull(bean);
	}
	
	@Test
	public void testGetAllBeans() {
		Set<InvoiceItemBean> beans = invoiceItemDao.getAllBeans();
		Assert.assertNotNull(beans);
		Assert.assertNotEquals(0, beans.size());
	}
	
	@Test
	public void testDelete() {
		invoiceItemDao.delete(id);
		InvoiceItemBean bean = invoiceItemDao.get(id);
		Assert.assertNull(bean);
	}
	
	private InvoiceItemBean getInvoiceItem() {
		Set<TaxBean> tax_set = new HashSet<TaxBean>();
		tax_set.add(new TaxBean("GST", 20.0f, "GST taxes", false));
		return new InvoiceItemBean(new ItemBean("Dettol", 40.0f, ItemCategory.NON_MEDICAL, tax_set), 3.0f, 400.0f, 20.0f, ItemCategory.NON_MEDICAL);
	}
}

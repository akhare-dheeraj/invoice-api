package com.invoice.api.dao;

import java.io.Serializable;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.invoice.api.bean.TaxBean;

public class TaxBeanDaoTest extends AbstractTest {
	@Autowired
	private AbstractInvoiceBaseDao<TaxBean> taxBeanDao;

	private Serializable id;

	@Before
	public void before() {
		taxBeanDao.openSessionAndTransaction();
		TaxBean bean = getTaxBean();
		taxBeanDao.persist(bean);
		id = taxBeanDao.save(bean);
		taxBeanDao.commitTransactionAndCloseSession();
		taxBeanDao.openSessionAndTransaction();
	}

	@After
	public void after() {
		taxBeanDao.commitTransactionAndCloseSession();
	}

	@Test
	public void testGet() {
		TaxBean bean = taxBeanDao.get(id);
		Assert.assertNotNull(bean);
	}

	@Test
	public void testGetAllBeans() {
		Set<TaxBean> beans = taxBeanDao.getAllBeans();
		Assert.assertNotNull(beans);
		Assert.assertNotEquals(0, beans.size());
	}

	@Test
	public void testDelete() {
		taxBeanDao.delete(id);
		TaxBean bean = taxBeanDao.get(id);
		Assert.assertNull(bean);
	}

	private TaxBean getTaxBean() {
		return new TaxBean("GST", 20.0f, "GST taxes", false);
	}
}

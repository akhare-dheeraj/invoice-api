package com.invoice.api.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.invoice.api.bean.ItemBean;
import com.invoice.api.bean.TaxBean;
import com.invoice.api.constants.ItemCategory;

public class ItemBeanDaoTest extends AbstractTest {
	@Autowired
	private ItemBeanDaoImpl itemBeanDao;

	private Serializable id;

	@Before
	public void before() {
		itemBeanDao.openSessionAndTransaction();
		ItemBean bean = getItemBean();
		itemBeanDao.persist(bean);
		id = itemBeanDao.save(bean);
		itemBeanDao.commitTransactionAndCloseSession();
		itemBeanDao.openSessionAndTransaction();
	}

	@After
	public void after() {
		itemBeanDao.commitTransactionAndCloseSession();
	}

	@Test
	public void testGet() {
		ItemBean bean = itemBeanDao.get(id);
		Assert.assertNotNull(bean);
	}

	@Test
	public void testGetAllBeans() {
		Set<ItemBean> beans = itemBeanDao.getAllBeans();
		Assert.assertNotNull(beans);
		Assert.assertNotEquals(0, beans.size());
	}

	@Test
	public void testDelete() {
		itemBeanDao.delete(id);
		ItemBean bean = itemBeanDao.get(id);
		Assert.assertNull(bean);
	}
	
	@Test
	public void testGetByName() {
		List<ItemBean> byName = itemBeanDao.getByName("Dettol");
		Assert.assertNotNull(byName);
		Assert.assertNotEquals(0, byName.size());
	}

	private ItemBean getItemBean() {
		Set<TaxBean> tax_set = new HashSet<TaxBean>();
		tax_set.add(new TaxBean("GST", 20.0f, "GST taxes", false));
		return new ItemBean("Dettol", 40.0f, ItemCategory.NON_MEDICAL, tax_set);
	}
	
	
}

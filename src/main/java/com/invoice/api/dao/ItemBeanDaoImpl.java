package com.invoice.api.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.invoice.api.bean.ItemBean;

public class ItemBeanDaoImpl extends AbstractInvoiceBaseDao<ItemBean> {
	protected static final Logger logger = LoggerFactory.getLogger(ItemBeanDaoImpl.class);

	@Override
	public ItemBean get(Serializable id) {
		logger.info("Getting item bean with id:" + id);
		Session session = getCurrentSession();
		ItemBean bean = (ItemBean) session.get(ItemBean.class, id);
		logger.info("Item bean with id " + id + ":" + bean);
		return bean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<ItemBean> getAllBeans() {
		logger.info("Getting all beans of items");
		Set<ItemBean> beans = null;
		Session session = getCurrentSession();
		List<ItemBean> beanList = session.createCriteria(ItemBean.class).list();
		if (beanList != null)
			beans = new HashSet<ItemBean>(beanList);
		logger.info("Fetched items list: " + beans);
		return beans;
	}

	@Override
	public void delete(Serializable id) {
		logger.info("Deleting items bean with id:" + id);
		Session session = getCurrentSession();
		ItemBean bean = (ItemBean) session.get(ItemBean.class, id);
		session.delete(bean);
		logger.info("Items bean deleted with id:" + id);
	}

	public List<ItemBean> getByName(String item_name) {
		logger.info("Fetching item bean by name :" + item_name);
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(ItemBean.class);
		@SuppressWarnings("unchecked")
		List<ItemBean> list = criteria.add(Restrictions.eq("item_name", item_name).ignoreCase()).list();
		logger.info("Fetched items by name: "+list);
		return list;
	}

}

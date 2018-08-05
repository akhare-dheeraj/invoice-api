package com.invoice.api.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.invoice.api.bean.InvoiceItemBean;

public class InvoiceItemBeanDaoImpl extends AbstractInvoiceBaseDao<InvoiceItemBean> {
	protected static final Logger logger = LoggerFactory.getLogger(InvoiceItemBeanDaoImpl.class);
	@Override
	public InvoiceItemBean get(Serializable id) {
		logger.info("Getting invoice item bean with id:" + id);
		Session session = getCurrentSession();
		InvoiceItemBean bean = (InvoiceItemBean) session.get(InvoiceItemBean.class, id);
		logger.info("Invoice item bean with id " + id + ":" + bean);
		return bean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<InvoiceItemBean> getAllBeans() {
		logger.info("Getting all beans of invoice item");
		Set<InvoiceItemBean> beans = null;
		Session session = getCurrentSession();
		List<InvoiceItemBean> beanList = session.createCriteria(InvoiceItemBean.class).list();
		if (beanList != null)
			beans = new HashSet<InvoiceItemBean>(beanList);
		logger.info("Fetched invoices item list: " + beans);
		return beans;
	}

	@Override
	public void delete(Serializable id) {
		logger.info("Deleting invoice item bean with id:" + id);
		Session session = getCurrentSession();
		InvoiceItemBean bean = (InvoiceItemBean) session.get(InvoiceItemBean.class, id);
		session.delete(bean);
		logger.info("Invoice item bean deleted with id:" + id);
	}

}

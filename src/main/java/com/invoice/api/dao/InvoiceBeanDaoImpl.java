package com.invoice.api.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.invoice.api.bean.InvoiceBean;

public class InvoiceBeanDaoImpl extends AbstractInvoiceBaseDao<InvoiceBean> {
	protected static final Logger logger = LoggerFactory.getLogger(InvoiceBeanDaoImpl.class);

	@Override
	public InvoiceBean get(Serializable id) {
		logger.info("Getting invoice bean with id:" + id);
		Session session = getCurrentSession();
		InvoiceBean bean = (InvoiceBean) session.get(InvoiceBean.class, id);
		logger.info("Invoice bean with id " + id + ":" + bean);
		return bean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<InvoiceBean> getAllBeans() {
		logger.info("Getting all beans of invoice");
		Set<InvoiceBean> beans = null;
		Session session = getCurrentSession();
		List<InvoiceBean> beanList = session.createCriteria(InvoiceBean.class).list();
		if (beanList != null)
			beans = new HashSet<InvoiceBean>(beanList);
		logger.info("Fetched invoices list: " + beans);
		return beans;
	}

	@Override
	public void delete(Serializable id) {
		logger.info("Deleting invoice bean with id:" + id);
		Session session = getCurrentSession();
		InvoiceBean bean = (InvoiceBean) session.get(InvoiceBean.class, id);
		session.delete(bean);
		logger.info("Invoice bean deleted with id:" + id);
	}
}

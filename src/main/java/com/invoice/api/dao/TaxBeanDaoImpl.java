package com.invoice.api.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.invoice.api.bean.TaxBean;

public class TaxBeanDaoImpl extends AbstractInvoiceBaseDao<TaxBean> {
	protected static final Logger logger = LoggerFactory.getLogger(TaxBeanDaoImpl.class);
	@Override
	public TaxBean get(Serializable id) {
		logger.info("Getting tax bean with id:" + id);
		Session session = getCurrentSession();
		TaxBean bean = (TaxBean) session.get(TaxBean.class, id);
		logger.info("Tax bean with id " + id + ":" + bean);
		return bean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<TaxBean> getAllBeans() {
		logger.info("Getting all beans of tax..");
		Set<TaxBean> beans = null;
		Session session = getCurrentSession();
		List<TaxBean> beanList = session.createCriteria(TaxBean.class).list();
		if (beanList != null)
			beans = new HashSet<TaxBean>(beanList);
		logger.info("Fetched tax beans list: " + beans);
		return beans;
	}

	@Override
	public void delete(Serializable id) {
		logger.info("Deleting tax bean with id:" + id);
		Session session = getCurrentSession();
		TaxBean bean = (TaxBean) session.get(TaxBean.class, id);
		session.delete(bean);
		logger.info("Tax bean deleted with id:" + id);
	}

}

package com.invoice.api.dao;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.invoice.api.bean.AbstractBaseInvoiceBean;

public abstract class AbstractInvoiceBaseDao<T extends AbstractBaseInvoiceBean> {
	protected static final Logger logger = LoggerFactory.getLogger(AbstractInvoiceBaseDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public abstract T get(Serializable id);

	public abstract Set<T> getAllBeans();

	public abstract void delete(Serializable id);

	public Serializable save(T bean) {
		logger.info("Saving bean:" + bean);
		Session session = getCurrentSession();
		Serializable id = session.save(bean);
		logger.info("Saved bean:" + bean);
		return id;
	}

	public void update(T bean) {
		logger.info("Updating bean with id:" + bean);
		Session session = getCurrentSession();
		session.update(bean);
		logger.info("Updated bean with id:" + bean);
	}

	public void persist(T bean) {
		logger.info("Persisting bean:" + bean);
		Session session = getCurrentSession();
		session.persist(bean);
		logger.info("Bean persisted:" + bean);
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Session openNewSession() {
		return sessionFactory.openSession();
	}

	public Transaction startTransaction() {
		return sessionFactory.getCurrentSession().getTransaction().isActive()
				? sessionFactory.getCurrentSession().getTransaction()
				: sessionFactory.getCurrentSession().beginTransaction();
	}

	public void commitTransaction() {
		if (sessionFactory.getCurrentSession().getTransaction().isActive())
			sessionFactory.getCurrentSession().getTransaction().commit();
	}

	public void closeSession() {
		if (sessionFactory.getCurrentSession().isOpen())
			sessionFactory.getCurrentSession().close();
	}

	public void openSessionAndTransaction() {
		openNewSession();
		startTransaction();
	}

	public void commitTransactionAndCloseSession() {
		commitTransaction();
		closeSession();
	}

	public void rollBackAndClose() {
		rollBack();
		closeSession();
	}
	
	public void rollBack() {
		if (getCurrentSession().getTransaction().isActive())
			getCurrentSession().getTransaction().rollback();
	}
}

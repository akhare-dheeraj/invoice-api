package com.invoice.api.bean.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.invoice.api.bean.AbstractBaseInvoiceBean;

public class BaseInvoiceBeanInterceptor extends EmptyInterceptor {

	protected Logger logger = LoggerFactory.getLogger(BaseInvoiceBeanInterceptor.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 7941314084467473082L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		
		if (entity instanceof AbstractBaseInvoiceBean) {
			logger.info("Adding create and modify date while saving for "+entity.getClass());
			AbstractBaseInvoiceBean bean = (AbstractBaseInvoiceBean) entity;
			Date date = new Date();
			if (bean.getCreated_date() == null)
				bean.setCreated_date(date);
			bean.setModified_date(date);
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		
		if (entity instanceof AbstractBaseInvoiceBean) {
			logger.info("Adding create and modify date while flushing dirty "+entity.getClass());
			AbstractBaseInvoiceBean bean = (AbstractBaseInvoiceBean) entity;
			Date date = new Date();
			if (bean.getCreated_date() == null)
				bean.setCreated_date(date);
			bean.setModified_date(date);
		}
		return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
	}
	
	
}

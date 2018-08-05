package com.invoice.api.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.invoice.api.bean.ItemBean;
import com.invoice.api.dao.ItemBeanDaoImpl;
import com.invoice.api.helper.dto.response.ItemBeanDtoResponse;
import com.invoice.api.helper.util.DtoUtils;

public class ItemBeanService {

	@Autowired
	private ItemBeanDaoImpl itemBeanDao;

	public ItemBean getItemByName(String item_name) throws Exception {
		try {
			itemBeanDao.openSessionAndTransaction();
			List<ItemBean> items = itemBeanDao.getByName(item_name);
			if (items != null && !items.isEmpty())
				return items.get(0);
		} catch (Exception e) {
			itemBeanDao.rollBackAndClose();
			throw e;
		} finally {
			itemBeanDao.commitTransactionAndCloseSession();
		}
		return null;
	}

	public ItemBeanDtoResponse saveItem(ItemBean bean) throws Exception {
		try {
			itemBeanDao.openSessionAndTransaction();
			itemBeanDao.persist(bean);
			Set<ItemBean> allBeans = new HashSet<>();
			allBeans.add(bean);
			return DtoUtils.convertToItemBeanDto(allBeans);
		} catch (Exception e) {
			itemBeanDao.rollBackAndClose();
			throw e;
		} finally {
			itemBeanDao.commitTransactionAndCloseSession();
		}
	}

	public ItemBeanDtoResponse getAllItemBeans() throws Exception {
		try {
			itemBeanDao.openSessionAndTransaction();
			Set<ItemBean> allBeans = itemBeanDao.getAllBeans();
			ItemBeanDtoResponse itemBeanDtoResponse = DtoUtils.convertToItemBeanDto(allBeans);
			return itemBeanDtoResponse;
		} catch (Exception e) {
			itemBeanDao.rollBackAndClose();
			throw e;
		} finally {
			itemBeanDao.commitTransactionAndCloseSession();
		}
	}
}

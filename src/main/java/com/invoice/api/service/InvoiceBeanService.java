package com.invoice.api.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.invoice.api.bean.InvoiceBean;
import com.invoice.api.bean.InvoiceItemBean;
import com.invoice.api.bean.ItemBean;
import com.invoice.api.dao.InvoiceBeanDaoImpl;
import com.invoice.api.dao.InvoiceItemBeanDaoImpl;
import com.invoice.api.dao.ItemBeanDaoImpl;
import com.invoice.api.helper.dto.request.InvoiceBeanDtoRequest;
import com.invoice.api.helper.dto.request.InvoiceBeanDtoRequest.Invoice;
import com.invoice.api.helper.dto.response.InvoiceBeanDtoResponse;
import com.invoice.api.helper.util.DtoUtils;

public class InvoiceBeanService {

	@Autowired
	private InvoiceBeanDaoImpl invoiceBeanDao;

	@Autowired
	private ItemBeanDaoImpl itemBeanDao;

	@Autowired
	private InvoiceItemBeanDaoImpl invoiceItemBeanDao;

	public InvoiceBeanDtoResponse saveInvoice(InvoiceBeanDtoRequest invoiceDto) throws Exception {
		try {
			itemBeanDao.openSessionAndTransaction();
			Set<Invoice> invoices = invoiceDto.getInvoiceItem();
			Set<InvoiceItemBean> invoiceItems = new HashSet<InvoiceItemBean>();
			for (Invoice invoice : invoices) {
				String item_name = invoice.getItem_name();
				List<ItemBean> byNameList = itemBeanDao.getByName(item_name);
				ItemBean itemByName = byNameList != null && !byNameList.isEmpty() ? byNameList.get(0) : null;
				if (itemByName == null) {
					itemByName = DtoUtils.createInvoiceItemFromDto(invoice);
					itemBeanDao.persist(itemByName);
					if (itemByName.getItem_id() == null)
						throw new Exception("Failed to create item bean");
				}
				InvoiceItemBean invoiceItemBean = DtoUtils.createInvoiceItemBean(itemByName, invoice);
				invoiceItemBeanDao.persist(invoiceItemBean);
				if (invoiceItemBean.getInvoice_item_id() == null)
					throw new Exception("Failed to create invoice item bean");
				invoiceItems.add(invoiceItemBean);
			}
			InvoiceBean invoiceBean = DtoUtils.createInvoiceBean(invoiceDto, invoiceItems);
			invoiceBean.setInvoice_items(invoiceItems);
			invoiceBeanDao.persist(invoiceBean);
			return DtoUtils.convertToInvoiceBeanDto(invoiceBean);
		} catch (Exception e) {
			itemBeanDao.rollBackAndClose();
			throw e;
		} finally {
			itemBeanDao.commitTransactionAndCloseSession();
		}
	}

	public InvoiceBeanDtoResponse getInvoiceById(Long id) throws Exception {
		try {
			invoiceBeanDao.openSessionAndTransaction();
			InvoiceBean bean = invoiceBeanDao.get(id);
			InvoiceBeanDtoResponse response = DtoUtils.convertToInvoiceBeanDto(bean);
			return response;
		} catch (Exception e) {
			invoiceBeanDao.rollBackAndClose();
			throw e;
		} finally {
			invoiceBeanDao.commitTransactionAndCloseSession();
		}
	}

	public InvoiceBeanDtoResponse updateInvoice(Long id, InvoiceBeanDtoRequest request) throws Exception {
		try {
			invoiceBeanDao.openSessionAndTransaction();
			InvoiceBean invoiceBean = invoiceBeanDao.get(id);
			if (invoiceBean == null)
				throw new Exception("Invoice with given id not found:" + id);
			DtoUtils.updateAndGenrateInvoiceBeanDto(invoiceBean, request);
			for(InvoiceItemBean itmBn : invoiceBean.getInvoice_items()) {
				invoiceItemBeanDao.update(itmBn);
			}
				
			invoiceBeanDao.update(invoiceBean);
			return DtoUtils.convertToInvoiceBeanDto(invoiceBean);
		} catch (Exception e) {
			invoiceBeanDao.rollBackAndClose();
			throw e;
		} finally {
			invoiceBeanDao.commitTransactionAndCloseSession();
		}
	}
}

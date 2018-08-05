package com.invoice.api.helper.util;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.invoice.api.bean.InvoiceBean;
import com.invoice.api.bean.InvoiceItemBean;
import com.invoice.api.bean.ItemBean;
import com.invoice.api.bean.TaxBean;
import com.invoice.api.constants.ItemCategory;
import com.invoice.api.helper.dto.request.InvoiceBeanDtoRequest;
import com.invoice.api.helper.dto.request.InvoiceBeanDtoRequest.Invoice;
import com.invoice.api.helper.dto.request.ItemBeanDtoRequest;
import com.invoice.api.helper.dto.response.InvoiceBeanDtoResponse;
import com.invoice.api.helper.dto.response.InvoiceBeanDtoResponse.InvoiceRecord;
import com.invoice.api.helper.dto.response.ItemBeanDtoResponse;
import com.invoice.api.helper.dto.response.ItemBeanDtoResponse.Item;

public class DtoUtils {
	public static ItemBean createInvoiceItemFromDto(Invoice invoice) {
		String item_name = invoice.getItem_name();
		Float tax_rate = invoice.getTax_rate();
		float price = invoice.getPrice();
		ItemCategory item_category = invoice.getItem_category();
		Set<TaxBean> taxes = new HashSet<TaxBean>();
		taxes.add(new TaxBean("Other", tax_rate, "Other taxes", true));
		return new ItemBean(item_name, price, item_category, taxes);
	}

	public static InvoiceBean createInvoiceBean(InvoiceBeanDtoRequest invoiceDto, Set<InvoiceItemBean> invoiceItems) {
		double total_tax = 0;
		double total_price = 0;
		double total_selling_price = 0;
		for (InvoiceItemBean invoice : invoiceItems) {
			total_tax += invoice.getTotal_tax();
			total_price += invoice.getTotal_price();
			total_selling_price += invoice.getSelling_price();
		}
		return new InvoiceBean(invoiceItems, total_tax, total_price, total_selling_price);
	}

	public static InvoiceItemBean createInvoiceItemBean(ItemBean itemByName, Invoice invoice) {
		InvoiceItemBean bean = new InvoiceItemBean(itemByName, invoice.getQuantity(), invoice.getPrice(),
				invoice.getTax_rate(), invoice.getItem_category());
		bean.setTotal_price((double) (bean.getQuantity() * bean.getPrice()));
		bean.setTotal_tax(bean.getTotal_price() * bean.getTax_rate() / 100);
		bean.setSelling_price(bean.getTotal_price() + bean.getTotal_tax());
		return bean;
	}

	public static ItemBeanDtoResponse convertToItemBeanDto(Set<ItemBean> allBeans) {
		Set<Item> items = new HashSet<ItemBeanDtoResponse.Item>();
		for (ItemBean bean : allBeans) {
			Item item = new Item(bean.getItem_name(),
					bean.getTaxes() != null && bean.getTaxes().size() > 0
							? bean.getTaxes().iterator().next().getTax_rate()
							: 20f,
					bean.getItem_price(), bean.getItem_category());
			item.setItem_id(bean.getItem_id());
			items.add(item);
		}
		return new ItemBeanDtoResponse(items);
	}

	public static InvoiceBeanDtoResponse convertToInvoiceBeanDto(InvoiceBean bean) {
		Set<InvoiceItemBean> invoice_items = bean.getInvoice_items();
		Set<InvoiceRecord> records = new HashSet<InvoiceBeanDtoResponse.InvoiceRecord>();
		for (InvoiceItemBean invoice_item : invoice_items) {
			Invoice invoiceBean = new Invoice(invoice_item.getInvoice_item().getItem_name(),
					invoice_item.getItem_category(), invoice_item.getTax_rate(), invoice_item.getPrice(),
					invoice_item.getQuantity());
			invoiceBean.setInvoice_id(invoice_item.getInvoice_item_id());
			records.add(new InvoiceRecord(invoiceBean, invoice_item.getTotal_tax(), invoice_item.getTotal_price(),
					invoice_item.getSelling_price()));
		}
		InvoiceBeanDtoResponse response = new InvoiceBeanDtoResponse(bean.getInvoice_id(), records, bean.getTotal_tax(),
				bean.getTotal_price(), bean.getSelling_price());
		bean.setCreated_date(
				bean.getCreated_date() == null ? new Date(System.currentTimeMillis()) : bean.getCreated_date());
		bean.setModified_date(new Date());
		response.setCreated_date(bean.getCreated_date());
		response.setLast_modified(bean.getModified_date());
		return response;
	}

	public static ItemBean createItemBean(ItemBeanDtoRequest request) {
		Item item = request.getItem();
		Set<TaxBean> taxes = new HashSet<>();
		taxes.add(new TaxBean("Other", item.getTax_rate(), "Other taxes", true));
		ItemBean itemBean = new ItemBean(item.getItem_name(), item.getPrice(), item.getItem_category(), taxes);
		return itemBean;
	}

	public static InvoiceBean updateAndGenrateInvoiceBeanDto(InvoiceBean invoiceBean,
			InvoiceBeanDtoRequest request) {
		Set<InvoiceItemBean> invoice_items = invoiceBean.getInvoice_items();
		Set<Invoice> invoices = request.getInvoiceItem();
		for (InvoiceItemBean item : invoice_items) {
			for (Invoice invoice : invoices) {
				if (item.getInvoice_item_id().equals(invoice.getInvoice_id())) {
					synchronizeInvoiceItemAndInvoice(item, invoice);
					break;
				}
			}
		}
		updateTaxes(invoiceBean);
		return invoiceBean;
	}

	private static void updateTaxes(InvoiceBean invoiceBean) {
		Set<InvoiceItemBean> beans = invoiceBean.getInvoice_items();
		double total_tax = 0;
		double total_price = 0;
		double total_selling_price = 0;
		for (InvoiceItemBean invoice : beans) {
			total_tax += invoice.getTotal_tax();
			total_price += invoice.getTotal_price();
			total_selling_price += invoice.getSelling_price();
		}
		invoiceBean.setTotal_tax(total_tax);
		invoiceBean.setTotal_price(total_price);
		invoiceBean.setSelling_price(total_selling_price);
	}

	public static void synchronizeInvoiceItemAndInvoice(InvoiceItemBean invoiceItemBean, Invoice invoiceBean) {
		if (invoiceItemBean.getItem_category() != invoiceBean.getItem_category()
				&& invoiceBean.getItem_category() != null)
			invoiceItemBean.setItem_category(invoiceBean.getItem_category());
		if (invoiceItemBean.getPrice() != invoiceBean.getPrice() && invoiceBean.getPrice() != null)
			invoiceItemBean.setPrice(invoiceBean.getPrice());
		if (invoiceItemBean.getTax_rate() != invoiceBean.getTax_rate() && invoiceBean.getTax_rate() != null)
			invoiceItemBean.setTax_rate(invoiceBean.getTax_rate());
		if (invoiceItemBean.getQuantity() != invoiceBean.getQuantity() && invoiceBean.getQuantity() != null)
			invoiceItemBean.setQuantity(invoiceBean.getQuantity());
		if(invoiceBean.getItem_name()!=null || !invoiceBean.getItem_name().trim().isEmpty())
			invoiceItemBean.getInvoice_item().setItem_name(invoiceBean.getItem_name());
		invoiceItemBean.setTotal_price((double) (invoiceItemBean.getPrice() * invoiceItemBean.getQuantity()));
		invoiceItemBean.setTotal_tax(invoiceItemBean.getTotal_price() * invoiceItemBean.getTax_rate() / 100);
		invoiceItemBean.setSelling_price(invoiceItemBean.getTotal_price() + invoiceItemBean.getTotal_tax());
	}
}

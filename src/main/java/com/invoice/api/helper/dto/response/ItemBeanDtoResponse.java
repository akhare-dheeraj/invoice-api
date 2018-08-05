package com.invoice.api.helper.dto.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.invoice.api.constants.ItemCategory;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemBeanDtoResponse {

	private Set<Item> items;
	
	public ItemBeanDtoResponse(Set<Item> items) {
		super();
		this.items = items;
	}


	public ItemBeanDtoResponse() {
		super();
	}

	public Set<Item> getItems() {
		return items;
	}


	public void setItems(Set<Item> items) {
		this.items = items;
	}


	public static class Item {
		private Long item_id;
		private String item_name;
		private Float tax_rate;
		private Float price;
		private ItemCategory item_category;
		public Item() {
			super();
		}
		public Item(String item_name, Float tax_rate, Float price, ItemCategory item_category) {
			super();
			this.price = price;
			this.item_name = item_name;
			this.tax_rate = tax_rate;
			this.item_category = item_category;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((item_name == null) ? 0 : item_name.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Item other = (Item) obj;
			if (item_name == null) {
				if (other.item_name != null)
					return false;
			} else if (!item_name.equals(other.item_name))
				return false;
			return true;
		}
		public String getItem_name() {
			return item_name;
		}
		public void setItem_name(String item_name) {
			this.item_name = item_name;
		}
		public float getTax_rate() {
			return tax_rate;
		}
		public void setTax_rate(float tax_rate) {
			this.tax_rate = tax_rate;
		}
		public ItemCategory getItem_category() {
			return item_category;
		}
		public void setItem_category(ItemCategory item_category) {
			this.item_category = item_category;
		}
		@Override
		public String toString() {
			return "Item [item_id=" + item_id + ", item_name=" + item_name + ", tax_rate=" + tax_rate + ", price="
					+ price + ", item_category=" + item_category + "]";
		}
		public Float getPrice() {
			return price;
		}
		public void setPrice(Float price) {
			this.price = price;
		}
		public void setTax_rate(Float tax_rate) {
			this.tax_rate = tax_rate;
		}
		public Long getItem_id() {
			return item_id;
		}
		public void setItem_id(Long item_id) {
			this.item_id = item_id;
		}
	}
}

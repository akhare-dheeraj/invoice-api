package com.invoice.api.helper.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.invoice.api.helper.dto.response.ItemBeanDtoResponse.Item;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemBeanDtoRequest {
		private Item item;

		public ItemBeanDtoRequest(Item item) {
			super();
			this.item = item;
		}

		public ItemBeanDtoRequest() {
		}

		@Override
		public String toString() {
			return "ItemBeanDtoRequest [item=" + item + "]";
		}

		public Item getItem() {
			return item;
		}

		public void setItem(Item item) {
			this.item = item;
		}
}

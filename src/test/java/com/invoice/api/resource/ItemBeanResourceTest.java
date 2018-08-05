package com.invoice.api.resource;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import com.invoice.api.constants.ItemCategory;
import com.invoice.api.helper.dto.request.ItemBeanDtoRequest;
import com.invoice.api.helper.dto.response.ItemBeanDtoResponse.Item;

public class ItemBeanResourceTest extends BaseResourceTest {
	
	@Test
	public void testCreateItem() {
		ItemBeanDtoRequest request = getItemRequest();
		Response response = target("/items/create")
							.request().post(Entity.entity(request, MediaType.APPLICATION_JSON));
		Assert.assertNotNull(response);
		System.out.println(response);
	}
	
	public void testGetAllItems() {
		
	}
	
	private Item getItemBean() {
		Item item = new Item("Dettotl", 20f, 100.0f, ItemCategory.NON_MEDICAL);
		return item;
	}
	
	private ItemBeanDtoRequest getItemRequest() {
		Item itemBean = getItemBean();
		ItemBeanDtoRequest request = new ItemBeanDtoRequest(itemBean);
		return request;
	}
}

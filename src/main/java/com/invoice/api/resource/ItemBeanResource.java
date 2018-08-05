package com.invoice.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.invoice.api.bean.ItemBean;
import com.invoice.api.helper.dto.request.ItemBeanDtoRequest;
import com.invoice.api.helper.dto.response.ItemBeanDtoResponse;
import com.invoice.api.helper.util.DtoUtils;
import com.invoice.api.service.ItemBeanService;

@Path("/items")
public class ItemBeanResource {

	@Autowired
	private ItemBeanService itemBeanService;

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllItems() {
		try {
			ItemBeanDtoResponse allItemBeans = itemBeanService.getAllItemBeans();
			return Response.ok(allItemBeans).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createItem(ItemBeanDtoRequest request) {
		try {
			ItemBean itemBean = DtoUtils.createItemBean(request);
			return Response.ok(itemBeanService.saveItem(itemBean)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e.getMessage()).build();
		}
	}
}

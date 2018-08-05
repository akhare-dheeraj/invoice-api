package com.invoice.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonObject;
import com.invoice.api.dto.validator.DtoValidator;
import com.invoice.api.helper.dto.request.InvoiceBeanDtoRequest;
import com.invoice.api.helper.dto.response.InvoiceBeanDtoResponse;
import com.invoice.api.service.InvoiceBeanService;

@Path("/invoices")
public class InvoiceBeanResource {

	@Autowired
	private InvoiceBeanService invoiceBeanService;

	@Autowired
	private DtoValidator validator;

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createInvoice(InvoiceBeanDtoRequest request) {
		try {
			validator.validate(request);
			InvoiceBeanDtoResponse response = invoiceBeanService.saveInvoice(request);
			return Response.ok(response).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getInvoiceById(@PathParam("id") Long id) {
		try {
			InvoiceBeanDtoResponse invoiceBean = invoiceBeanService.getInvoiceById(id);
			return Response.ok(invoiceBean).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e.getMessage()).build();

		}
	}

	@PUT
	@Path("/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateInvoice(@PathParam("id") Long id, InvoiceBeanDtoRequest request) {
		try {
			InvoiceBeanDtoResponse response = invoiceBeanService.updateInvoice(id, request);
			return Response.ok(response).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test( ) {
		JsonObject obj = new JsonObject();
		obj.addProperty("statu", Boolean.TRUE);
		return Response.ok(obj.toString()).build();
	}
}

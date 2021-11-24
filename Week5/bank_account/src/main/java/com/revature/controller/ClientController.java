package com.revature.controller;

import io.javalin.http.Handler;

import java.util.List;

import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.model.Client;
import com.revature.service.ClientService;


import io.javalin.Javalin;

public class ClientController {

	private ClientService clientService;
	
	public ClientController() {
		this.clientService = new ClientService();
	}
	
	//EDITING THE FIRST NAME OF THE CLIENT
	
	private Handler editFirstName = (ctx) -> {
		
		//Extracting the id from the URL path
		String clientId = ctx.pathParam("id");
		
		int id = Integer.parseInt(clientId);
		
		/********************************************************************
		 * Extracting the information in the form of JSON from the body and place
		 * it into an object of the type EditFirstNameDTO
		 **************************************************************************/
		AddOrUpdateClientDTO dto = ctx.bodyAsClass(AddOrUpdateClientDTO.class);
		
		/********************************************************************
		 * When we invoke the editFirstNameOfClient method (serviceLayer), it will contact the updateClient
		 * method in the DAO layer, which will then return the Client object representation of the record that was newly updated
		 */
		Client clientThatWasJustEdited = this.clientService.editFirstNameOfClient(clientId, dto.getFirstName());
		
		/***********************************
		 * We then take that object and convert it into JSON
		 * This JSON is then sent back to the client that sent the request which is Postman
		 * in this case
		 */
		ctx.json(clientThatWasJustEdited);
	};
	
	//EDITING LAST NAME OF THE CLIENT
	
	private Handler editLastName = (ctx) -> {
		
		//Extracting the id from the URL path
		String clientId = ctx.pathParam("id");
		
		int id = Integer.parseInt(clientId);		
		
		AddOrUpdateClientDTO dto = ctx.bodyAsClass(AddOrUpdateClientDTO.class);
		
		Client clientThatWasJustEdited = this.clientService.editLastNameOfClient(clientId, dto.getLastName());
		
		ctx.json(clientThatWasJustEdited);
	};
	
	//EDITING THE PHONE NUMBER OF THE CLIENT
	
	private Handler editPhoneNumber = (ctx) -> {
		
		//Extracting the id from the URL path
		String clientId = ctx.pathParam("id");
		
		int id = Integer.parseInt(clientId);		
		
		AddOrUpdateClientDTO dto = ctx.bodyAsClass(AddOrUpdateClientDTO.class);
		
		Client clientThatWasJustEdited = this.clientService.editPhoneNumberOfClient(clientId, dto.getPhoneNumber());
		
		ctx.json(clientThatWasJustEdited);
	};
	
	//ADDING NEW CLIENT
	
	private Handler addNewClient = (ctx) -> {
		AddOrUpdateClientDTO dto = ctx.bodyAsClass(AddOrUpdateClientDTO.class);
		
		Client client = this.clientService.addClient(dto);
		
		ctx.json(client);
		ctx.status(201);
	};
	
	//GETTING ALL CLIENTS
	
	private Handler getAllClients = (ctx) -> {
		List<Client> clients = this.clientService.getAllClient();
		
		ctx.json(clients);
	};
	
	//GETTING CLIENT BY ID
	
	private Handler getClientById = (ctx) -> {
		String id = ctx.pathParam("id");
		
		Client client = this.clientService.getClientById(id);	
		
		ctx.json(client);
	};
	
	//EDIT CLIENT BY ID
	
	private Handler editClientById = (ctx) -> {
		
		String clientId = ctx.pathParam("id");
				
		
		AddOrUpdateClientDTO dto = ctx.bodyAsClass(AddOrUpdateClientDTO.class); 
		
		
		Client clientThatWasJustEdited = this.clientService.editClientById(clientId, dto.getFirstName(),dto.getLastName(),
				dto.getPhoneNumber());
		
		ctx.json(clientThatWasJustEdited); 
		
	};
	
	//DELETE CLIENT BY ID
	private Handler deleteClientById = (ctx) -> {
		String id = ctx.pathParam("id");
		
		this.clientService.deleteClientById(id);	
		
		
		ctx.json("Client with an id " + id + " was deleted");
		
	};
	
	public void registerEndpoints(Javalin app) {
		app.patch("/clients/{id}/firstname", editFirstName);
		app.patch("/clients/{id}/lastname", editLastName);
		app.patch("/clients/{id}/phonenumber", editPhoneNumber);
				
		app.post("/clients", addNewClient);
		app.get("/clients/{id}", getClientById);
		app.get("clients", getAllClients);
		app.put("/clients/{id}", editClientById);
		app.delete("/clients/{id}", deleteClientById);
		
		
	}
}

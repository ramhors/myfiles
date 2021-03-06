package com.revature.controller;

import java.io.InputStream;
import java.util.List;

import org.apache.tika.Tika;

import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.MessageDTO;
import com.revature.dto.UpdateStatusDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.AuthorizationService;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;

public class ReimbursementController implements Controller{

	private AuthorizationService authService;
	private ReimbursementService reimbursementService;
	private Reimbursement reimbursements;
	
	public ReimbursementController() {
		this.authService = new AuthorizationService();
		this.reimbursementService = new ReimbursementService();
	}
	
	private Handler getReimbursement = (ctx) -> {
		//Role is permitted for Associate and trainer
		User currentLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeRegularAndManager(currentLoggedInUser );
		
		List<Reimbursement> reimbursements = this.reimbursementService.getReimbursement(currentLoggedInUser);
		
		ctx.json(reimbursements);
	};	
	
	
	private Handler submitReimbursement = (ctx) -> {
		User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeRegularAndManager(currentlyLoggedInUser);
		
		//AddReimbursementDTO dto = ctx.bodyAsClass(AddReimbursementDTO.class);
		Reimbursement reimbursements = ctx.bodyAsClass(Reimbursement.class);
		
		Reimbursement reimbursement = this.reimbursementService.submitReimbursement(currentlyLoggedInUser, reimbursements);
				
		ctx.json(reimbursement);
		ctx.status(201);
	};
	
	private Handler addReimbursement = (ctx) -> {
		User currentLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeRegularAndManager(currentLoggedInUser);
		
		String reimbursementType = ctx.formParam("type");
		
		String reimbursementAmount = ctx.formParam("amount");
		
		String reimbursementDescription = ctx.formParam("description");
		
		UploadedFile file = ctx.uploadedFile("receipt");
		
		if(file == null) {
			ctx.status(400);
			ctx.json(new MessageDTO("Must have an image to upload"));
		return;
		}
		
		if(reimbursementType == null) {
			ctx.status(400);
			ctx.json(new MessageDTO("Must enter reimbursement type"));
		return;
		}
		
		if(reimbursementAmount == null) {
			ctx.status(400);
			ctx.json(new MessageDTO("Must enter amount"));
		return;
		}
		if(reimbursementDescription == null) {
			ctx.status(400);
			ctx.json(new MessageDTO("Must enter amount"));
		return;
		}
		
		InputStream contents = file.getContent();
		
		Tika tika = new Tika();
		String mimeType = tika.detect(contents);
		
		Reimbursement reimbursement = this.reimbursementService.addReimbursement(currentLoggedInUser, reimbursementAmount, reimbursementDescription, mimeType,
				reimbursementType,contents);
		
		ctx.json("reimbursement");
		ctx.json("Adding reimbursement is sucessful");
		ctx.status(201);
		
	};
	
	
	
	private Handler getReimbursementById = (ctx) -> {
		String reimbursementId = ctx.pathParam("id");
		
		User currentLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeRegularAndManager(currentLoggedInUser );
		
		Reimbursement reimbursements = this.reimbursementService.getReimbursementById(currentLoggedInUser,reimbursementId);
		
		ctx.json(reimbursements);
		ctx.status(404);
	};
	
	private Handler updateReimbursement = (ctx) -> {
		User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeManager(currentlyLoggedInUser);
		
		String reimbursementId = ctx.pathParam("id");
		
		String status = ctx.formParam("status");		
		
		Reimbursement updateReimbursement = this.reimbursementService.updateReimbursement(currentlyLoggedInUser, reimbursementId,status);
		
		ctx.json(updateReimbursement);
	};
	
	private Handler updateWithReceipt = (ctx) -> {
		User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeRegularAndManager(currentlyLoggedInUser);
		
		String reimbursementId = ctx.pathParam("id");
		
		UploadedFile file = ctx.uploadedFile("reimb_receipt");
		
		if(file == null) {
			ctx.status(400);
			ctx.json(new MessageDTO("Must have an image to upload"));
			return;
		}
		InputStream content = file.getContent();
		
		Tika tika = new Tika();
		String mimeType = tika.detect(content);
		
		Reimbursement updateReimbursement = this.reimbursementService.updateWithReceipt(currentlyLoggedInUser,mimeType,reimbursementId,content);
		ctx.json(updateReimbursement);
	};
	
	private Handler getReceiptFromReimbursementById = (ctx) -> {
		
		User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeRegularAndManager(currentlyLoggedInUser);
		
		String reimbursementId = ctx.pathParam("id");
		
		InputStream image = this.reimbursementService.getImageFromReimbursementById(currentlyLoggedInUser, reimbursementId);
		
		Tika tika = new Tika();
		String mimeType = tika.detect(image);
		
		ctx.contentType(mimeType);
		ctx.result(image);
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/reimbursements", getReimbursement);
		app.get("/reimbursements/{id}", getReimbursementById);		
		app.get("/reimbursements/{id}/image", getReceiptFromReimbursementById);
		
	//	app.post("/reimbursements", submitReimbursement);
	//	app.post("/reimbursements/amount/type/description", submitReimbursement);
		app.post("/reimbursements", addReimbursement);
		
		app.patch("/reimbursements/{id}/status", updateReimbursement);
		app.patch("/reimbursements/{id}/image", updateWithReceipt);
		
		
		
	}

}

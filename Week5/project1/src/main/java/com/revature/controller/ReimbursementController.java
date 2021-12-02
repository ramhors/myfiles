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
		
		AddReimbursementDTO dto = ctx.bodyAsClass(AddReimbursementDTO.class);
		
		Reimbursement reimbursement = this.reimbursementService.submitReimbursement(currentlyLoggedInUser, dto);
		
		ctx.json(reimbursement);
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
		//UpdateStatusDTO dto = ctx.bodyAsClass(UpdateStatusDTO.class);
		
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
		
		app.post("/reimbursements", submitReimbursement);
		
		app.patch("/reimbursements/{id}/status", updateReimbursement);
		app.patch("/reimbursements/{id}/image", updateWithReceipt);
		
		
		
	}

}

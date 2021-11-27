package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import com.revature.dao.ReimbursementDAO;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class ReimbursementService {
	
	private ReimbursementDAO reimbursementDao;
	
	public ReimbursementService() {
		this.reimbursementDao = new ReimbursementDAO();
	}
	
	public ReimbursementService(ReimbursementDAO reimbursementDao) {
		this.reimbursementDao = reimbursementDao;
	}
	
	/***********
	 * Grabbing the reimbursement that is associated only to the logged user
	 **************/
	public List<Reimbursement> getReimbursement(User currentlyLoggedUser) throws SQLException {
		List<Reimbursement> reimbursements = null;
		
		if(currentlyLoggedUser.getUserRole().equals("manager")) {
			reimbursements = this.reimbursementDao.getAllReimbursement();
		}else if(currentlyLoggedUser.getUserRole().equals("employee")) {
			reimbursements = this.reimbursementDao.getAllReimbursementByEmployee(currentlyLoggedUser.getUserId());
		}
		return reimbursements;
	}

}

package com.revature.service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.dao.ReimbursementDAO;
import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.JoinTableDTO;
import com.revature.exception.InvalidParameterException;
import com.revature.exception.ReceiptNotFoundException;
import com.revature.exception.ReimbursementAlreadyResolvedException;
import com.revature.exception.ReimbursementNotFound;
import com.revature.exception.UnauthorizedException;
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

		if (currentlyLoggedUser.getUserRole().equals("manager")) {
			reimbursements = this.reimbursementDao.getAllReimbursement();
		} else if (currentlyLoggedUser.getUserRole().equals("employee")) {
			reimbursements = this.reimbursementDao.getAllReimbursementByEmployee(currentlyLoggedUser.getUserId());
		}
		return reimbursements;
	}

	public Reimbursement getReimbursementById(User currentlyLoggedUser, String reimbursementId)
			throws SQLException, ReimbursementNotFound, UnauthorizedException, InvalidParameterException {
		try {
			int id = Integer.parseInt(reimbursementId);

			Reimbursement reimbursement = this.reimbursementDao.getReimbursementByItsId(id);

			if (reimbursement == null) {
				throw new ReimbursementNotFound("Reimbursement with id of " + reimbursementId + "was not found");
			}
			return reimbursement;
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int value");
		}
	}

	public Reimbursement updateWithReceipt(User currentlyLoggedInUser, String mimeType, String reimbursementId,
			InputStream content) throws InvalidParameterException, SQLException, ReimbursementNotFound {
		try {
			int id = Integer.parseInt(reimbursementId);

			Reimbursement reimbursement = this.reimbursementDao.getReimbursementByItsId(id);

			if (reimbursement == null) {
				throw new ReimbursementNotFound("Reimbursement with id " + reimbursementId + " was not found");
			}
			if (reimbursement.getReceipt() == null) {
				this.reimbursementDao.updateWithReceipt(id, content);
			}

			Set<String> allowedFileTypes = new HashSet<>();
			allowedFileTypes.add("image/jpeg");
			allowedFileTypes.add("image/jpg");
			allowedFileTypes.add("image/png");
			allowedFileTypes.add("image/gif");

			if (!allowedFileTypes.contains(mimeType)) {
				throw new InvalidParameterException("Only PNG,JPEG or GIF are allowed");
			}

			return this.reimbursementDao.getReimbursementByItsId(id);

		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Reimbursement id supplied must be an int");
		}

	}

	// checking if the mimetype is either image/jpg, image/png or image/gif
	public Reimbursement addReimbursement(User currentlyLoggedInUser, double amount, String description,
			String mimeType, String reimbursementType, InputStream content)
			throws SQLException, InvalidParameterException {
		Set<String> allowedFileTypes = new HashSet<>();
		allowedFileTypes.add("image/jpeg");
		allowedFileTypes.add("image/jpg");
		allowedFileTypes.add("image/png");
		allowedFileTypes.add("image/gif");

		if (!allowedFileTypes.contains(mimeType)) {
			throw new InvalidParameterException("When adding a receipt image, only PNG, JPEG, or GIF are allowed");
		}
		// Whoever is logged in will be the actual author of the reimbursement
		int authorId = currentlyLoggedInUser.getUserId();

		Reimbursement addedReimbursement = this.reimbursementDao.addReimbursement(amount, reimbursementType,
				description, authorId, content);

		return addedReimbursement;
	}

	public Reimbursement submitReimbursement(User currentlyLoggedInUser, AddReimbursementDTO dto)
			throws SQLException, InvalidParameterException {

		int authorId = currentlyLoggedInUser.getUserId();

		if (dto.getAmount() == 0) {
			throw new InvalidParameterException("You have to an amount to submit");
		}
		if (dto.getType().trim().equals("")) {
			throw new InvalidParameterException("Type field can not be empty");
		}
		if (dto.getDescription().trim().equals("")) {
			throw new InvalidParameterException("Description field can not be empty");
		}
		dto.setDescription(dto.getDescription().trim());
		dto.setType(dto.getType().trim());
		Reimbursement insertReimb = this.reimbursementDao.submitReimbursement(dto, authorId);

		return insertReimb;
	}

	public Reimbursement updateReimbursement(User currentlyLoggedUser, String reimbursementId, String status)
			throws SQLException, ReimbursementNotFound, ReimbursementAlreadyResolvedException,
			InvalidParameterException {
		try {
			int id = Integer.parseInt(reimbursementId);
			Reimbursement reimbursement = this.reimbursementDao.getReimbursementByItsId(id);

			if (reimbursement == null) {
				throw new ReimbursementNotFound("Reimbursement with id of" + reimbursementId + "was not found");
			}

			if (reimbursement.getResolver() == 0) {
				this.reimbursementDao.updateReimbursement(id, status, currentlyLoggedUser.getUserId());
			} else {
				throw new ReimbursementAlreadyResolvedException("Reimbursement is already paid");
			}

			return this.reimbursementDao.getReimbursementByItsId(id);
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int");
		}
	}

	public InputStream getImageFromReimbursementById(User currentlyLoggedInUser, String reimbursementId)
			throws SQLException, UnauthorizedException, ReceiptNotFoundException, InvalidParameterException {
		try {
			int id = Integer.parseInt(reimbursementId);

			if (currentlyLoggedInUser.getUserRole().equals("employee")) {
				int employeeId = currentlyLoggedInUser.getUserId();
				List<Reimbursement> reimbursementBelongToEmployee = this.reimbursementDao
						.getAllReimbursementByEmployee(employeeId);

				Set<Integer> reimbursementIdsEncoutered = new HashSet<>();
				for (Reimbursement r : reimbursementBelongToEmployee) {
					reimbursementIdsEncoutered.add(r.getReimbId());
				}
				if (!reimbursementIdsEncoutered.contains(id)) {
					throw new UnauthorizedException("You can not access a receipt that does not belong to yourself");
				}
			}
			// Grabbing the image
			InputStream image = this.reimbursementDao.getReceiptFromReimbursementById(id);

			if (image == null) {
				throw new ReceiptNotFoundException("Receipt was not found for reimbursement id " + id);
			}

			return image;
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Reimbursement id supplied must be an int");
		}

	}

}

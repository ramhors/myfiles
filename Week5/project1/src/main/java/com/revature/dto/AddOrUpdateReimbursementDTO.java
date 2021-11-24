package com.revature.dto;

import java.util.Objects;

public class AddOrUpdateReimbursementDTO {

	private String dateSubmitted;
	private String dateResolved;
	private String status;
	private String type;
	private String description;
	private String receipt;
	private int author;
	
	
	AddOrUpdateReimbursementDTO() {}


	public AddOrUpdateReimbursementDTO(String dateSubmitted, String dateResolved, String status, String type,
			String description, String receipt, int author) {
		super();
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.status = status;
		this.type = type;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
	}


	public String getDateSubmitted() {
		return dateSubmitted;
	}


	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}


	public String getDateResolved() {
		return dateResolved;
	}


	public void setDateResolved(String dateResolved) {
		this.dateResolved = dateResolved;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getReceipt() {
		return receipt;
	}


	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}


	public int getAuthor() {
		return author;
	}


	public void setAuthor(int author) {
		this.author = author;
	}


	@Override
	public int hashCode() {
		return Objects.hash(author, dateResolved, dateSubmitted, description, receipt, status, type);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddOrUpdateReimbursementDTO other = (AddOrUpdateReimbursementDTO) obj;
		return author == other.author && Objects.equals(dateResolved, other.dateResolved)
				&& Objects.equals(dateSubmitted, other.dateSubmitted) && Objects.equals(description, other.description)
				&& Objects.equals(receipt, other.receipt) && Objects.equals(status, other.status)
				&& Objects.equals(type, other.type);
	}


	@Override
	public String toString() {
		return "AddOrUpdateReimbursementDTO [dateSubmitted=" + dateSubmitted + ", dateResolved=" + dateResolved
				+ ", status=" + status + ", type=" + type + ", description=" + description + ", receipt=" + receipt
				+ ", author=" + author + "]";
	}

	
	
}

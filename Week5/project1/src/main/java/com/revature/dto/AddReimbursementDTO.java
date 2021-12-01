package com.revature.dto;

import java.util.Objects;

public class AddReimbursementDTO {
	
	private double amount;		
	private String type;
	private String description;
	private String receipt;
	private String submitDate;
	private int author;
	
	public AddReimbursementDTO() {}

	public AddReimbursementDTO(double amount, String type, String description,String submitDate) {
		super();
		this.amount = amount;
		this.type = type;
		this.description = description;
		this.submitDate = submitDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, author, description, receipt, submitDate, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddReimbursementDTO other = (AddReimbursementDTO) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount) && author == other.author
				&& Objects.equals(description, other.description) && Objects.equals(receipt, other.receipt)
				&& Objects.equals(submitDate, other.submitDate) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "AddReimbursementDTO [amount=" + amount + ", type=" + type + ", description=" + description
				+ ", receipt=" + receipt + ", submitDate=" + submitDate + ", author=" + author + "]";
	}

	
}

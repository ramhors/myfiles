package com.revature.model;

import java.util.Objects;

public class Reimbursement {

	private int reimbId;
	private double amount;
	private String dateSubmitted;
	private String dateResolved;
	private String status;
	private String type;
	private String description;
	private String receipt;
	private int author;
	private int resolver;
	
	public Reimbursement() {}

	public Reimbursement(int reimbId, double amount, String dateSubmitted, String dateResolved, String status,
			String type, String description, String receipt, int author, int resolver) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.status = status;
		this.type = type;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
	}
	
	public Reimbursement(int reimbId, double amount, String type, String description, int author,String dateSubmitted) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.type = type;
		this.description = description;		
		this.author = author;
		this.dateSubmitted = dateSubmitted;
	}
	

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, author, dateResolved, dateSubmitted, description, receipt, reimbId, resolver,
				status, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount) && author == other.author
				&& Objects.equals(dateResolved, other.dateResolved)
				&& Objects.equals(dateSubmitted, other.dateSubmitted) && Objects.equals(description, other.description)
				&& Objects.equals(receipt, other.receipt) && reimbId == other.reimbId && resolver == other.resolver
				&& Objects.equals(status, other.status) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", amount=" + amount + ", dateSubmitted=" + dateSubmitted
				+ ", dateResolved=" + dateResolved + ", status=" + status + ", type=" + type + ", description="
				+ description + ", receipt=" + receipt + ", author=" + author + ", resolver=" + resolver + "]";
	}

	
	
		
}

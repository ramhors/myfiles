package com.revature.dto;

import java.util.Objects;

public class UpdateStatusDTO {
	
	private String status;
	String resolvedDate;
	private int resolverId;
	private int reimbursementId;
	
	public UpdateStatusDTO() {
		
	}

	public UpdateStatusDTO(String status, String resolvedDate, int resolverId, int reimbursementId) {
		super();
		this.status = status;
		this.resolvedDate = resolvedDate;
		this.resolverId = resolverId;
		this.reimbursementId = reimbursementId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(String resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reimbursementId, resolvedDate, resolverId, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateStatusDTO other = (UpdateStatusDTO) obj;
		return reimbursementId == other.reimbursementId && Objects.equals(resolvedDate, other.resolvedDate)
				&& resolverId == other.resolverId && Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "UpdateStatusDTO [status=" + status + ", resolvedDate=" + resolvedDate + ", resolverId=" + resolverId
				+ ", reimbursementId=" + reimbursementId + "]";
	}

	
	
}

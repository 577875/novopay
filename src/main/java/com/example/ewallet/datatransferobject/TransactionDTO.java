package com.example.ewallet.datatransferobject;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;


/** Client Facing Model of Transaction **/
public class TransactionDTO {

	@ApiModelProperty(required = false, hidden = true)
	private Long id;

	@ApiModelProperty(required = false, hidden = true)
	private Long userAccountId;

	private BigDecimal amount;
	private String details;
	private Date transactionDate;
	private Long transactionReference;

	public TransactionDTO() {
	}

	public TransactionDTO(TransactionDTOBuilder builder) {
		id = builder.id;
		userAccountId = builder.userAccountId;
		amount = builder.amount;
		details = builder.details;
		transactionDate = builder.transactionDate;
		transactionReference = builder.transactionReference;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Long getTransactionReference() {
		return transactionReference;
	}

	public void setTransactionReference(Long transactionReference) {
		this.transactionReference = transactionReference;
	}

	public static class TransactionDTOBuilder {

		private Long id;
		private Long userAccountId;
		private BigDecimal amount;
		private String details;
		private Date transactionDate;
		private Long transactionReference;

		public TransactionDTOBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public TransactionDTOBuilder setUserAccountId(Long userAccountId) {
			this.userAccountId = userAccountId;
			return this;
		}

		public TransactionDTOBuilder setAmount(BigDecimal amount) {
			this.amount = amount;
			return this;
		}

		public TransactionDTOBuilder setDetails(String details) {
			this.details = details;
			return this;
		}

		public TransactionDTOBuilder setTransactionDate(Date transactionDate) {
			this.transactionDate = transactionDate;
			return this;
		}

		public TransactionDTOBuilder setTransactionReference(Long transactionReference) {
			this.transactionReference = transactionReference;
			return this;
		}

		public TransactionDTO build() {
			return new TransactionDTO(this);
		}

	}

}

package com.example.ewallet.datatransferobject;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/** Client Facing Model of Transaction **/
public class UserAccountDTO {

	@ApiModelProperty(required = false, hidden = true)
	private Long id;

	private String userName;
	private String email;
	private Date dateCreated;

	@ApiModelProperty(required = false, hidden = true)
	private BigDecimal balance;

	public UserAccountDTO() {
	}

	public UserAccountDTO(UserAccountDTOBuilder builder) {
		id = builder.id;
		userName = builder.userName;
		email = builder.email;
		dateCreated = builder.dateCreated;
		balance = builder.balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public static class UserAccountDTOBuilder {

		private Long id;
		private String userName;
		private String email;
		private Date dateCreated;
		private BigDecimal balance;

		public UserAccountDTOBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public UserAccountDTOBuilder setUserName(String userName) {
			this.userName = userName;
			return this;
		}

		public UserAccountDTOBuilder setEmail(String email) {
			this.email = email;
			return this;
		}

		public UserAccountDTOBuilder setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
			return this;
		}

		public UserAccountDTOBuilder setBalance(BigDecimal balance) {
			this.balance = balance;
			return this;
		}

		public UserAccountDTO build() {
			return new UserAccountDTO(this);
		}

	}

}

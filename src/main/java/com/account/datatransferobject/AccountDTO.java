package com.account.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {
	@JsonIgnore
	private Long id;

	@NotNull(message = "FirstName can not be null!")
	private String firstname;

	private String lastname;
	private String accountbankid;

	private AccountDTO() {
	}

	private AccountDTO(Long id, String firstname, String lastname, String accountbankid) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.accountbankid = accountbankid;
	}

	public static AccountDTOBuilder newBuilder() {
		return new AccountDTOBuilder();
	}

	@JsonProperty
	public Long getId() {
		return id;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @return the accountbankid
	 */
	public String getAccountbankid() {
		return accountbankid;
	}

	public static class AccountDTOBuilder {
		private Long id;
		private String firstname;
		private String lastname;
		private String accountbankid;

		public AccountDTOBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public AccountDTOBuilder setFirstname(String firstname) {
			this.firstname = firstname;
			return this;
		}

		public AccountDTOBuilder setLastname(String lastname) {
			this.lastname = lastname;
			return this;
		}

		public AccountDTOBuilder setAccountbankid(String accountbankid) {
			this.accountbankid = accountbankid;
			return this;
		}

		public AccountDTO createAccountDTO() {
			return new AccountDTO(id, firstname, lastname, accountbankid);
		}

	}
}

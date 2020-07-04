package com.example.ewallet.datatransferobject.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.ewallet.datatransferobject.UserAccountDTO;
import com.example.ewallet.models.UserAccount;

/** The Class to map Database Objects and Data Transfer Objects */
public class UserAccountMapper {

	/** converts DTO to Database Object */
	public static UserAccount dtoToDO(UserAccountDTO a) {
		UserAccount account = new UserAccount.UserAccountBuilder().setDateCreated(new Date()).setId(a.getId())
				.setUserName(a.getUserName()).setEmail(a.getEmail()).build();
		return account;
	}

	/** Converts Database Object to DTO */
	public static UserAccountDTO doToDTO(UserAccount a) {
		double balance = a.getTransactions().stream().mapToDouble(o -> o.getAmount().doubleValue()).sum();
		UserAccountDTO dto = new UserAccountDTO.UserAccountDTOBuilder().setId(a.getId())
				.setDateCreated(a.getDateCreated()).setUserName(a.getUserName()).setId(a.getId()).setEmail(a.getEmail())
				.setBalance(new BigDecimal(balance)).build();
		return dto;
	}

	/** Converts List of Database Object to List of DTO */
	public static List<UserAccountDTO> doToDTOList(List<UserAccount> account) {
		return account.stream().map(UserAccountMapper::doToDTO).collect(Collectors.toList());
	}

}

package com.account.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.account.datatransferobject.AccountDTO;
import com.account.domainobject.AccountDO;


/**
 * 
 * @author neel
 *
 */
public class AccountMapper
{
	/**
	 * 
	 * @param accountDTO
	 * @param id
	 * @return
	 */
    public static AccountDO makeAccountDO(AccountDTO accountDTO, long id)
    {
        return new AccountDO(id, accountDTO.getFirstname(), accountDTO.getLastname());
    }

    /**
     * 
     * @param accountDO
     * @return
     */
    public static AccountDTO makeAccountDTO(AccountDO accountDO)
    {
        AccountDTO.AccountDTOBuilder accountDTOBuilder = AccountDTO.newBuilder()
            .setId(accountDO.getId())
            .setFirstname(accountDO.getFirstname())
            .setLastname(accountDO.getLastname());
      
        return accountDTOBuilder.createAccountDTO();
    }
    
    /**
     * 
     * @param accountDO
     * @param accBank
     * @return
     */
    public static AccountDTO makeBankDTO(AccountDO accountDO, String accBank)
    {
        AccountDTO.AccountDTOBuilder accountDTOBuilder = AccountDTO.newBuilder()
            .setId(accountDO.getId())
            .setFirstname(accountDO.getFirstname())
            .setLastname(accountDO.getLastname())
            .setAccountbankid(accBank);
      
        return accountDTOBuilder.createAccountDTO();
    }

    /**
     * 
     * @param accounts
     * @return
     */
	public static List<AccountDTO> makeDriverDTOList(Collection<AccountDO> accounts)
    {
        return accounts.stream()
            .map(AccountMapper::makeAccountDTO)
            .collect(Collectors.toList());
    }
}

package com.account.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.account.dataaccessobject.AccountRepository;
import com.account.domainobject.AccountDO;
import com.account.exception.EntityNotFoundException;

/**
 * 
 * @author neel
 *
 */
@Service
public class DefaultBankService implements BankService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultBankService.class);

	private final AccountRepository accountRepository;

	public DefaultBankService(final AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDO find(Long id) throws EntityNotFoundException {
		return findDriverChecked(id);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws EntityNotFoundException
	 */
	private AccountDO findDriverChecked(long id) throws EntityNotFoundException {
		return accountRepository.findById(id);
	}

	

}

package com.account.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.account.dataaccessobject.AccountRepository;
import com.account.domainobject.AccountDO;
import com.account.exception.ConstraintsViolationException;
import com.account.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have
 * business logic for account details.
 * <p/>
 */
@Service
public class DefaultAccountService implements AccountService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultAccountService.class);

	private final AccountRepository accountRepository;

	public DefaultAccountService(final AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDO find(Long id) throws EntityNotFoundException{
		return findAccountChecked(id);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws EntityNotFoundException 
	 */
	private AccountDO findAccountChecked(long id) throws EntityNotFoundException{
		AccountDO account;
		try {
			account = accountRepository.findById(id);
		}
		catch (Exception e) {
			LOG.warn("EntityNotFoundException while finding a account: {}", id, e);
			throw new EntityNotFoundException(e.getMessage());
		}
		return account;
	}
	
	@Override
	public AccountDO create(AccountDO accountDO) throws ConstraintsViolationException {
		AccountDO account;
		try {
			account = accountRepository.save(accountDO);
		} catch (Exception e) {
			LOG.warn("ConstraintsViolationException while creating a account: {}", accountDO, e);
			throw new ConstraintsViolationException(e.getMessage());
		}
		return account;
	}

	@Override
	public List<AccountDO> findAll() {
		return (List<AccountDO>) accountRepository.findAll();		
	}
}

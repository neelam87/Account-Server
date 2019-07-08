package com.account.service;

import java.util.List;

import com.account.domainobject.AccountDO;
import com.account.exception.ConstraintsViolationException;
import com.account.exception.EntityNotFoundException;

/**
 * @author neel
 **/
public interface AccountService
{

	AccountDO find(Long id) throws EntityNotFoundException;

    AccountDO create(AccountDO driverDO) throws ConstraintsViolationException;

    List<AccountDO> findAll();

}

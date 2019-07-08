package com.account.service;

import java.util.List;

import com.account.domainobject.AccountDO;
import com.account.exception.EntityNotFoundException;

public interface BankService
{

	AccountDO find(Long id) throws EntityNotFoundException;
}

package com.account.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.account.domainobject.AccountDO;

/**
 * Database Access Object for account table.
 * <p/>
 */

public interface AccountRepository extends CrudRepository<AccountDO, Long>
{
	AccountDO findById(long id);
}
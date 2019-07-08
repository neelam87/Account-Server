/**
 * 
 */
package com.account.util;

import com.account.domainobject.AccountDO;

/**
 * @author neel
 *
 */
public class AccountUtil {
	 private static String IBAN = "DE89370400440532013087";
	
	/**
	 * @param accountDetails
	 * @return
	 */
	public static String accountToIBAN(AccountDO accountDetails) {
		String accBank;
		int accLength = accountDetails.getId().toString().length();
		IBAN = (IBAN == null || IBAN.length() == 0) ? null : (IBAN.substring(0, IBAN.length() - accLength));
		accBank = IBAN.concat(accountDetails.getId().toString());
		return accBank;
	}
}

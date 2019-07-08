package com.account.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.account.controller.mapper.AccountMapper;
import com.account.datatransferobject.AccountDTO;
import com.account.domainobject.AccountDO;
import com.account.exception.ConstraintsViolationException;
import com.account.exception.EntityNotFoundException;
import com.account.service.AccountService;
import com.account.util.AccountUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author neel
 * All operations with a account details will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1")
@Api(tags = "BankAccountDetails", description = "Bank account service")
public class AccountController {

	private final AccountService accountService;

	@Autowired
	public AccountController(final AccountService accountService) {
		this.accountService = accountService;
	}

	@ApiOperation(value = "Get account details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get the account details") })
	@GetMapping("/account/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountDTO getAccount(@PathVariable long id) throws EntityNotFoundException {
		return AccountMapper.makeAccountDTO(accountService.find(id));
	}

	
	@ApiOperation(value = "Create account")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Create account") })
	@PostMapping("/account/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountDTO createAccount(@Valid @RequestBody AccountDTO accountDTO, @PathVariable long id)
			throws ConstraintsViolationException {
		AccountDO accountDO = AccountMapper.makeAccountDO(accountDTO, id);
		return AccountMapper.makeAccountDTO(accountService.create(accountDO));
	}

	
	@ApiOperation(value = "Get all account details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get all account details") })
	@GetMapping("/accounts")
	@ResponseStatus(HttpStatus.OK)
	public List<AccountDTO> getAllAccount() {
		return AccountMapper.makeDriverDTOList(accountService.findAll());
	}
	
	
	@ApiOperation(value = "Get bank details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get bank details") })
	@GetMapping("/bank/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountDTO getBankDetails(@PathVariable long id) throws EntityNotFoundException {
		AccountDO accountDetails = accountService.find(id);
		String accBank = null;
		if(accountDetails!=null){
			accBank = AccountUtil.accountToIBAN(accountDetails);
		}
		return AccountMapper.makeBankDTO(accountService.find(id), accBank);
	}
}

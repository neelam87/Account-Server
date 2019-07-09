package com.account.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.account.domainobject.AuthToken;
import com.account.service.AuthService;
import com.bsc.rfg.portfolio.domain.model.AuthRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/oauth")
@Api(tags = "OAuth", description = "Auth service")
public class AuthController {

	@Autowired
	public AuthService authService;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	@ApiOperation(value = "Hits the Internal Auth Service to Get the token")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Received Token", response = AuthToken.class),
			@ApiResponse(code = 400, message = "Bad request error"), @ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ResponseBody
	public ResponseEntity<?> getData(@ApiParam("Authorization Request") @RequestBody AuthRequest authRequest) {
		ResponseEntity<AuthToken> response = null;
		try {
			response = authService.getToken(authRequest);
			return ResponseEntity.created(URI.create("/token")).body(response);
		} catch (Exception ex) {
			//return buildAuthErrorResponse(ex);
		}
		return response;
	}

//	private ResponseEntity<?> buildAuthErrorResponse(Exception ex) {
//		HttpStatus statusCode;
//		ExceptionResponse error = new ExceptionResponse();
//		if (ex instanceof PortfolioException) {
//			error.setErrorCode(((PortfolioException) ex).getErrorCode());
//			error.setErrorMessage(((PortfolioException) ex).getErrorMsg());
//			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
//		} else
//			if (ex instanceof AuthorizationException) {
//			error.setErrorCode(PortfolioConstants.UNAUTHORIZED_ERROR_CODE);
//			error.setErrorMessage(PortfolioConstants.UNAUTHORIZED_ERROR_MSG);
//			statusCode = HttpStatus.UNAUTHORIZED;
//		} 
//			else {
//			error.setErrorCode(PortfolioConstants.UNEXPECTED_ERROR_CODE);
//			error.setErrorMessage(PortfolioConstants.UNEXPECTED_ERROR_MSG + " - " + ex.getMessage());
//			statusCode = HttpStatus.BAD_REQUEST;
//		}
//		return new ResponseEntity<ExceptionResponse>(error, statusCode);
//	}
}
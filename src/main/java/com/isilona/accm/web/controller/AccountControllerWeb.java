package com.isilona.accm.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.isilona.accm.db.model.Account;
import com.isilona.accm.web.data.response.AccountDto;
import com.isilona.accm.web.service.AccountServiceWeb;

@RestController
@RequestMapping(value = "/account")
public class AccountControllerWeb {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountControllerWeb.class);

    @Autowired
    private AccountServiceWeb userServiceWeb;

    // ALL
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<AccountDto>> getAccounts() {
	LOGGER.info("GET /account /list");
	List<AccountDto> entityList = userServiceWeb.getAccounts();
	return new ResponseEntity<List<AccountDto>>(entityList, HttpStatus.OK);
    }

    // FIND
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AccountDto getAccountById(@PathVariable("id") Long id) {
	LOGGER.info("GET /account /{id}: " + id);
	return userServiceWeb.getAccountById(id);
    }

    // DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public boolean deleteAccountById(@PathVariable("id") Long id) {
	LOGGER.info("DELETE /account /{id}: " + id);
	userServiceWeb.deleteAccountById(id);
	return true;
    }

    // UPDATE
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Account saveAccountById(@RequestBody AccountDto accountDTO, @PathVariable Long id) {
	LOGGER.info("UPDATE /account /{id}: " + id);

	Account updatedAccount = userServiceWeb.saveAccount(accountDTO);

	return updatedAccount;
    }

    // NEW
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Account registerAccount(@Valid @RequestBody AccountDto newAccount, WebRequest request) {
	LOGGER.info("POST /account/register");

	Account savedAccount = userServiceWeb.saveAccount(newAccount);

	return savedAccount;
    }

}

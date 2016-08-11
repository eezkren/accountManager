package com.isilona.accm.web.service;

import java.util.ArrayList;
import java.util.List;

//import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.isilona.accm.db.model.Account;
import com.isilona.accm.db.repository.AccountRepository;
import com.isilona.accm.web.data.mapping.AccountMapper;
import com.isilona.accm.web.data.response.AccountDto;

/**
 * Account service handling account creation and manipulations
 * 
 * @author iivanov
 *
 */

@Service
public class AccountServiceWeb {

    @Autowired
    private AccountMapper mapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    // CREATE / UPDATE

    /**
     * Create / Update {@link Account}
     * 
     * @param accountDto
     * @return
     */
    public Account saveAccount(AccountDto accountDto) {

	Account toSave = mapper.accountDtoToAccount(accountDto);

	Account saved = accountRepository.save(toSave);

	return saved;
    }

    // READ

    /**
     * Find one {@link Account} by a given id
     * 
     * @param id
     * @return
     */
    public AccountDto getAccountById(Long id) {

	Account found = accountRepository.findOne(id);

	AccountDto responseDto = mapper.accountToAccountDto(found);

	return responseDto;
    }

    // DELETE

    /**
     * Delete one {@link Account} by a given id
     * 
     * @param id
     * @return
     */

    public void deleteAccountById(Long id) {

	accountRepository.delete(id);

    }

    // ALL

    /**
     * Get list with all {@link Account}s
     * 
     * @param accountToCreate
     * @return status of the request
     */

    public List<AccountDto> getAccounts() {

	List<Account> allList = accountRepository.findAll();
	List<AccountDto> responseList = new ArrayList<AccountDto>(allList.size());

	for (Account entity : allList) {
	    AccountDto dto = mapper.accountToAccountDto(entity);
	    responseList.add(dto);
	}

	return responseList;
    }

}
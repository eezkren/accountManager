package com.isilona.accm.web.service;

import java.util.ArrayList;
import java.util.List;

//import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

import com.isilona.accm.db.model.Account;
import com.isilona.accm.db.repository.AccountRepository;
import com.isilona.accm.web.data.mapping.AccountMapper;
import com.isilona.accm.web.data.mapping.CustomRevisionMapper;
import com.isilona.accm.web.data.response.AccountDto;
import com.isilona.accm.web.data.response.AccountRevisionDto;
import com.isilona.accm.web.data.response.CustomRevisionDto;

/**
 * Account service handling account creation and manipulations
 * 
 * @author iivanov
 *
 */

@Service
public class AccountServiceWeb {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private CustomRevisionMapper customRevisionMapper;

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
    public AccountDto saveAccount(AccountDto accountDto) {

	Account toSave = accountMapper.accountDtoToAccount(accountDto);

	Account saved = accountRepository.save(toSave);

	AccountDto result = accountMapper.accountToAccountDto(saved);

	return result;
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

	AccountDto responseDto = accountMapper.accountToAccountDto(found);

	return responseDto;
    }

    // DELETE

    public AccountDto deleteAccountById(Long id) {

	Account found = accountRepository.findOne(id);

	AccountDto responseDto = accountMapper.accountToAccountDto(found);

	accountRepository.delete(found);

	return responseDto;

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
	    AccountDto dto = accountMapper.accountToAccountDto(entity);
	    responseList.add(dto);
	}

	return responseList;
    }

    // HISTORY

    public List<AccountRevisionDto> getHistory(Long id) {

	Revisions<Integer, Account> revisionsList = accountRepository.findRevisions(id);
	List<AccountRevisionDto> responseList = new ArrayList<AccountRevisionDto>(revisionsList.getContent().size());

	for (Revision<Integer, Account> revision : revisionsList) {

	    AccountRevisionDto dto = new AccountRevisionDto();

	    AccountDto accountDto = accountMapper.accountToAccountDto(revision.getEntity());
	    CustomRevisionDto customRevisionDto = customRevisionMapper
		    .customRevisionToCustomRevisionDto(revision.getMetadata().getDelegate());

	    dto.setAccount(accountDto);
	    dto.setRevision(customRevisionDto);

	    responseList.add(dto);
	}

	return responseList;
    }

}
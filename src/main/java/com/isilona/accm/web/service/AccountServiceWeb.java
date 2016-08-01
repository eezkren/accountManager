package com.isilona.accm.web.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    /**
     * Get list with all {@link Account}s
     * 
     * @param accountToCreate
     * @return status of the request
     */

    // ALL
    public List<AccountDto> getAccounts() {

	List<Account> accounts = accountRepository.findAll();
	List<AccountDto> usersResponse = new ArrayList<AccountDto>(accounts.size());

	for (Account account : accounts) {
	    // AccountDto userDTO = object2dto(account);
	    AccountDto userDTO = mapper.accountToAccountDto(account);
	    usersResponse.add(userDTO);
	}

	return usersResponse;
    }

    /**
     * Find one {@link Account} by a given id
     * 
     * @param id
     * @return
     */

    // FIND
    public AccountDto getAccountById(Long id) {

	Account account = accountRepository.findOne(id);
	// AccountDto responseDTO = object2dto(account);
	AccountDto responseDTO = mapper.accountToAccountDto(account);

	return responseDTO;
    }

    /**
     * delete one {@link Account} by a given id
     * 
     * @param id
     * @return
     */

    // DELETE
    public void deleteAccountById(Long id) {

	accountRepository.delete(id);

    }

    /**
     * delete one {@link Account} by a given id
     * 
     * @param id
     * @return
     */
    public Account saveAccount(AccountDto accountDTO) {

	// Account toSave = dto2object(accountDTO);

	Account toSave = mapper.accountDtoToAccount(accountDTO);

	Account newAcc = accountRepository.save(toSave);

	return newAcc;
    }

    /**
     * 
     * Transformation method - Creates {@link AccountDto} from given
     * {@link Account}
     * 
     * @param account
     * @return
     */
    // private AccountDto object2dto(Account account) {
    //
    // AccountDto result = new AccountDto();
    //
    // result.setId(account.getId());
    // result.setFirstName(account.getFirstName());
    // result.setLastName(account.getLastName());
    // result.setEmail(account.getEmail());
    //
    // if (null == account.getDateOfBirth()) {
    // // result.setDateOfBirth("");
    // } else {
    // // result.setDateOfBirth(account.getDateOfBirth().toString());
    // }
    //
    // return result;
    //
    // }

    /**
     * 
     * Transformation method - Creates {@link Account} from given
     * {@link AccountDto}
     * 
     * @param dto
     * @return
     */
    // private Account dto2object(AccountDto dto) {
    //
    // Account result = new Account();
    //
    // result.setId(dto.getId());
    // result.setFirstName(dto.getFirstName());
    // result.setLastName(dto.getLastName());
    // result.setEmail(dto.getEmail());
    //
    // if ("".equals(dto.getDateOfBirth())) {
    // result.setDateOfBirth(null);
    // } else {
    // DateTimeFormatter formatter =
    // DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    // result.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth(), formatter));
    // }
    //
    // return result;
    //
    // }

}
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
import com.isilona.accm.web.data.response.AccountDTO;

/**
 * Account service handling account creation and manipulations
 * 
 * @author iivanov
 *
 */
@Service
public class AccountServiceWeb {

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
    public List<AccountDTO> getAccounts() {

        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> usersResponse = new ArrayList<AccountDTO>(accounts.size());

        for (Account account : accounts) {
            AccountDTO userDTO = object2dto(account);
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
    public AccountDTO getAccountById(Long id) {

        Account account = accountRepository.findOne(id);
        AccountDTO responseDTO = object2dto(account);

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
    public Account saveAccount(AccountDTO accountDTO) {

        Account toSave = dto2object(accountDTO);

        Account newAcc = accountRepository.save(toSave);

        return newAcc;
    }

    /**
     * 
     * Transformation method - Creates {@link AccountDTO} from given {@link Account}
     * 
     * @param account
     * @return
     */
    private AccountDTO object2dto(Account account) {

        AccountDTO result = new AccountDTO();

        result.setId(account.getId());
        result.setFirstName(account.getFirstName());
        result.setLastName(account.getLastName());
        result.setEmail(account.getEmail());

        if (null == account.getDateOfBirth()) {
            result.setDateOfBirth("");
        } else {
            result.setDateOfBirth(account.getDateOfBirth().toString());
        }

        return result;

    }

    /**
     * 
     * Transformation method - Creates {@link Account} from given {@link AccountDTO}
     * 
     * @param dto
     * @return
     */
    private Account dto2object(AccountDTO dto) {

        Account result = new Account();

        result.setId(dto.getId());
        result.setFirstName(dto.getFirstName());
        result.setLastName(dto.getLastName());
        result.setEmail(dto.getEmail());

        if ("".equals(dto.getDateOfBirth())) {
            result.setDateOfBirth(null);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            result.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth(), formatter));
        }

        return result;

    }

}
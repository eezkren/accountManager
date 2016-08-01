package com.isilona.accm.web.data.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.isilona.accm.db.model.Account;
import com.isilona.accm.web.data.response.AccountDto;

/**
 * @author iivanov
 *
 */

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto accountToAccountDto(Account account);

    @Mapping(source = "dateOfBirth", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", target = "dateOfBirth")
    Account accountDtoToAccount(AccountDto accountDto);

}

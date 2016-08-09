package unit.com.isilona.accm.web.mapping;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.isilona.accm.config.RootConfig;
import com.isilona.accm.db.model.Account;
import com.isilona.accm.web.data.mapping.AccountMapper;
import com.isilona.accm.web.data.response.AccountDto;

@ContextConfiguration(classes = { RootConfig.class })
public class AccountMapperTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void shouldMapAccountToDto() {

	// given
	Account account = new Account();
	account.setId(3L);
	account.setFirstName("FirstName");
	account.setLastName("LastName");
	account.setEmail("email");
	account.setDateOfBirth(LocalDate.of(2014, Month.JULY, 9));

	// when
	AccountDto accountDto = accountMapper.accountToAccountDto(account);

	// then
	assertThat(accountDto, notNullValue());
	assertThat(accountDto.getId(), is("3"));
	assertThat(accountDto.getFirstName(), is("FirstName"));
	assertThat(accountDto.getLastName(), is("LastName"));
	assertThat(accountDto.getEmail(), is("email"));
	assertThat(accountDto.getDateOfBirth(), is("2014-07-09"));
    }

    @Test
    public void shouldMapDtoToAccount() {

	// given
	AccountDto accountDto = new AccountDto();
	accountDto.setFirstName("FirstName");
	accountDto.setLastName("LastName");
	accountDto.setEmail("email");
	accountDto.setDateOfBirth("2016-08-24T00:00:00.000Z");

	// when
	Account account = accountMapper.accountDtoToAccount(accountDto);

	// then
	assertThat(account, notNullValue());
	assertThat(account.getId(), is(nullValue()));
	assertThat(account.getFirstName(), is("FirstName"));
	assertThat(account.getLastName(), is("LastName"));
	assertThat(account.getEmail(), is("email"));
	assertThat(account.getDateOfBirth(), is(LocalDate.of(2016, Month.AUGUST, 24)));
    }

}

package unit.com.isilona.accm.web.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.isilona.accm.config.RootConfig;
import com.isilona.accm.db.model.Account;
import com.isilona.accm.db.repository.AccountRepository;
import com.isilona.accm.web.data.mapping.AccountMapper;
import com.isilona.accm.web.data.response.AccountDto;
import com.isilona.accm.web.service.AccountServiceWeb;

@WebAppConfiguration
@ContextConfiguration(classes = { RootConfig.class })
public class TestAccountServiceWeb extends AbstractTestNGSpringContextTests {

    @Spy
    @Autowired
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceWeb accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeClass
    public void setup() {
	MockitoAnnotations.initMocks(this);
    }

    // CREATE / UPDATE
    @Test
    public void saveAccount() {

	// create new

	AccountDto toCreateDto = new AccountDto();
	toCreateDto.setFirstName("First Name New");
	toCreateDto.setLastName("Last Name New");
	toCreateDto.setEmail("Email New");
	toCreateDto.setDateOfBirth("2016-08-24T00:00:00.000Z");

	Account createdEntity = new Account();
	createdEntity.setFirstName("First Name New");
	createdEntity.setLastName("Last Name New");
	createdEntity.setEmail("Email New");
	createdEntity.setDateOfBirth(LocalDate.of(2016, Month.AUGUST, 24));
	createdEntity.setId(1L);

	when(accountRepository.save(Mockito.any(Account.class))).thenReturn(createdEntity);

	Account createdResult = accountService.saveAccount(toCreateDto);

	assertThat(createdResult.getFirstName(), is("First Name New"));
	assertThat(createdResult.getLastName(), is("Last Name New"));
	assertThat(createdResult.getEmail(), is("Email New"));
	assertThat(createdResult.getDateOfBirth(), is(LocalDate.of(2016, Month.AUGUST, 24)));
	assertThat(createdResult.getId(), is(1L));

	// update

	AccountDto toUpdateDto = new AccountDto();
	toUpdateDto.setId("2");
	toUpdateDto.setFirstName("First Name Updated");
	toUpdateDto.setLastName("Last Name Updated");
	toUpdateDto.setEmail("Email Updated");
	toUpdateDto.setDateOfBirth("2016-08-24T00:00:00.000Z");

	Account updatedEntity = new Account();
	updatedEntity.setFirstName("First Name Updated");
	updatedEntity.setLastName("Last Name Updated");
	updatedEntity.setEmail("Email Updated");
	updatedEntity.setDateOfBirth(LocalDate.of(2016, Month.AUGUST, 25));
	updatedEntity.setId(2L);

	when(accountRepository.save(Mockito.any(Account.class))).thenReturn(updatedEntity);

	Account updatedResult = accountService.saveAccount(toUpdateDto);

	assertThat(updatedResult.getFirstName(), is("First Name Updated"));
	assertThat(updatedResult.getLastName(), is("Last Name Updated"));
	assertThat(updatedResult.getEmail(), is("Email Updated"));
	assertThat(updatedResult.getDateOfBirth(), is(LocalDate.of(2016, Month.AUGUST, 25)));
	assertThat(updatedResult.getId(), is(2L));

    }

    // READ

    @Test
    public void getAccountById() {

	// create new

	// find previously created

    }

    // DELETE

    @Test
    public void deleteAccountById() {

	// create new

	// delete previously created

    }

    // ALL

    @Test
    public void getAccounts() {

    }

    @Test
    public void deleteWithPermission() {
	Account account = new Account();
	account.setFirstName("FN");

	when(accountRepository.findOne(1L)).thenReturn(account);

	AccountDto result = accountService.getAccountById(1L);

	assertThat(result.getFirstName(), is("FN"));
    }

}

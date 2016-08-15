package unit.com.isilona.accm.web.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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

	AccountDto createdResult = accountService.saveAccount(toCreateDto);

	assertThat(createdResult.getFirstName(), is("First Name New"));
	assertThat(createdResult.getLastName(), is("Last Name New"));
	assertThat(createdResult.getEmail(), is("Email New"));
	assertThat(createdResult.getDateOfBirth(), is("2016-08-24"));
	assertThat(createdResult.getId(), is("1"));

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

	AccountDto updatedResult = accountService.saveAccount(toUpdateDto);

	assertThat(updatedResult.getFirstName(), is("First Name Updated"));
	assertThat(updatedResult.getLastName(), is("Last Name Updated"));
	assertThat(updatedResult.getEmail(), is("Email Updated"));
	assertThat(updatedResult.getDateOfBirth(), is("2016-08-25"));
	assertThat(updatedResult.getId(), is("2"));

    }

    // READ

    @Test
    public void getAccountById() {

	Account toFindEntity = new Account();
	toFindEntity.setFirstName("First Name Found");
	toFindEntity.setLastName("Last Name Found");
	toFindEntity.setEmail("Email Found");
	toFindEntity.setDateOfBirth(LocalDate.of(2016, Month.AUGUST, 11));
	toFindEntity.setId(3L);

	when(accountRepository.findOne(3L)).thenReturn(toFindEntity);

	AccountDto foundResult = accountService.getAccountById(3L);

	assertThat(foundResult.getFirstName(), is("First Name Found"));
	assertThat(foundResult.getLastName(), is("Last Name Found"));
	assertThat(foundResult.getEmail(), is("Email Found"));
	assertThat(foundResult.getDateOfBirth(), is("2016-08-11"));
	assertThat(foundResult.getId(), is("3"));

    }

    // DELETE

    @Test
    public void deleteAccountById() {

	Account toDeleteEntity = new Account();
	toDeleteEntity.setFirstName("First Name Delete");
	toDeleteEntity.setLastName("Last Name Delete");
	toDeleteEntity.setEmail("Email Delete");
	toDeleteEntity.setDateOfBirth(LocalDate.of(2016, Month.AUGUST, 11));
	toDeleteEntity.setId(4L);

	when(accountRepository.findOne(4L)).thenReturn(toDeleteEntity);

	AccountDto deleteResult = accountService.deleteAccountById(4L);

	assertThat(deleteResult.getFirstName(), is("First Name Delete"));
	assertThat(deleteResult.getLastName(), is("Last Name Delete"));
	assertThat(deleteResult.getEmail(), is("Email Delete"));
	assertThat(deleteResult.getDateOfBirth(), is("2016-08-11"));
	assertThat(deleteResult.getId(), is("4"));

    }

    // ALL

    @Test
    public void getAccounts() {

	Account toFindEntity1 = new Account();
	toFindEntity1.setFirstName("First Name 1");
	toFindEntity1.setLastName("Last Name 1");
	toFindEntity1.setEmail("Email 1");
	toFindEntity1.setDateOfBirth(LocalDate.of(2016, Month.JULY, 2));
	toFindEntity1.setId(5L);

	Account toFindEntity2 = new Account();
	toFindEntity2.setFirstName("First Name 2");
	toFindEntity2.setLastName("Last Name 2");
	toFindEntity2.setEmail("Email 2");
	toFindEntity2.setDateOfBirth(LocalDate.of(2016, Month.JULY, 3));
	toFindEntity2.setId(6L);

	List<Account> toFindList = new ArrayList<Account>();
	toFindList.add(toFindEntity1);
	toFindList.add(toFindEntity2);

	when(accountRepository.findAll()).thenReturn(toFindList);

	List<AccountDto> foundResult = accountService.getAccounts();

	assertThat(foundResult.get(0).getFirstName(), is("First Name 1"));
	assertThat(foundResult.get(0).getLastName(), is("Last Name 1"));
	assertThat(foundResult.get(0).getEmail(), is("Email 1"));
	assertThat(foundResult.get(0).getDateOfBirth(), is("2016-07-02"));
	assertThat(foundResult.get(0).getId(), is("5"));

	assertThat(foundResult.get(1).getFirstName(), is("First Name 2"));
	assertThat(foundResult.get(1).getLastName(), is("Last Name 2"));
	assertThat(foundResult.get(1).getEmail(), is("Email 2"));
	assertThat(foundResult.get(1).getDateOfBirth(), is("2016-07-03"));
	assertThat(foundResult.get(1).getId(), is("6"));

    }

}

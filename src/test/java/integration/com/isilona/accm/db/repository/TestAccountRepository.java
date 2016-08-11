package integration.com.isilona.accm.db.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.isilona.accm.config.RootConfig;
import com.isilona.accm.db.model.Account;
import com.isilona.accm.db.repository.AccountRepository;
import com.isilona.accm.util.JsonReader;

@ContextConfiguration(classes = { RootConfig.class })
public class TestAccountRepository extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void saveAll() {

	List<Account> existingAccounts = accountRepository.findAll();
	int existingAccountsSize = existingAccounts.size();

	ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource("repository/accountData.json").getFile());

	List<Account> accountsToAdd = JsonReader.getJsonData(file, Account.class);

	accountRepository.save(accountsToAdd);

	List<Account> currentAccounts = accountRepository.findAll();
	int currentAccountsSize = currentAccounts.size();

	assertThat(currentAccountsSize, is(existingAccountsSize + 2));

    }

    // CREATE / UPDATE
    @Test
    public void saveAccount() {

	// create new

	List<Account> existingAccounts = accountRepository.findAll();
	int existingAccountsCount = existingAccounts.size();

	Account toCreate = new Account();
	toCreate.setFirstName("First Name New");
	toCreate.setLastName("Last Name New");
	toCreate.setEmail("Email New");
	toCreate.setDateOfBirth(LocalDate.now());

	Account created = accountRepository.save(toCreate);
	Long createdId = created.getId();

	List<Account> createdAccounts = accountRepository.findAll();
	int createdAccountsCount = createdAccounts.size();

	assertThat(createdAccountsCount, is(existingAccountsCount + 1));
	assertThat(createdId, is(notNullValue()));

	// update previously created

	Account toUpdate = new Account();
	toUpdate.setId(createdId);
	toUpdate.setFirstName("First Name Updated");
	toUpdate.setLastName("Last Name Updated");
	toUpdate.setEmail("Email Updated");
	toUpdate.setDateOfBirth(LocalDate.now());

	Account updated = accountRepository.save(toUpdate);

	List<Account> updatedAccounts = accountRepository.findAll();
	int updatedAccountsCount = updatedAccounts.size();

	assertThat(updatedAccountsCount, is(createdAccountsCount));
	assertThat(updated.getId(), is(createdId));
	assertThat(updated.getFirstName(), not("First Name New"));

    }

    // READ

    @Test
    public void getAccountById() {

	// create new

	Account toCreate = new Account();
	toCreate.setFirstName("First Name New");
	toCreate.setLastName("Last Name New");
	toCreate.setEmail("Email New");
	toCreate.setDateOfBirth(LocalDate.now());

	Account created = accountRepository.save(toCreate);

	// find previously created

	Account found = accountRepository.findOne(created.getId());

	assertThat(found.getFirstName(), is("First Name New"));
	assertThat(found.getLastName(), is("Last Name New"));
	assertThat(found.getEmail(), is("Email New"));

    }

    // DELETE

    @Test
    public void deleteAccountById() {

	List<Account> existingAccounts = accountRepository.findAll();
	int existingAccountsCount = existingAccounts.size();

	// create new

	Account toCreate = new Account();
	toCreate.setFirstName("First Name New");
	toCreate.setLastName("Last Name New");
	toCreate.setEmail("Email New");
	toCreate.setDateOfBirth(LocalDate.now());

	Account created = accountRepository.save(toCreate);
	Long createdId = created.getId();

	List<Account> createdAccounts = accountRepository.findAll();
	int createdAccountsCount = createdAccounts.size();

	assertThat(createdAccountsCount, is(existingAccountsCount + 1));
	assertThat(createdId, is(notNullValue()));

	// delete previously created

	accountRepository.delete(createdId);

	List<Account> deletedAccounts = accountRepository.findAll();
	int deletedAccountsCount = deletedAccounts.size();

	assertThat(deletedAccountsCount, is(existingAccountsCount));

	Account deleted = accountRepository.findOne(createdId);

	assertThat(deleted, nullValue());

    }

    // ALL

    @Test
    public void getAccounts() {

	List<Account> existingAccounts = null;

	existingAccounts = accountRepository.findAll();

	assertThat(existingAccounts, notNullValue());

    }

}

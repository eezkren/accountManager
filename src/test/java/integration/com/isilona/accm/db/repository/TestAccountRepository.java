package integration.com.isilona.accm.db.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
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

}

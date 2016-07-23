package integration.com.isilona.accm.db.repository;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.isilona.accm.config.RootConfig;
import com.isilona.accm.db.model.Account;
import com.isilona.accm.db.repository.AccountRepository;
import com.isilona.accm.util.JsonReader;

@ContextConfiguration(classes = { RootConfig.class })
@Rollback(value = false)
public class TestAccountRepository extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void saveAll() {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("repository/accountData.json").getFile());

        List<Account> accounts = JsonReader.getJsonData(file, Account.class);

        accountRepository.save(accounts);

    }

}

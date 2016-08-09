package unit.com.isilona.accm.web.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

//import org.junit.Before;
//import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
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

    @Test
    public void setup() {
	MockitoAnnotations.initMocks(this);
    }

    @Test(dependsOnMethods = "setup")
    public void deleteWithPermission() {
	Account account = new Account();
	account.setFirstName("FN");

	when(accountRepository.findOne(1L)).thenReturn(account);

	AccountDto result = accountService.getAccountById(1L);

	assertThat(result.getFirstName(), is("FN"));
    }

}

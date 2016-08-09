package unit.com.isilona.accm.web.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.Test;

import com.isilona.accm.config.RootConfig;
import com.isilona.accm.web.controller.AccountControllerWeb;
import com.isilona.accm.web.data.response.AccountDto;
import com.isilona.accm.web.service.AccountServiceWeb;

@WebAppConfiguration
@ContextConfiguration(classes = { RootConfig.class })
public class TestAccountControllerWeb extends AbstractTestNGSpringContextTests {

    private MockMvc mockMvc;

    @Mock
    private AccountServiceWeb accountServiceMock;

    @InjectMocks
    AccountControllerWeb controllerUnderTest;

    @Test
    public void setUp() {
	AccountDto first = new AccountDto();
	first.setFirstName("FN1");
	first.setLastName("LN1");

	AccountDto second = new AccountDto();
	second.setFirstName("FN2");
	second.setLastName("LN2");

	List<AccountDto> entityList = new LinkedList<>();
	entityList.add(first);
	entityList.add(second);

	MockitoAnnotations.initMocks(this);
	when(accountServiceMock.getAccounts()).thenReturn(entityList);
	mockMvc = MockMvcBuilders.standaloneSetup(controllerUnderTest).build();

    }

    @Test(dependsOnMethods = "setUp")
    public void showHomePage_ShouldRenderHomePage() throws Exception {

	mockMvc.perform(get("/account/list")).andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].firstName", is("FN1")));
    }

}

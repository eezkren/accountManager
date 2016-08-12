package unit.com.isilona.accm.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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

    @BeforeClass
    public void setup() {
	MockitoAnnotations.initMocks(this);
	mockMvc = MockMvcBuilders.standaloneSetup(controllerUnderTest).build();
    }

    // CREATE
    @Test
    public void createAccount() throws Exception {

	AccountDto toCreateDto = new AccountDto();
	toCreateDto.setFirstName("First Name New");
	toCreateDto.setLastName("Last Name New");
	toCreateDto.setEmail("Email New");
	toCreateDto.setDateOfBirth("2016-08-24T00:00:00.000Z");

	AccountDto createdDto = new AccountDto();
	createdDto.setFirstName("First Name New");
	createdDto.setLastName("Last Name New");
	createdDto.setEmail("Email New");
	createdDto.setDateOfBirth("2016-08-24");
	createdDto.setId("1");

	when(accountServiceMock.saveAccount(toCreateDto)).thenReturn(createdDto);

	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	String json = ow.writeValueAsString(toCreateDto);

	mockMvc.perform(post("/account/new").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print())
		.andExpect(jsonPath("$.id", is("1"))).andExpect(jsonPath("$.firstName", is("First Name New")))
		.andExpect(jsonPath("$.lastName", is("Last Name New"))).andExpect(jsonPath("$.email", is("Email New")))
		.andExpect(jsonPath("$.dateOfBirth", is("2016-08-24")));

    }

    // UPDATE
    @Test
    public void updateAccount() throws Exception {

	AccountDto toUpdateDto = new AccountDto();
	toUpdateDto.setId("2");
	toUpdateDto.setFirstName("First Name Updated");
	toUpdateDto.setLastName("Last Name Updated");
	toUpdateDto.setEmail("Email Updated");
	toUpdateDto.setDateOfBirth("2016-08-24T00:00:00.000Z");

	AccountDto updatedDto = new AccountDto();
	updatedDto.setFirstName("First Name Updated");
	updatedDto.setLastName("Last Name Updated");
	updatedDto.setEmail("Email Updated");
	updatedDto.setDateOfBirth("2016-08-24");
	updatedDto.setId("2");

	when(accountServiceMock.saveAccount(toUpdateDto)).thenReturn(updatedDto);

	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	String json = ow.writeValueAsString(toUpdateDto);

	mockMvc.perform(put("/account/2").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print())
		.andExpect(jsonPath("$.id", is("2"))).andExpect(jsonPath("$.firstName", is("First Name Updated")))
		.andExpect(jsonPath("$.lastName", is("Last Name Updated")))
		.andExpect(jsonPath("$.email", is("Email Updated")))
		.andExpect(jsonPath("$.dateOfBirth", is("2016-08-24")));

    }

    // READ

    @Test
    public void getAccountById() {

    }

    // DELETE

    @Test
    public void deleteAccountById() {

    }

    // ALL

    @Test
    public void getAccounts() {

    }

    @Test
    public void showHomePage_ShouldRenderHomePage() throws Exception {

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

	mockMvc.perform(get("/account/list")).andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].firstName", is("FN1")));
    }

}

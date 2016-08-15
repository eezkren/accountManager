package unit.com.isilona.accm.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.isilona.accm.config.RootConfig;
import com.isilona.accm.web.controller.AccountControllerWeb;
import com.isilona.accm.web.data.response.AccountDto;
import com.isilona.accm.web.exception.CustomResponseEntityExceptionHandler;
import com.isilona.accm.web.service.AccountServiceWeb;

@WebAppConfiguration
@ContextConfiguration(classes = { RootConfig.class })
public class TestAccountControllerWeb extends AbstractTestNGSpringContextTests {

    private MockMvc mockMvc;

    @Mock
    private AccountServiceWeb accountServiceMock;

    @InjectMocks
    AccountControllerWeb controllerUnderTest;

    private ExceptionHandlerExceptionResolver createExceptionResolver() {
	ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
	    @Override
	    protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod,
		    Exception exception) {
		Method method = new ExceptionHandlerMethodResolver(CustomResponseEntityExceptionHandler.class)
			.resolveMethod(exception);
		return new ServletInvocableHandlerMethod(new CustomResponseEntityExceptionHandler(), method);
	    }
	};
	exceptionResolver.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	exceptionResolver.afterPropertiesSet();
	return exceptionResolver;
    }

    @BeforeClass
    public void setup() {
	MockitoAnnotations.initMocks(this);
	mockMvc = MockMvcBuilders.standaloneSetup(controllerUnderTest)
		.setHandlerExceptionResolvers(createExceptionResolver()).build();
    }

    // CREATE

    @Test
    public void createAccount() throws Exception {

	AccountDto toCreateDto = new AccountDto();
	toCreateDto.setFirstName("First Name New");
	toCreateDto.setLastName("Last Name New");
	toCreateDto.setEmail("email@new.com");
	toCreateDto.setDateOfBirth("2016-08-24T00:00:00.000Z");

	AccountDto createdDto = new AccountDto();
	createdDto.setFirstName("First Name New");
	createdDto.setLastName("Last Name New");
	createdDto.setEmail("email@new.com");
	createdDto.setDateOfBirth("2016-08-24");
	createdDto.setId("1");

	when(accountServiceMock.saveAccount(toCreateDto)).thenReturn(createdDto);

	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	String json = ow.writeValueAsString(toCreateDto);

	mockMvc.perform(post("/account/new").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print())
		.andExpect(jsonPath("$.id", is("1"))).andExpect(jsonPath("$.firstName", is("First Name New")))
		.andExpect(jsonPath("$.lastName", is("Last Name New")))
		.andExpect(jsonPath("$.email", is("email@new.com")))
		.andExpect(jsonPath("$.dateOfBirth", is("2016-08-24")));

    }

    // UPDATE

    @Test
    public void updateAccount() throws Exception {

	AccountDto toUpdateDto = new AccountDto();
	toUpdateDto.setId("2");
	toUpdateDto.setFirstName("First Name Updated");
	toUpdateDto.setLastName("Last Name Updated");
	toUpdateDto.setEmail("email@updated.com");
	toUpdateDto.setDateOfBirth("2016-08-24T00:00:00.000Z");

	AccountDto updatedDto = new AccountDto();
	updatedDto.setFirstName("First Name Updated");
	updatedDto.setLastName("Last Name Updated");
	updatedDto.setEmail("email@updated.com");
	updatedDto.setDateOfBirth("2016-08-24");
	updatedDto.setId("2");

	when(accountServiceMock.saveAccount(toUpdateDto)).thenReturn(updatedDto);

	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	String json = ow.writeValueAsString(toUpdateDto);

	mockMvc.perform(put("/account/2").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print())
		.andExpect(jsonPath("$.id", is("2"))).andExpect(jsonPath("$.firstName", is("First Name Updated")))
		.andExpect(jsonPath("$.lastName", is("Last Name Updated")))
		.andExpect(jsonPath("$.email", is("email@updated.com")))
		.andExpect(jsonPath("$.dateOfBirth", is("2016-08-24")));

    }

    // READ

    @Test
    public void getAccountById() throws Exception {

	AccountDto foundDto = new AccountDto();
	foundDto.setId("3");
	foundDto.setFirstName("First Name Found");
	foundDto.setLastName("Last Name Found");
	foundDto.setEmail("Email Found");
	foundDto.setDateOfBirth("2016-08-24");

	when(accountServiceMock.getAccountById(3L)).thenReturn(foundDto);

	mockMvc.perform(get("/account/3")).andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.id", is("3"))).andExpect(jsonPath("$.firstName", is("First Name Found")))
		.andExpect(jsonPath("$.lastName", is("Last Name Found")))
		.andExpect(jsonPath("$.email", is("Email Found")))
		.andExpect(jsonPath("$.dateOfBirth", is("2016-08-24")));

    }

    // DELETE

    @Test
    public void deleteAccountById() throws Exception {

	AccountDto deletedDto = new AccountDto();
	deletedDto.setFirstName("First Name Deleted");
	deletedDto.setLastName("Last Name Deleted");
	deletedDto.setEmail("Email Deleted");
	deletedDto.setDateOfBirth("2016-08-24");
	deletedDto.setId("4");

	when(accountServiceMock.deleteAccountById(4L)).thenReturn(deletedDto);

	mockMvc.perform(delete("/account/4")).andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.id", is("4"))).andExpect(jsonPath("$.firstName", is("First Name Deleted")))
		.andExpect(jsonPath("$.lastName", is("Last Name Deleted")))
		.andExpect(jsonPath("$.email", is("Email Deleted")))
		.andExpect(jsonPath("$.dateOfBirth", is("2016-08-24")));

    }

    // ALL

    @Test
    public void getAccounts() throws Exception {

	AccountDto accountDto_1 = new AccountDto();
	accountDto_1.setId("5");
	accountDto_1.setFirstName("First Name 5");
	accountDto_1.setLastName("Last Name 5");
	accountDto_1.setEmail("Email 5");
	accountDto_1.setDateOfBirth("2016-08-25");

	AccountDto accountDto_2 = new AccountDto();
	accountDto_2.setId("6");
	accountDto_2.setFirstName("First Name 6");
	accountDto_2.setLastName("Last Name 6");
	accountDto_2.setEmail("Email 6");
	accountDto_2.setDateOfBirth("2016-08-26");

	AccountDto accountDto_3 = new AccountDto();
	accountDto_3.setId("7");
	accountDto_3.setFirstName("First Name 7");
	accountDto_3.setLastName("Last Name 7");
	accountDto_3.setEmail("Email 7");
	accountDto_3.setDateOfBirth("2016-08-27");

	List<AccountDto> accountList = new ArrayList<>();
	accountList.add(accountDto_1);
	accountList.add(accountDto_2);
	accountList.add(accountDto_3);

	when(accountServiceMock.getAccounts()).thenReturn(accountList);

	mockMvc.perform(get("/account/list")).andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].id", is("5")))
		.andExpect(jsonPath("$[0].firstName", is("First Name 5")))
		.andExpect(jsonPath("$[0].lastName", is("Last Name 5")))
		.andExpect(jsonPath("$[0].email", is("Email 5")))
		.andExpect(jsonPath("$[0].dateOfBirth", is("2016-08-25"))).andExpect(jsonPath("$[1].id", is("6")))
		.andExpect(jsonPath("$[1].firstName", is("First Name 6")))
		.andExpect(jsonPath("$[1].lastName", is("Last Name 6")))
		.andExpect(jsonPath("$[1].email", is("Email 6")))
		.andExpect(jsonPath("$[1].dateOfBirth", is("2016-08-26"))).andExpect(jsonPath("$[2].id", is("7")))
		.andExpect(jsonPath("$[2].firstName", is("First Name 7")))
		.andExpect(jsonPath("$[2].lastName", is("Last Name 7")))
		.andExpect(jsonPath("$[2].email", is("Email 7")))
		.andExpect(jsonPath("$[2].dateOfBirth", is("2016-08-27")));
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

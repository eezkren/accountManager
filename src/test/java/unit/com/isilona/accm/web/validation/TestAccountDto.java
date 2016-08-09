package unit.com.isilona.accm.web.validation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.isilona.accm.config.RootConfig;
import com.isilona.accm.web.data.response.AccountDto;

@ContextConfiguration(classes = { RootConfig.class })
public class TestAccountDto extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private Validator validator;

    public class TestAllNull {

	@BeforeClass
	public void init() throws Exception {
	    springTestContextPrepareTestInstance();

	}

	@Test
	public void testEN() {

	    Locale.setDefault(new Locale("en"));

	    AccountDto accountDTO = new AccountDto();

	    Set<ConstraintViolation<AccountDto>> violations = validator.validate(accountDTO);

	    for (ConstraintViolation<AccountDto> constraintViolation : violations) {

		switch (constraintViolation.getPropertyPath().toString()) {
		case "firstName":
		    assertThat(constraintViolation.getMessage(), is("Your First Name is required."));
		    break;

		case "lastName":
		    assertThat(constraintViolation.getMessage(), is("Your Last Name is required."));
		    break;

		case "email":
		    assertThat(constraintViolation.getMessage(), is("Your email is required."));
		    break;

		default:
		    break;
		}

	    }

	    assertThat(violations, hasSize(3));

	}

	@Test
	public void testBG() {

	    Locale.setDefault(new Locale("bg"));

	    AccountDto accountDTO = new AccountDto();

	    Set<ConstraintViolation<AccountDto>> violations = validator.validate(accountDTO);

	    for (ConstraintViolation<AccountDto> constraintViolation : violations) {
		switch (constraintViolation.getPropertyPath().toString()) {
		case "firstName":
		    assertThat(constraintViolation.getMessage(), is("Изисква се собствено име."));
		    break;

		case "lastName":
		    assertThat(constraintViolation.getMessage(), is("Изисква се фамилно име."));
		    break;

		case "email":
		    assertThat(constraintViolation.getMessage(), is("Изисква се email."));
		    break;

		default:
		    break;
		}
	    }

	    assertThat(violations, hasSize(3));

	}
    }

    public class TestAllOK {

	@Test
	public void test() {

	    AccountDto accountDTO = new AccountDto();
	    accountDTO.setFirstName("firstName");
	    accountDTO.setLastName("lastName");
	    accountDTO.setEmail("iivanov@abv.bg");

	    Set<ConstraintViolation<AccountDto>> violations = validator.validate(accountDTO);

	    assertThat(violations, hasSize(0));

	}
    }

    public class TestFirstName {

	@Test
	public void test() {

	    AccountDto accountDTO = new AccountDto();
	    accountDTO.setFirstName("firstName");
	    accountDTO.setLastName("lastName");
	    accountDTO.setEmail("iivanov@abv.bg");

	    Set<ConstraintViolation<AccountDto>> violations = validator.validate(accountDTO);

	    assertThat(violations, hasSize(0));

	}
    }
}

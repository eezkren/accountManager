package unit.com.isilona.accm.web.mapping;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.isilona.accm.config.RootConfig;
import com.isilona.accm.db.model.CustomRevision;
import com.isilona.accm.web.data.mapping.CustomRevisionMapper;
import com.isilona.accm.web.data.response.CustomRevisionDto;

@ContextConfiguration(classes = { RootConfig.class })
public class CustomRevisionMapperTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CustomRevisionMapper customRevisionMapper;

    @Test
    public void shouldMapCustomRevisionToDto() {

	// given
	CustomRevision customRevision = new CustomRevision();
	customRevision.setId(2);
	customRevision.setRemoteIp("127.0.0.1");
	customRevision.setTimestamp(1471727352810L);

	// when
	CustomRevisionDto customRevisionDto = customRevisionMapper.customRevisionToCustomRevisionDto(customRevision);

	// then
	assertThat(customRevisionDto, notNullValue());
	assertThat(customRevisionDto.getId(), is("2"));
	assertThat(customRevisionDto.getRemoteIp(), is("127.0.0.1"));
	assertThat(customRevisionDto.getRevisionDate(), is("2016 08 21 00:09:12"));
    }

}

package com.isilona.accm.config.db;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Common Settings for DataSource
 * <ul>
 * <li>{@link Configuration} - defines this class as a Spring Configuration
 * class</li>
 * <li>{@link PropertySource} - replaces
 * &lt;context:property-placeholder/&gt;</li>
 * <li>{@link Bean} - replaces &lt;bean/&gt;</li>
 * </ul>
 * 
 * @author iivanov
 *
 */

@Configuration
@PropertySource("classpath:/data/jdbc-pg.properties")
public class DataSourceConfig {

    // Database Configuration
    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";

    private static final String PROPERTY_NAME_DBCP_INITIAL_SIZE = "dbcp.initialSize";
    private static final String PROPERTY_NAME_DBCP_MAX_ACTIVE = "dbcp.maxActive";
    private static final String PROPERTY_NAME_DBCP_MAX_IDLE = "dbcp.maxIdle";
    private static final String PROPERTY_NAME_DBCP_MIN_IDLE = "dbcp.minIdle";

    @Resource
    private Environment environment;

    @Autowired
    private StrongTextEncryptor encryptor;

    /**
     * Apache commons implementation of {@link DataSource}
     * 
     * @return {@link BasicDataSource}
     */
    @Bean
    public DataSource dataSource() {
	BasicDataSource dataSource = new BasicDataSource();

	dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
	dataSource.setUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
	dataSource.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
	// dataSource.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

	String passwordPropertyValue = environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD);
	if (PropertyValueEncryptionUtils.isEncryptedValue(passwordPropertyValue)) {
	    dataSource.setPassword(PropertyValueEncryptionUtils.decrypt(passwordPropertyValue, encryptor));
	} else {
	    dataSource.setPassword(passwordPropertyValue);
	}

	// POOLING
	String initialSize = environment.getRequiredProperty(PROPERTY_NAME_DBCP_INITIAL_SIZE);
	dataSource.setInitialSize(Integer.parseInt(initialSize));

	String maxTotal = environment.getRequiredProperty(PROPERTY_NAME_DBCP_MAX_ACTIVE);
	dataSource.setMaxTotal(Integer.parseInt(maxTotal));

	String maxIdle = environment.getRequiredProperty(PROPERTY_NAME_DBCP_MAX_IDLE);
	dataSource.setMaxIdle(Integer.parseInt(maxIdle));

	String minIdle = environment.getRequiredProperty(PROPERTY_NAME_DBCP_MIN_IDLE);
	dataSource.setMinIdle(Integer.parseInt(minIdle));

	return dataSource;
    }

}

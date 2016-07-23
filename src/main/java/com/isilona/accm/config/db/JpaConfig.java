package com.isilona.accm.config.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Common Settings for JPA
 * <ul>
 * <li>{@link Configuration} - defines this class as a Spring Configuration class</li>
 * <li>{@link PropertySource} - replaces &lt;context:property-placeholder/&gt;</li>
 * <li>{@link EnableTransactionManagement} - replaces &lt;tx:annotation-driven/&gt;</li>
 * <li>{@link Bean} - replaces &lt;bean/&gt;</li>
 * </ul>
 * 
 * @author iivanov
 *
 */

@Configuration
@PropertySource("classpath:/data/hibernate.properties")
@EnableTransactionManagement
public class JpaConfig {

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";

    // Declares the base package of the entity classes.
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.isilona.accm.db.model";

    @Autowired
    private Environment environment;
    @Autowired
    private DataSource dataSource;

    /**
     * @return {@link JpaTransactionManager}
     * @throws ClassNotFoundException
     */
    @Bean
    public JpaTransactionManager transactionManager() throws ClassNotFoundException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }

    /**
     * this method name is associated with {@link RepositoryConfig} <br>
     * entityManagerFactoryRef = "entityManagerFactoryBean" <br>
     * if you want to remove the "entityManagerFactoryRef" then this method should be renamed to
     * entityManagerFactory
     * 
     * @return
     * @throws ClassNotFoundException
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() throws ClassNotFoundException {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource);
        // fix need of persistence.xml
        entityManagerFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        Properties jpaProterties = new Properties();
        jpaProterties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        jpaProterties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        jpaProterties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL,
                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
        jpaProterties.put(PROPERTY_NAME_HIBERNATE_USE_SQL_COMMENTS,
                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_USE_SQL_COMMENTS));
        jpaProterties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY,
                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
        jpaProterties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO,
                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));

        entityManagerFactoryBean.setJpaProperties(jpaProterties);

        return entityManagerFactoryBean;
    }

}

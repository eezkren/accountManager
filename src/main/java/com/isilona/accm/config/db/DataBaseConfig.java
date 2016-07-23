package com.isilona.accm.config.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

/**
 * @author iivanov
 *
 */
@Configuration
@Import({ DataSourceConfig.class, JpaConfig.class, RepositoryConfig.class })
public class DataBaseConfig {
    /**
     * 
     * When using JPA, the standard exceptions are somewhat out of keeping with Spring’s exception
     * model. Spring provides support for automatically translating these exceptions into Spring’s
     * DataAccessException hierarchy.
     * 
     * @return {@link PersistenceExceptionTranslationPostProcessor}
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}

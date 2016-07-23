package com.isilona.accm.config.db;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Common Settings for Sprin Data Repository
 * <ul>
 * <li>{@link Configuration} - defines this class as a Spring Configuration class</li>
 * <li>{@link EnableJpaRepositories} - replaces Spring Data Jpa &lt;jpa:repositories/&gt;</li>
 * <li><i>repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class</i> - fixes
 * org.springframework.data.mapping.PropertyReferenceException: No property findRevisions found for
 * type Entity</li>
 * </ul>
 * 
 * @author iivanov
 *
 */

@Configuration
@EnableJpaRepositories(basePackages = { "com.isilona.accm.db.repository" }, entityManagerFactoryRef = "entityManagerFactoryBean")
public class RepositoryConfig {

}

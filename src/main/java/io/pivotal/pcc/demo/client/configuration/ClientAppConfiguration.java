package io.pivotal.pcc.demo.client.configuration;

import com.github.javafaker.Faker;
import io.pivotal.pcc.demo.client.domain.Book;
import io.pivotal.pcc.demo.client.repositories.jpa.JPABookRepository;
import io.pivotal.pcc.demo.client.repositories.pcc.BookRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.gemfire.cache.config.EnableGemfireCaching;
import org.springframework.data.gemfire.config.annotation.*;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@ClientCacheApplication
@EnableClusterConfiguration
@EnableEntityDefinedRegions(basePackageClasses = Book.class)
@EnableGemfireCaching
@EnableGemfireRepositories(basePackageClasses = BookRepository.class)
@EnablePdx
@EnableSecurity
@EnableJpaRepositories(basePackageClasses = JPABookRepository.class)
public class ClientAppConfiguration {

	@Bean
	public Faker getFakeValueService() {
		return new Faker();
	}

	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScript(new ClassPathResource("./schema.sql"));

		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
		return dataSourceInitializer;
	}
}

package shopping.cart;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class SpringConfig {

  @Bean
  public DataSource dataSource() {
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    return builder.setType(EmbeddedDatabaseType.HSQL).build();
  }

//      @Bean
//      public DataSource dataSource(){
//          DriverManagerDataSource dataSource = new DriverManagerDataSource();
//          dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//          dataSource.setUrl("jdbc:postgresql://localhost:5432/testdb");
//          dataSource.setUsername( "postgres" );
//          dataSource.setPassword("x");
//          return dataSource;
//      }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("shopping.cart");
    factory.setDataSource(dataSource());
    factory.setJpaProperties(additionalProperties());
    return factory;
  }

  Properties additionalProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.default-access", "field");
    properties.setProperty("hibernate.default-lazy", "false");
    properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
//    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

    return properties;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

    return transactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

}

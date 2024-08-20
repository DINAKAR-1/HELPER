package in.gov.cgg.strutsSpringIntegration;

import com.cgg.struts.plugin.DataBasePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "in.gov.cgg.*")
public class AppInitializer implements WebApplicationInitializer
{

    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

    public void onStartup(ServletContext container) throws ServletException
    {

        // System.out.println("==============in dispatcherr==============");
        // ctx.register(WebConfig.class);
        // ctx.setServletContext(container);
        // ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
        // servlet.setLoadOnStartup(1);
        // servlet.addMapping("*.go");
        /*
         * MultipartConfigElement multipartConfig = new MultipartConfigElement("/tmp");
         * servlet.setMultipartConfig(multipartConfig); FilterRegistration.Dynamic
         * multipartFilter = container.addFilter("multipartFilter",
         * MultipartFilter.class); multipartFilter.addMappingForUrlPatterns(null, true,
         * "/*");
         */
        // Create the Spring application context
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("in.gov.cgg.*"); // Package where your Spring configuration is located
        // Register the dispatcher servlet

        ServletRegistration.Dynamic dispatcherServlet = container.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/"); // Map to root URL or any specific URL pattern
        // Optionally, add more servlets, filters, listeners, etc.
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        System.out.println("==============in entityManagerFactory==============");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        //vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setGenerateDdl(false);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("in.gov.cgg");
        factory.setPersistenceUnitName("TSBOILERS");

        // factory.setPersistenceUnitName("tsswfms");
        Properties properties = new Properties();
        properties.setProperty("show_sql", "true");
        factory.setJpaPropertyMap(Collections.singletonMap("hibernate.show_sql", "true"));

        factory.setJpaProperties(properties);
        factory.setDataSource(dataSource());

        return factory;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public JdbcTemplate jdbcTemplateConnection()
    {
        System.out.println("=========== jdbcTemplateConnection ==============");

        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource()
    {
        System.out.println("===========" + DataBasePlugin.datasource + "==============");
        return DataBasePlugin.datasource;
    }

//other way to get data source or to get another db connections
/*	@Bean
	public DataSource dataSource1(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClass("org.postgresql.Driver");
		dataSource.setJdbcUrl("jdbc:postgresql://127.0.0.1:58613/ekalyan");
		dataSource.setUser( "readonly");
		dataSource.setPassword("readonly" );

		return dataSource;
	}*/
}

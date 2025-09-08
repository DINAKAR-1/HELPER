package in.gov.cgg.strutsSpringIntegration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver; 
@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "in.gov.cgg")
public class WebConfig extends WebMvcConfigurerAdapter
{

    // @Bean
    // public TilesConfigurer tilesConfigurer()
    // {
    //     TilesConfigurer tilesConfigurer = new TilesConfigurer();
    //     tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
    //     // If you are using classpath, use the following:
    //     // tilesConfigurer.setDefinitions("classpath:/WEB-INF/tiles.xml");
    //     return tilesConfigurer;
    // }

    // @Bean
    // public TilesViewResolver tilesViewResolver()
    // {
    //     return new TilesViewResolver();
    // }

    // // @Bean
    // // public InternalResourceViewResolver jspViewResolver()
    // // {
    // //     InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();
    // //     jspViewResolver.setPrefix("/WEB-INF/springPages/");
    // //     jspViewResolver.setSuffix(".jsp");
    // //     return jspViewResolver;
    // // }


    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

	  @Bean
	    public TilesConfigurer tilesConfigurer() {
	        TilesConfigurer tilesConfigurer = new TilesConfigurer();
	        tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/tiles.xml"  });
	        tilesConfigurer.setCheckRefresh(true);
	        return tilesConfigurer;
	    }
	  public void onStartup(ServletContext container) throws ServletException 
		{

			System.out.println("==============in dispatcherr==============");
			ctx.register(WebConfig.class);
			ctx.setServletContext(container);
			ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
			servlet.setLoadOnStartup(1);
			servlet.addMapping("*.go"); 
			/*
			 * MultipartConfigElement multipartConfig = new MultipartConfigElement("/tmp");
			 * servlet.setMultipartConfig(multipartConfig); FilterRegistration.Dynamic
			 * multipartFilter = container.addFilter("multipartFilter",
			 * MultipartFilter.class); multipartFilter.addMappingForUrlPatterns(null, true,
			 * "/*");
			 */
		}

 
	  
	  @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		  registry.addResourceHandler("/gos/**").addResourceLocations("/gos/","/content/gos");
		  registry.addResourceHandler("/content/**").addResourceLocations("/content/");
		  registry.addResourceHandler("/images/**").addResourceLocations("/images/");
	        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
	        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
	        registry.addResourceHandler("/code.jquery.com/**").addResourceLocations("/code.jquery.com/");
	        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
	        registry.addResourceHandler("/javascriptDatePicker/**").addResourceLocations("/javascriptDatePicker/");
	        registry.addResourceHandler("/datatable/**").addResourceLocations("/datatable/");
	        registry.addResourceHandler("/datepicker/**").addResourceLocations("/datepicker/");
	       
	        registry.addResourceHandler("/downloads/**").addResourceLocations("/downloads/");
	        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
	        registry.addResourceHandler("/pdffiles/**").addResourceLocations("/pdffiles/");
	       
	    }
	  @Bean
	  public InternalResourceViewResolver viewResolver() {
	      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	      resolver.setPrefix("/WEB-INF/jsp/spring");
	      resolver.setSuffix(".jsp");
	      return resolver;
	  }
	  
	  @Override
		public void configureViewResolvers(ViewResolverRegistry registry)
		{
			TilesViewResolver viewResolver = new TilesViewResolver();
			registry.viewResolver(viewResolver);
		}
	   // Configure the MultipartResolver to handle file uploads
    // @Bean
    // public MultipartResolver multipartResolver() {
    //     return new StandardServletMultipartResolver();
    // }
}

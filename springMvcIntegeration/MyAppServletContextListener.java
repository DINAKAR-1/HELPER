package in.gov.cgg.strutsSpringIntegration;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyAppServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Register the filter
        ServletContext container = sce.getServletContext();
        Filter AuthenticationFilter = new LoggingFilter();
        container.addFilter("AuthenticationFilter", AuthenticationFilter)
                 .addMappingForUrlPatterns(null, false, "*.go");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup logic
    }
}
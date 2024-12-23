package in.gov.cgg.strutsSpringIntegration;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialize filter (if needed)
        System.out.println("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        // Cast to HttpServletRequest to access session and other HTTP-specific details
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // Get the session (false means do not create a new session if it doesn't exist)
        HttpSession session = httpRequest.getSession(false); // 'false' means it won't create a session if not already created
        
    // Check if the session exists
        if (session != null) {
            // Retrieve the user_serial_no session attribute
            Object userSerialNo = session.getAttribute("user_serial_no");
            // Check if the user_serial_no is null
            if (userSerialNo == null) {
                System.out.println("Not logged in.");
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/home.do");  // Adjust the URL as needed
                return;  // Stop further processing and redirect the user
            } else {
                System.out.println("Logged in with user_serial_no: " + userSerialNo);
            }
        } else {
            System.out.println("No session exists for this request."); 
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/home.do");  // Adjust the URL as needed
        }  
        // Continue the request-response chain
        chain.doFilter(request, response);  
    }
 
    @Override
    public void destroy() {
        // Clean up resources (if needed)
        System.out.println("AuthenticationFilter destroyed");
    }
}
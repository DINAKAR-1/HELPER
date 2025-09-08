package in.gov.cgg.strutsSpringIntegration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController
{
    @RequestMapping("/example")
    public String example()
    {
        System.out.println("in this----------------");
        return "home"; // JSP page or view nae
    }
}

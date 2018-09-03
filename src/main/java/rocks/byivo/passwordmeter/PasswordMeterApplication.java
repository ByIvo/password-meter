package rocks.byivo.passwordmeter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PasswordMeterApplication extends SpringBootServletInitializer{
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PasswordMeterApplication.class);
    }
    
    public static void main(String[] args) {
	SpringApplication.run(PasswordMeterApplication.class, args);
    }
    
}

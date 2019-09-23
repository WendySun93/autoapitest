import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.research.repair")
public class Application {
    public static void main(String[] args) {
//        SpringApplication application = new SpringApplication(Application.class);
        SpringApplication.run(Application.class,args);

    }
}

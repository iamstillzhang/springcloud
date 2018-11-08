package still.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableEurekaClient
@SpringBootApplication
@EnableEurekaServer
public class Application {
    
        @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World, Still! ";
    }

    @RequestMapping("/testname")
    @ResponseBody
    String getTestName() {
        return "testname";
    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

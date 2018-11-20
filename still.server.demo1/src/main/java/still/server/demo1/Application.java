package still.server.demo1;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        System.out.println(name + " welcome . My is microservice provider user");
        return name + " welcome . My is microservice provider user";
    }

    @RequestMapping("/name")
    public String name() {
        return "still.server.demo1";
    }
    
    @RequestMapping("/user")
    public String user() {
        String host = null;
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println("get server host Exception e:"+ e.toString());
        } 
        return "{name:222}";
        //return String.format("{\"name\":\"still.server.demo1\",\"ip\":\"%s\",\"port\":%d}",
        //        host, 9001);
    }

    @RequestMapping("/ip")
    public String ip() {
        String host = null;

        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println("get server host Exception e:"+ e.toString());
        } catch (Exception e) {
            System.out.println("get server host Exception e:"+ e.toString());
        }
        return host;
    }

    @RequestMapping("/helloworld")
    public String helloworld() {
        return "helloworld";
    }
}

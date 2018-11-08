package still.server.demo2;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.client.RestClientException;

@SpringBootApplication
@RestController
@EnableEurekaClient
@EnableDiscoveryClient
public class Application {

    protected static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @Bean
    @LoadBalanced
    RestTemplate rest() {
        return new RestTemplate();
    }
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/test2")
    public String test2() {
        try {
            ResponseEntity<String> url = restTemplate.getForEntity("http://still.server.demo1/ip", String.class);
            String res = restTemplate.getForEntity("http://still.server.demo1/ip", String.class).getBody();
            return "home" + res;
        } catch (RestClientException e) {
            return "ERROR:" + e.toString();
        }
    }

    @Autowired
    private EurekaClient discoveryClient;

    @RequestMapping("/test1")
    public String test1() {
        StringBuilder res = new StringBuilder();
        try {
            InstanceInfo instance = null;
            res.append(discoveryClient.getAllKnownRegions().toString());
            instance = discoveryClient.getNextServerFromEureka("still.server.demo1", false);
            res.append(String.format("%s:%d<br>",
                    instance.getIPAddr(),
                    instance.getPort()
            ));
            instance = discoveryClient.getNextServerFromEureka("still.server.demo1", false);
            res.append(String.format("%s:%d<br>",
                    instance.getIPAddr(),
                    instance.getPort()
            ));
            instance = discoveryClient.getNextServerFromEureka("still.server.demo1", false);
            res.append(String.format("%s:%d<br>",
                    instance.getIPAddr(),
                    instance.getPort()
            ));

            RestTemplate myTemplate = new RestTemplate();
            String demo1_ip = myTemplate.getForEntity(String.format("http://%s:%d/ip",
                    instance.getIPAddr(),
                    instance.getPort()),
                    String.class).getBody();
            res.append(String.format("获取still.server.demo1的ip方法，返回:%s<br>", demo1_ip));
        } catch (RestClientException e) {
            logger.error(e.toString());
            res.append(e.toString());
        }
        return res.toString();
    }
}

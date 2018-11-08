package still.server.ribbin.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbinController {
    private static Logger LOGGER = LoggerFactory.getLogger(RibbinController.class);
   
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/test1")
    public String test1() {
        StringBuilder res;
        res = new StringBuilder();
        try {
            ServiceInstance instance = loadBalancerClient.choose("still.server.demo1");
            res.append(String.format("loadBalancerClient.choose:%s  %s:%d <br>",
                    instance.getServiceId(),
                    instance.getHost(),
                    instance.getPort()));

            ResponseEntity<String> url = restTemplate.getForEntity("http://still.server.demo1/ip", String.class);
            String demo1Ip = restTemplate.getForEntity("http://still.server.demo1/ip", String.class).getBody();
            res.append(String.format("still.server.demo1/ip:%s<br>" ,demo1Ip));
        } catch (RestClientException e) {
            res.append(String.format("ERROR:%s<br>" , e.toString()));
        }
        catch (Exception e) {
            res.append(String.format("ERROR:%s<br>" , e.toString()));
        }
        return res.toString();
    }

}

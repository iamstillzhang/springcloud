package still.server.feign.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import still.server.feign.model.ClientDemo1;

@RestController
public class FeignController {
    private static Logger LOGGER = LoggerFactory.getLogger(FeignController.class);
      
    @Autowired
    ClientDemo1 clientDemo1;

    @RequestMapping("/test1")
    public String test1() {
        StringBuilder res;
        res = new StringBuilder();
        try {
            res.append(String.format("still.server.demo1/ip:%s<br>" ,clientDemo1.getIP()));
        } catch (RestClientException e) {
            res.append(String.format("ERROR:%s<br>" , e.toString()));
        }
        catch (Exception e) {
            res.append(String.format("ERROR:%s<br> " , e.toString()));
        }
        return res.toString();
    }
    
     @RequestMapping("/test2")
    public String test2() {
        StringBuilder res;
        res = new StringBuilder();
        try {
            res.append(String.format("JSON:%s<br>" ,clientDemo1.getUser2()));
            FastJson json = new 
            clientDemo1.getUser();
            res.append(String.format("still.server.demo1/ip:%s<br>" ,clientDemo1.getIP()));
        } catch (RestClientException e) {
            res.append(String.format("ERROR:%s<br>" , e.toString()));
        }
        catch (Exception e) {
            res.append(String.format("ERROR:%s<br>" , e.toString()));    
        }
        return res.toString();
    }
    

}

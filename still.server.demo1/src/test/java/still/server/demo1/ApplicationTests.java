package still.server.demo1;

import java.net.URL;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 
public class ApplicationTests {

    /**
     * @LocalServerPort 提供了 @Value("${local.server.port}") 的代替
     */
   // @LocalServerPort
    final private int port = 9001;
    private URL base;
    @Autowired
    private TestRestTemplate restTemplate;
    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/", port);
        System.out.println(String.format("port is : [%d]", port));
        this.base = new URL(url);
    }
    //@Test
    public void testHome() throws Exception {
         ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.base.toString() + "/", String.class, "");
        String body = response.getBody();
        if(body !=null && body.contains("home"))
            return;
        throw new Exception("ERROR");
    }
    @Test
    public void contextLoads() {
    }
    /**
     * 向"/name"地址发送请求，并打印返回结果
     *
     * @throws Exception
     */
    //@Test
    public void test1() throws Exception {

        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.base.toString() + "/name", String.class, "");
        System.out.println(String.format("测试结果为：%s", response.getBody()));
    }

}

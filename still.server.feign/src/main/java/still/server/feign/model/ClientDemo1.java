/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package still.server.feign.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author xinhui.zhang
 */
@FeignClient("STILL.SERVER.DEMO1")
public interface ClientDemo1 {
    @RequestMapping(method = RequestMethod.GET, value = "/ip")
    public  String  getIP();
}

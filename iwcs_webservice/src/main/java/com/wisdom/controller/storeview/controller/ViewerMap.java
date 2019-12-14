package com.wisdom.controller.storeview.controller;


import com.wisdom.controller.storeview.utils.HttpRequest;
import com.wisdom.controller.storeview.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;

@RestController
@RequestMapping("/api/")
public class ViewerMap {
    @GetMapping(value = "/getMap/{id}")
    public Result getMap(@PathVariable Integer id) {
        StringBuilder result = new StringBuilder();
        try{
//            System.getProperty("user.dir")
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/map.xml"));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){
                result.append(s+System.lineSeparator());
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Result(result.toString());
    }
    @GetMapping(value = "/getPoint/{id}")
    public Result getPoint(@PathVariable Integer id) {
        String responseString = "";
        try{
            String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:cs1=\"http://client.ws.ecs.hikvision.com/\" xmlns:cs2=\"http://client.ws.custom.tcs.hikvision.com/\" xmlns:cs3=\"http://client.ws.tcs.iwcs.hikvision.com/\" xmlns:cs4=\"http://mc.webservice.remote.wms.hikvision.com/\"><SOAP-ENV:Body><cs1:getMapDataInfo><mapCode>164F3B99EE211BV</mapCode></cs1:getMapDataInfo></SOAP-ENV:Body></SOAP-ENV:Envelope>";
            responseString = HttpRequest.sendPost("http://192.168.105.30/rcs/services/ClientService", body);
            Integer start = responseString.indexOf("<return>");
            Integer end = responseString.indexOf("</return>");
            responseString = responseString.substring(start + 8, end - start).replace("&amp;", "&").replace("&lt;", "<").replace("&gt;", ">").replace("&nbsp;", " ").replace("&quot;", "\"").replace("&#39;", "\'");
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Result(responseString);
    }
}

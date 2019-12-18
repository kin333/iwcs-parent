package com.wisdom.controller.storeview.controller;


import com.wisdom.controller.storeview.utils.HttpRequest;
import com.wisdom.controller.storeview.utils.Result;
import com.wisdom.iwcs.domain.base.BaseMap;
import com.wisdom.iwcs.domain.base.MapData;
import com.wisdom.iwcs.mapper.base.BaseMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

@RestController
@RequestMapping("/api/")
public class ViewerMap {

    @Autowired
    BaseMapMapper baseMapMapper;

    @PostMapping(value = "/getMap")
    public Result getMap(@RequestBody MapData data) {

        BaseMap baseMap = baseMapMapper.selectMapByCode(data.getMapCode());

        if (baseMap.getVersion().equals(data.getVersion())) {
            return new Result("1");
        } else {
            return new Result(baseMap);
        }
//        StringBuilder result = new StringBuilder();
//        try{
////            System.getProperty("user.dir")
//            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/map.xml"));//构造一个BufferedReader类来读取文件
//            String s = null;
//            while((s = br.readLine())!=null){
//                result.append(s+System.lineSeparator());
//            }
//            br.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return new Result(result.toString());
    }
    @GetMapping(value = "/getPoint/{mapCode}")
    public Result getPoint(@PathVariable String mapCode) {
        String responseString = "";
        try{
            String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:cs1=\"http://client.ws.ecs.hikvision.com/\" xmlns:cs2=\"http://client.ws.custom.tcs.hikvision.com/\" xmlns:cs3=\"http://client.ws.tcs.iwcs.hikvision.com/\" xmlns:cs4=\"http://mc.webservice.remote.wms.hikvision.com/\">" +
                    "<SOAP-ENV:Body><cs1:getMapDataInfo><mapCode>"+ mapCode +"</mapCode></cs1:getMapDataInfo></SOAP-ENV:Body></SOAP-ENV:Envelope>";
            responseString = HttpRequest.sendPost("http://192.168.105.30/rcs/services/ClientService", body);
            Integer start = responseString.indexOf("<return>");
            Integer end = responseString.indexOf("</return>");
            responseString = responseString.substring(start + 8, end - start).replace("&amp;", "&").replace("&lt;", "<").replace("&gt;", ">").replace("&nbsp;", " ").replace("&quot;", "\"").replace("&#39;", "\'");
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Result(responseString);
    }

    public static String compress(String str) throws IOException {
        if (null == str || str.length() <= 0) {
            return str;
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 使用默认缓冲区大小创建新的输出流
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        // 将 b.length 个字节写入此输出流
        gzip.write(str.getBytes());
        gzip.close();
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toString("ISO-8859-1");
    }
}

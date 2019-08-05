package com.wisdom.test;

import com.wisdom.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

/**
 * @ClassName: ComparingVersionsConsistent
 * @Description: 版本properties文件对比工具类，未完成待修改
 * @Author: baoxun
 * @Date: 2019/8/2
 */

@Service
public class ComparingVersionsConsistent {
    private final Logger logger = LoggerFactory.getLogger(ComparingVersionsConsistent.class);

    public static String VERTION = "version";

    /**
     * 启动项目时判断项目是重启还是更新
     */
    public void compare() {
        Properties upgradeLog = new Properties();
        Properties service = new Properties();
        InputStream upgradeLogStream = null;
        InputStream serviceStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            //获取项目当前版本信息文件的输入流
            upgradeLogStream = new ClassPathResource("static/check/upgradeLog.properties").getInputStream();
            //获取服务器上次启动版本信息文件的输入流
            serviceStream = new ClassPathResource("static/service/server.properties").getInputStream();
            //加载文件流
            upgradeLog.load(upgradeLogStream);
            service.load(serviceStream);

            //获取版本信息
            String currentVersion = upgradeLog.getProperty(VERTION);
            String preVersion = service.getProperty(VERTION);

            //发送结果
            if (currentVersion.equals(preVersion)) {
                logger.info(">>>>>>>>>>>>>>>>>>项目重启成功,版本号:{}", currentVersion);
            } else {
                //更新服务器的版本信息
                logger.info(">>>>>>>>>>>>>>>>>>项目启动成功,当前版本号:{},更新前版本号:{}", currentVersion, preVersion);
                service.setProperty(VERTION, currentVersion);
                String path = Application.class.getClassLoader().getResource("static/service/server.properties").getPath();
                fileOutputStream = new FileOutputStream(path);
                service.store(fileOutputStream, "Update value");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (upgradeLogStream != null) {
                    upgradeLogStream.close();
                }
                if (serviceStream != null) {
                    serviceStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


//        File file =new File("service");
//        //如果文件夹不存在则创建
//        if  (!file .exists()  && !file .isDirectory())
//        {
//            logger.info("service路径不存在");
//            file .mkdir();
//        } else {
//            System.out.println("//目录存在");
//            /*判断server.properties文件是否存在*/
//            File filee=new File("service\\server.properties");
//            if(!filee.exists()) {
//                logger.info("文件不存在");
//                try {
//                    filee.createNewFile();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }
//        /*判断currentInfo.properties与server.properties文件是否一致*/
//        try {
//            String file1 = "upgradeLog\\upgradeLog.properties";
//            String file2 = "service\\server.properties";
//            compareProperties(file1, file2);
//        } catch (Exception e) {
//            System.err.println("Error: " + e.getMessage());
//        }
    }
    /**
     * 比较两个properties文件,所有信息比较
     * @param file1
     * @param file2
     */
    /*
    public static void compareProperties(String file1,String file2){

        ArrayList<Property> list1 = ComparingVersionsConsistent.getPropList(file1);
        ArrayList<Property> list2 = ComparingVersionsConsistent.getPropList(file2);
        ArrayList<String> only1 = new ArrayList<String>();
        ArrayList<String> only2 = new ArrayList<String>();
        ArrayList<String> diff = new ArrayList<String>();

        for(Property p1:list1){
            boolean found = false;
            String tmpValue = "";
            for(Property p2:list2){
                if(p2.getKey().equals(p1.getKey())){
                    found = true;
                    tmpValue = p2.getValue();
                }
            }
            if(!found){
                only1.add(p1.getKey()+" = "+p1.getValue()+"\n");
            } else if(!tmpValue.equals(p1.getValue())){
                diff.add("Key: "+p1.getKey()+"\n"+"value1 = "+p1.getValue()+"\n"+"value2 = "+tmpValue+"\n");
            }
        }
        for(Property p2:list2){
            boolean found = false;
            for(Property p1:list1){
                if(p1.getKey().equals(p2.getKey())){
                    found = true;
                }
            }
            if(!found){
                only2.add(p2.getKey()+" = "+p2.getValue()+"\n");
            }
        }
        System.out.println("========================只存在文件1中的: ");
        for(String str:only1){
            System.out.println(str);
        }
        System.out.println("========================只存在文件2中的: ");
        for(String str:only2){
            System.out.println(str);
        }
        System.out.println("========================两个文件都有，但value不同的 : ");
        for(String str:diff){
            System.out.println(str);
        }
    }
    */

    /**
     * 比较两个properties文件,版本号比较
     * @param file1
     * @param file2
     */
    public void compareProperties(String file1, String file2) {
        Properties upgradeLog = new Properties();
        Properties service = new Properties();
        Properties hello = new Properties();

        try {
            InputStream in = this.getClass().getResourceAsStream("hello.properties");
            ///加载属性列表
            hello.load(in);
            Iterator<String> it = hello.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
                System.out.println(key+":"+hello.getProperty(key));
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        ArrayList<Property> list1 = ComparingVersionsConsistent.getPropList(file1);
//        ArrayList<Property> list2 = ComparingVersionsConsistent.getPropList(file2);
//        for(Property p1:list1){
//            boolean found = false;
//            String tmpValue = "";
//            for(Property p2:list2){
//                if(p2.getKey().equals(p1.getKey())){
//                    found = true;
//                    tmpValue = p2.getValue();
//                }
//            }
//            if(!tmpValue.equals(p1.getValue())){
//                logger.info("请更新当前版本！！！");
//            }else{
//                logger.info("当前版本已是最新！");
//            }
//        }//for
    }
    /**
     * 获取文件中k&v
     * @param file
     * @return
     */
    public ArrayList<Property> getPropList(String file){
        ArrayList<Property> pList = new ArrayList<Property>();
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fileInputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                //过滤注释
                if(!strLine.contains("#") && strLine.contains("=")){
                    pList.add(new Property(strLine));
                }
            }
            in.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return pList;
    }
    static class Property {
        private String key;
        private String value;
        public Property(String str) {
            super();
            str = str.trim();
            String[] tmpArr = str.split("=",2);
            this.key = tmpArr[0].trim();
            this.value = tmpArr[1].trim();
        }
        public String getKey() {
            return key;
        }
        public String getValue() {
            return value;
        }
    }
}

package com.wisdom.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;

/**
 * @ClassName: ComparingVersionsConsistent
 * @Description: 版本properties文件对比工具类，未完成待修改
 * @Author: baoxun
 * @Date: 2019/8/2
 */

@Service
public class ComparingVersionsConsistent {
    private static final Logger logger = LoggerFactory.getLogger(ComparingVersionsConsistent.class);

    public  static void compare() {

        File file =new File("service");
        //如果文件夹不存在则创建
        if  (!file .exists()  && !file .isDirectory())
        {
            logger.info("service路径不存在");
            file .mkdir();
        } else {
            System.out.println("//目录存在");
            /*判断server.properties文件是否存在*/
            File filee=new File("service\\server.properties");
            if(!filee.exists()) {
                logger.info("文件不存在");
                try {
                    filee.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        /*判断currentInfo.properties与server.properties文件是否一致*/
        try {
            String file1 = "check\\upgradeLog.properties";
            String file2 = "service\\server.properties";
            ComparingVersionsConsistent.compareProperties(file1, file2);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
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
    public static void compareProperties(String file1,String file2) {
        ArrayList<Property> list1 = ComparingVersionsConsistent.getPropList(file1);
        ArrayList<Property> list2 = ComparingVersionsConsistent.getPropList(file2);
        for(Property p1:list1){
            boolean found = false;
            String tmpValue = "";
            for(Property p2:list2){
                if(p2.getKey().equals(p1.getKey())){
                    found = true;
                    tmpValue = p2.getValue();
                }
            }
            if(!tmpValue.equals(p1.getValue())){
                logger.info("请更新当前版本！！！");
            }else{
                logger.info("当前版本已是最新！");
            }
        }//for
    }
    /**
     * 获取文件中k&v
     * @param file
     * @return
     */
    public static ArrayList<Property> getPropList(String file){
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

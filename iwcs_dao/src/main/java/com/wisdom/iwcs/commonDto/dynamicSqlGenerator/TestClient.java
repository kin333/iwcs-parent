package com.wisdom.iwcs.commonDto.dynamicSqlGenerator;

import java.util.HashMap;

/**
 * 测试
 *
 * @author ted
 * @create 2019-03-02 下午4:37
 **/
public class TestClient {

    public static void main(String[] args) {
        String originSql = "(  [t.podtype] = #{podType} and [b.bincode_type] != #{bincodeType}  )  or  t.podType = $F3";
        SqlDymaticInstructionHandler sqlDymaticInstructionHandler = new SqlDymaticInstructionHandler();
        HashMap<String, Object> params = new HashMap<>();
        params.put("podType", "F2");
        params.put("bincodeType", "30*30");
        sqlDymaticInstructionHandler.handle(originSql, params);
        System.out.println(sqlDymaticInstructionHandler.output());
        System.out.println(sqlDymaticInstructionHandler.getResultStr());

    }
}

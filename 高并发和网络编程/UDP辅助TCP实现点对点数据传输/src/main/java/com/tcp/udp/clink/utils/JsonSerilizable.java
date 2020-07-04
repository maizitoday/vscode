package com.tcp.udp.clink.utils;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-01-14 12:25:32
 * @LastEditTime : 2020-01-14 14:40:13
 * @LastEditors  : 麦子
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class JsonSerilizable {
    /* 将HashMap序列化为字符串存入json文件中 */
    public static String serilizableForMap(Object objMap) throws IOException {

        String listString = JSON.toJSONString(objMap, true);// (maps,CityEntity.class);
        return listString;
    }

    /* 将json文件中的内容读取出来，反序列化为HashMap */
    public static <T, K> HashMap<K, T> deserilizableForMapFromFile(String listString2, Class<T> clazz)
            throws IOException {

        Map<K, T> map = JSON.parseObject(listString2, new TypeReference<Map<K, T>>() {
        });

        return (HashMap<K, T>) map;
    }

    public static byte[] changeMapToByte(Map<String, Object> map) {

        byte[] bytes = null;
        try {
            bytes = JsonSerilizable.serilizableForMap(map).getBytes();
        } catch (Exception e) {
            System.out.println("map到byte[]转换异常");
            e.printStackTrace();
        }

        return bytes;
    }

    public static Map<String, Object> changeByteToMap(byte[] bytes) {
        Map<String, Object> retmap = null;
        try {
            if (bytes != null) {
                retmap = JsonSerilizable.deserilizableForMapFromFile(new String(bytes), Object.class);
            } else {
                System.out.println("changeByteToMap中bytes为null");
            }
        } catch (Exception e) {
            System.out.println("byte到map转换异常");
            e.printStackTrace();
        }
        return retmap;
    }

}
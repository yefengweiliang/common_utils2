package com.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * json转换工具类
 *
 * @author wanghongshen
 * @date 2019-03-01
 */
@SuppressWarnings("all")
public class JsonUtil {


        private static final SerializeConfig config;

        static {
            config = new SerializeConfig();
            config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
            config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        }

        private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
                SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
                SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
                SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
                SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
        };


        /**
         * 转换成字符串
         *
         * @param object 对象
         * @return 字符串
         */
        public static String toJSONString(Object object) {
            return JSON.toJSONString(object, config);
        }

        /**
         * 转换成字符串 ,带有过滤器
         *
         * @param object 对象
         * @return 字符串
         */
        public static String toJSONWithFeatures(Object object) {
            return JSON.toJSONString(object, config, features);
        }


        /**
         * 转成bean对象
         *
         * @param text 字符串
         * @return 对象
         */
        public static Object toBean(String text) {
            return JSON.parse(text);
        }

        /**
         * 转成具体的泛型bean对象
         *
         * @param text 字符串
         * @param clazz 泛型对象
         * @param <T> 泛型
         * @return 泛型对象
         */
        public static <T> T toBean(String text, Class<T> clazz) {
            return JSON.parseObject(text, clazz);
        }

        /**
         * 转换为数组Array
         *
         * @param text 字符串
         * @param <T> 泛型
         * @return 数组
         */
        public static <T> Object[] toArray(String text) {
            return JSON.parseArray(text, (Class<Object>) null).toArray();
        }

        /**
         * 转换为具体的泛型数组Array
         *
         * @param text 字符串
         * @param clazz 泛型对象
         * @param <T> 泛型
         * @return 泛型数组
         */
        public static <T> Object[] toArray(String text, Class<T> clazz) {
            return JSON.parseArray(text, clazz).toArray();
        }

        /**
         * 转换为具体的泛型List
         * @param text 字符串
         * @param clazz 泛型对象
         * @param <T> 泛型
         * @return 泛型list
         */
        public static <T> List<T> toList(String text, Class<T> clazz) {
            return JSON.parseArray(text, clazz);
        }

        /**
         * 将javabean转化为序列化的json字符串
         *
         * @param keyvalue bean对象
         * @return json对象
         */
        public static Object beanToJson(KeyValue keyvalue) {
            String textJson = JSON.toJSONString(keyvalue);
            Object objectJson = JSON.parse(textJson);
            return objectJson;
        }

        /**
         * 将string转化为序列化的json字符串
         *
         * @param text 字符串
         * @return json对象
         */
        public static Object textToJson(String text) {
            Object objectJson = JSON.parse(text);
            return objectJson;
        }

        /**
         * json字符串转化为map
         *
         * @param s json字符串
         * @return map
         */
        public static Map stringToMap(String s) {
            Map m = JSONObject.parseObject(s);
            return m;
        }

        /**
         * 将map转化为string
         *
         * @param m map
         * @return 字符串
         */
        public static String mapToString(Map m) {
            String s = JSONObject.toJSONString(m);
            return s;
        }

        /**
         * 用fastjson 将jsonString 解析成 List<Map<String,Object>>
         *
         * @param jsonString json字符串
         * @return list<Map>
         */
        public static List<Map<String, Object>> getListMap(String jsonString) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            try {
                // 两种写法
                // list = JSON.parseObject(jsonString, new
                // TypeReference<List<Map<String, Object>>>(){}.getType());

                list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
}


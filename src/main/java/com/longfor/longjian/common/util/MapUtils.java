package com.longfor.longjian.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapUtils<K, V> extends HashMap<K, V> {

    public MapUtils<K, V> putVal(K key, V value) {
        super.put(key, value);
        return this;
    }
    
    public Map<String,Object> obj2MapWithHumpName(Object obj) {
    	try {
    		Map<String,Object> map=new HashMap<String, Object>();
            Field[] fields = obj.getClass().getDeclaredFields();
            for(Field field:fields){
                field.setAccessible(true);
                map.put(getHumpName(field.getName()), field.get(obj));
            }
            return map;
		} catch (Exception e) {
			log.error("obj2Map is error",e);
			return null;
		}
    }
    
    private String getHumpName(String name) {
    	StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel :  camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    	
    }

}

package com.longfor.longjian.common.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.longfor.longjian.common.base.LjBaseResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class InitClassAttr {

    private final static String LONGJIAN_PACKAGE = "com.longfor.longjian";

    public static void init(LjBaseResponse res) {
        if (Objects.isNull(res.getData())) {
            if (res.getDataClazz() == 1) {
                res.setData(Maps.newHashMap());
            }
            if (res.getDataClazz() == 2) {
                res.setData(Lists.newArrayList());
            }
            if (res.getDataClazz() == 3) {
                if (Objects.isNull(res.getBaseDateClazz())) {
                    res.setBaseDateClazz(Integer.class);
                }
                res.setData(initBaseType(res.getBaseDateClazz()));
            }
            return;
        }
        Object initResult = initAttr(res.getData(), res.getData().getClass());
//        if (isBaseType(res.getDataClazz())) {
        res.setData(initResult);
//        }
    }

    public static Object initAttr(Object object, Class clazz) {
        if (isBaseType(clazz)) {
            if (Objects.nonNull(object)) {
                return object;
            }
            return initBaseType(clazz);
        }
        if (isListType(clazz)) {
            return initList(object);
        }
        if (isMapType(clazz)) {
            return initMap(object);
        }
        if (clazz.getPackage().getName().startsWith(LONGJIAN_PACKAGE)) {
            return ljInit(object, clazz);
        }
        return object;
    }

    private static Object initList(Object object) {
        if (Objects.nonNull(object)) {
            List list = (List) object;
            Object nonNullObject = list.stream().filter(vo -> Objects.nonNull(vo)).findFirst().orElse(null);
            if (Objects.isNull(nonNullObject)) {
                list.clear();
                return object;
            }
            for (int i = 0; i < list.size(); i++) {
                Object item = list.get(i);
                Class cc;
                if (Objects.isNull(item)) {
                    cc = nonNullObject.getClass();
                    list.set(i, getInit(cc));
                    continue;
                } else {
                    cc = item.getClass();
                }
                Object initResult = initAttr(item, cc);
                if (isBaseType(cc)) {
                    list.set(i, initResult);
                }
            }
            return object;
        }
        return Lists.newArrayList();
    }

    private static Object initMap(Object object) {
        if (Objects.nonNull(object)) {
            Map map = (Map) object;
            Set keys = map.keySet();
            Object nonNullObject = map.values().stream().filter(vo -> Objects.nonNull(vo)).findFirst().orElse(null);
            if (Objects.isNull(nonNullObject)) {
                map.clear();
                return object;
            }
            keys.forEach(k -> {
                Object v = map.get(k);
                Class cc;
                if (Objects.isNull(v)) {
                    cc = nonNullObject.getClass();
                    map.put(k, getInit(cc));
                    return;
                } else {
                    cc = v.getClass();
                }
                Object initResult = initAttr(v, cc);
                if (isBaseType(cc)) {
                    map.put(k, initResult);
                }
            });
            return object;
        }
        return Maps.newHashMap();
    }

    private static Object ljInit(Object object, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Class clazzField = field.getType();
            try {
                field.setAccessible(true);
                if (Objects.isNull(field.get(object)) && isBaseType(clazzField)) {
                    initBaseType(field, clazzField, object);
                    continue;
                }
                if (isListType(clazzField)) {
                    List list = (List) field.get(object);
                    if (Objects.nonNull(list)) {
//                        list.forEach(vo -> initAttr(vo, vo.getClass()));
                        initList(list);
                    } else {
                        field.set(object, Lists.newArrayList());
                    }
                    continue;
                }
                if (isMapType(clazzField)) {
                    Map map = (Map) field.get(object);
                    if (Objects.nonNull(map)) {
//                        map.values().forEach(vo -> initAttr(vo, vo.getClass()));
                        initMap(map);
                    } else {
                        field.set(object, Maps.newHashMap());
                    }
                    continue;
                }
                if (clazzField.getPackage().getName().startsWith(LONGJIAN_PACKAGE)) {
                    initAttr(field.get(object), field.getType());
                }
            } catch (Exception e) {
                log.error("null返回值处理失败", e);
            }
        }
        return object;
    }

    private static Object getInit(Class clazz) {
        if (isListType(clazz)) {
            return Lists.newArrayList();
        }
        if (isMapType(clazz)) {
            return Maps.newHashMap();
        }
        if (isBaseType(clazz)) {
            return initBaseType(clazz);
        }
        return 0;
    }

    public static void initBaseType(Field field, Class clazz, Object object) throws IllegalAccessException {
        if (Integer.class.isAssignableFrom(clazz) || int.class.isAssignableFrom(clazz)) {
            field.set(object, 0);
        }
        if (Long.class.isAssignableFrom(clazz) || long.class.isAssignableFrom(clazz)) {
            field.set(object, 0);
        }
        if (Float.class.isAssignableFrom(clazz) || float.class.isAssignableFrom(clazz)) {
            field.set(object, 0);
        }
        if (Double.class.isAssignableFrom(clazz) || double.class.isAssignableFrom(clazz)) {
            field.set(object, 0);
        }
        if (Byte.class.isAssignableFrom(clazz) || byte.class.isAssignableFrom(clazz)) {
            field.set(object, 0);
        }
        if (Short.class.isAssignableFrom(clazz) || short.class.isAssignableFrom(clazz)) {
            field.set(object, 0);
        }
        if (Character.class.isAssignableFrom(clazz) || char.class.isAssignableFrom(clazz)) {
            field.set(object, 0);
        }
        if (Boolean.class.isAssignableFrom(clazz) || boolean.class.isAssignableFrom(clazz)) {
            field.set(object, false);
        }
        if (String.class.isAssignableFrom(clazz)) {
            field.set(object, "");
        }
        if (Date.class.isAssignableFrom(clazz)) {
            field.set(object, new Date(0L));
        }
    }

    public static Object initBaseType(Class clazz) {
        if (Integer.class.isAssignableFrom(clazz) || int.class.isAssignableFrom(clazz)) {
            return 0;
        }
        if (Long.class.isAssignableFrom(clazz) || long.class.isAssignableFrom(clazz)) {
            return 0;
        }
        if (Float.class.isAssignableFrom(clazz) || float.class.isAssignableFrom(clazz)) {
            return 0;
        }
        if (Double.class.isAssignableFrom(clazz) || double.class.isAssignableFrom(clazz)) {
            return 0;
        }
        if (Byte.class.isAssignableFrom(clazz) || byte.class.isAssignableFrom(clazz)) {
            return 0;
        }
        if (Short.class.isAssignableFrom(clazz) || short.class.isAssignableFrom(clazz)) {
            return 0;
        }
        if (Character.class.isAssignableFrom(clazz) || char.class.isAssignableFrom(clazz)) {
            return 0;
        }
        if (Boolean.class.isAssignableFrom(clazz) || boolean.class.isAssignableFrom(clazz)) {
            return false;
        }
        if (String.class.isAssignableFrom(clazz)) {
            return "";
        }
        if (Date.class.isAssignableFrom(clazz)) {
            return new Date(0L);
        }
        return 0;
    }

    public static boolean isBaseType(Class clazz) {
        return Integer.class.isAssignableFrom(clazz)
                || int.class.isAssignableFrom(clazz)
                || Long.class.isAssignableFrom(clazz)
                || long.class.isAssignableFrom(clazz)
                || Float.class.isAssignableFrom(clazz)
                || float.class.isAssignableFrom(clazz)
                || Double.class.isAssignableFrom(clazz)
                || double.class.isAssignableFrom(clazz)
                || Byte.class.isAssignableFrom(clazz)
                || byte.class.isAssignableFrom(clazz)
                || Short.class.isAssignableFrom(clazz)
                || short.class.isAssignableFrom(clazz)
                || Character.class.isAssignableFrom(clazz)
                || char.class.isAssignableFrom(clazz)
                || Boolean.class.isAssignableFrom(clazz)
                || boolean.class.isAssignableFrom(clazz)
                || String.class.isAssignableFrom(clazz)
                || Date.class.isAssignableFrom(clazz);
    }

    public static boolean isListType(Class clazz) {
        return List.class.isAssignableFrom(clazz);
    }

    public static boolean isMapType(Class clazz) {
        return Map.class.isAssignableFrom(clazz);
    }

    private static void demo() {
        InitTest test = new InitTest();
        ListTest list = new ListTest();
        ListTest list2 = new ListTest();
        test.listTest = Lists.newArrayList(list, list2);
        MapTest map = new MapTest();
        MapTest map1 = new MapTest();
        test.mapTest = Maps.newHashMap();
        test.mapTest.put("map", map);
        test.mapTest.put("map1", map1);
//        test.baseList = Lists.newArrayList(1, null);
        LjBaseResponse<InitTest> response = new LjBaseResponse<>(test);
        System.out.println(JSON.toJSONString(response.getData()));
        InitClassAttr.init(response);
        System.out.println(JSON.toJSONString(response.getData()));
    }

    private static void demo1() {
        LjBaseResponse<InitTest> response = new LjBaseResponse<>(null);
        System.out.println(JSON.toJSONString(response.getData()));
        response.setDataClazz(3);
        InitClassAttr.init(response);
        System.out.println(JSON.toJSONString(response.getData()));
    }

    private static void demo2() {
        LjBaseResponse<List> response = new LjBaseResponse<>(Lists.newArrayList(1, null, Lists.newArrayList(1, null), Maps.newHashMap()));
        System.out.println(JSON.toJSONString(response.getData()));
        InitClassAttr.init(response);
        System.out.println(JSON.toJSONString(response.getData()));
    }

    private static void demo3() {
        LjBaseResponse<Map> response = new LjBaseResponse<>();
        Map map = Maps.newHashMap();
        map.put("a", null);
        map.put("b", 1);
        response.setData(map);
        System.out.println(JSON.toJSONString(response.getData()));
        InitClassAttr.init(response);
        System.out.println(JSON.toJSONString(response.getData()));
    }

    public static void main(String[] args) throws IllegalAccessException {
//        demo();
//        demo1();
//        demo2();
//        demo3();
        System.out.println(new Date().getTime());
        System.out.println(new Date(0L).getTime());
    }

}

@Data
class InitTest {
    Integer sex;
    List<Integer> baseList;
    List<ListTest> listTest;
    Map<String, MapTest> mapTest;
}

@Data
class ListTest {
    String name;
}

@Data
class MapTest {
    Integer age;
}
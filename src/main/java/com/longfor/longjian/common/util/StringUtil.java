package com.longfor.longjian.common.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 字符串处理工具类
 *
 * @author zhouxingjia
 */
@Slf4j
public class StringUtil {

    public static List<Integer> strToInts(String str, String split) {
        if (StringUtils.isBlank(str)) {
            return Lists.newArrayList();
        }
        split = ".".equals(split) ? "\\." : split;
        Stream<String> sids = Arrays.stream(str.split(split));
        return sids.filter(StringUtils::isNotBlank).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
    }

    public static List<Float> strToFloats(String str, String split) {
        if (StringUtils.isBlank(str)) {
            return Lists.newArrayList();
        }
        split = ".".equals(split) ? "\\." : split;
        Stream<String> sids = Arrays.stream(str.split(split));
        return sids.filter(StringUtils::isNotBlank).map(Float::parseFloat).collect(Collectors.toList());
    }

    public static List<String> strToStrs(String str, String split) {
        if (StringUtils.isBlank(str)) {
            return Lists.newArrayList();
        }
        split = ".".equals(split) ? "\\." : split;
        Stream<String> sids = Arrays.stream(str.split(split));
        return sids.filter(StringUtils::isNotBlank).collect(Collectors.toList());
    }

    /**
     * 去掉字符串首尾的指定字符
     *
     * @param source  需要处理的字符串
     * @param element 需要去掉的字符
     * @return
     */
    public static String trimFirstAndLastChar(String source, char element) {
        if (source.equals(String.valueOf(element))) {
            return source;
        }
        boolean beginIndexFlag;
        boolean endIndexFlag;
        do {
            int beginIndex = source.indexOf(element) == 0 ? 1 : 0;
            int endIndex = source.lastIndexOf(element) + 1 == source.length() ? source.lastIndexOf(element) : source.length();
            source = source.substring(beginIndex, endIndex);
            beginIndexFlag = (source.indexOf(element) == 0);
            endIndexFlag = (source.lastIndexOf(element) + 1 == source.length());
        } while (beginIndexFlag || endIndexFlag);
        return source;
    }

    /**
     * 默认以逗号为分隔符
     *
     * @param str
     * @return
     */
    public static List<Integer> strToInts(String str) {
        return strToInts(str, ",");
    }

    /**
     * 将字符串中的中文转成Unicode
     *
     * @param cn
     * @return
     */
    public static String cnToUnicode(String cn) {
        char[] chars = cn.toCharArray();
        StringBuilder returnStr = new StringBuilder();
        for (char aChar : chars) {
            if (isChinese(aChar)) {
                returnStr.append("\\u").append(Integer.toString(aChar, 16));
            } else {
                returnStr.append(aChar);
            }
        }
        return returnStr.toString();
    }

    /**
     * 判定输入的是否是汉字
     *
     * @param c
     * @return
     */
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }


    public List<Integer> splitToIdsSlash(String idsStr,boolean ignoreBlank){
        return splitToIds(idsStr,"/",ignoreBlank);
    }

    public List<Integer> splitToIds(String idsStr, String sep, boolean ignoreBlank) {
        List<Integer> ids = new ArrayList<>();
        for (String idStr:idsStr.split(sep)
             ) {
            idsStr = idStr.trim();
            if ("".equals(idsStr)){
                continue;
            }
            try {
                int id = Integer.parseInt(idsStr);
                ids.add(id);
            }catch (NumberFormatException e){
                if (ignoreBlank){
                    continue;
                }
                log.error("error1: "+ e.getMessage());
                throw e;
            }
        }
        return ids;
    }

}

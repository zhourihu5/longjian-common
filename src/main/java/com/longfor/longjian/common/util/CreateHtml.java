package com.longfor.longjian.common.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

public class CreateHtml {

    public static String create(String templatePath, String ftl, Object param) throws Exception{
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        Writer out = null;
        String res = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(templatePath));
            // step4 加载模版文件
            Template template = configuration.getTemplate(ftl);
            // step5 生成数据
//            out = new OutputStreamWriter(System.out);
            out = new StringWriter();
            // step6 输出文件
            template.process(param, out);
            res = out.toString();
        } finally {
            if (null != out) {
                out.flush();
            }
        }
        System.out.println(res);
        return res;
    }

//    public static void main(String[] args) throws Exception{
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("name", "itdragon博客");
//        dataMap.put("dateTime", new Date());
//
////        List<User> users = new ArrayList<>();
////        users.add(new User(1, "ITDragon 博客"));
////        users.add(new User(2, "欢迎"));
////        users.add(new User(3, "You！"));
////        dataMap.put("users", users);
//        create(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath() + "templates", dataMap);
//    }
}



package com.longfor.longjian.common.push.xiaomi;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 小米推送消息 工具类
 * Created by Wang on 2018/8/3.
 */
public class XmPushUtil {

    private static Logger logger = LoggerFactory.getLogger(XmPushUtil.class);

    /**
     * @param APP_SECRET_KEY  小米key
     * @param MY_PACKAGE_NAME  小米包
     * @param title     消息标题
     * @param description  消息内容
     * @param accoutList 消息接收人  集合
     * @return
     */
    public static boolean sendMessageToUserAccounts(String APP_SECRET_KEY,String MY_PACKAGE_NAME,String title,String description,List<String> accoutList){

        boolean flag=true;
        Constants.useOfficial();
        Sender sender = new Sender(APP_SECRET_KEY);
        Message message = new Message.Builder()
                .title(title)
                .extra(Constants.EXTRA_PARAM_NOTIFY_EFFECT,Constants.NOTIFY_LAUNCHER_ACTIVITY)
                .description(description).payload("")
                .restrictedPackageName(MY_PACKAGE_NAME)
                .notifyType(1)     // 使用默认提示音提示
                .build();
        try {
            Result result= sender.sendToUserAccount(message, accoutList, 3); //根据accountList, 发送消息到指定设备上
            flag=result.getErrorCode().getValue()==0?true:false;//返回0则表示成功
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return flag;
    }

    public  static void main(String args[]){
        String APP_SECRET_KEY="vk8L5K3GgnhP0WxkXgx7yg==";
        String MY_PACKAGE_NAME="com.longfor.building";
        String title ="测测看看";
        String description ="但是速度速度所多所多所多所打扫打扫打扫的所多";
        List<String> accoutList = new ArrayList<String>();
        accoutList.add("user_id_longhu_16764");  //useraccount非空白，不能包含逗号, 长度小于128
        boolean flag=sendMessageToUserAccounts(APP_SECRET_KEY,MY_PACKAGE_NAME,title,description,accoutList);
        System.out.println(flag);
    }
}

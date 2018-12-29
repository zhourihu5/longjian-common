package com.longfor.longjian.common.push;

import com.longfor.longjian.common.push.android.AndroidCustomizedcast;
import com.longfor.longjian.common.push.android.AndroidNotification;
import com.longfor.longjian.common.push.ios.IOSCustomizedcast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 友盟推送消息工具类
 * Created by Wang on 2018/8/3.
 */
public class UmPushUtil {

    private static Logger logger = LoggerFactory.getLogger(UmPushUtil.class);

    private static PushClient client = new PushClient();

    /**
     * @param appkey  安卓key
     * @param appMasterSecret  安卓secret
     * @param alias  接收人 要求不超过500个, 多个以英文逗号间隔
     * @param alias_type  可由开发者自定义
     * @param ticker   通知栏提示文字
     * @param title    通知标题
     * @param text     通知文字描述
     * @param custom   用户自定义内容，可以为字符串或者JSON格式
     * @param taskId  通知业务id
     * @return
     */
    public static boolean sendAndroidCustomizedcast(String appkey,String appMasterSecret,
                                                    String alias,String alias_type,
                                                    String ticker,String title,String text,
                                                    String custom,String taskId) {
        boolean flag=true;
        try {
            AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey,appMasterSecret);
            customizedcast.setAlias(alias, alias_type);
            customizedcast.setTicker( ticker);
            customizedcast.setTitle(title);
            customizedcast.setText(text);
            customizedcast.goCustomAfterOpen(custom);
            customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
            //测试环境用testMode()  正式用setProductionMode()
            customizedcast.setTestMode();
            customizedcast.setExtraField("task_id",taskId);
            flag=  client.send(customizedcast);
        } catch (Exception e) {
            logger.error(e.getMessage());
            flag=false;
        }
        return flag;
    }

    /**
     *
     * @param appkey  ios key
     * @param appMasterSecret ios secret
     * @param alias   要求不超过500个, 多个以英文逗号间隔
     * @param alias_type  可由开发者自定义
     * @param alert  通知栏提示文字
     * @param taskId  通知业务id
     * @return
     */
    public static boolean sendIOSCustomizedcast(String appkey,String appMasterSecret,String alias,String alias_type,String alert,String taskId) {
        boolean flag=true;
        try {
            IOSCustomizedcast customizedcast = new IOSCustomizedcast(appkey,appMasterSecret);
            customizedcast.setAlias(alias, alias_type);
            customizedcast.setAlert(alert);
            //测试环境用testMode()  正式用setProductionMode()
            //customizedcast.setProductionMode();
            customizedcast.setTestMode();
            customizedcast.setCustomizedField("task_id",taskId);
            flag=client.send(customizedcast);
        } catch (Exception e) {
            flag=false;
            logger.error(e.getMessage());
        }
        return flag;
    }

//    public static  void main(String args[]){
//
//        String AndroidAppkey="5b504eb6a40fa35084000068";
//        String AndroidAppMasterSecret="agygyb9svtewqdgpj4vrnx7tiv7mc2wz";
//        String alias="user_id_longhu_17787";
//        String alias_type="user_id";
//        String AndroidTicker="Android 啥阿萨 ";
//        String AndroidTitle="项目填报提醒";
//        String AndroidText="项目[杭州龙湖大江东B项目一期1组团] 今日状态填报提醒";
//        String AndroidCustom="Android_message";
//        String AndroidMessageId="358540";
//       boolean Android_result= sendAndroidCustomizedcast(AndroidAppkey,AndroidAppMasterSecret,alias,alias_type,AndroidTicker,AndroidTitle,AndroidText,AndroidCustom,AndroidMessageId);
//
//        String IOSAppkey="5b559ccb8f4a9d7a4800000f";
//        String IOSAppMasterSecret="zzl7yp8youbkvto9os8ucs1x2fu6rdw2";
//        String IOSAlert="项目[杭州龙湖大江东B项目一期1组团] 今日状态填报提醒";
//        String IOSMessageId="358540";
////       boolean IOS_result= sendIOSCustomizedcast(IOSAppkey,IOSAppMasterSecret,alias,alias_type,IOSAlert,IOSMessageId, 0);
//
//
//       // logger.debug("Android_result==="+Android_result);
//        //logger.debug("IOS_result==="+IOS_result);
//
//    }
}
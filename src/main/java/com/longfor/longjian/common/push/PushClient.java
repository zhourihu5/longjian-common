package com.longfor.longjian.common.push;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class PushClient {


    private Logger logger = LoggerFactory.getLogger(PushClient.class);
	
	// The user agent
	protected final String USER_AGENT = "Mozilla/5.0";

	// This object is used for sending the post request to Umeng
	protected HttpClient client = new DefaultHttpClient();
	
	// The host
	protected static final String host = "http://msg.umeng.com";
	
	// The post path
	protected static final String postPath = "/api/send";

	public boolean send(UmengNotification msg)  {
		String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
        try {
            msg.setPredefinedKeyValue("timestamp", timestamp);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        String url = host + postPath;
        String postBody = msg.getPostBody();
        logger.debug("postBody==="+postBody);
        String sign = null;
        try {
            sign = DigestUtils.md5Hex(("POST" + url + postBody + msg.getAppMasterSecret()).getBytes("utf8"));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        url = url + "?sign=" + sign;
        HttpPost post = new HttpPost(url);
        post.setHeader("User-Agent", USER_AGENT);
        StringEntity se = new StringEntity(postBody, "UTF-8");
        post.setEntity(se);
        HttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        int status = response.getStatusLine().getStatusCode();

        try(InputStreamReader in=new InputStreamReader(response.getEntity().getContent())){
            try (BufferedReader rd= new BufferedReader(in)){
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        if (status == 200) {
            logger.debug("Notification sent successfully.");
        } else {
            logger.error("Failed to send the notification!");
			return false;
        }
        return true;
    }

}

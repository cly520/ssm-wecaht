package cy.ly.listener;

import cy.ly.menu.MenuManager;
import cy.ly.utlis.WeixinUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessTokenThread extends Thread {

    private static Logger log = LoggerFactory.getLogger(AccessTokenThread.class);
    private static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static String access_token_vul;

    @Override
    public void run() {
        while (true) {
            access_token_vul = getAccessToken();
            try {
                Thread.sleep(7200 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getAccessToken() {
        String url = access_token_url.replace("APPID", MenuManager.appId).replace("APPSECRET", MenuManager.appSecret);
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                return jsonObject.getString("access_token");
            } catch (Exception e) {
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return null;
    }
}

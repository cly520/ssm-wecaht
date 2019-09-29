package cy.ly.service.impl;

import cy.ly.listener.AccessTokenThread;
import cy.ly.service.UserInfoService;
import cy.ly.utlis.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private static String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public JSONObject getUserInfo(String openId){
        String url = user_info_url.replace("ACCESS_TOKEN",AccessTokenThread.access_token_vul).replace("OPENID", openId);
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);
        System.out.println(jsonObject.toString());
        return jsonObject;
    }
}

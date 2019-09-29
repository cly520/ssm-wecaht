package cy.ly.weboauth;

import cy.ly.listener.AccessTokenThread;
import cy.ly.menu.MenuManager;
import cy.ly.utlis.WeixinUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Controller
@RequestMapping("/weixin")
public class WechatOauth {

    private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

    @RequestMapping("/oauth")
    public void oauth(HttpServletResponse response) {
        try {
            String path = MenuManager.REAL_URL + "weixin/invoke";
            path = URLEncoder.encode(path, "UTF-8");
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                    + "appid=" + MenuManager.appId
                    + "&redirect_uri=" + path
                    + "&response_type=code" +
                    "&scope=snsapi_userinfo"
                    + "&state=CLY"
                    + "#wechat_redirect";
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。
    @RequestMapping("/invoke")
    public void invoke(HttpServletRequest request) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String url = " https://api.weixin.qq.com/sns/oauth2/access_token?"
                + "appid=" + MenuManager.appId
                + "&secret=" + MenuManager.appSecret
                + "&code=" + code
                + "&grant_type=authorization_code";
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);
        String accessToken = null;
        String openid = null;
        String scope = null;
        if (null != jsonObject) {
            try {
                accessToken = jsonObject.getString("access_token");
                openid = jsonObject.getString("openid");
                scope = jsonObject.getString("scope");
            } catch (Exception e) {
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        String userInfoUrl = " https://api.weixin.qq.com/sns/userinfo?"
                + "access_token=" + accessToken
                + "&openid=" + openid
                + "&lang=zh_CN";
        JSONObject object = WeixinUtil.httpRequest(userInfoUrl, "GET", null);
        if(null != object){
            try {

            }catch (Exception e){
                // 获取userInfo失败
                log.error("获取userInfo失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
    }
}

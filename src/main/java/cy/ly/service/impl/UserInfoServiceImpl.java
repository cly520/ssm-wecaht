package cy.ly.service.impl;

        import com.fasterxml.jackson.databind.ObjectMapper;
        import cy.ly.bean.WeiUsers;
        import cy.ly.dto.WeiUsersDTO;
        import cy.ly.listener.AccessTokenThread;
        import cy.ly.service.UserInfoService;
        import cy.ly.service.WeiUsersService;
        import cy.ly.utlis.WeixinUtil;
        import net.sf.json.JSONObject;
        import org.springframework.beans.BeanUtils;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.io.IOException;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private WeiUsersService weiUsersService;

    private static String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    public void addWeiUsers(String openId) {
        JSONObject userInfo = this.getUserInfo(openId);
        WeiUsers weiwusers = this.getWeiwusers(userInfo);
        this.insertWeiusers(weiwusers);
    }

    public JSONObject getUserInfo(String openId) {
        String url = user_info_url.replace("ACCESS_TOKEN", AccessTokenThread.access_token_vul).replace("OPENID", openId);
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);
//        System.out.println(jsonObject.toString());
        return jsonObject;
    }

    public WeiUsers getWeiwusers(JSONObject jsonObject) {
        WeiUsers weiUsers = new WeiUsers();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            WeiUsersDTO weiUsersDTO = objectMapper.readValue(jsonObject.toString(), WeiUsersDTO.class);
            BeanUtils.copyProperties(weiUsersDTO, weiUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weiUsers;
    }

    public void insertWeiusers(WeiUsers weiUsers) {
        WeiUsers weiUser = weiUsersService.selectWeiUsersByOpenId(weiUsers.getOpenid());
        if (weiUser == null)
            weiUsersService.insert(weiUsers);
    }
}

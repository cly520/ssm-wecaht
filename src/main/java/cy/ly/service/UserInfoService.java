package cy.ly.service;

import cy.ly.bean.WeiUsers;
import net.sf.json.JSONObject;

public interface UserInfoService {

    JSONObject getUserInfo(String openId);

    void addWeiUsers(String openId);

    WeiUsers getWeiwusers(JSONObject jsonObject);

    void insertWeiusers(WeiUsers weiUsers);
}

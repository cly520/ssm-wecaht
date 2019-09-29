package cy.ly.service;

import net.sf.json.JSONObject;

public interface UserInfoService {

    JSONObject getUserInfo(String openId);
}

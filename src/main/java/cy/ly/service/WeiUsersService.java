package cy.ly.service;

import cy.ly.bean.WeiUsers;
import net.sf.json.JSONObject;

public interface WeiUsersService {
    int insert(WeiUsers weiUsers);
    WeiUsers selectWeiUsersById(Integer id);

    WeiUsers selectWeiUsersByOpenId(String openid);
}

package cy.ly.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WeiUsersDTO implements Serializable {
    private Integer id;

    private String openid;

    private String nickname;

    private String sex;

    private String city;

    private String province;

    private String country;

    private String headimgurl;

    private Integer subscribe;

    private String language;

    private String remark;

    private  Long subscribe_time;

    private List<String> tagid_list;

    private String subscribe_scene;

    private Integer qr_scene;

    private String qr_scene_str;

    private Integer groupid;
}

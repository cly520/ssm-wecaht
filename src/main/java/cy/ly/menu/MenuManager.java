package cy.ly.menu;

import cy.ly.button.Button;
import cy.ly.button.CommonButton;
import cy.ly.button.ComplexButton;
import cy.ly.dto.AccessTokenDTO;
import cy.ly.button.ViewButton;
import cy.ly.utlis.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单管理器类
 * 
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
/***
 * 自定义菜单的创建步骤
	1、找到AppId和AppSecret。自定义菜单申请成功后，在“高级功能”-“开发模式”-“接口配置信息”的最后两项就是；
	2、根据AppId和AppSecret，以https get方式获取访问特殊接口所必须的凭证access_token；
	3、根据access_token，将json格式的菜单数据通过https post方式提交。
 */
	
	public final static String REAL_URL="http://cly520.natapp1.cc/"; //个人花生壳
	//public final static String REAL_URL = "http://wxmobsa.yidatec.com/weixin/"; //正式号服务器	
	
	public final static String appId="wx679be1c1a9519c5c";
	public final static String appSecret = "cf5421faa505fcd2b1e22eb10409f45d";
	
	public static void resultMenu(){
		// 调用接口获取access_token
		AccessTokenDTO accessToken = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != accessToken) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), accessToken.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}
	
	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = MenuManager.appId;
		// 第三方用户唯一凭证密钥
		String appSecret = MenuManager.appSecret;
		// 调用接口获取access_token
		AccessTokenDTO accessToken = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != accessToken) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(),accessToken.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {

		ViewButton btn10 = new ViewButton();
			btn10.setName("会议发单");
		btn10.setType("view");
		btn10.setUrl("http://www.baidu.com");

		CommonButton btn11 = new CommonButton();
		btn11.setName("会议抢单");
		btn11.setType("click");
		btn11.setKey("11");
		
//-------------------------------------------------------
		ViewButton btn20 = new ViewButton();
		btn20.setName("发单排行榜");
		btn20.setType("view");
		btn20.setUrl("http://www.baidu.com");

//------------------------------------------------------------		
		ViewButton btn30 = new ViewButton();
		btn30.setName("个人中心");
		btn30.setType("view");
		btn30.setUrl("http://www.baidu.com");

		ViewButton btn31 = new ViewButton();
		btn31.setName("版本信息");
		btn31.setType("view");
		btn31.setUrl("http://www.baidu.com");

		CommonButton btn32 = new CommonButton();
		btn32.setName("联系我们");
		btn32.setType("click");
		btn32.setKey("32");
		
		//###############################################一级子菜单
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("会议");
		mainBtn1.setSub_button(new Button[] { btn10,btn11});

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("公告栏");
		mainBtn2.setSub_button(new Button[] { btn20});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("系统消息");
		mainBtn3.setSub_button(new Button[] {btn30,btn31,btn32});

		/**
		 * 这是公众号目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}
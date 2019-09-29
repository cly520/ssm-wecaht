package cy.ly.service.impl;

import cy.ly.dto.ArticleDTO;
import cy.ly.message.NewsMessage;
import cy.ly.message.TextMessage;
import cy.ly.service.CoreService;
import cy.ly.service.UserInfoService;
import cy.ly.utlis.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CoreServiceImpl implements CoreService {
    @Autowired
    private UserInfoService userInfoService;
    public String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";

            // xml请求解析 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // 发送方帐号（open_id） 下面三行代码是： 从HashMap中取出消息中的字段；
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            // 回复文本消息 组装要返回的文本消息对象；
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(System.currentTimeMillis());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);
            // 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义
            // textMessage.setContent("欢迎访问<a
            // href=\"http://www.baidu.com/index.php?tn=site888_pg\">百度</a>!");
            // 将文本消息对象转换成xml字符串
            respMessage = MessageUtil.textMessageToXml(textMessage);
            /**
             * 演示了如何接收微信发送的各类型的消息，根据MsgType判断属于哪种类型的消息；
             */

            // 接收用户发送的文本消息内容
            String content = requestMap.get("Content");

            // 创建图文消息
            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setCreateTime(System.currentTimeMillis());
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            newsMessage.setFuncFlag(0);

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                respContent = "您发送的是文本消息！";
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是音频消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    userInfoService.getUserInfo(fromUserName);
                    respContent = "欢迎关注微信公众号";
                }
                // 取消订阅
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    String eventKey = requestMap.get("EventKey");

                    if (eventKey.equals("11")) {
                        respContent = "菜单项被点击！";

                    }
                    else if (eventKey.equals("70")) {

                        List<ArticleDTO> articleDTOList = new ArrayList<ArticleDTO>();

                        // 单图文消息
                        ArticleDTO articleDTO = new ArticleDTO();
                        articleDTO.setTitle("标题");
                        articleDTO.setDescription("描述内容");
                        articleDTO.setPicUrl(
                                "图片");
                        articleDTO.setUrl("跳转连接");


                        articleDTOList.add(articleDTO);
                        // 设置图文消息个数
                        newsMessage.setArticleCount(articleDTOList.size());
                        // 设置图文消息
                        newsMessage.setArticleDTOS(articleDTOList);
                        // 将图文消息对象转换为XML字符串
                        respMessage = MessageUtil.newsMessageToXml(newsMessage);
                        return respMessage;
                    }

                }

            }

            // 组装要返回的文本消息对象；
            textMessage.setContent(respContent);
            // 调用消息工具类MessageUtil将要返回的文本消息对象TextMessage转化成xml格式的字符串；
            respMessage = MessageUtil.textMessageToXml(textMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return respMessage;
    }
}

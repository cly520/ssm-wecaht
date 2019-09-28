package cy.ly.message;

import cy.ly.dto.ArticleDTO;

import java.util.List;


/**
 * 文本消息
 */
public class NewsMessage extends BaseMessage {
	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<ArticleDTO> articleDTOS;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<ArticleDTO> getArticleDTOS() {
		return articleDTOS;
	}

	public void setArticleDTOS(List<ArticleDTO> articleDTOS) {
		this.articleDTOS = articleDTOS;
	}
}
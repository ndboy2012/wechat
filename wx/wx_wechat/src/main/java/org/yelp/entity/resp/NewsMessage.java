package org.yelp.entity.resp;


import java.util.List;
import org.yelp.entity.common.Article;

public class NewsMessage extends BaseMessage{

	// 图文消息个数，限制为10条以? 
    private int ArticleCount;  
    // 多条图文消息信息，默认第tem为大�? 
    private List<Article> Articles;  
  
    public int getArticleCount() {  
        return ArticleCount;  
    }  
  
    public void setArticleCount(int articleCount) {  
        ArticleCount = articleCount;  
    }  
  
    public List<Article> getArticles() {  
        return Articles;  
    }  
  
    public void setArticles(List<Article> articles) {  
        Articles = articles;  
    }  
}

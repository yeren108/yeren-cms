package com.yeren.cms.service;

import java.util.List;

import com.yeren.cms.modle.Article;
import com.yeren.cms.modle.Link;
import com.yeren.cms.modle.Site;
import com.yeren.cms.util.PageBean;

public interface ArticleService {
	void save(Article article);

	int delete(Integer id);//基本不用
	
	int softDelete(Integer id);//软删除
	
	int hardDelete(Integer id);//硬删除
	
	List<Article> findAll();//查询所有
	
	List<Article> findSomeBycategoryId(Integer categoryId);
	
	List<Article> findAll(Article article,PageBean pb);//查找所有用户
	
	int getSum();
	
	List<Article> findByAttribute(String attribute);//模糊查询
	
	List<Article> findArticleDataByArticle(Integer id);//通过文章找内容
	
	List<Article> findLinkByArticle(Integer id);//通过文章找链接

	void update(Article article);

	List<Article> findById(Integer id);
	
	void changeStatus(Integer id);//修改上下线状态
	
	List<Article> findArticleTopN(Integer categoryId,Integer n);
}

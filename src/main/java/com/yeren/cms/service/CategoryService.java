package com.yeren.cms.service;

import java.util.List;

import com.yeren.cms.modle.Article;
import com.yeren.cms.modle.Category;
import com.yeren.cms.modle.Link;
import com.yeren.cms.modle.Site;
import com.yeren.cms.util.PageBean;

public interface CategoryService {
	void save(Category category);

	int delete(Integer id);//基本不用
	
	int softDelete(Integer id);//软删除
	
	int hardDelete(Integer id);//硬删除

	void update(Category category);

	List<Category> findById(Integer id);
	
	List<Category> findAll();
	
	List<Category> findSomeBySiteId(Integer siteId);
	
	List<Category> findAll(Category category,PageBean pb);//查找所有用户
	
	int getSum();
	
	List<Category> findByAttribute(String attribute);//模糊查询
	
	List<Category> findCategoryByCategory(Integer id);//通过父栏目找所有旗下的子栏目
	
	List<Article> findArticleByCategory(Integer id);//通过栏目找所有旗下的文章
	
	List<Link> findLinkByCategory(Integer id);//通过栏目找所有旗下的链接
	
	void changeStatus(Integer id);//修改上下线状态
	
}

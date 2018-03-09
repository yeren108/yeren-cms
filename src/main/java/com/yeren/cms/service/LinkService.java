package com.yeren.cms.service;

import java.util.List;

import com.yeren.cms.modle.Link;
import com.yeren.cms.modle.Site;
import com.yeren.cms.util.PageBean;

public interface LinkService {
	void save(Link link);

	int delete(Integer id);
	
	int deleteByArticleId(Integer articleId);

	void update(Link link);

	List<Link> findById(Integer id);
	
	List<Link> findByAttribute(String attribute);
	
	List<Link> findByAttribute2(String attribute);
	
	List<Link> findAll();
	
	List<Link> findSomeBycategoryId(Integer categoryId);
	
	List<Link> findAll(PageBean pb);//查找所有用户
	
	int getSum();
	
	List<Link> findByCategoryId(Integer categoryId);
	
	List<Link> findByArticleId(Integer articleId);
	
	void changeStatus(Integer id);//修改上下线状态
	
	List<Link> findLinkTopN(Integer categoryId,Integer n);
}

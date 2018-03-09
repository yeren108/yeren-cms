package com.yeren.cms.service;

import java.util.List;

import com.yeren.cms.modle.Category;
import com.yeren.cms.modle.Site;
import com.yeren.cms.util.PageBean;

public interface SiteService {
	void save(Site site);

	int hardDelete(Integer id);//硬删除

	int softDelete(Integer id);//软删除

	int delete(Integer id);//基本不用

	void update(Site site);

	List<Site> findById(Integer id);//通过站点ID找站点
	
	String findNameById(Integer id);//通过ID找站点名称
	
	List<Site> findByAttribute(String attribute);//模糊查询
	
	List<Site> findAll();//查找所有站点
	
	List<Site> findAll(PageBean pb);//查找所有用户
	
	int getSum();
	
	List<Category> findCategoryBySite(Integer id);//站点找旗下的栏目
	
	void changeStatus(Integer id);//修改上下线状态
	
	
	
}

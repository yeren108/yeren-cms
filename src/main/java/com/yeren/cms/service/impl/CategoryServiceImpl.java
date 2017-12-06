package com.yeren.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeren.cms.dao.CategoryDao;
import com.yeren.cms.modle.Article;
import com.yeren.cms.modle.Category;
import com.yeren.cms.modle.Link;
import com.yeren.cms.modle.Site;
import com.yeren.cms.service.CategoryService;
import com.yeren.cms.util.PageBean;

@Repository
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public void save(Category category) {
		categoryDao.save(category);
	}

	@Override
	public int delete(Integer id) {
		return categoryDao.delete(id);
	}

	@Override
	public void update(Category category) {
		categoryDao.update(category);
	}

	@Override
	public List<Category> findById(Integer id) {
		return categoryDao.findById(id);
	}

	@Override
	public int softDelete(Integer id) {
		return categoryDao.softDelete(id);
	}

	@Override
	public int hardDelete(Integer id) {
		return categoryDao.hardDelete(id);
	}

	@Override
	public List<Category> findCategoryByCategory(Integer id) {
		return categoryDao.findCategoryByCategory(id);
	}

	@Override
	public List<Article> findArticleByCategory(Integer id) {
		return categoryDao.findArticleByCategory(id);
	}

	@Override
	public List<Link> findLinkByCategory(Integer id) {
		return categoryDao.findLinkByCategory(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	
	@Override
	public List<Category> findByAttribute(String attribute) {
		return categoryDao.findByAttribute(attribute);
	}

	@Override
	public void changeStatus(Integer id) {
		categoryDao.changeStatus(id);
	}

	@Override
	public List<Category> findAll(Category category, PageBean pb) {
		return categoryDao.findAll(category, pb);
	}

	@Override
	public int getSum() {
		return categoryDao.getSum();
	}

	@Override
	public List<Category> findSomeBySiteId(Integer siteId) {
		return categoryDao.findSomeBySiteId(siteId);
	}

}

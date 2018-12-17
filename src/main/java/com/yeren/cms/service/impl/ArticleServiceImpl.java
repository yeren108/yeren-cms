package com.yeren.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yeren.cms.dao.ArticleDao;
import com.yeren.cms.modle.Article;
import com.yeren.cms.modle.Link;
import com.yeren.cms.service.ArticleService;
import com.yeren.cms.util.PageBean;
//@Cacheable(value="ArticleServiceImpl",keyGenerator = "userKeyGenerator")
@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	ArticleDao articleDao;
	
	@Override
	public void save(Article article) {
		articleDao.save(article);
	}

	@Override
	public int delete(Integer id) {
		return articleDao.delete(id);
	}

	@Override
	public void update(Article article) {
		articleDao.update(article);
	}

	@Override
	public List<Article> findById(Integer id) {
		return articleDao.findById(id);
	}

	@Override
	public int softDelete(Integer id) {
		return articleDao.softDelete(id);
	}

	@Override
	public int hardDelete(Integer id) {
		return articleDao.hardDelete(id);
	}

	@Override
	public List<Article> findAll() {
		return articleDao.findAll();
	}

	@Override
	public List<Article> findArticleDataByArticle(Integer id) {
		return articleDao.findArticleDataByArticle(id);
	}

	@Override
	public List<Article> findLinkByArticle(Integer id) {
		return articleDao.findLinkByArticle(id);
	}

	@Override
	public List<Article> findByAttribute(String attribute) {
		return articleDao.findByAttribute(attribute);
	}

	@Override
	public void changeStatus(Integer id) {
		articleDao.changeStatus(id);
	}

	//@Cacheable(value="ArticleServiceImpl",key="#pb.page")
	@Override
	public List<Article> findAll(PageBean pb) {
		return articleDao.findAll(pb);
	}

	@Override
	public int getSum() {
		return articleDao.getSum();
	}

	@Override
	public List<Article> findSomeBycategoryId(Integer categoryId) {
		return articleDao.findSomeBycategoryId(categoryId);
	}

	@Override
	public List<Article> findArticleTopN(Integer categoryId, Integer n) {
		return articleDao.findArticleTopN(categoryId, n);
	}

}

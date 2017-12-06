package com.yeren.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeren.cms.dao.LinkDao;
import com.yeren.cms.modle.Link;
import com.yeren.cms.service.LinkService;
import com.yeren.cms.util.PageBean;

@Service
public class LinkServiceImpl implements LinkService {
	@Autowired
	LinkDao LinkDao;

	@Override
	public void save(Link link) {
		LinkDao.save(link);
	}

	@Override
	public int delete(Integer id) {
		return LinkDao.delete(id);
	}

	@Override
	public void update(Link link) {
		LinkDao.update(link);
	}

	@Override
	public List<Link> findById(Integer id) {
		return LinkDao.findById(id);
	}

	@Override
	public List<Link> findByCategoryId(Integer categoryId) {
		return LinkDao.findByCategoryId(categoryId);
	} 
	@Override
	public int deleteByArticleId(Integer articleId) {
		return LinkDao.deleteByArticleId(articleId);
	}

	@Override
	public List<Link> findByArticleId(Integer articleId) {
		return LinkDao.findByArticleId(articleId);
	}

	@Override
	public List<Link> findAll() {
		return LinkDao.findAll();
	}

	@Override
	public List<Link> findByAttribute(String attribute) {
		return LinkDao.findByAttribute(attribute);
	}

	@Override
	public List<Link> findByAttribute2(String attribute) {
		return LinkDao.findByAttribute2(attribute);
	}

	@Override
	public void changeStatus(Integer id) {
		LinkDao.changeStatus(id);
	}

	@Override
	public List<Link> findAll(Link link, PageBean pb) {
		return LinkDao.findAll(link, pb);
	}

	@Override
	public int getSum() {
		return LinkDao.getSum();
	}

	@Override
	public List<Link> findSomeBycategoryId(Integer categoryId) {
		return LinkDao.findSomeBycategoryId(categoryId);
	}

	@Override
	public List<Link> findLinkTopN(Integer categoryId, Integer n) {
		return LinkDao.findLinkTopN(categoryId, n);
	}

}

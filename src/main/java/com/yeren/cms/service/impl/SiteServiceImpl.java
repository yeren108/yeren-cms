package com.yeren.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yeren.cms.dao.SiteDao;
import com.yeren.cms.modle.Category;
import com.yeren.cms.modle.Site;
import com.yeren.cms.service.SiteService;
import com.yeren.cms.util.PageBean;
@Cacheable(value="SiteServiceImpl",keyGenerator = "userKeyGenerator")
@Service
public class SiteServiceImpl implements SiteService{
	
	@Autowired
	SiteDao siteDao;

	@Override
	public void save(Site site) {
		siteDao.save(site);
	}

	@Override
	public int delete(Integer id) {
		return siteDao.delete(id);
	}

	@Override
	public void update(Site site) {
		siteDao.update(site);
	}

	@Override
	public List<Site> findById(Integer id) {
		return siteDao.findById(id);
	}

	@Override
	public int hardDelete(Integer id) {
		return siteDao.hardDelete(id);
	}

	@Override
	public int softDelete(Integer id) {
		return siteDao.softDelete(id);
	}

	@Override
	public List<Site> findAll() {
		return siteDao.findAll();
	}

	@Override
	public List<Category> findCategoryBySite(Integer id) {
		return siteDao.findCategoryBySite(id);
	}

	@Override
	public List<Site> findByAttribute(String attribute) {
		return siteDao.findByAttribute(attribute);
	}

	@Override
	public String findNameById(Integer id) {
		return siteDao.findNameById(id);
	}

	@Override
	public void changeStatus(Integer id) {
		siteDao.changeStatus(id);
	}

	@Cacheable(value="SiteServiceImpl",key="#pb.page")
	@Override
	public List<Site> findAll( PageBean pb) {
		return siteDao.findAll(pb);
	}

	@Override
	public int getSum() {
		return siteDao.getSum();
	}

	
}

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
@Service
public class SiteServiceImpl implements SiteService{
	
	@Autowired
	SiteDao siteDao;

	@CacheEvict(value= {"findById","findAll","findCategoryBySite","findByAttribute","findNameById","findAll","getSum"},allEntries=true)
	@Override
	public void save(Site site) {
		siteDao.save(site);
	}

	@CacheEvict(value= {"findById","findAll","findCategoryBySite","findByAttribute","findNameById","findAll","getSum"},allEntries=true)
	@Override
	public int delete(Integer id) {
		return siteDao.delete(id);
	}

	@CacheEvict(value= {"findById","findAll","findCategoryBySite","findByAttribute","findNameById","findAll","getSum"},allEntries=true)
	@Override
	public void update(Site site) {
		siteDao.update(site);
	}

	@Cacheable(value="findById",key="#id")
	@Override
	public List<Site> findById(Integer id) {
		return siteDao.findById(id);
	}

	@CacheEvict(value= {"findById","findAll","findCategoryBySite","findByAttribute","findNameById","findAll","getSum"},allEntries=true)
	@Override
	public int hardDelete(Integer id) {
		return siteDao.hardDelete(id);
	}

	@CacheEvict(value= {"findById","findAll","findCategoryBySite","findByAttribute","findNameById","findAll","getSum"},allEntries=true)
	@Override
	public int softDelete(Integer id) {
		return siteDao.softDelete(id);
	}

	@Cacheable("findAll")
	@Override
	public List<Site> findAll() {
		return siteDao.findAll();
	}

	@Cacheable(value="findCategoryBySite",key = "#id")
	@Override
	public List<Category> findCategoryBySite(Integer id) {
		return siteDao.findCategoryBySite(id);
	}

	@Cacheable(value="findByAttribute",key = "#attribute")
	@Override
	public List<Site> findByAttribute(String attribute) {
		return siteDao.findByAttribute(attribute);
	}

	@Cacheable(value="findNameById",key="#id")
	@Override
	public String findNameById(Integer id) {
		return siteDao.findNameById(id);
	}

	@CacheEvict(value= {"findById","findAll","findCategoryBySite","findByAttribute","findNameById","findAll","getSum"},allEntries=true)
	@Override
	public void changeStatus(Integer id) {
		siteDao.changeStatus(id);
	}

	@Cacheable(value="findAll-site", key="(#pb.page)")
	@Override
	public List<Site> findAll(Site site, PageBean pb) {
		return siteDao.findAll(site, pb);
	}

	@Cacheable("getSum")
	@Override
	public int getSum() {
		return siteDao.getSum();
	}

	
}

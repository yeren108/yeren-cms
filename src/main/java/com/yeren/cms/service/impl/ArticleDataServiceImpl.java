package com.yeren.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeren.cms.dao.ArticleDataDao;
import com.yeren.cms.modle.ArticleData;
import com.yeren.cms.service.ArticleDataService;

@Service
public class ArticleDataServiceImpl implements ArticleDataService {

	@Autowired
	ArticleDataDao articleDataDao;

	@Override
	public void save(ArticleData articleData) {
		articleDataDao.save(articleData);
	}

	@Override
	public int delete(Integer id) {
		return articleDataDao.delete(id);
	}

	@Override
	public void update(ArticleData articleData) {
		articleDataDao.update(articleData);
	}

	@Override
	public List<ArticleData> findById(Integer id) {
		return articleDataDao.findById(id);
	}

}

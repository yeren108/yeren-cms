package com.yeren.cms.service;

import java.util.List;

import com.yeren.cms.modle.ArticleData;

public interface ArticleDataService {
	void save(ArticleData articleData);

	int delete(Integer id);

	void update(ArticleData articleData);

	List<ArticleData> findById(Integer id);
}

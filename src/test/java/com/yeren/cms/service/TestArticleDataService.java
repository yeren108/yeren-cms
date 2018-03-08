package com.yeren.cms.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yeren.cms.modle.ArticleData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:webApplicationContext-test.xml")
public class TestArticleDataService {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;

	@Autowired
	ArticleDataService articleDataService;

//	@Test
	public void testSave() {
		ArticleData articleData = new ArticleData();
		articleData.setData("tttt");
		articleDataService.save(articleData);
	}

//	@Test
	public void testFindById() {
		List<ArticleData> list = articleDataService.findById(1);
		if (list.size() != 0) {
			String data = list.get(0).getData();
			System.out.println("name:" + data);
		}
	}

//	@Test
	public void testDelete() {
		List<ArticleData> list = articleDataService.findById(1);
		if (list.size() != 0) {
			articleDataService.delete(list.get(0).getId());
		}
	}

//	@Test
	public void testUpdate() {
		List<ArticleData> list = articleDataService.findById(2);
		if (list.size() != 0) {
			list.get(0).setData("qqqqqqq");
			articleDataService.update(list.get(0));
		}
	}
	
	@Test
	public void testSuccess() {
		System.out.println("success test");
	}
}
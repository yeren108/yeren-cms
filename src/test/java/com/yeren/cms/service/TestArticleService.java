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

import com.yeren.cms.modle.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context-test.xml")
public class TestArticleService {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;

	@Autowired
	ArticleService articleService;

//	@Test
	public void testSave() {
		Article article = new Article();
		article.setName("OK");
		article.setCategoryId(4);
		articleService.save(article);
	}

//	@Test
	public void testFindById() {
		List<Article> list = articleService.findById(3);
		if (list.size() != 0) {
			String name = list.get(0).getName();
			System.out.println("name:" + name);
		}
	}

//	@Test
	public void testDelete() {
		List<Article> list = articleService.findById(5);
		if (list.size() != 0) {
			articleService.delete(list.get(0).getId());
		}
	}

//	@Test
	public void testUpdate() {
		List<Article> list = articleService.findById(1);
		if (list.size() != 0) {
			list.get(0).setName("WB");
			articleService.update(list.get(0));
		}
	}
	
//	@Test
	public void testSoftDelete() {
		
	}
	
//	@Test
	public void testHardDelete() {
		int hardDelete = articleService.hardDelete(3);
			System.out.println("删除了："+hardDelete);
	}
	
//	@Test
	public void testFindAll() {
		List<Article> list = articleService.findAll();
		if (list.size() != 0) {
			System.out.println("个数有:" +list.size());
		}
	}
	
//	@Test
	public void testFindArticleDataByArticle() {
		List<Article> list = articleService.findArticleDataByArticle(6);
		if (list.size() != 0) {
			System.out.println("data个数有:" +list.size());
		}
	}
	
//	@Test
	public void testFindLinkByArticle() {
		List<Article> list = articleService.findLinkByArticle(6);
		if (list.size() != 0) {
			System.out.println("link个数有:" +list.size());
		}
	}
	
	@Test
	public void testSuccess() {
		System.out.println("success test");
	}
	
}
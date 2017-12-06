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
import com.yeren.cms.modle.Category;
import com.yeren.cms.modle.Link;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context-test.xml")
public class TestCategoryService {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;

	@Autowired
	CategoryService categoryService;

//	@Test
	public void testSave() {
		Category category = new Category();
		category.setName("OK");
		category.setSiteId(3);
		categoryService.save(category);
	}

//	@Test
	public void testFindById() {
		List<Category> list = categoryService.findById(1);
		if (list.size() != 0) {
			String name = list.get(0).getName();
			System.out.println("name:" + name);
		}
	}

	//不用了
//	@Test
	public void testDelete() {
		List<Category> list = categoryService.findById(2);
		if (list.size() != 0) {
			categoryService.delete(list.get(0).getId());
		}
	}
	
//	@Test
	public void testSoftDelete() {
		List<Category> list = categoryService.findById(3);
		if (list.size() != 0) {
			int softDelete = categoryService.softDelete(list.get(0).getId());
			System.out.println("softDelete:"+softDelete);
		}
	}
	
//	@Test
	public void testHardDelete() {
		List<Category> list = categoryService.findById(3);
		if (list.size() != 0) {
			int softDelete = categoryService.hardDelete(list.get(0).getId());
			System.out.println("hardDelete:"+softDelete);
		}
	}

//	@Test
	public void testUpdate() {
		List<Category> list = categoryService.findById(3);
		if (list.size() != 0) {
			list.get(0).setName("WB");
			categoryService.update(list.get(0));
		}
	}
	
//	@Test
	public void testFindCategoryByCategory(){
		List<Category> list = categoryService.findCategoryByCategory(4);
		if (list.size() != 0) {
			System.out.println("子栏目个数有:"+list.size());
		}
	}
	
//	@Test
	public void testFindArticleByCategory(){
		List<Article> list = categoryService.findArticleByCategory(4);
		if (list.size() != 0) {
			System.out.println("标题个数有:"+list.size());
		}
	}
	
//	@Test
	public void testFindLinkByCategory(){
		List<Link> list = categoryService.findLinkByCategory(2);
		if (list.size() != 0) {
			System.out.println("链接个数有:"+list.size());
		}
	}
	
	@Test
	public void testSuccess() {
		System.out.println("success test");
	}
}
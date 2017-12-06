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
import com.yeren.cms.modle.Site;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context-test.xml")
public class TestSiteService {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;

	@Autowired
	SiteService siteService;

//	@Test
	public void testSave() {
		Site site = new Site();
		site.setName("OK");
		siteService.save(site);
	}

	// @Test
	public void testFindById() {
		List<Site> list = siteService.findById(3);
		if (list.size() != 0) {
			String name = list.get(0).getName();
			System.out.println("name:" + name);
		}
	}

	// @Test
	public void testDelete() {
		List<Site> list = siteService.findById(5);
		if (list.size() != 0) {
			siteService.delete(list.get(0).getId());
		}
	}

	// @Test
	public void testHardDelete() {
		List<Site> list = siteService.findById(2);
		if (list.size() != 0) {
			int hardDelete = siteService.hardDelete(list.get(0).getId());
			System.out.println(hardDelete);
		}
	}

//	@Test
	public void testSoftDelete() {
		List<Site> list = siteService.findById(2);
		if (list.size() != 0) {
			int hardDelete = siteService.softDelete(list.get(0).getId());
			System.out.println(hardDelete);
		}
	}

	// @Test
	public void testUpdate() {
		List<Site> list = siteService.findById(1);
		if (list.size() != 0) {
			list.get(0).setName("WB");
			siteService.update(list.get(0));
		}
	}
	
//	@Test
	public void testFindAll() {
		List<Site> list = siteService.findAll();
		if (list.size() != 0) {
			System.out.println("站点个数有:"+list.size());
		}
	}
	
//	@Test
	public void testFindCategoryBySite(){
		List<Category> list = siteService.findCategoryBySite(1);
		if (list.size() != 0) {
			System.out.println("栏目个数有:"+list.size());
		}
	}

//	@Test
	public void testFindByAttribute(){
		String attribute="5";
		List<Site> list = siteService.findByAttribute(attribute);
		if (list.size() != 0) {
			System.out.println("模糊查询得到的个数有:"+list.size());
		}
	}
	
//	@Test
	public void testFindNameById(){
		Integer id=5;
		String name = siteService.findNameById(id);
		System.out.println("站点名称："+name);
		
	}
	
	@Test
	public void testSuccess() {
		System.out.println("success test");
	}
}
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

import com.yeren.cms.modle.Link;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:webApplicationContext-test.xml")
public class TestLinkService {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;

	@Autowired
	LinkService linkService;

//	@Test
	public void testSave() {
		Link link = new Link();
		link.setUrl("https://www.baidu.com");
		link.setCategoryId(2);
		link.setName("OK");
		linkService.save(link);
	}

//	@Test
	public void testFindById() {
		List<Link> list = linkService.findById(1);
		if (list.size() != 0) {
			String name = list.get(0).getName();
			System.out.println("name:" + name);
		}
	}

//	@Test
	public void testDelete() {
		List<Link> list = linkService.findById(1);
		if (list.size() != 0) {
			linkService.delete(list.get(0).getId());
		}
	}

//	@Test
	public void testUpdate() {
		List<Link> list = linkService.findById(2);
		if (list.size() != 0) {
			list.get(0).setName("WB");
			linkService.update(list.get(0));
		}
	}
	
//	@Test
	public void testFindLinkTopN() {
		List<Link> list = linkService.findLinkTopN(11, 2);
		if (list.size() != 0) {
			String name = list.get(0).getName();
			System.out.println("name:" + name);
		}
	}
	
	@Test
	public void testSuccess() {
		System.out.println("success test");
	}
}
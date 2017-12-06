package com.yeren.cms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeren.cms.modle.Article;
import com.yeren.cms.modle.ArticleData;
import com.yeren.cms.modle.Link;
import com.yeren.cms.service.ArticleDataService;
import com.yeren.cms.service.ArticleService;
import com.yeren.cms.service.LinkService;

@Controller
@RequestMapping("/choose")
public class OpenToTYwebSiteController {
	@Autowired
	LinkService linkService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	ArticleDataService articleDataService;
	
	private static final Logger logger = LoggerFactory.getLogger(OpenToTYwebSiteController.class);

	
	@RequestMapping(value = "/link", method = RequestMethod.GET)
	public void link(HttpServletRequest request, HttpServletResponse response) {
		logger.info("====================调用OpenToTYwebSiteController-->接口：/link====================");
		response.setCharacterEncoding("utf-8");
		String categoryId=request.getParameter("categoryId");
		String n=request.getParameter("n");
		Integer categoryIdInt=Integer.parseInt(categoryId);
		Integer nInt=Integer.parseInt(n);
		
		List<Link> list = linkService.findLinkTopN(categoryIdInt, nInt);
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(list);
		
		PrintWriter out;
		try {
			out = response.getWriter();
			String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
			out.println(jsonpCallback+"("+jsonArray.toString()+")");//返回jsonp格式数据  
	        out.flush();  
	        out.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
	}
	
	@RequestMapping(value = "/article", method = RequestMethod.GET)
	@ResponseBody
	public void article(HttpServletRequest request, HttpServletResponse response) {
		logger.info("====================调用OpenToTYwebSiteController-->接口：/article====================");
		response.setCharacterEncoding("utf-8");
		String categoryId=request.getParameter("categoryId");
		String n=request.getParameter("n");
		Integer categoryIdInt=Integer.parseInt(categoryId);
		Integer nInt=Integer.parseInt(n);
		
		List<Article> list = articleService.findArticleTopN(categoryIdInt, nInt);
		
		
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(list);
		PrintWriter out;
		try {
			out = response.getWriter();
			String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
			out.println(jsonpCallback+"("+jsonArray.toString()+")");//返回jsonp格式数据  
	        out.flush();  
	        out.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	
	@RequestMapping(value = "/articleMore", method = RequestMethod.GET)
	@ResponseBody
	public void articleMore(HttpServletRequest request, HttpServletResponse response) {
		logger.info("====================调用OpenToTYwebSiteController-->接口：/article====================");
		response.setCharacterEncoding("utf-8");
		String articleId=request.getParameter("articleId");
		Integer articleIdInt=Integer.parseInt(articleId);
		
		
		List<ArticleData> articleDatasList = articleDataService.findById(articleIdInt);
		List<Link> linkList = linkService.findByArticleId(articleIdInt);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(articleDatasList);
		jsonArray.add(linkList);
		PrintWriter out;
		try {
			out = response.getWriter();
			String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
			out.println(jsonpCallback+"("+jsonArray.toString()+")");//返回jsonp格式数据  
	        out.flush();  
	        out.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
}

package com.yeren.cms.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeren.cms.converter.ArticleConverter;
import com.yeren.cms.modle.Article;
import com.yeren.cms.modle.ArticleData;
import com.yeren.cms.modle.Category;
import com.yeren.cms.modle.Link;
import com.yeren.cms.modle.Site;
import com.yeren.cms.service.ArticleDataService;
import com.yeren.cms.service.ArticleService;
import com.yeren.cms.service.CategoryService;
import com.yeren.cms.service.LinkService;
import com.yeren.cms.util.PageBean;
import com.yeren.cms.vo.ArticleVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	ArticleService articleService;
	
	@Autowired
	ArticleDataService articleDataService;
	
	@Autowired
	LinkService linkService;
	
	@Autowired
	CategoryService categoryService;
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	

	@RequestMapping(value="/list", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray listAll(HttpServletRequest request, HttpServletResponse response){
		logger.info("====================调用ArticleController-->接口：/list====================");
		List<Article> list = articleService.findAll();
		List<ArticleVo> ArticleVoList = ArticleConverter.convert2Vo(list);
		Map<String, String> map = new HashMap<String, String>();
		//将站点存到静态变量中
		updateMap(map);
		
		for(int i=0;i<ArticleVoList.size();i++){
			ArticleVoList.get(i).setCategoryName(map.get(ArticleVoList.get(i).getCategoryId()+""));
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(ArticleVoList);
		return jsonArray;
	}
	
	
	/**
	 * 页面用的
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listByPage", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject listByPage(HttpServletRequest request, HttpServletResponse response){
		logger.info("====================调用ArticleController-->接口：/listByPage====================");
		String page=request.getParameter("page");
		if("".equals(page)||"null".equals(page)||page==null){
			page="1";
		}
		String rows=request.getParameter("rows");
		if("".equals(rows)||"null".equals(rows)||rows==null){
			rows="7";
		}
		int sum = articleService.getSum();//总量
		
		PageBean pb=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Article article=new Article();//暂时不用
		List<Article> list = articleService.findAll(article, pb);
		
		
		List<ArticleVo> ArticleVoList = ArticleConverter.convert2Vo(list);
		Map<String, String> map = new HashMap<String, String>();
		//将站点存到静态变量中
		updateMap(map);
		
		for(int i=0;i<ArticleVoList.size();i++){
			ArticleVoList.get(i).setCategoryName(map.get(ArticleVoList.get(i).getCategoryId()+""));
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(ArticleVoList);
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("jsonArray", jsonArray);
		jsonObject.put("sum", sum);
		return jsonObject;
	}
	
	@RequestMapping(value="/find", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray findByAttribute(HttpServletRequest request, HttpServletResponse response,String attribute){
		logger.info("====================调用ArticleController-->接口：/find====================");
		List<Article> list = articleService.findByAttribute(attribute);
		List<ArticleVo> ArticleVoList = ArticleConverter.convert2Vo(list);
		Map<String, String> map = new HashMap<String, String>();
		updateMap(map);
		
		for(int i=0;i<ArticleVoList.size();i++){
			ArticleVoList.get(i).setCategoryName(map.get(ArticleVoList.get(i).getCategoryId()+""));
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(ArticleVoList);
		return jsonArray;
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public void add(HttpServletRequest request, HttpServletResponse response,Article article) throws ServletException, IOException{
		logger.info("====================调用ArticleController-->接口：/add====================");
		String dateStr = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		//保存标题
		article.setCreateTime(dateStr);//创建时间
		article.setUpdateTime(dateStr);//更新时间
		article.setStatus("1");//默认上线
		article.setSort(1);//默认排序
		articleService.save(article);
		Integer articleId = article.getId();
		//保存内容
		String data=request.getParameter("data");
		ArticleData articleData=new ArticleData();
		articleData.setCreateTime(dateStr);//创建时间
		articleData.setUpdateTime(dateStr);//更新时间
		articleData.setId(articleId);
		articleData.setData(data);
		articleDataService.save(articleData);
		//保存链接
		String url=request.getParameter("url");
		Link link=new Link();
		link.setCreateTime(dateStr);//创建时间
		link.setUpdateTime(dateStr);//更新时间
		link.setCategoryId(article.getCategoryId());
		link.setArticleId(articleId);
		link.setName(article.getName());
		link.setUrl(url);
		linkService.save(link);
		response.sendRedirect("../crud/article/articleList.jsp");
	}
	
	@RequestMapping(value="/updatePre",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject updatePre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.info("====================调用ArticleController-->接口：/updatePre====================");
		String id=request.getParameter("id");
		String categoryId=request.getParameter("categoryId");
		Map<String, String> map = new HashMap<String, String>();
		updateMap(map);
		String categoryName = map.get(categoryId);
		List<Article> articleList = articleService.findById(Integer.parseInt(id));
		List<ArticleData> articleDatasList = articleDataService.findById(Integer.parseInt(id));
		List<Link> linkList = linkService.findByArticleId(Integer.parseInt(id));
		System.out.println("");
		JSONObject json=new JSONObject();
		json.put("success", true);
		json.put("categoryName", categoryName);
		json.put("articleList", articleList);
		json.put("articleDatasList", articleDatasList);
		json.put("linkList", linkList);
		return json;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public void update(HttpServletRequest request, HttpServletResponse response,Article article) throws ServletException, IOException{
		logger.info("====================调用ArticleController-->接口：/update====================");
		String dateStr = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		String data=request.getParameter("data");
		String url=request.getParameter("url");
		//更新link
		Link link=linkService.findByArticleId((article.getId())).get(0);
		link.setName(article.getName());
		link.setUrl(url);
		link.setCategoryId(article.getCategoryId());
		link.setArticleId(article.getId());
		link.setCreateTime(article.getCreateTime());
		link.setUpdateTime(dateStr);
		linkService.update(link);
		//更新data
		ArticleData articleData=new ArticleData();
		articleData.setId(article.getId());
		articleData.setData(data);
		articleData.setCreateTime(article.getCreateTime());
		articleData.setUpdateTime(dateStr);
		articleDataService.update(articleData);
		//更新article
		article.setUpdateTime(dateStr);
		articleService.update(article);
		response.sendRedirect("../crud/article/articleList.jsp");
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public synchronized JSONObject  delete(HttpServletRequest request, HttpServletResponse response,Integer id) throws ServletException, IOException{
		logger.info("====================调用ArticleController-->接口：/delete====================");
		linkService.deleteByArticleId(id);
		int hardDelete = articleService.hardDelete(id);
		
		JSONObject json=new JSONObject();
		
		json.put("success", true);
		json.put("message", hardDelete);
		return json;
	}
	
	@RequestMapping(value="/changeStatus")
	@ResponseBody
	public void changeStatus(HttpServletRequest request, HttpServletResponse response,Integer id) throws ServletException, IOException{
		logger.info("====================调用ArticleController-->接口：/changeStatus====================");
		articleService.changeStatus(id);
		response.sendRedirect("../crud/article/articleList.jsp");
	}
	
	@RequestMapping(value="/down")
	@ResponseBody
	public void down(HttpServletRequest request, HttpServletResponse response,Integer id) throws ServletException, IOException{
		logger.info("====================调用ArticleController-->接口：/down====================");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date uDate=new java.util.Date();
		java.sql.Date sDate=new java.sql.Date(uDate.getTime());
		String strDate=df.format(sDate);
		List<Article> articleList = articleService.findById(id);
		List<Article> findAll = articleService.findSomeBycategoryId(articleList.get(0).getCategoryId());
		int[] array=new int[findAll.size()];
		for(int i=0;i<findAll.size();i++){
			array[i]=findAll.get(i).getSort();
		}
		for(int i=0;i<array.length;i++){
			if(articleList.get(0).getSort()==array[i]){
				if(i+1!=array.length){
					array[i]=array[i+1];
					array[i+1]=articleList.get(0).getSort();
					findAll.get(i).setSort(array[i]);
					findAll.get(i).setUpdateTime(strDate);
					articleService.update(findAll.get(i));
					findAll.get(i+1).setSort(array[i+1]);
					findAll.get(i+1).setUpdateTime(strDate);
					articleService.update(findAll.get(i+1));
					break;
				}
			}
		}
		response.sendRedirect("../crud/article/articleList.jsp");
	}
	
	
	@RequestMapping(value="/up")
	@ResponseBody
	public void up(HttpServletRequest request, HttpServletResponse response,Integer id) throws ServletException, IOException{
		logger.info("====================调用ArticleController-->接口：/up====================");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date uDate=new java.util.Date();
		java.sql.Date sDate=new java.sql.Date(uDate.getTime());
		String strDate=df.format(sDate);
		List<Article> articleList = articleService.findById(id);
		List<Article> findAll = articleService.findSomeBycategoryId(articleList.get(0).getCategoryId());
		int[] array=new int[findAll.size()];
		for(int i=0;i<findAll.size();i++){
			array[i]=findAll.get(i).getSort();
		}
		for(int i=0;i<array.length;i++){
			if(articleList.get(0).getSort()==array[i]){
				if(i!=0){
					array[i]=array[i-1];
					array[i-1]=articleList.get(0).getSort();
					findAll.get(i-1).setSort(array[i-1]);
					findAll.get(i-1).setUpdateTime(strDate);
					articleService.update(findAll.get(i-1));
					findAll.get(i).setSort(array[i]);
					findAll.get(i).setUpdateTime(strDate);
					articleService.update(findAll.get(i));
					break;
				}
			}
		}
		response.sendRedirect("../crud/article/articleList.jsp");
	}
	
	private void updateMap(Map<String, String> map){
		map.clear();
		List<Category> findAll = categoryService.findAll();
		for (Category category : findAll) {
			map.put(category.getId() + "", category.getName());
		}
	}
}

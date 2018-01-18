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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeren.cms.converter.LinkConverter;
import com.yeren.cms.modle.Category;
import com.yeren.cms.modle.Link;
import com.yeren.cms.modle.Site;
import com.yeren.cms.service.CategoryService;
import com.yeren.cms.service.LinkService;
import com.yeren.cms.util.PageBean;
import com.yeren.cms.vo.LinkVO;

@Controller
@RequestMapping("/link")
public class LinkController {

	@Autowired
	LinkService linkService;

	@Autowired
	CategoryService categoryService;

	private static final Logger logger = LoggerFactory.getLogger(LinkController.class);
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray listAll(HttpServletRequest request, HttpServletResponse response) {
		logger.info("====================调用LinkController-->接口：/list====================");
		List<Link> list = linkService.findAll();
		List<LinkVO> linkVoList = LinkConverter.convert2Vo(list);
		Map<String, String> map = new HashMap<String, String>();
		updateMap(map);
		
		for(int i=0;i<linkVoList.size();i++){
			linkVoList.get(i).setCategoryName(map.get(linkVoList.get(i).getCategoryId()+""));
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(linkVoList);
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
		logger.info("====================调用LinkController-->接口：/listByPage====================");
		String page=request.getParameter("page");
		if("".equals(page)||"null".equals(page)||page==null){
			page="1";
		}
		String rows=request.getParameter("rows");
		if("".equals(rows)||"null".equals(rows)||rows==null){
			rows="7";
		}
		int sum = linkService.getSum();//总量
		
		PageBean pb=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Link link=new Link();//暂时不用
		List<Link> list = linkService.findAll(link, pb);
		
		List<LinkVO> linkVoList = LinkConverter.convert2Vo(list);
		Map<String, String> map = new HashMap<String, String>();
		updateMap(map);
		
		for(int i=0;i<linkVoList.size();i++){
			linkVoList.get(i).setCategoryName(map.get(linkVoList.get(i).getCategoryId()+""));
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(linkVoList);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("jsonArray", jsonArray);
		jsonObject.put("sum", sum);
		return jsonObject;
	}

	@RequestMapping(value = "/list_jsp", method = RequestMethod.GET)
	public String listByPage() {
		return "crud/link/linkList";
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray findByAttribute(HttpServletRequest request, HttpServletResponse response, String attribute) {
		logger.info("====================调用LinkController-->接口：/find====================");
		List<Link> list = linkService.findByAttribute(attribute);
		List<LinkVO> linkVoList = LinkConverter.convert2Vo(list);
		Map<String, String> map = new HashMap<String, String>();
		updateMap(map);
		
		for(int i=0;i<linkVoList.size();i++){
			linkVoList.get(i).setCategoryName(map.get(linkVoList.get(i).getCategoryId()+""));
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(linkVoList);
		return jsonArray;
	}
	
	@RequestMapping(value = "/find2", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray findByAttribute2(HttpServletRequest request, HttpServletResponse response, String attribute) {
		logger.info("====================调用LinkController-->接口：/find2====================");
		List<Link> list = linkService.findByAttribute2(attribute);
		List<LinkVO> linkVoList = LinkConverter.convert2Vo(list);
		Map<String, String> map = new HashMap<String, String>();
		updateMap(map);
		
		for(int i=0;i<linkVoList.size();i++){
			linkVoList.get(i).setCategoryName(map.get(linkVoList.get(i).getCategoryId()+""));
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(linkVoList);
		return jsonArray;
	}

	@RequestMapping(value = "/add_jsp", method = RequestMethod.GET)
	public String add() {
		return "crud/link/linkAdd";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, HttpServletResponse response, Link link) throws ServletException, IOException {
		logger.info("====================调用LinkController-->接口：/add====================");
		String dateStr = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		link.setCreateTime(dateStr);//创建时间
		link.setUpdateTime(dateStr);//更新时间
		link.setStatus("1");//默认上线
		link.setSort(1);//默认排序
		link.setArticleId(-1);
		linkService.save(link);
		return "crud/link/linkList";
	}

	
	@RequestMapping(value = "/update_jsp", method = RequestMethod.GET)
	public String update(String id,String name,String url,String categoryId,String categoryName,String sort,String status,String createTime,ModelMap modelMap) {
		modelMap.addAttribute("id", id);
		modelMap.addAttribute("name", name);
		modelMap.addAttribute("url", url);
		modelMap.addAttribute("categoryId", categoryId);
		modelMap.addAttribute("categoryName", categoryName);
		modelMap.addAttribute("sort", sort);
		modelMap.addAttribute("status", status);
		modelMap.addAttribute("createTime", createTime);
		return "crud/link/linkUpdate";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, HttpServletResponse response, Link link) throws ServletException, IOException {
		logger.info("====================调用LinkController-->接口：/update====================");
		String dateStr = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		link.setUpdateTime(dateStr);//更新时间
		link.setArticleId(-1);
		linkService.update(link);
		return "crud/link/linkList";
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public JSONObject delete(HttpServletRequest request, HttpServletResponse response, Integer id) throws ServletException, IOException {
		logger.info("====================调用LinkController-->接口：/delete====================");
		int delete = linkService.delete(id);
		JSONObject json = new JSONObject();
		json.put("success", true);
		json.put("message", delete);
		return json;
	}
	
	@RequestMapping(value="/changeStatus")
	public String changeStatus(HttpServletRequest request, HttpServletResponse response,Integer id) throws ServletException, IOException{
		logger.info("====================调用LinkController-->接口：/changeStatus====================");
		linkService.changeStatus(id);
		return "crud/link/linkList";
	}
	
	@RequestMapping(value="/down")
	public String down(HttpServletRequest request, HttpServletResponse response,Integer id) throws ServletException, IOException{
		logger.info("====================调用LinkController-->接口：/down====================");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date uDate=new java.util.Date();
		java.sql.Date sDate=new java.sql.Date(uDate.getTime());
		String strDate=df.format(sDate);
		List<Link> linkList = linkService.findById(id);
		List<Link> findAll = linkService.findSomeBycategoryId(linkList.get(0).getCategoryId());
		int[] array=new int[findAll.size()];
		for(int i=0;i<findAll.size();i++){
			array[i]=findAll.get(i).getSort();
		}
		for(int i=0;i<array.length;i++){
			if(linkList.get(0).getSort()==array[i]){
				if(i+1!=array.length){
					array[i]=array[i+1];
					array[i+1]=linkList.get(0).getSort();
					findAll.get(i).setSort(array[i]);
					findAll.get(i).setUpdateTime(strDate);
					linkService.update(findAll.get(i));
					findAll.get(i+1).setSort(array[i+1]);
					findAll.get(i+1).setUpdateTime(strDate);
					linkService.update(findAll.get(i+1));
					break;
				}
			}
		}
		return "crud/link/linkList";
	}
	
	
	@RequestMapping(value="/up")
	public String up(HttpServletRequest request, HttpServletResponse response,Integer id) throws ServletException, IOException{
		logger.info("====================调用LinkController-->接口：/up====================");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date uDate=new java.util.Date();
		java.sql.Date sDate=new java.sql.Date(uDate.getTime());
		String strDate=df.format(sDate);
		List<Link> linkList = linkService.findById(id);
		List<Link> findAll = linkService.findSomeBycategoryId(linkList.get(0).getCategoryId());
		int[] array=new int[findAll.size()];
		for(int i=0;i<findAll.size();i++){
			array[i]=findAll.get(i).getSort();
		}
		for(int i=0;i<array.length;i++){
			if(linkList.get(0).getSort()==array[i]){
				if(i!=0){
					array[i]=array[i-1];
					array[i-1]=linkList.get(0).getSort();
					findAll.get(i-1).setSort(array[i-1]);
					findAll.get(i-1).setUpdateTime(strDate);
					linkService.update(findAll.get(i-1));
					findAll.get(i).setSort(array[i]);
					findAll.get(i).setUpdateTime(strDate);
					linkService.update(findAll.get(i));
					break;
				}
			}
		}
		return "crud/link/linkList";
	}
	
	private void updateMap(Map<String, String> map){
		map.clear();
		List<Category> findAll = categoryService.findAll();
		for (Category category : findAll) {
			map.put(category.getId() + "", category.getName());
		}
	}
}

package com.yeren.cms.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeren.cms.modle.Site;
import com.yeren.cms.service.SiteService;
import com.yeren.cms.util.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/site")
public class SiteController {
	@Autowired
	SiteService siteService;
	
	private static final Logger logger = LoggerFactory.getLogger(SiteController.class);
	
	/**
	 * 非页面用的
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray listAll(HttpServletRequest request, HttpServletResponse response){
		logger.info("====================调用SiteController-->接口：/list====================");
		List<Site> list = siteService.findAll();
		JSONArray jsonArray=new JSONArray();
		jsonArray.addAll(list);
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
		logger.info("====================调用SiteController-->接口：/listByPage====================");
		String page=request.getParameter("page");
		if("".equals(page)||"null".equals(page)||page==null){
			page="1";
		}
		String rows=request.getParameter("rows");
		if("".equals(rows)||"null".equals(rows)||rows==null){
			rows="7";
		}
		
		
		int sum = siteService.getSum();//总量
		
		PageBean pb=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Site site=new Site();//暂时不用
		List<Site> list = siteService.findAll(site, pb);
		JSONArray jsonArray=new JSONArray();
		jsonArray.addAll(list);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("jsonArray", jsonArray);
		jsonObject.put("sum", sum);
		return jsonObject;
	}
	
	@RequestMapping(value = "/list_jsp", method = RequestMethod.GET)
	public String listByPage() {
		return "crud/site/siteList";
	}
	
	@RequestMapping(value="/find/{attribute}", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray findByAttribute(HttpServletRequest request, HttpServletResponse response,@PathVariable String attribute){
		logger.info("====================调用SiteController-->接口：/find====================");
		List<Site> list = siteService.findByAttribute(attribute);
		JSONArray jsonArray=new JSONArray();
		jsonArray.addAll(list);
		return jsonArray;
	}
	
	@RequestMapping(value="/find-/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray findById(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id){
		logger.info("====================调用SiteController-->接口：/find/{id}====================");
		List<Site> list = siteService.findById(id);
		list.get(0).getName();
		JSONArray jsonArray=new JSONArray();
		jsonArray.addAll(list);//TODO 要写
		return jsonArray;
	}
	
	@RequestMapping(value = "/add_jsp", method = RequestMethod.GET)
	public String add() {
		return "crud/site/siteAdd";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(HttpServletRequest request, HttpServletResponse response,Site site) throws ServletException, IOException{
		logger.info("====================调用SiteController-->接口：/add====================");
		String dateStr = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		site.setCreateTime(dateStr);//创建时间
		site.setUpdateTime(dateStr);//更新时间
		site.setStatus("1");//默认上线
		site.setSort(1);//默认排序
		siteService.save(site);
		
		return "crud/site/siteList";
	}
	
	@RequestMapping(value = "/update_jsp", method = RequestMethod.GET)
	public String update(String id,String name,String sort,String status,String createTime,ModelMap model) {
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("sort", sort);
		model.addAttribute("status", status);
		model.addAttribute("createTime", createTime);
		return "crud/site/siteUpdate";
	}
	
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(HttpServletRequest request, HttpServletResponse response,Site site) throws ServletException, IOException{
		logger.info("====================调用SiteController-->接口：/update====================");
		String dateStr = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		site.setUpdateTime(dateStr);//更新时间
		siteService.update(site);
		return "crud/site/siteList";
	}
	
	@RequestMapping(value="/delete/{id}")
	@ResponseBody
	public JSONObject delete(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id) throws ServletException, IOException{
		logger.info("====================调用SiteController-->接口：/delete/{id}====================");
		int softDelete = siteService.softDelete(id);
		JSONObject json=new JSONObject();
		if(softDelete>0){
			json.put("success", true);
			json.put("message", softDelete);
		}else {
			json.put("success", false);
			json.put("message", softDelete);
		}
		return json;
	}
	
	@RequestMapping(value="/changeStatus/{id}")
	public String changeStatus(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id) throws ServletException, IOException{
		logger.info("====================调用SiteController-->接口：/changeStatus/{id}====================");
		siteService.changeStatus(id);
		return "crud/site/siteList";
	}
	
	@RequestMapping(value="/down/{id}")
	public String down(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id) throws ServletException, IOException{
		logger.info("====================调用SiteController-->接口：/down/{id}====================");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date uDate=new java.util.Date();
		java.sql.Date sDate=new java.sql.Date(uDate.getTime());
		String strDate=df.format(sDate);
		List<Site> siteList = siteService.findById(id);
		List<Site> findAll = siteService.findAll();
		int[] array=new int[findAll.size()];
		for(int i=0;i<findAll.size();i++){
			array[i]=findAll.get(i).getSort();
		}
		for(int i=0;i<array.length;i++){
			if(siteList.get(0).getSort()==array[i]){
				if(i+1!=array.length){
					array[i]=array[i+1];
					array[i+1]=siteList.get(0).getSort();
					findAll.get(i).setSort(array[i]);
					findAll.get(i).setUpdateTime(strDate);
					siteService.update(findAll.get(i));
					findAll.get(i+1).setSort(array[i+1]);
					findAll.get(i+1).setUpdateTime(strDate);
					siteService.update(findAll.get(i+1));
					break;
				}
			}
		}
		return "crud/site/siteList";
	}
	
	
	@RequestMapping(value="/up/{id}")
	public String up(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id) throws ServletException, IOException{
		logger.info("====================调用SiteController-->接口：/up/{id}====================");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date uDate=new java.util.Date();
		java.sql.Date sDate=new java.sql.Date(uDate.getTime());
		String strDate=df.format(sDate);
		List<Site> siteList = siteService.findById(id);
		List<Site> findAll = siteService.findAll();
		int[] array=new int[findAll.size()];
		for(int i=0;i<findAll.size();i++){
			array[i]=findAll.get(i).getSort();
		}
		for(int i=0;i<array.length;i++){
			if(siteList.get(0).getSort()==array[i]){
				if(i!=0){
					array[i]=array[i-1];
					array[i-1]=siteList.get(0).getSort();
					findAll.get(i-1).setSort(array[i-1]);
					findAll.get(i-1).setUpdateTime(strDate);
					siteService.update(findAll.get(i-1));
					findAll.get(i).setSort(array[i]);
					findAll.get(i).setUpdateTime(strDate);
					siteService.update(findAll.get(i));
					break;
				}
			}
		}
		return "crud/site/siteList";
	}
	
}

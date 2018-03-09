package com.yeren.cms.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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

import com.yeren.cms.converter.CategoryConverter;
import com.yeren.cms.modle.Category;
import com.yeren.cms.modle.Site;
import com.yeren.cms.service.CategoryService;
import com.yeren.cms.service.SiteService;
import com.yeren.cms.util.PageBean;
import com.yeren.cms.vo.CategoryVo;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@Autowired
	SiteService SiteService;
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray listAll(HttpServletRequest request, HttpServletResponse response) {
		logger.info("====================调用CategoryController-->接口：/list====================");
		List<Category> list = categoryService.findAll();
		List<CategoryVo> categoryVoList = CategoryConverter.convert2Vo(list);
		Map<String, String> map = new HashMap<String, String>();
		updateMap(map);
		
		for(int i=0;i<categoryVoList.size();i++){
			categoryVoList.get(i).setSiteName(map.get(categoryVoList.get(i).getSiteId()+""));
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(categoryVoList);
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
		logger.info("====================调用CategoryController-->接口：/listByPage====================");
		String page=request.getParameter("page");
		if("".equals(page)||"null".equals(page)||page==null){
			page="1";
		}
		String rows=request.getParameter("rows");
		if("".equals(rows)||"null".equals(rows)||rows==null){
			rows="7";
		}
		int sum = categoryService.getSum();//总量
		
		PageBean pb=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		List<Category> list = categoryService.findAll(pb);
		
		
		List<CategoryVo> categoryVoList = CategoryConverter.convert2Vo(list);
		Map<String, String> map = new HashMap<String, String>();
		updateMap(map);
		
		for(int i=0;i<categoryVoList.size();i++){
			categoryVoList.get(i).setSiteName(map.get(categoryVoList.get(i).getSiteId()+""));
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(categoryVoList);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("jsonArray", jsonArray);
		jsonObject.put("sum", sum);
		return jsonObject;
	}

	@RequestMapping(value = "/list_jsp", method = RequestMethod.GET)
	public String listByPage() {
		return "crud/category/categoryList";
	}
	
	@RequestMapping(value = "/find/{attribute}", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray findByAttribute(HttpServletRequest request, HttpServletResponse response,@PathVariable String attribute) {
		logger.info("====================调用CategoryController-->接口：/find/{attribute}====================");
		List<Category> list = categoryService.findByAttribute(attribute);
		List<CategoryVo> categoryVoList = CategoryConverter.convert2Vo(list);
		Map<String, String> map = new HashMap<String, String>();
		//将站点存到静态变量中
		updateMap(map);
		
		for(int i=0;i<categoryVoList.size();i++){
			categoryVoList.get(i).setSiteName(map.get(categoryVoList.get(i).getSiteId()+""));
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(categoryVoList);
		return jsonArray;
	}

	@RequestMapping(value = "/add_jsp", method = RequestMethod.GET)
	public String add() {
		return "crud/category/categoryAdd";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, HttpServletResponse response, Category category) throws ServletException, IOException {
		logger.info("====================调用CategoryController-->接口：/add====================");
		String dateStr = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		category.setSort(1);//默认排序
		category.setStatus("1");//默认上线状态
		category.setCreateTime(dateStr);//创建时间
		category.setUpdateTime(dateStr);//更新时间
		categoryService.save(category);
		return "crud/category/categoryList";
	}

	@RequestMapping(value = "/update_jsp", method = RequestMethod.GET)
	public String update(String id,String name,String siteId,String siteName,String parentId,String sort,String status,String createTime,ModelMap model) {
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("siteId", siteId);
		model.addAttribute("siteName", siteName);
		model.addAttribute("parentId", parentId);
		model.addAttribute("sort", sort);
		model.addAttribute("status", status);
		model.addAttribute("createTime", createTime);
		return "crud/category/categoryUpdate";
	}
	
	
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, HttpServletResponse response, Category category) throws ServletException, IOException {
		logger.info("====================调用CategoryController-->接口：/update====================");
		String dateStr = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		category.setUpdateTime(dateStr);//更新时间
		categoryService.update(category);
		return "crud/category/categoryList";
	}
	

	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public JSONObject delete(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id) throws ServletException, IOException {
		logger.info("====================调用CategoryController-->接口：/delete/{id}====================");
		int softDelete = categoryService.softDelete(id);
		JSONObject json = new JSONObject();
		json.put("success", true);
		json.put("message", softDelete);
		return json;
	}
	
	@RequestMapping(value="/changeStatus/{id}")
	public String changeStatus(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id) throws ServletException, IOException{
		logger.info("====================调用CategoryController-->接口：/changeStatus/{id}====================");
		categoryService.changeStatus(id);
		return "crud/category/categoryList";
	}
	
	/**
	 * 同一个站点才可以上移下移
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/down/{id}")
	public String down(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id) throws ServletException, IOException{
		logger.info("====================调用CategoryController-->接口：/down/{id}====================");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date uDate=new java.util.Date();
		java.sql.Date sDate=new java.sql.Date(uDate.getTime());
		String strDate=df.format(sDate);
		List<Category> categoryList = categoryService.findById(id);
		List<Category> findAll = categoryService.findSomeBySiteId(categoryList.get(0).getSiteId());
		int[] array=new int[findAll.size()];
		for(int i=0;i<findAll.size();i++){
			array[i]=findAll.get(i).getSort();
		}
		for(int i=0;i<array.length;i++){
			if(categoryList.get(0).getSort()==array[i]){
				if(i+1!=array.length){
					array[i]=array[i+1];
					array[i+1]=categoryList.get(0).getSort();
					findAll.get(i).setSort(array[i]);
					findAll.get(i).setUpdateTime(strDate);
					categoryService.update(findAll.get(i));
					findAll.get(i+1).setSort(array[i+1]);
					findAll.get(i+1).setUpdateTime(strDate);
					categoryService.update(findAll.get(i+1));
					break;
				}
			}
		}
		return "crud/category/categoryList";
	}
	
	
	/**
	 * 同一个站点才可以上移下移
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/up/{id}")
	public String up(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id) throws ServletException, IOException{
		logger.info("====================调用CategoryController-->接口：/up/{id}====================");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date uDate=new java.util.Date();
		java.sql.Date sDate=new java.sql.Date(uDate.getTime());
		String strDate=df.format(sDate);
		List<Category> categoryList = categoryService.findById(id);
		List<Category> findAll = categoryService.findSomeBySiteId(categoryList.get(0).getSiteId());
		int[] array=new int[findAll.size()];
		for(int i=0;i<findAll.size();i++){
			array[i]=findAll.get(i).getSort();
		}
		for(int i=0;i<array.length;i++){
			if(categoryList.get(0).getSort()==array[i]){
				if(i!=0){
					array[i]=array[i-1];
					array[i-1]=categoryList.get(0).getSort();
					findAll.get(i-1).setSort(array[i-1]);
					findAll.get(i-1).setUpdateTime(strDate);
					categoryService.update(findAll.get(i-1));
					findAll.get(i).setSort(array[i]);
					findAll.get(i).setUpdateTime(strDate);
					categoryService.update(findAll.get(i));
					break;
				}
			}
		}
		return "crud/category/categoryList";
	}
	
	private void updateMap(Map<String, String> map){
		map.clear();
		List<Site> findAll = SiteService.findAll();
		for (Site site : findAll) {
			map.put(site.getId() + "", site.getName());
		}
	}
}

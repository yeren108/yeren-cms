package com.yeren.cms.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.yeren.cms.modle.Category;
import com.yeren.cms.vo.CategoryVo;

public class CategoryConverter {
	public static CategoryVo convert2Vo(Category category) {
		CategoryVo vo = new CategoryVo();
		if (category == null) {
			return vo;
		}
		BeanUtils.copyProperties(category, vo);
//		BeanUtils.copyProperties(category, vo,new String[]{"createTime","updateTime"});
//		vo.setCreateTime(vo.getCreateTime());
//		vo.setUpdateTime(vo.getUpdateTime());
		return vo;
	}

	public static List<CategoryVo> convert2Vo(List<Category> entityList) {
		List<CategoryVo> voList = new ArrayList<CategoryVo>();
		if (entityList == null || entityList.size() == 0) {
			return voList;
		}
		for (Category entity : entityList) {
			voList.add(convert2Vo(entity));
		}

		return voList;
	}
}

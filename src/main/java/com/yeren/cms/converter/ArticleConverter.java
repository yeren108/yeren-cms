package com.yeren.cms.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.yeren.cms.modle.Article;
import com.yeren.cms.vo.ArticleVo;

public class ArticleConverter {
	public static ArticleVo convert2Vo(Article article) {
		ArticleVo vo = new ArticleVo();
		if (article == null) {
			return vo;
		}
		BeanUtils.copyProperties(article, vo);
//		BeanUtils.copyProperties(category, vo,new String[]{"createTime","updateTime"});
//		vo.setCreateTime(vo.getCreateTime());
//		vo.setUpdateTime(vo.getUpdateTime());
		return vo;
	}
	
	
	/**
	 * 
	 * BeanUtils.copyProperties(entity, vo,new String[]{"applyTime","updateTime"});
		vo.setApplyTime(DateUtil.getDayAndTimeStr(entity.getApplyTime()));
		vo.setUpdateTime(DateUtil.getDayAndTimeStr(entity.getUpdateTime()));
		entity.setUpdateDate(DateUtil.getDateFromStr(vo.getUpdateDate()+" 00:00:00"));
	 */

	public static List<ArticleVo> convert2Vo(List<Article> articleList) {
		List<ArticleVo> voList = new ArrayList<ArticleVo>();
		if (articleList == null || articleList.size() == 0) {
			return voList;
		}
		for (Article article : articleList) {
			voList.add(convert2Vo(article));
		}

		return voList;
	}
}

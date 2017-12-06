package com.yeren.cms.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.yeren.cms.modle.Link;
import com.yeren.cms.vo.LinkVO;

public class LinkConverter {
	public static LinkVO convert2Vo(Link link) {
		LinkVO vo = new LinkVO();
		if (link == null) {
			return vo;
		}
		BeanUtils.copyProperties(link, vo);
//		BeanUtils.copyProperties(category, vo,new String[]{"createTime","updateTime"});
//		vo.setCreateTime(vo.getCreateTime());
//		vo.setUpdateTime(vo.getUpdateTime());
		return vo;
	}

	public static List<LinkVO> convert2Vo(List<Link> linkList) {
		List<LinkVO> voList = new ArrayList<LinkVO>();
		if (linkList == null || linkList.size() == 0) {
			return voList;
		}
		for (Link link : linkList) {
			voList.add(convert2Vo(link));
		}

		return voList;
	}
}

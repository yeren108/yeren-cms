package com.yeren.cms.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cms_link")
public class Link implements Serializable {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String name;
	@Column
	private String url;
	@Column(name = "category_id")
	private Integer categoryId;
	@Column(name = "article_id")
	private Integer articleId;
	@Column
	private Integer sort;
	@Column
	private String status;
	@Column(name = "create_time")
	private String createTime;
	@Column(name = "update_time")
	private String updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Link() {

	}

	public Link(Integer id, String name, String url, Integer categoryId, Integer articleId, Integer sort, String status, String createTime,
			String updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.categoryId = categoryId;
		this.articleId = articleId;
		this.sort = sort;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	

}

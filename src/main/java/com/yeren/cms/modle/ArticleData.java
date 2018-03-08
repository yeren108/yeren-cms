package com.yeren.cms.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cms_article_data")
public class ArticleData implements Serializable {
	@Id
	@Column
	private Integer id;
	@Column
	private String data;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

	public ArticleData() {

	}

	public ArticleData(Integer id, String data, String createTime, String updateTime) {
		super();
		this.id = id;
		this.data = data;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

}

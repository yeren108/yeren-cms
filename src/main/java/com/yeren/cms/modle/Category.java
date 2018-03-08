package com.yeren.cms.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cms_category")
public class Category implements Serializable {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String name;
	@Column(name = "site_id")
	private Integer siteId;
	@Column(name = "parent_id")
	private Integer parentId;
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

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public Category() {

	}

	public Category(Integer id, String name, Integer siteId, Integer parentId, Integer sort, String status, String createTime, String updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.siteId = siteId;
		this.parentId = parentId;
		this.sort = sort;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	

}

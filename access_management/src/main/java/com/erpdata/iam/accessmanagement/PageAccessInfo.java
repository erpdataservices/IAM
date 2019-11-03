package com.erpdata.iam.accessmanagement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAGE_ACCESS_INFO")
public class PageAccessInfo {

	@Id
	@GeneratedValue
	private Long pageAccessInfoId;
	private Long roleId;
	private Long pageId;

	public Long getPageAccessInfoId() {
		return pageAccessInfoId;
	}

	public void setPageAccessInfoId(Long pageAccessInfoId) {
		this.pageAccessInfoId = pageAccessInfoId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

}

package com.erpdata.iam.accessmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accessManagementServices")
public class AccessManagementServices {

	private UserInfoRepository userInfoRepository;
	private RoleInfoRepository roleInfoRepository;
	private UserRolesRepository userRolesRepository;
	private PageAccessInfoRepository pageAccessInfoRepository;

	@Autowired
	public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}

	@Autowired
	public void setRoleInfoRepository(RoleInfoRepository roleInfoRepository) {
		this.roleInfoRepository = roleInfoRepository;
	}

	@Autowired
	public void setUserRolesRepository(UserRolesRepository userRolesRepository) {
		this.userRolesRepository = userRolesRepository;
	}

	@Autowired
	public void setPageAccessInfoRepository(PageAccessInfoRepository pageAccessInfoRepository) {
		this.pageAccessInfoRepository = pageAccessInfoRepository;
	}

	@PostMapping("/addPageAccess")
	public Long addPageAccess(@RequestBody RequestData requestData) {

		PageAccessInfo pageAccessInfo = pageAccessInfoRepository.getByPageIdAndRoleId(requestData.getPageId(),
				requestData.getRoleId());

		if (pageAccessInfo != null) {
			// send error saying page access already exists
			// return from here;
		}

		PageAccessInfo newPageAccessInfo = new PageAccessInfo();
		newPageAccessInfo.setPageId(requestData.getPageId());
		newPageAccessInfo.setRoleId(requestData.getRoleId());
		PageAccessInfo updatedPageAccessInfo = pageAccessInfoRepository.save(newPageAccessInfo);

		// Catch Exception and send meaningful error message

		return updatedPageAccessInfo.getPageAccessInfoId();
	}

	@PostMapping("/hasPageAccess")
	public Boolean hasPageAccess(@RequestBody RequestData requestData) {

		// 1. Check if user exists

		// 2. Find roles

		// 3 . Match pageId and roleIds

		PageAccessInfo pageAccessInfo = pageAccessInfoRepository.getByPageIdAndRoleId(1l, 1l);

		// Catch Exception and send meaningful error message

		return pageAccessInfo == null ? false : true;
	}
	
	//Add update & Delete operations
}

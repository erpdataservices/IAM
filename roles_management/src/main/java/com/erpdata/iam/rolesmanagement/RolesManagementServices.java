package com.erpdata.iam.rolesmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rolesManagementServices")
public class RolesManagementServices {

	private UserInfoRepository userInfoRepository;
	private RoleInfoRepository roleInfoRepository;
	private UserRolesRepository userRolesRepository;

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

	@PostMapping("/addRole")
	public Long addRole(@RequestBody RoleInfo roleInfo) {

		// 1.Check if role contain only string

		RoleInfo updatedRoleInfo = roleInfoRepository.save(roleInfo);

		// Catch Exception and send meaningful error message

		return updatedRoleInfo.getRoleId();
	}

	@PostMapping("/addUserRole")
	public String addUserRole(@RequestBody UserRoles UserRoles) {

		// 1.Check if user exists, if not exception

		// 2. Check if role exists, if not exception

		// 3. check if same userrole already exists, if not exception

		userRolesRepository.save(UserRoles);

		// Catch Exception and send meaningful error message

		return "Success";
	}

	// 3. Write update service for RoleInfo, UserRoles & Include Delete methods
}

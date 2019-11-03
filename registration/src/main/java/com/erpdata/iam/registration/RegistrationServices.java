package com.erpdata.iam.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registrationServices")
public class RegistrationServices {

	private UserInfoRepository userInfoRepository;

	@Autowired
	public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}

	@PostMapping("/registerUser")
	public Long registerUser(@RequestBody UserInfo userInfo) {

		// 1. Validate email id

		// 2. Validate Date of birth

		// 3. Validate Address

		// 4. Validate username contain only alphabet and number

		// 5. Encript password

		UserInfo updatedUserInfo = userInfoRepository.save(userInfo);

		// Catch Exception and send meaningful error message

		return updatedUserInfo.getUserId();
	}

	// Add reset password operation

	// Add delete & update operations
}

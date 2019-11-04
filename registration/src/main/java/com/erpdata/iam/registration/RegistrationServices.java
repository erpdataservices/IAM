package com.erpdata.iam.registration;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registrationServices")
public class RegistrationServices {

	private static final int ITERATIONS = 65536;
	private static final int KEY_LENGTH = 512;
	private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
	private static final SecureRandom RAND = new SecureRandom();

	private UserInfoRepository userInfoRepository;

	@Autowired
	public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}

	@PostMapping("/registerUser")
	public Object registerUser(@RequestBody UserInfo userInfo) {

		// 1. Check username is not empty
		if (StringUtils.isEmpty(userInfo.getUserName())) {
			return "Username is mandatory";
		}

		// 2. Validate username contain only alphabet and number
		if (!isValidUserName(userInfo.getUserName())) {
			return "Invalid user name pattern. Only use alphabets and numbers.";
		}

		// 3. Validate Employee Id
		if (StringUtils.isEmpty(userInfo.getEmployeeId())) {
			return "Employee id is mandatory";
		}

		// 4. Validate password is not empty
		if (StringUtils.isEmpty(userInfo.getPassword())) {
			return "Password should not be null or empty.";
		}

		// 5. Encript password
		String salt = generateSalt(userInfo.getPassword().length());
		String encriptedPassword = encryptPassword(userInfo.getPassword(), salt);
		userInfo.setPassword(encriptedPassword);

		// 6. Validate email is not empty
		if (StringUtils.isEmpty(userInfo.getEmailId())) {
			return "Email Id should not be null or empty.";
		}

		// 7. Validate email pattern
		if (!isValidEmailId(userInfo.getEmailId())) {
			return "Invalid Email id format. Please enter a valid format like 'abc@xyz.com'";
		}

		UserInfo updatedUserInfo = userInfoRepository.save(userInfo);

		// Catch Exception and send meaningful error message

		return updatedUserInfo.getUserId();
	}

	private String encryptPassword(String password, String salt) {
		try {
			char[] chars = password.toCharArray();
			byte[] bytes = salt.getBytes();
			PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);
			SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
			byte[] securePassword = fac.generateSecret(spec).getEncoded();
			return Optional.of(Base64.getEncoder().encodeToString(securePassword)).get();

		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			System.err.println("Exception encountered in hashPassword()");
			return Optional.empty().toString();

		}
	}

	public String generateSalt(final int length) {

		byte[] salt = new byte[length];
		RAND.nextBytes(salt);

		return Optional.of(Base64.getEncoder().encodeToString(salt)).get();
	}

	boolean isValidEmailId(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	boolean isValidUserName(String email) {
		String regex = "^[a-zA-Z0-9]*$";
		return email.matches(regex);
	}

	// Add reset password operation

	// Add delete & update operations
}

package poly.service;

import poly.dto.UserDTO;

public interface IUserService {

	UserDTO checkID(String id) throws Exception;
	
	UserDTO checkEmail(String email) throws Exception;
	
	int regUser(UserDTO uDTO) throws Exception;
	
	int verifyEmail(String id, String state) throws Exception;
	
	UserDTO loginProc(UserDTO uDTO) throws Exception;
	
	UserDTO checkLogin(String id, String pw) throws Exception;
	
	String findUserID(String email) throws Exception;
	
	UserDTO recoverPw(UserDTO uDTO) throws Exception;
	
	int verifyPwFind(String id) throws Exception;
	
	int recoverPwProc(String id, String password) throws Exception;
	
	UserDTO getUserInfo(String id) throws Exception;
	
	UserDTO getUserEditInfo(UserDTO uDTO) throws Exception;
	
	int updateUser(UserDTO uDTO) throws Exception;
	
	int deleteUserInfo(String id, String pw) throws Exception;
	
	UserDTO adminLogin(String id, String pw) throws Exception;
	
}

package poly.service;

import poly.dto.UserDTO;

public interface IUserService {

	UserDTO checkID(String id) throws Exception;
	
	UserDTO checkEmail(String email) throws Exception;
	
	int regUser(UserDTO uDTO) throws Exception;
	
	int verifyEmail(String id, String state) throws Exception;
	
	int loginProc(UserDTO uDTO) throws Exception;
}

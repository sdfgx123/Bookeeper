package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserDTO;

@Mapper("UserMapper")
public interface IUserMapper {

	UserDTO checkID(String id) throws Exception;
	
	UserDTO checkEmail(String email) throws Exception;
	
	int regUser(UserDTO uDTO) throws Exception;
	
	int verifyEmail(String id, String state) throws Exception;
	
	UserDTO loginProc(UserDTO uDTO) throws Exception;
	
	UserDTO checkLogin(String id, String pw) throws Exception;
	
	String findUserID(String email) throws Exception;
	
	void setFindPassword(String id, String state) throws Exception;
	
	UserDTO recoverPw(UserDTO uDTO) throws Exception;
	
	int verifyPwFind(String id) throws Exception;
	
	int recoverPwProc(String id, String password) throws Exception;

}

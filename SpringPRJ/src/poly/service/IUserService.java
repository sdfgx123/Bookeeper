package poly.service;

import poly.dto.UserDTO;

public interface IUserService {

	UserDTO checkID(String id) throws Exception;
	
	UserDTO checkEmail(String email) throws Exception;
	
}

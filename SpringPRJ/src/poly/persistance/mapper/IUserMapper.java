package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserDTO;

@Mapper("UserMapper")
public interface IUserMapper {

	UserDTO checkID(String id) throws Exception;
	
	UserDTO checkEmail(String email) throws Exception;
	
	int regUser(UserDTO uDTO) throws Exception;
	
	int verifyEmail(String id, String state) throws Exception;
}

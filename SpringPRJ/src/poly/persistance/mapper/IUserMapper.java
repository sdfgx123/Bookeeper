package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.UserDTO;

@Mapper("UserMapper")
public interface IUserMapper {

	UserDTO checkID(String id) throws Exception;
	
	UserDTO checkEmail(String email) throws Exception;
}

package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.UserDTO;

@Mapper("UserMapper")
public interface IUserMapper {

	UserDTO getLoginInfo(UserDTO uDTO);

	List<UserDTO> getUserList(UserDTO uDTO);
	
}

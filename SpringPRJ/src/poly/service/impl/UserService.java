package poly.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.UserDTO;
import poly.persistance.mapper.IUserMapper;
import poly.service.IUserService;

@Service("UserService")
public class UserService implements IUserService{
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="UserMapper")
	private IUserMapper userMapper;

	@Override
	public UserDTO checkID(String id) throws Exception {
		
		return userMapper.checkID(id);
	}

	@Override
	public UserDTO checkEmail(String email) throws Exception {
		
		return userMapper.checkEmail(email);
	}
	
}

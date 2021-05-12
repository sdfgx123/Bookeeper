package poly.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.UserDTO;
import poly.persistance.mapper.IUserMapper;
import poly.service.IUserService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

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

	@Override
	public int regUser(UserDTO uDTO) throws Exception {
		
		return userMapper.regUser(uDTO);
	}

	@Override
	public int verifyEmail(String id, String state) throws Exception {
		
		
		return userMapper.verifyEmail(id, state);
	}

	@Override
	public UserDTO loginProc(UserDTO uDTO) throws Exception {
		String password = uDTO.getPassword();
		password = EncryptUtil.encHashSHA256(password);
		uDTO.setPassword(password);
		
		return userMapper.loginProc(uDTO);
	}

	@Override
	public UserDTO checkLogin(String id, String pw) throws Exception {
		
		return userMapper.checkLogin(id, pw);
	}

	@Override
	public String findUserID(String email) throws Exception {
		
		return userMapper.findUserID(email);
	}

	@Override
	public UserDTO recoverPw(UserDTO uDTO) throws Exception {
		
		UserDTO rDTO = new UserDTO();
		
		//아이디 + 발급날짜로 
				rDTO = userMapper.recoverPw(uDTO);
				
				if (rDTO == null) {
					return null;
					
				} else {
					
					String id = uDTO.getId();
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
					Date d = new Date();
					Calendar c = Calendar.getInstance();
					c.setTime(d);
					c.add(Calendar.MINUTE, 20);
					
					String timeLimit = sdf.format(c.getTime());
					log.info("timeLimit : " + timeLimit);
					
					// 암호화된 암호와 아이디를 섞어서 해시 코드 생성
					String accessCode = EncryptUtil.encAES128CBC(timeLimit + "," + id);
					log.info("access code : " + accessCode);
					
					// 앞서 만든 코드를 데이터베이스 암호란에 업데이트
					rDTO.setPassword(accessCode);
					
					// 암호 찾기 활성화
					userMapper.setFindPassword(id, "1");
					return rDTO;
				}
	}

	@Override
	public int verifyPwFind(String id) throws Exception {
		
		return userMapper.verifyPwFind(id);
	}

	@Override
	public int recoverPwProc(String id, String password) throws Exception {
		password = EncryptUtil.encHashSHA256(password);
		return userMapper.recoverPwProc(id, password);
	}

	@Override
	public UserDTO getUserInfo(String id) throws Exception {
		
		UserDTO rDTO = new UserDTO();
		
		rDTO = userMapper.getUserInfo(id);
		
		return rDTO;
	}

	@Override
	public UserDTO getUserEditInfo(UserDTO uDTO) throws Exception {
		
		return userMapper.getUserEditInfo(uDTO);
	}

	@Override
	public int updateUser(UserDTO uDTO) throws Exception {
		
		return userMapper.updateUser(uDTO);
	}

}

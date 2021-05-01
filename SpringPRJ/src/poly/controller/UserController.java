package poly.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.util.CmmUtil;

@Controller
@RequestMapping(value = "user/")
public class UserController {

	private Logger log = Logger.getLogger(this.getClass());
	
	// 영역 : Resource
	
	// 영역 : 로그인 관련
	
	// 로그인
	@RequestMapping(value = "userLogin")
	public String UserLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		String error = CmmUtil.nvl(request.getParameter("error"));
		
		// error가 1이면 아이디 또는 비밀번호 틀림
		log.info("error : " + error);
		
		//에러가 1이면 error 변수에 1을 넣은 모델 객체를 만들어라
		if(error.equals("1")) {
			model.addAttribute("error", "1");
		}
		
		log.info(this.getClass());
		
		return "/user/userLogin";
	}
	
	// 유저 회원가입
	@RequestMapping(value = "userRegister")
	public String UserRegister() throws Exception {
		
		return "/user/userRegister";
	}
}

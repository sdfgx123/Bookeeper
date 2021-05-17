package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.service.impl.UserService;

@Controller
@RequestMapping(value = "admin/")
public class AdminController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "UserService")
	private UserService userService;
	
	// 관리자 로그인 페이지 호출
	@RequestMapping(value = "AdminLogin")
	public String AdminLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + " .AdminLogin start");
		
		return "/admin/adminLogin";
		
	}

}

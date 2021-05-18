package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.UserDTO;
import poly.service.impl.UserService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Controller
@RequestMapping(value = "admin/")
public class AdminController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "UserService")
	private UserService userService;
	
	// 관리자 메인 페이지 호출
	@RequestMapping(value = "AdminMain")
	public String AdminMain() throws Exception {
		
		log.info(this.getClass().getName() + " .AdminMain start");
		
		return "/admin/adminMain";
	}
	
	// 관리자 로그인 페이지 호출
	@RequestMapping(value = "AdminLogin")
	public String AdminLogin() throws Exception {
		
		log.info(this.getClass().getName() + " .AdminLogin start");
		
		return "/admin/adminLogin";
		
	}
	
	// 관리자 로그인 프로세스
	@RequestMapping(value = "DoAdminLogin", method = RequestMethod.POST)
	@ResponseBody
	public String DoAdminLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) throws Exception {
		
		log.info(this.getClass().getName() + " .DoAdminLogin start");
		
		String id = CmmUtil.nvl(request.getParameter("id"));
		String pw = CmmUtil.nvl(request.getParameter("pw"));
		log.info("id : " + id);
		log.info("pw : " + pw);
		pw = EncryptUtil.encHashSHA256(pw);
		log.info("암호화 pw : " + pw);
		
		UserDTO rDTO = userService.adminLogin(id, pw);
		
		if (rDTO == null) {
			log.info("null");
			log.info(this.getClass().getName() + " .DoAdminLogin end");
			return "1";
		}
		
		log.info("user_seq : " + rDTO.getUser_seq());
		session.setAttribute("user_seq", rDTO.getUser_seq());
		session.setAttribute("user_type", rDTO.getUser_type());
		session.setAttribute("id", rDTO.getId());
		log.info("관리자 로그인 세션 탑재 완료");
		
		log.info("session identification : " + session.getAttribute("id"));
		log.info(this.getClass().getName() + " .DoAdminLogin end");
		return "0";
		
	}

}

package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.UserDTO;
import poly.service.impl.UserService;
import poly.util.CmmUtil;

@Controller
@RequestMapping(value = "my/")
public class MyController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "UserService")
	private UserService userService;
	
	@RequestMapping(value = "MyMain")
	public String MyMain(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws Exception {
		log.info(this.getClass().getName() + " .MyMain start");
		
		String user_seq = CmmUtil.nvl(String.valueOf(session.getAttribute("user_seq")));
		log.info("user_seq code : " + user_seq);
		
		if (CmmUtil.nvl(user_seq).equals("")) {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/index.do");
			
			return "/redirect";
		}
		
		UserDTO uDTO = userService.getUserInfo(user_seq);
		
		if(uDTO == null) {
			model.addAttribute("msg", "존재하지 않는 회원입니다.");
			model.addAttribute("url", "/index.do");
			return "/redirect";
		}
		
		model.addAttribute("uDTO", uDTO);
		
		return "/my/myMain";
		
	}
}

package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.UserDTO;
import poly.service.impl.UserService;
import poly.util.CmmUtil;

@Controller
@RequestMapping(value = "lib/")
public class LibController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "UserService")
	private UserService userService;
	
	@RequestMapping(value = "LibMain")
	public String LibMain(HttpSession session, ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + " .MyMain start");
		
		String id = CmmUtil.nvl((String) session.getAttribute("id"));
		log.info("id : " + id);
		
		if (id.equals("")) {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/index.do");
			
			return "/redirect";
		}
		
		UserDTO uDTO = userService.getUserInfo(id);
		
		if(uDTO == null) {
			model.addAttribute("msg", "존재하지 않는 회원입니다.");
			model.addAttribute("url", "/index.do");
			return "/redirect";
		}
		
		model.addAttribute("uDTO", uDTO);
		
		return "/lib/libMain";
	}
	
	// 내 서재에 책 추가하기 기능
	@RequestMapping(value = "InsertBookInfo")
	public String InsertBookInfo(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		log.info(this.getClass().getName() + " .InsertBookInfo start");
		
		String id = CmmUtil.nvl((String) session.getAttribute("id"));
		log.info("id : " + id);
		
		if (id.equals("")) {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/index.do");
			
			return "/redirect";
		}
		
		return "/index";
		
	}

}

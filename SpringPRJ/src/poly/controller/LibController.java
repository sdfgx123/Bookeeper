package poly.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.util.CmmUtil;

@Controller
@RequestMapping(value = "lib/")
public class LibController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = "LibMain")
	public String LibMain(HttpSession session, ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + " .MyMain start");
		
		String user_seq = CmmUtil.nvl(String.valueOf(session.getAttribute("user_seq")));
		log.info("user_seq code : " + user_seq);
		
		if (CmmUtil.nvl(user_seq).equals("")) {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/index.do");
			
			return "/redirect";
		}
		
		return "/lib/libMain";
		
	}

}

package poly.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.util.CmmUtil;


@Controller
public class MainController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="index")
	public String Index() {
		log.info(this.getClass().getName() + "index start");
		
		return "/index";
	}
	
	@RequestMapping(value="doForm") 
	public String doForm(HttpServletRequest request, ModelMap model) {
		
		log.info(this.getClass().getName() + " doForm start");
		
		String query = CmmUtil.nvl(request.getParameter("bookName"));
		
		log.info("사용자가 입력한 쿼리 : " + query);
		
		model.addAttribute("bookName",query);
		
		return "/form_result";
	}
	
	
	
	@RequestMapping(value="form_result")
	public String form_result() {
		
		log.info(this.getClass().getName() + "form_result start");
		
		return "/form_result";
	}
	
	@RequestMapping(value="header")
	public String header() {
		log.info(this.getClass().getName() + "header start");
		
		return "/header";
	}
}

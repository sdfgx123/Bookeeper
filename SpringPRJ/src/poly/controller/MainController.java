package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="index")
	public String Index() {
		log.info(this.getClass().getName() + "index start");
		
		return "/index";
	}
	
	@RequestMapping(value="form_result")
	public String form_result() {
		
		log.info(this.getClass().getName() + "form_result start");
		
		return "/form_result";
	}
			
}

package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "my/")
public class MyController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = "MyMain")
	public String MyMain() throws Exception {
		log.info(this.getClass().getName() + " .MyMain start");
		
		return "/my/myMain";
		
	}
}

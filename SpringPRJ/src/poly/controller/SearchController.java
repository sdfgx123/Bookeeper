package poly.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.util.CmmUtil;

@Controller
@RequestMapping(value = "search/")
public class SearchController {

	private Logger log = Logger.getLogger(this.getClass());

	// 메인페이지 책 검색 결과 반환
	@RequestMapping(value = "doForm")
	public String doForm(HttpServletRequest request, ModelMap model) {

		log.info(this.getClass().getName() + " doForm start");

		String query = CmmUtil.nvl(request.getParameter("bookName"));

		log.info("사용자가 입력한 쿼리 : " + query);

		model.addAttribute("bookName", query);

		return "/search/searchMain";
	}

	@RequestMapping(value = "SearchDetail")
	public String SearchDetail(HttpServletRequest request, ModelMap model) {
		log.info(this.getClass().getName() + " SearchDetail start");
		
		String query = CmmUtil.nvl(request.getParameter("bookName"));
		
		log.info("query : " + query);
		
		model.addAttribute("bookName", query);
		
		return "/search/searchDetail";
	}
}

package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "notice/")
public class NoticeController {

	private Logger log = Logger.getLogger(this.getClass());
	
	// 영역 : Resource
	
	//공지사항 리스트
	@RequestMapping(value = "noticeList")
	public String noticeList() {
		
		return "/notice/noticeList";
	}
}

package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.NoticeDTO;
import poly.service.impl.NoticeService;

@Controller
@RequestMapping(value = "notice/")
public class NoticeController {

	private Logger log = Logger.getLogger(this.getClass());
	
	// 영역 : Resource
	@Resource(name = "NoticeService")
	private NoticeService noticeService;
	
	//공지사항 리스트
	@RequestMapping(value = "noticeList")
	public String noticeList(ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + " noticeList start");
		
		List<NoticeDTO> rList = noticeService.getNoticeList();
		
		if(rList == null) {
			rList = new ArrayList<NoticeDTO>();
		}
		
		model.addAttribute("rList", rList);
		
		rList = null;
		
		log.info(this.getClass().getName() + " noticeList start");
		
		return "/notice/noticeList";
	}
}

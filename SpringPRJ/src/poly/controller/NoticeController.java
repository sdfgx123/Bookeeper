package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.NoticeDTO;
import poly.service.impl.NoticeService;
import poly.util.CmmUtil;

@Controller
@RequestMapping(value = "notice/")
public class NoticeController {

	private Logger log = Logger.getLogger(this.getClass());
	
	// 영역 : Resource
	@Resource(name = "NoticeService")
	private NoticeService noticeService;
	
	// 공지사항 상세
	@RequestMapping(value = "NoticeInfo")
	public String NoticeInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		log.info(this.getClass().getName() + " NoticeInfo start");
		
		String nSeq = CmmUtil.nvl(request.getParameter("seq"));
		
		log.info("nSeq : " + nSeq);
		
		NoticeDTO pDTO = new NoticeDTO();
		pDTO.setSeq(nSeq);
		
		// 공지사항 내용 가져오기
		NoticeDTO rDTO = noticeService.getNoticeInfo(pDTO);
		
		if (rDTO == null) {
			rDTO = new NoticeDTO();

		}
		
		log.info("getNoticeInfo success!!!");
		log.info("title before revertXSS : " + rDTO.getPost_title());
		
		rDTO.setPost_content(CmmUtil.revertXSS(rDTO.getPost_content()));
		rDTO.setPost_title(CmmUtil.revertXSS(rDTO.getPost_title()));
		log.info("title before revertXSS : " + rDTO.getPost_title());
		
		model.addAttribute("rDTO", rDTO);
		
		rDTO = null;
		pDTO = null;
		
		log.info(this.getClass().getName() + " NoticeInfo end");
		
		return "/notice/noticeInfo";
	}
	
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
	
	// 공지사항 등록 화면 호출
	@RequestMapping(value = "NoticeForm")
	public String NoticeForm() throws Exception {
		
		log.info(this.getClass().getName() + " NoticeForm start");
		
		return "/notice/noticeForm";
	}
	
	// 공지사항 등록 프로세스
	@RequestMapping(value = "DoNoticeForm")
	public String DoNoticeForm(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + " DoNoticeForm start");
		
		String title = CmmUtil.nvl(request.getParameter("title"));
		String content = CmmUtil.nvl(request.getParameter("content"));
		
		// 0 : 실패, 1 : 성공
		int res = 0;
		String msg = "";
		String url = "/notice/noticeList.do";
		
		try {
			res = noticeService.insertNoticeInfo(title, content);
		} catch (Exception e) {
			msg = "공지사항 등록에 실패 하였습니다 : " + e.toString();
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName() + " DoNoticeForm end");
			msg = "공지사항 등록에 성공 하였습니다.";
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
		}
		return "/redirect";
	}
	
	// 공지사항 삭제
	@RequestMapping(value = "DeleteNoticeInfo")
	public String DeleteNoticeInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + " DeleteNoticeInfo start");
		
		String seq = CmmUtil.nvl(request.getParameter("seq"));
		log.info("seq : " + seq);
		
		int num = Integer.parseInt(seq);
		log.info("int num : " + num);
		
		int res = 0;
		
		String msg = "";
		String url = "/notice/noticeList.do";
		
		res = noticeService.deleteNoticeInfo(num);
		
		if (res > 0) {
			msg = "게시글 삭제에 성공 하였습니다.";
		} else {
			msg = "게시글 삭제에 실패 하였습니다. 잠시 후 다시 시도 하십시오.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		log.info(this.getClass().getName() + " DeleteNoticeInfo end");
		
		return "/redirect";
	}
}

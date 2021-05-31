package poly.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.LibDTO;
import poly.dto.UserDTO;
import poly.persistance.mongo.ILibMapper;
import poly.service.impl.LibService;
import poly.service.impl.UserService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

@Controller
@RequestMapping(value = "lib/")
public class LibController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "UserService")
	private UserService userService;
	
	@Resource(name = "LibService")
	private LibService libService;
	
	@Resource(name = "LibMapper")
	private ILibMapper libMapper;
	
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
		
		List<LibDTO> rList = libService.getBookInfo(id);
		log.info(rList);
		if (rList == null) {
			log.info("rList null");
            rList = new ArrayList<>();
        }
		log.info(this.getClass().getName() + " .MyMain end");
		
		model.addAttribute("rList", rList);
		
		return "/lib/libMain";
	}
	
	// 내 서재에 책 추가하기 기능
	@RequestMapping(value = "InsertBookInfo")
	public String InsertBookInfo(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		log.info(this.getClass().getName() + " .InsertBookInfo start");
		
		String id = CmmUtil.nvl((String) session.getAttribute("id"));
		log.info("id : " + id);
		
		List<LibDTO> pList = new ArrayList<>();
		
		String title = CmmUtil.nvl(request.getParameter("title"));
		String contents = CmmUtil.nvl(request.getParameter("contents"));
		String thumbnail = CmmUtil.nvl(request.getParameter("thumbnail"));
		String datetime = CmmUtil.nvl(request.getParameter("datetime"));
		String authors = CmmUtil.nvl(request.getParameter("authors"));
		String publisher = CmmUtil.nvl(request.getParameter("publisher"));
		
		log.info(title);
		log.info(contents);
		log.info(thumbnail);
		log.info(datetime);
		log.info(authors);
		log.info(publisher);
		
		LibDTO pDTO = new LibDTO();
		pDTO.setTitle(title);
		pDTO.setContents(contents);
		pDTO.setThumbnail(thumbnail);
		pDTO.setDatetime(datetime);
		pDTO.setAuthors(authors);
		pDTO.setPublisher(publisher);
		
		pList.add(pDTO);
		
		String colNm = id + "_library";
		libMapper.insertBook(pList, colNm);
		String url="/index.do";
		String msg="내 서재에 추가 하였습니다.";
		log.info(this.getClass().getName() + " .InsertBookInfo end");
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		
		return "/redirect";
		
	}
	
	@RequestMapping(value = "LibDetail")
	public String LibDetail(HttpSession session, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + " .LibDetail start");
		String id = CmmUtil.nvl((String) session.getAttribute("id"));
		log.info("id : " + id);
		String _id = CmmUtil.nvl(request.getParameter("_id"));
		log.info("_id" + _id);
		List<LibDTO> rList = libService.getBookDetail(id, _id);
		if (rList == null) {
			log.info("rList null");
            rList = new ArrayList<>();
        }
		log.info(this.getClass().getName() + " .LibDetail end");
		model.addAttribute("rList", rList);
		return "/lib/libDetail";
	}
	
}

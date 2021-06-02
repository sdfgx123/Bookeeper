package poly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.LibDTO;
import poly.dto.UserDTO;
import poly.persistance.mapper.IMemoMapper;
import poly.persistance.mongo.ILibMapper;
import poly.service.IMemoService;
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
	
	@Resource(name = "MemoMapper")
	private IMemoMapper memoMapper;
	
	@Resource(name = "MemoService")
	private IMemoService memoService;
	
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
		String isbn = CmmUtil.nvl(request.getParameter("isbn"));
		
		log.info(title);
		log.info(contents);
		log.info(thumbnail);
		log.info(datetime);
		log.info(authors);
		log.info(publisher);
		log.info(isbn);
		
		LibDTO pDTO = new LibDTO();
		pDTO.setTitle(title);
		pDTO.setContents(contents);
		pDTO.setThumbnail(thumbnail);
		pDTO.setDatetime(datetime);
		pDTO.setAuthors(authors);
		pDTO.setPublisher(publisher);
		pDTO.setIsbn(isbn);
		
		pList.add(pDTO);
		
		String colNm = id + "_library";
		libMapper.insertBook(pList, colNm);
		int res = memoService.initMemo(colNm, isbn);
		String url="/index.do";
		String msg="내 서재에 추가 하였습니다.";
		log.info(this.getClass().getName() + " .InsertBookInfo end");
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		
		return "/redirect";
		
	}
	
	//책 상세 jsp 호출
	@RequestMapping(value = "LibDetail")
	public String LibDetail(HttpSession session, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + " .LibDetail start");
		String id = CmmUtil.nvl((String) session.getAttribute("id"));
		log.info("id : " + id);
		String isbn = CmmUtil.nvl(request.getParameter("isbn"));
		log.info("isbn" + isbn);
		String colNm = id + "_library";
		String memo = memoService.getMemo(colNm, isbn);
		log.info("memo :" + memo);
		List<LibDTO> rList = libService.getBookDetail(id, isbn);
		if (rList == null) {
			log.info("rList null");
            rList = new ArrayList<>();
        }
		log.info(this.getClass().getName() + " .LibDetail end");
		model.addAttribute("rList", rList);
		model.addAttribute("memo", memo);
		return "/lib/libDetail";
	}
	
	@RequestMapping(value = "MemoForm")
	public String MemoForm(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) throws Exception {
		log.info(this.getClass().getName() + " .MemoForm start");
		String isbn = CmmUtil.nvl(request.getParameter("isbn"));
		log.info("Memo Form 실행할 때의 isbn : " + isbn);
		String id = CmmUtil.nvl((String) session.getAttribute("id"));
		log.info("id : " + id);
		model.addAttribute("isbn", isbn);
		return "/lib/memoForm";
	}
	
	@RequestMapping(value = "DoMemoForm")
	public String DoMemoForm(HttpServletRequest request, ModelMap model, HttpServletResponse response, HttpSession session) throws Exception {
		log.info(this.getClass().getName() + " .DoMemoForm start");
		List<LibDTO> pList = new ArrayList<>();
		String isbn = request.getParameter("isbn");
		String memo = CmmUtil.nvl(request.getParameter("memo"));
		String id = CmmUtil.nvl((String) session.getAttribute("id"));
		log.info("isbn : " + isbn);
		log.info("memo : " + memo);
		log.info("id : " + id);
		LibDTO pDTO = new LibDTO();
		pDTO.setMemo(memo);
		pList.add(pDTO);
		String colnm = id + "_library";
		int res = 0;
		res = memoService.insertMemo(colnm, isbn, memo);
		if (res==0) {
			String url="/index.do";
			String msg="오류가 발생 하였습니다. 잠시 후 다시 시도 하십시오.";
			log.info(this.getClass().getName() + " .InsertBookInfo end");
			model.addAttribute("url", url);
			model.addAttribute("msg", msg);
			return "/redirect";
		}
		String url="/index.do";
		String msg="메모 추가에 성공 하였습니다.";
		log.info(this.getClass().getName() + " .InsertBookInfo end");
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		return "/redirect";
		
	}
	
	@RequestMapping(value = "EditMemoForm")
	public String EditMemoForm(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws Exception {
		log.info(this.getClass().getName() + " .EditMemoForm start");
		String isbn = CmmUtil.nvl(request.getParameter("isbn"));
		log.info("isbn : " + isbn);
		String id = CmmUtil.nvl((String) session.getAttribute("id"));
		log.info("id : " + id);
		String colNm = id + "_library";
		log.info("colNm : " + colNm);
		String memo = memoService.getMemo(colNm, isbn);
		log.info("EditMemoForm에서 getMemo 타고 와서 가져온 메모 : " + memo);
		model.addAttribute("memo", memo);
		model.addAttribute("isbn", isbn);
		return "/lib/editMemoForm";
	}
	
	@RequestMapping(value = "DoEditMemoForm")
	public String DoEditMemoForm(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		log.info(this.getClass().getName() + " .DoEditMemoForm start");
		String memo = CmmUtil.nvl(request.getParameter("memo"));
		String isbn = CmmUtil.nvl(request.getParameter("isbn"));
		String id = CmmUtil.nvl((String) session.getAttribute("id"));
		log.info("memo : " + memo);
		log.info("isbn : " + isbn);
		String colnm = id + "_library";
		int res = 0;
		res = memoService.insertMemo(colnm, isbn, memo);
		if (res==0) {
			String url="/index.do";
			String msg="오류가 발생 하였습니다. 잠시 후 다시 시도 하십시오.";
			log.info(this.getClass().getName() + " .InsertBookInfo end");
			model.addAttribute("url", url);
			model.addAttribute("msg", msg);
			return "/redirect";
		}
		String url="/index.do";
		String msg="메모 수정에 성공 하였습니다.";
		log.info(this.getClass().getName() + " .DoEditMemoForm end");
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		return "/redirect";
	}
	
}

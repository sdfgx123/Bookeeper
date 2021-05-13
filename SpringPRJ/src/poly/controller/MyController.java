package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.UserDTO;
import poly.service.impl.UserService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Controller
@RequestMapping(value = "my/")
public class MyController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "UserService")
	private UserService userService;
	
	// 마이페이지 메인
	@RequestMapping(value = "MyMain")
	public String MyMain(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws Exception {
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
		
		return "/my/myMain";
	}
	
	// 회원정보 수정 JSP
	@RequestMapping(value = "UserEdit")
	public String UserEdit(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		
		log.info(this.getClass().getName() + " .UserEdit start");
		
		String seq = request.getParameter("user_seq");
		int user_seq = Integer.parseInt(seq);
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUser_seq(user_seq);
		
		uDTO = userService.getUserEditInfo(uDTO);
		model.addAttribute("uDTO", uDTO);
		
		return "/my/editUserInfo";
		
	}
	
	// 회원정보 수정 처리
	@RequestMapping(value = "DoUserEdit")
	public String DoUserEdit(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + " .DoUserEdit start");
		
		
		
		String user_name = request.getParameter("user_name");
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		String user_tel = request.getParameter("user_tel");
		String regdate = request.getParameter("regdate");
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUser_name(user_name);
		uDTO.setEmail(email);
		uDTO.setId(id);
		uDTO.setUser_tel(user_tel);
		uDTO.setRegdate(regdate);
		
		int res = 0;
		
		try {
			res = userService.updateUser(uDTO);
		} catch (Exception e) {
			log.info(e.toString());
		}
		
		String msg = "";
		String url = "/my/MyMain.do?id=" + id;
		
		if (res == 0) {
			msg = "회원정보 수정에 실패 하였습니다. 다시 시도해 주십시오.";
		} else {
			msg = "회원정보 수정에 성공 하였습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		log.info(this.getClass().getName() + " .DoUserEdit end");
		
		return "/redirect";
	}
	
	@RequestMapping(value = "DeleteUserInfo")
	public String DeleteUserInfo() throws Exception {
		
		log.info(this.getClass().getName() + " .DeleteUserInfo start");
		
		return "/my/deleteUserInfo";
		
	}
	
	@RequestMapping(value = "DoDeleteUserInfo")
	public String DoDeleteUserInfo(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + " .DoDeleteUserInfo start");
		
		String id = CmmUtil.nvl(request.getParameter("id"));
		String pw = CmmUtil.nvl(request.getParameter("password"));
		
		log.info("id : " + id);
		log.info("pw : " + pw);
		
		pw = EncryptUtil.encHashSHA256(pw);
		log.info("암호화한 pw : " + pw);
		
		int res = 0;
		
		String msg = "";
		String url = "/index.do";
		
		res = userService.deleteUserInfo(id, pw);
		
		if (res > 0) {
			msg = "회원 탈퇴에 성공 하였습니다. Bookeeper를 이용해 주셔서 감사합니다.";
			session.invalidate();
		} else {
			msg = "회원 탈퇴에 실패 하였습니다. 잠시 후 다시 시도하여 주십시오.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		log.info(this.getClass().getName() + " .DoDeleteUserInfo end");
		
		return "/redirect";
	}
}

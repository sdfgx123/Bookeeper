package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import poly.dto.MailDTO;
import poly.dto.UserDTO;
import poly.service.IMailService;
import poly.service.IUserService;
import poly.service.impl.MailService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@EnableWebMvc
@Controller
@RequestMapping(value = "user/")
public class UserController {

	private Logger log = Logger.getLogger(this.getClass());
	
	// 영역 : Resource
	@Resource(name = "UserService")
	private IUserService userService;
	
	@Resource(name = "MailService")
	private IMailService mailService;
	
	// 영역 : 로그인 관련
	
	// 로그인
	@RequestMapping(value = "userLogin")
	public String UserLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		log.info("userLogin start");
		
		String error = CmmUtil.nvl(request.getParameter("error"));
		
		// error가 1이면 아이디 또는 비밀번호 틀림
		log.info("error : " + error);
		
		//에러가 1이면 error 변수에 1을 넣은 모델 객체를 만들어라
		if(error.equals("1")) {
			model.addAttribute("error", "1");
		}
		
		log.info(this.getClass());
		
		return "/user/userLogin";
	}
	
	// 유저 회원가입
	@RequestMapping(value = "userRegister")
	public String UserRegister() {
		
		return "/user/userRegister";
	}
	
	//아이디, 이메일 중복 확인
	@ResponseBody
	@RequestMapping(value = "DupCheck")
	public String DupCheck(HttpServletRequest request) throws Exception {
		
		log.info(this.getClass().getName() + " .DupCheck start");
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		log.info("id : " + id);
		log.info("email : " + email);
		
		int result = 0;
		
		UserDTO uDTO = null;
		
		// 아이디 중복확인 또는 이메일 중복확인인지 확인
		if (id != null) {
			uDTO = userService.checkID(id);
		} else {
			uDTO = userService.checkEmail(email);
		}
		
		// 중복된 아이디 또는 이메일 발견
		if (uDTO != null) {
			result = 1;
		}
		
		return Integer.toString(result);
	}
	
	// 유저 회원가입 프로세스
	@RequestMapping(value = "UserRegProc")
	public String userRegProc(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute UserDTO uDTO) throws Exception {
		
		log.info(this.getClass().getName() + " .userRegProc start");
		
		uDTO.setUser_type("0");
		
		int result = 0;
		
		try {
			result = userService.regUser(uDTO);
		} catch(Exception e) {
			log.info(e.toString());
		}
		
		String msg = "";
		String url = "/user/userLogin.do";
		
		if (result == 0) {
			msg = "가입에 실패 하였습니다.";
		} else {
			MailDTO mDTO = new MailDTO();
			mDTO.setTitle("Bookeeper 이메일 인증 요청");
			mDTO.setToMail(uDTO.getEmail());
			StringBuilder content = new StringBuilder();
			content.append("아래 링크를 클릭하시면 이메일 인증이 완료됩니다.\n");
			content.append("http://localhost:8080/user/VerifyEmail.do?code=");
			String id = uDTO.getId();
			String code = EncryptUtil.encAES128CBC(id + ",1");
			content.append(code);
			
			mDTO.setContents(content.toString());
			mailService.doSendMail(mDTO);
			
			msg = "가입 신청이 완료 되었습니다. 이메일 인증 메일을 확인해 주십시오.";
		}
		
		log.info(this.getClass().getName() + " .userRegProc end");
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "/redirect";
	}
	
	@RequestMapping(value = "VerifyEmail")
	public String VerifyEmail(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + " .VerifyEmail start");
		
		String[] decoded;
		
		try {
			String code = request.getParameter("code").replaceAll(" ", "+");
			decoded = EncryptUtil.decAES128CBC(code).split(",");
		} catch (Exception e) {
			model.addAttribute("msg", "잘못된 URL 입니다.");
			model.addAttribute("url", "/user/userLogin.do");
			return "/redirect";
		}
		
		int res = 0;
		
		try {
			res = userService.verifyEmail(decoded[0], decoded[1]);
		} catch (Exception e) {
			log.info(e.toString());
			model.addAttribute("msg", "잘못된 접근 입니다.");
			model.addAttribute("url", "/user/userLogin.do");
			return "/redirect";
		}
		
		if (res>0) {
			model.addAttribute("msg", "이메일 인증에 성공 하였습니다.");
			model.addAttribute("url", "/user/userLogin.do");
		} else {
			model.addAttribute("msg", "이메일 인증에 실패 하였습니다. 잠시 후 다시 시도 하십시오.");
			model.addAttribute("url", "/user/userLogin.do");
		}
		
		log.info(this.getClass().getName() + " .VerifyEmail end");
		return "/redirect";
	}
	
	//로그인 처리 : 아이디, 비밀번호 검증
	@ResponseBody
	@RequestMapping(value = "LoginTest", method = RequestMethod.POST, produces="application/text; charset=UTF8")
	public String loginTest(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session, @ModelAttribute UserDTO uDTO) throws Exception {
		
		log.info(this.getClass().getName() + " .loginTest start");
		
		uDTO = userService.loginProc(uDTO);
		
		log.info("uDTO null 여부 판단 : " + uDTO);
		
		// 아이디 또는 암호 틀렸을 경우
		if (uDTO == null) {
			return "1";
		}
		
		// 이메일 미인증
		if (uDTO.getUser_state() == 0) {
			return "2";
		}
		
		log.info("세션 부여 전까지 통과");
		session.setAttribute("user_seq", uDTO.getUser_seq());
		session.setAttribute("user_type", uDTO.getUser_type());
		session.setAttribute("user_state", uDTO.getUser_state());
		
		log.info("session : " + session.getAttribute("user_seq"));
		
		return "0";
	}
	
}

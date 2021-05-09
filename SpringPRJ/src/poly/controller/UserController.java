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

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import poly.dto.MailDTO;
import poly.dto.UserDTO;
import poly.service.IMailService;
import poly.service.IUserService;
import poly.service.impl.MailService;
import static poly.util.CmmUtil.nvl;

import java.text.SimpleDateFormat;
import java.util.Date;

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

	// 비밀번호 초기화 폼
	@RequestMapping(value = "RecoverPwForm")
	public String RecoverPwForm(HttpServletRequest request, ModelMap model) throws Exception {
		log.info(this.getClass().getName() + " .RecoverPwForm start");

		String code = request.getParameter("code").replaceAll(" ", "+");
		log.info("code : " + code);

		try {

			String[] decoded = EncryptUtil.decAES128CBC(code).split(",");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");

			Date d = sdf.parse(decoded[0]);
			String id = decoded[1];
			Date now = new Date();

			log.info("link date : " + sdf.format(d));
			log.info("now : " + sdf.format(now));

			if (d.compareTo(now) > 0) {
				log.info("시간지남");
				model.addAttribute("msg", "만료된 초기화 코드입니다.");
				model.addAttribute("url", "/index.do");
				return "/redirect";
			}

			int res = 0;
			try {
				res = userService.verifyPwFind(id);
			} catch (Exception e) {

				model.addAttribute("msg", "비정상적인 접근입니다.");
				model.addAttribute("url", "/index.do");
				return "/redirect";
			}

			if (res == 0) {
				log.info("초기화요청한적없음");
				model.addAttribute("msg", "만료된 초기화 코드입니다.");
				model.addAttribute("url", "/index.do");
				return "/redirect";
			}

			model.addAttribute("code", code);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "유효하지 않은 코드입니다.");
			model.addAttribute("url", "/index.do");
			return "/redirect";
		}
		
		return "/user/recoverPwForm";

	}


	@RequestMapping(value = "RecoverPwFormProc")
	public String RecoverPwFormProc(HttpServletRequest request, ModelMap model) throws Exception {
		log.info(this.getClass().getName() + " .RecoverPwFormProc start");
		
		String code = request.getParameter("code").replaceAll(" ", "+");
		log.info("code : " + code);
		String password = request.getParameter("password");
		String id = null;
		
		try {
			String[] decoded = EncryptUtil.decAES128CBC(code).split(",");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");

			Date d = sdf.parse(decoded[0]);
			id = decoded[1];

			Date now = new Date();

			if (d.compareTo(now) > 0) {
				model.addAttribute("msg", "만료된 초기화 코드입니다.");
				model.addAttribute("url", "/index.do");
				return "/redirect";
			}
			
			int res = 0;
			try {
				res = userService.verifyPwFind(id);
			} catch (Exception e) {
				model.addAttribute("msg", "비정상적인 접근입니다.");
				model.addAttribute("url", "/index.do");
				return "/redirect";
			}
			
			if(res==0) {
				model.addAttribute("msg", "만료된 초기화 코드입니다.");
				model.addAttribute("url", "/index.do");
				return "/redirect";
			}
			
			model.addAttribute("code", code);
			
			
		} catch (Exception e) {
			model.addAttribute("msg", "유효하지 않은 코드입니다.");
			model.addAttribute("url", "/index.do");
			return "/redirect";
		}
		
		int result;
		// 히새 암호화된 암호를 찾아서 새 암호로 엎어씌움
		result = userService.recoverPwProc(id, password);
		
		model.addAttribute("title", "암호 초기화");
		model.addAttribute("findType", "pwProc");
		String msg;
		String status;
		if(result>0) {
			msg = "암호 초기화에 성공하였습니다.";
			status = "0";
		}else {
			msg = "암호 초기화에 실패하였습니다.";
			status = "1";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("status", status);
		
		return "/user/findResult";
		
		
	}
	
	@RequestMapping(value = "FindPwProc")
	public String findPwProc(HttpServletRequest request, ModelMap model) throws Exception {
		log.info(this.getClass().getName() + " .FindPwProc start");

		UserDTO uDTO = new UserDTO();
		uDTO.setId(request.getParameter("id"));

		// uDTO에 암호화된 패스워드와 이메일 불러옴
		uDTO = userService.recoverPw(uDTO);

		model.addAttribute("title", "비밀번호 초기화");
		model.addAttribute("findType", "pw");

		// 없을 경우
		if (uDTO == null) {
			model.addAttribute("msg", "해당 아이디는 존재하지 않습니다.");
			model.addAttribute("status", "1");
			return "/user/FindResult";

			// 있을 경우
		} else {
			MailDTO mDTO = new MailDTO();
			mDTO.setTitle("Bookeeper 비밀번호 초기화 링크");
			mDTO.setToMail(uDTO.getEmail());
			StringBuilder content = new StringBuilder();
			content.append("아래 링크를 클릭하시면 암호 초기화 페이지로 이동합니다.\n");
			content.append("http://localhost:8080/user/RecoverPwForm.do?code=");
			content.append(uDTO.getPassword());
			mDTO.setContents(content.toString());
			int res = mailService.doSendMail(mDTO);
			if (res > 0) {
				String email = uDTO.getEmail();
				String[] splitEmail = email.split("@");
				String id = splitEmail[0];
				String domain = splitEmail[1];

				// 아이디 가리기
				String censoredId = id.substring(0, 2);
				if (id.length() <= 6) {
					for (int i = 2; i < id.length(); i++) {
						censoredId += "*";
					}
				} else {
					for (int i = 2; i < id.length() - 2; i++) {
						censoredId += "*";
					}
					censoredId += id.substring(id.length() - 2, id.length());
					censoredId += id.substring(censoredId.length(), id.length());

				}

				String censoredEmail = censoredId + "@" + domain;
				StringBuilder msg = new StringBuilder("아래 이메일로 초기화 링크를 보내드렸습니다:<br>");
				msg.append(censoredEmail);
				msg.append("<br>");
				msg.append("암호 초기화 링크는 20분간 유효합니다.");

				model.addAttribute("msg", msg.toString());
				model.addAttribute("status", "0");
			}

			return "/user/findResult";
		}

	}

	// 비밀번호 찾기 jsp
	@RequestMapping(value = "FindPw")
	public String FindPw() throws Exception {
		log.info(this.getClass().getName() + " .FindPw start");

		return "/user/findPw";
	}

	// 아이디 찾기
	@RequestMapping(value = "FindIDProc")
	public String FindIDProc(HttpServletRequest request, ModelMap model) throws Exception {
		log.info(this.getClass().getName() + " .FindIDProc start");

		String email = request.getParameter("email");
		String id = userService.findUserID(email);
		model.addAttribute("title", "아이디 찾기 결과");
		model.addAttribute("findType", "id");

		// status : 0 성공, 1 실패

		if (id == null) {
			model.addAttribute("msg", "해당 이메일로 가입된 아이디가 없습니다.");
			model.addAttribute("status", "1");
			return "/user/findResult";
		} else {
			String censoredId = id.substring(0, 2);
			if (id.length() <= 6) {
				for (int i = 2; i < id.length(); i++) {
					censoredId += "*";
				}
			} else {
				for (int i = 2; i < id.length() - 2; i++) {
					censoredId += "*";
				}
				censoredId += id.substring(id.length() - 2, id.length());
				censoredId += id.substring(censoredId.length(), id.length());
			}
			model.addAttribute("msg", "해당 이메일로 가입된 아이디는<br>" + censoredId + " 입니다.");
			model.addAttribute("status", "0");
			model.addAttribute("id");
			return "/user/findResult";
		}
	}

	@RequestMapping(value = "FindID")
	public String FindID() throws Exception {
		log.info(this.getClass().getName() + " .FindID");
		return "/user/findID";
	}

	// 로그인 프로세스 : 확정
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	@ResponseBody
	public String doLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model)
			throws Exception {
		log.info(this.getClass().getName() + " .doLogin start");

		String pw = nvl(request.getParameter("pw"));
		String id = nvl(request.getParameter("id"));
		log.info("id : " + id);
		log.info("pw : " + pw);
		pw = EncryptUtil.encHashSHA256(pw);
		log.info("암호화한 pw : " + pw);
		UserDTO rDTO = userService.checkLogin(id, pw);
		if (rDTO == null) {
			log.info("null");
			log.info(this.getClass().getName() + " .doLogin end");
			return "1";
		}
		log.info("user_seq : " + rDTO.getUser_seq());
		session.setAttribute("user_seq", rDTO.getUser_seq());
		session.setAttribute("user_type", rDTO.getUser_type());
		session.setAttribute("id", rDTO.getId());

		log.info("session identification : " + session.getAttribute("id"));
		log.info(this.getClass().getName() + " .doLogin end");
		return "0";

	}

	// 로그인 페이지 호출
	@RequestMapping(value = "userLogin")
	public String UserLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		log.info("userLogin start");

		String error = nvl(request.getParameter("error"));

		// error가 1이면 아이디 또는 비밀번호 틀림
		log.info("error : " + error);

		// 에러가 1이면 error 변수에 1을 넣은 모델 객체를 만들어라
		if (error.equals("1")) {
			model.addAttribute("error", "1");
		}

		log.info(this.getClass());

		return "/user/userLogin";
	}

	// 로그아웃
	@RequestMapping(value = "logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model)
			throws Exception {
		log.info(this.getClass().getName() + " .logout start");

		session.invalidate();
		String url = "/index.do";
		String msg = "로그아웃 하였습니다.";
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);

		log.info(this.getClass().getName() + " .logout end");

		return "/redirect";
	}

	// 유저 회원가입
	@RequestMapping(value = "userRegister")
	public String UserRegister() {

		return "/user/userRegister";
	}

	// 아이디, 이메일 중복 확인
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
	public String userRegProc(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		log.info(this.getClass().getName() + " .userRegProc start");

		UserDTO uDTO = new UserDTO();

		String user_name = CmmUtil.nvl(request.getParameter("user_name"));
		String id = CmmUtil.nvl(request.getParameter("id"));
		String email = CmmUtil.nvl(request.getParameter("email"));
		String user_tel = CmmUtil.nvl(request.getParameter("user_tel"));
		String password = CmmUtil.nvl(request.getParameter("password"));

		log.info(user_name);
		log.info(id);
		log.info(email);
		log.info(user_tel);
		log.info(password);

		password = EncryptUtil.encHashSHA256(password);
		log.info(password);

		uDTO.setUser_name(user_name);
		uDTO.setId(id);
		uDTO.setEmail(email);
		uDTO.setUser_tel(user_tel);
		uDTO.setPassword(password);

		uDTO.setUser_type("0");

		int result = 0;

		try {
			result = userService.regUser(uDTO);
		} catch (Exception e) {
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
			id = uDTO.getId();
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
	public String VerifyEmail(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

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

		if (res > 0) {
			model.addAttribute("msg", "이메일 인증에 성공 하였습니다.");
			model.addAttribute("url", "/user/userLogin.do");
		} else {
			model.addAttribute("msg", "이메일 인증에 실패 하였습니다. 잠시 후 다시 시도 하십시오.");
			model.addAttribute("url", "/user/userLogin.do");
		}

		log.info(this.getClass().getName() + " .VerifyEmail end");
		return "/redirect";
	}

	// 로그인 처리 : 아이디, 비밀번호 검증
	@ResponseBody
	@RequestMapping(value = "LoginTest", method = RequestMethod.POST, produces = "application/text; charset=UTF8")
	public String loginTest(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			HttpSession session, @ModelAttribute UserDTO uDTO) throws Exception {

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

package co.smartooth.customer.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import co.smartooth.customer.service.CustomerMailAuthService;
import co.smartooth.customer.service.CustomerUserService;
import co.smartooth.web.utils.AES256Util;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2023. 08. 17
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class CustomerMailAuthController {


	@Autowired(required = false)
	private CustomerMailAuthService customerMailAuthService;

	
	@Autowired(required = false)
	private CustomerUserService customerUserService;
	
	
	
	/**
	 * 기능   : 인증 메일 발송 (이메일 유효성 검사)
	 * 작성자 : 정주현 
	 * 작성일 : 2023. 07. 03
	 * 수정일 : 2023. 08. 04
	 */
	@PostMapping(value = {"/customer/user/signUp/emailAuth.do"})
	@ResponseBody
		public HashMap<String,Object> mailAuth(@RequestBody HashMap<String, String> paramMap) {

		HashMap<String,Object> hm = new HashMap<String,Object>();
		int isExistEmail = 0;

		// Parameter :: userId 값 검증
		String userId = (String)paramMap.get("userId");
		// (Null 체크 및 공백 체크)
		if(userId == null || userId.equals("")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter.");
			return hm;
		}
		
		try {
			// 아이디 중복 체크 :: 0 일 경우, 아이디가 존재 하지 않는 경우, 0이 아닌 경우 아이디가 존재
			isExistEmail = customerUserService.isExistId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(isExistEmail == 0) { // 아이디가 존재하지 않는 경우, 메일 발송
			try {
				
				customerMailAuthService.sendMail(userId);
				customerMailAuthService.updateAuthStatusN(userId);
				
			} catch (Exception e) {
				hm.put("code", "500");
				// hm.put("msg", "Server Error");
				hm.put("msg", "서버 에러가 발생했습니다.\\n관리자에게 문의해주시기 바랍니다.");
				e.printStackTrace();
				return hm;
			}
			hm.put("code", "000");
			 hm.put("msg", "메일 발송이 완료되었습니다.");
			// hm.put("msg", "Mail Sent Completed.");
			
		}else {
			hm.put("code", "402");
			hm.put("msg", "해당 이메일은 이미 등록되어 있는 이메일입니다.");			
			// hm.put("msg", "This ID is already registered.");			
		}
		return hm;
	}
	
	
	
	/**
	 * 기능   : 인증 메일 URL 클릭 후 메일 인증 진행
	 * 작성자 : 정주현 
	 * 작성일 : 2023. 07. 03
	 */
	@GetMapping(value = {"/customer/user/signUp/emailConfirm.do"})
	@ResponseBody
	public void signUpConfirm(@RequestParam Map<String, String> paramMap) throws Exception {

		// userId(email), authKey 가 일치할경우 authStatus 업데이트
		String userId = paramMap.get("userId");
		String authKey = paramMap.get("authKey");
		String decId = "";
		String decEmailAuthKey = "";
		
		// 아이디와 인증번호 복호화
		AES256Util aes256Util = new AES256Util();
		decEmailAuthKey = aes256Util.aesDecode(authKey);
		decId = aes256Util.aesDecode(userId);
		
		// 인증 메일 상태 'Y'로 변경
		customerMailAuthService.updateAuthStatusY(decId, decEmailAuthKey);

	}
	
	
	
	/**
	 * 기능   : 앱에서 메일 인증 여부 확인
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 07. 03
	 */
	@PostMapping(value = {"/customer/user/signUp/emailAuthChk.do"})
	@ResponseBody
	public HashMap<String,Object> emailAuthChk(@RequestBody Map<String, String> paramMap) throws Exception {

		HashMap<String,Object> hm = new HashMap<String,Object>();
		String authStatusYn = "";
		String userId = paramMap.get("userId");
		
		if(userId == null || userId.equals("")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter.");
			return hm;
		}
		
		// 사용자의 메일 인증 여부 확인
		authStatusYn = customerMailAuthService.isEmailAuthEnabled(userId);
		// Null 체크
		if(authStatusYn != null && !authStatusYn.equals("")) {
			if(authStatusYn.equals("Y")) { // Y일 경우 인증 완료
				hm.put("code", "000");
				hm.put("msg", "인증 완료");
			}else {  // N일 경우 인증 실패
				hm.put("code", "403");
				hm.put("msg", "인증 실패\n이메일 인증을 다시 시도해주시기 바랍니다.");
			}
		}else {
			hm.put("code", "405");
			hm.put("msg", "해당 아이디가 없거나 인증을 요청하지 않은 아이디입니다.");
		}
		return hm;
	}
}

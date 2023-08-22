package co.smartooth.customer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.smartooth.customer.service.CustomerMailAuthService;
import co.smartooth.customer.service.CustomerTeethService;
import co.smartooth.customer.service.CustomerUserService;
import co.smartooth.customer.vo.CustomerTeethInfoVO;
import co.smartooth.customer.vo.CustomerUserVO;
import co.smartooth.web.utils.AES256Util;
import co.smartooth.web.utils.JwtTokenUtil;

/**
 * 작성자 : 정주현
 * 작성일 : 2023. 06. 28
 * 수정일 : 2023. 08. 17
 * 서버분리 : 2023. 08. 01
 */
@Controller
public class CustomerUserController {

	@Value("${loginUrl}")
	private String loginUrl;

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired(required = false)
	private CustomerUserService customerUserService;

	@Autowired(required = false)
	private CustomerTeethService customerTeethService;

	@Autowired(required = false)
	private CustomerMailAuthService customerMailAuthService;

	
	private static boolean tokenValidation = false;
	
	
	
	
	/**
	 * 기능   : 아이디/이메일 중복 확인
	 * 작성자 : 정주현 
	 * 작성일 : 2023. 06. 28
	 * 수정일 : 2023. 08. 22
	 */
	@PostMapping(value = {"/customer/user/duplicateChkId.do"})
	@ResponseBody
	public HashMap<String,Object> duplicateChkId(@RequestBody HashMap<String, String> paramMap){

		
		logger.debug("========== Customer.UserController ========== duplicateChkId.do ==========");
		logger.debug("========== Customer.UserController ========== duplicateChkId.do ==========");
		logger.debug("========== Customer.UserController ========== duplicateChkId.do ==========");
		logger.debug("========== Customer.UserController ========== duplicateChkId.do ==========");
		
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		int isExistId = 0;

		String userId = (String)paramMap.get("userId");
		// Parameter = userEmail 값 검증 (Null 체크 및 공백 체크)
		if(userId == null || userId.equals("")) {
			hm.put("code", "401");
			hm.put("msg", "아이디(이메일)가 전달되지 않았습니다.");
			return hm;
		}
		// 하드코딩
		userId = "KRPA__________01-"+userId;

		try {
			// 조회 앱 아이디(이메일) 등록 여부 확인 (0: 없음, 1: 있음)
			isExistId =  customerUserService.isExistId(userId);
			
			if(isExistId > 0) {
				hm.put("code", "402");
				hm.put("msg", "해당 아이디는 이미 등록되어 있습니다.");
			}else {
				hm.put("code", "000");
				hm.put("msg", "등록이 가능한 아이디입니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("code", "500");
			hm.put("msg", "Server Error.");
		}
		
		
		return hm;
	}
	
	
	
	/**
	 * 기능   : 회원가입 API
	 * 작성자 : 정주현 
	 * 작성일 : 2023. 06. 28
	 * 가입단계에서 에러가 발생할 경우 모든 것을 Rollback 처리
	 */
	@PostMapping(value = { "/customer/user/register.do" })
	@ResponseBody
	@Transactional
	public HashMap<String,Object> register(@RequestBody HashMap<String, Object> paramMap) throws Exception{
		
		
		logger.debug("========== Customer.UserCountroller ========== /customer/user/register.do ==========");
		logger.debug("========== Customer.UserCountroller ========== /customer/user/register.do ==========");
		logger.debug("========== Customer.UserCountroller ========== /customer/user/register.do ==========");
		logger.debug("========== Customer.UserCountroller ========== /customer/user/register.do ==========");
		
		
		//회원 아이디 - email
		String userId = null;
		// 회원 비밀번호
		String userPwd = null;
		// 회원 타입
		String userType = null;
		// 회원 이름
		String userName = null;
		// 회원 이메일
		String userEmail = null;
		
		// 회원 생일
		String userBirthday = null;
		// 회원 거주 - 국
		String userCountry = null;
		// 회원 거주 - 주
		String userState = null;
		// 회원 전화번호
		String userTelNo = null;
		// 회원 성별
		String userSex = null;
		// 푸쉬토큰
		String pushToken = null;
		// 회원 치아 상태
		String teethStatus = null;
		
		// 암호화 된 아이디
		//String encodeUserId = null;
		// 암호화 된 비밀번호
		//String encodeUserPwd = null;
		// 암호화 된 이름
		//String encodeUserName = null;

		// 회원 정보 VO
		CustomerUserVO customerUserVO = new CustomerUserVO();
		// 치아 정보 VO
		CustomerTeethInfoVO customerTeethInfoVO = new CustomerTeethInfoVO();
		// 고객 맵핑 정보 VO
		//CustomerMappingInfoVO customerMappingInfoVO = new CustomerMappingInfoVO();  
		// return 값
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		
		try {
			
			// 암호화 메소드
			AES256Util aes256Util = new AES256Util();

			// 아이디 암호화
			userId = (String)paramMap.get("userId");
			//userId = aes256Util.aesEncode(userId);
			
			// 회원 이메일
			userEmail = userId;
			
			// 비밀번호 암호화
			userPwd = (String)paramMap.get("userPwd");
			userPwd = aes256Util.aesEncode(userPwd);
			
			// 이름 암호화
			userName = (String)paramMap.get("userName");
			//userName = aes256Util.aesEncode(userName);
			
			// 회원 종류
			userType = (String)paramMap.get("userType");
			if(userType == null || "".equals(userType)) {
				userType = "PA";
			}
			
			// 회원 거주 국
			userCountry = (String)paramMap.get("userCountry");
			if(userCountry == null || "".equals(userCountry)) {
				userCountry = "KR";
			}
			
			// 회원 거주 주
			userState = (String)paramMap.get("userState");
			if(userState == null || "".equals(userState)) {
				userState = "";
			}
			
			// 정확히 정해야 하는 부분 GR은 일반인데, 현재 일반앱에서도 GR을 사용중
			// KRPA__________01-jhjung@smartooth.co
			// 밑줄은 10개
			userId = userCountry+userType+"__________01-"+userEmail;		
			
			
			// 회원 생일
			userBirthday = (String)paramMap.get("userBirthday");
			// 회원 전화번호
			userTelNo = (String)paramMap.get("userTelNo");
			// 회원 성별
			userSex = (String)paramMap.get("userSex");
			// 푸쉬토큰
			pushToken = (String)paramMap.get("pushToken");
			// 치아 상태
			teethStatus = (String)paramMap.get("teethStatus");
			if(teethStatus == null || "".equals(teethStatus)) {
				teethStatus = "100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100";
			}
			
			/** 회원 - 14세미만 일 경우 상세 정보 입력 **/
			String prUserName = (String)paramMap.get("pUserName");
			String prUserTelNo = (String)paramMap.get("prUserTelNo");
			String prUserEmail = (String)paramMap.get("prUserTelNo");
			
			// 동의 여부
			String agreYn = null;
			
			// 회원 가입 시 부모 이름 및 부모의 번호가 있을 경우 동의 한 것으로 처리 : Y 
			if(prUserName!=null && !prUserName.equals("") || prUserTelNo!=null &&!prUserTelNo.equals("")) {
				agreYn = "Y";
			}
			
			// 회원 정보 등록
			customerUserVO.setUserId(userId);
			customerUserVO.setUserEmail(userId);
			customerUserVO.setUserPwd(userPwd);
			customerUserVO.setUserName(userName);
			customerUserVO.setUserType(userType);
			customerUserVO.setUserBirthday(userBirthday);
			customerUserVO.setUserCountry(userCountry);
			customerUserVO.setUserState(userState);
			customerUserVO.setUserTelNo(userTelNo);
			customerUserVO.setUserSex(userSex);
			customerUserVO.setPushToken(pushToken);
			customerUserVO.setUserEmail(userEmail);
			
			// 법정대리인 보호자 정보
			customerUserVO.setPrUserName(prUserName);
			customerUserVO.setPrUserTelNo(prUserTelNo);
			customerUserVO.setPrUserEmail(prUserEmail);
			
			// 동의 여부
			customerUserVO.setAgreYn(agreYn);
			
			// 치아 정보 등록
			customerTeethInfoVO.setUserId(userId);
			customerTeethInfoVO.setTeethStatus(teethStatus); 
			
			// 조회 앱 회원 등록 (회원가입)
			customerUserService.insertUserInfo(customerUserVO);
			// 조회 앱 회원 상세 정보 등록
			customerUserService.insertCustomerUserDetail(customerUserVO);
			// 조회 앱 회원 치아 상태 등록
			customerTeethService.insertUserTeethInfo(customerTeethInfoVO);
			
		} catch (Exception e) {
			
			hm.put("code", "500");
			hm.put("msg", "회원 가입에 실패하였습니다.\n관리자에게 문의해주시기 바랍니다.");
			e.printStackTrace();
			
		}
		
		hm.put("code", "000");
		hm.put("msg", "회원 등록이 완료되었습니다.");
		
		return hm;
	}
	
	
	
	/**
	 * 기능 : 앱 이메일/아이디 찾기
	 * 작성자 : 정주현
	 * 작성일 : 2023. 07. 03 
	 */
	@PostMapping(value = { "/customer/user/findUserId.do" })
	@ResponseBody
	public HashMap<String,Object> searchId(@RequestParam Map<String, String> paramMap, HttpServletRequest request, HttpSession session, Model model) throws Exception {
		
		
		// E-mail을 전달받아서 등록되어있는 이메일이 있는지 확인한다.
		logger.debug("========== Customer.UserCountroller ========== /customer/user/findId.do ==========");
		logger.debug("========== Customer.UserCountroller ========== /customer/user/findId.do ==========");
		logger.debug("========== Customer.UserCountroller ========== /customer/user/findId.do ==========");
		logger.debug("========== Customer.UserCountroller ========== /customer/user/findId.do ==========");
		
		
		// return 값
		HashMap<String,Object> hm = new HashMap<String,Object>();
		int isExistEmail = 0;

		String userId = (String)paramMap.get("userId");
		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		if(userId == null || userId.equals("")) {
			hm.put("code", "401");
			hm.put("msg", "이메일이 전달되지 않았습니다.");
			return hm;
		}

		try {
			// 조회 앱 이메일 등록 여부 확인 (0: 없음, 1: 있음)
			isExistEmail =  customerUserService.isExistId(userId);
			
			if(isExistEmail == 0) {
				hm.put("code", "402");
				hm.put("msg", "해당 이메일은 등록되어 있지 않습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("code", "500");
			hm.put("msg", "서버 에러가 발생했습니다.\n관리자에게 문의해주시기 바랍니다.");
		}
		
		hm.put("code", "000");
		hm.put("msg", "회원으로 등록된 이메일 아이디입니다.");
		
		return hm;

	}
	
	
	
	/**
	 * 기능   : 비밀번호 찾기 (이메일 발송)
	 * 작성자 : 정주현 
	 * 작성일 : 2023. 07. 03
	 */
	@PostMapping(value = {"/customer/user/findUserPwd.do"})
	@ResponseBody
		public HashMap<String,Object> findUserPwd(@RequestBody HashMap<String, String> paramMap) {
		
		
		logger.debug("========== Cutomer.UserController ========== findUserPwd.do ==========");
		logger.debug("========== Cutomer.UserController ========== findUserPwd.do ==========");
		logger.debug("========== Cutomer.UserController ========== findUserPwd.do ==========");
		logger.debug("========== Cutomer.UserController ========== findUserPwd.do ==========");

		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		
		int isExistEmail = 0;
		String emailAuthKey = null;

		
		// Parameter :: userId 값 검증
		String userId = (String)paramMap.get("userId");
		// (Null 체크 및 공백 체크)
		if(userId == null || userId.equals("")) {
			hm.put("code", "401");
			hm.put("msg", "이메일을 전달받지 못했습니다.");
			//hm.put("msg", "There is no userId parameter.");
			return hm;
		}
		
		emailAuthKey = jwtTokenUtil.createToken(userId);
		
		try {
			// 이메일 등록 여부 :: Email이 없을 경우 0, Email이 있을 경우 1
			isExistEmail = customerUserService.isExistId(userId);
			
		} catch (Exception e) {
			hm.put("code", "500");
			hm.put("msg", "서버 에러가 발생하였습니다. 관리자에게 문의해주시기 바랍니다.");
			//hm.put("msg", "Server Error.");
			e.printStackTrace();
		}

		if (isExistEmail == 1) { // 이메일이 존재 할 경우 메일 발송
			try {
				// 이메일 안에 비밀번호 변경 url을 전송하도록 함
				customerMailAuthService.sendResetPasswordEmail(userId, emailAuthKey);
				
			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "서버 에러가 발생했습니다.\n관리자에게 문의해주시기 바랍니다.");
				//hm.put("msg", "Server Error.");
				e.printStackTrace();
				return hm;
			}
			
		} else { // 아이디가 없을 경우 JSON code 및 msg RETURN
			hm.put("code", "405");
			hm.put("msg", "해당 이메일은 등록되어 있지 않습니다.");
			// hm.put("msg", "There is no ID.");
		}
		
		hm.put("code", "000");
		hm.put("msg", "메일이 발송 되었습니다.");
		// hm.put("msg", "Mail Sent Completed.");
		
		return hm;
	}	
	
	
	
	/**
	 * 기능   : 비밀번호 초기화 메일 인증
	 * 작성자 : 정주현 
	 * 작성일 : 2023. 07. 04
	 */
	@GetMapping(value = {"/customer/user/resetUserPwd.do"})
	  public String userList(HttpServletRequest request, Model model) throws Exception {
		
		logger.debug("========== Cutomer.UserController ========== resetUserPwd.do ==========");
		logger.debug("========== Cutomer.UserController ========== resetUserPwd.do ==========");
		logger.debug("========== Cutomer.UserController ========== resetUserPwd.do ==========");
		logger.debug("========== Cutomer.UserController ========== resetUserPwd.do ==========");

		
		boolean tokenValidation = false;
		String userId = null;
		String emailAuthKey = null;
		
		userId = request.getParameter("userId");
		emailAuthKey = request.getParameter("emailAuthKey");
		
		// 복호화
		AES256Util aes256Util = new AES256Util();
		userId = aes256Util.aesDecode(userId);
		
		// 토큰 검증
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		tokenValidation = jwtTokenUtil.validateToken(emailAuthKey);
		
		model.addAttribute("userId", userId);
		
		if(tokenValidation) {
		    return "/customer/user/resetUserPwd";
		}else {
			return "/status/500_mailAuth";
		}
	}
	
	
	
	/**
	 * 기능   : 조회 앱 회원 정보(개인정보) 수정
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 07. 05
	 */
	@PostMapping(value = {"/customer/user/updateUserInfo.do"})
	@ResponseBody
	public HashMap<String,Object> updateUserInfo(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		
		logger.debug("========== Cutomer.UserController ========== /customer/user/updateUserInfo.do ==========");
		logger.debug("========== Cutomer.UserController ========== /customer/user/updateUserInfo.do ==========");
		logger.debug("========== Cutomer.UserController ========== /customer/user/updateUserInfo.do ==========");
		logger.debug("========== Cutomer.UserController ========== /customer/user/updateUserInfo.do ==========");
		
		// 인증 토큰
		String userAuthToken = null;
		// 조회 앱 회원 아이디 (이메일)
		String userId = null;
		// 조회 앱 회원 이름
		String userName = null;
		// 조회 앱 회원 생년월일
		String userBirthday = null;
		// 조회 앱 회원 거주 국
		String userCountry = null;
		// 조회 앱 회원 거주 주
		String userState = null;
		// 조회 앱 회원 주소 (시도, 시군구, 읍면동)
		String sidoNm = null;
		String sigunguNm = null;
		String eupmyeondongNm = null;
		// 조회 앱 회원 전화번호
		String userTelNo = null;
		// 조회 앱 회원 성별
		String userSex = null;
		

		HashMap<String,Object> hm = new HashMap<String,Object>();
		CustomerUserVO customerUserVO = new CustomerUserVO();
		
		userId = (String)paramMap.get("userId");
		
		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		userId= (String)paramMap.get("userId");
		if(userId == null || userId.equals("") || userId.equals(" ")) {
			hm.put("code", "401");
			hm.put("msg", "아이디(이메일)가 전달되지 않았습니다.");
			// hm.put("msg", "There is no userId parameter");
			return hm;
		}
		
		userAuthToken = (String)paramMap.get("userAuthToken");
		userName = (String)paramMap.get("userName");
		userBirthday = (String)paramMap.get("userBirthday");
		userCountry = (String)paramMap.get("userCountry");
		userState = (String)paramMap.get("userState");
		userTelNo = (String)paramMap.get("userTelNo");
		userSex = (String)paramMap.get("userSex");
		
		// TOKEN 검증
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
		
		if (tokenValidation) {
			try {
				
				customerUserVO.setUserId(userId);
				customerUserVO.setUserName(userName);
				customerUserVO.setUserBirthday(userBirthday);
				customerUserVO.setUserCountry(userCountry);
				customerUserVO.setUserState(userState);
				customerUserVO.setSidoNm(sidoNm);
				customerUserVO.setSigunguNm(sigunguNm);
				customerUserVO.setEupmyeondongNm(eupmyeondongNm);
				customerUserVO.setUserTelNo(userTelNo);
				customerUserVO.setUserSex(userSex);
				
				customerUserService.updateUserInfo(customerUserVO);
				
			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "서버 에러가 발생하였습니다. 관리자에게 문의해주시기 바랍니다.");
				//hm.put("msg", "Server Error.");
				e.printStackTrace();
			}
		}else {
			hm.put("code", "400");
			hm.put("msg", "로그인 토큰이 유효하지 않습니다.\n다시 로그인 해주시기 바랍니다.");
			// hm.put("msg", "The token is not valid.");
		}
		
		hm.put("code", "000");
		hm.put("msg", "회원 정보 수정 완료");
		//hm.put("msg", "Success");
		return hm;
	}
	
	
	
	/**
	 * 기능   : 조회 앱 푸시토큰 업데이트(수정)
	 * 작성자 : 정주현 
	 * 작성일 : 2023. 08. 17
	 * 수정일 : 2023. 08. 17
	 */
	@PostMapping(value = {"/customer/user/updatePushToken.do"})
	@ResponseBody
	public HashMap<String,Object> updateUserPushToken(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		
		
		logger.debug("========== Cutomer.UserController ========== /customer/user/updatePushToken.do ==========");
		logger.debug("========== Cutomer.UserController ========== /customer/user/updatePushToken.do ==========");
		logger.debug("========== Cutomer.UserController ========== /customer/user/updatePushToken.do ==========");
		logger.debug("========== Cutomer.UserController ========== /customer/user/updatePushToken.do ==========");
		
		
		// 조회 앱 회원 아이디 (이메일)
		String userId = "KRPA__________01-"+(String)paramMap.get("userId");
		String pushToken = (String)paramMap.get("pushToken");
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		if(userId == null || userId.equals("") || userId.equals(" ")) {
			hm.put("code", "401");
			hm.put("msg", "아이디(이메일)가 전달되지 않았습니다.");
			// hm.put("msg", "There is no userId parameter");
			return hm;
		}
		
		// 인증 토큰
		String userAuthToken = (String)paramMap.get("userAuthToken");
		
		// TOKEN 검증
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
		
		if (tokenValidation) {
			try {
				// 푸시토큰 업데이트(등록, 수정)
				customerUserService.updatePushToken(userId, pushToken);
				
			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "푸시토큰 등록 중 오류가 발생하였습니다.\n관리자에게 문의해주시기 바랍니다.");
				//hm.put("msg", "Server Error.");
				e.printStackTrace();
			}
		}else {
			hm.put("code", "400");
			hm.put("msg", "로그인 토큰이 유효하지 않습니다.\n다시 로그인 해주시기 바랍니다.");
			// hm.put("msg", "The token is not valid.");
		}
		
		hm.put("code", "000");
		hm.put("msg", "토큰 등록(수정) 완료");
		//hm.put("msg", "Success");
		return hm;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	
//	
//	/**
//	 * 작성자 : 정주현
//	 * 작성일 : 2022. 03. 16
//	 * 기능 : 개인정보 동의서로 회원가입
//	 */
//	@PostMapping(value = { "/web/user/insertUserInfo.do" })
//	public String insertUserInfo(@RequestParam Map<String, String> paramMap, HttpServletRequest request, HttpSession session, Model model, RedirectAttributes redirectAttributes) throws Exception {
//		
//		
//		// 법정대리인
//		String paUserId = null;
//		String paUserName = null;
//		String paUserPwd = null;
//		String paUserTelNo = null;
//		String paUserTelNo1 = null;
//		String paUserTelNo2 = null;
//		String paUserTelNo3 = null;
//		
//		// 피측정자
//		String userId = null;
//		String userPwd = null;
//		String userName = null;
//		String userTelNo = null;
//		String userTelNo1 = null;
//		String userTelNo2 = null;
//		String userTelNo3 = null;
//		String userBritday = null;
//		String userSex = null;
//		String strBirthday = null;
//		int userSeqNo = 0;
//		
//		// 기관 및 부서 정보
//		String schoolCode = null;
//		String classCode = null;
//		
//		// 주소 정보
//		String countryNm = null;
//		String countryCd = null;
//		String organType = null;
//		
//		// 기관 주소
//		String organSidoNm = null;
//		String organSigunguNm = null;
//		String organEupmyeondongNm = null;
//		String addressDetail = null;
//		
//		// 치아 정보 (하드코딩) - 모든 치아 정상
//		//String userTeethStatus = "100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100";
//		
//		String userTeethStatus = "100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100";
//		
//		// 피측정자
//		UserVO webStUserVO = new UserVO();
//		// 법정대리인
//		UserVO webPaUserVO = new UserVO();
//		// 기관 정보
//		WebOrganVO webOrganVO = new WebOrganVO();
//		// 지역 정보 (나라)
//		WebLocationVO webLocationVO = new WebLocationVO();
//		// 치아 상태 정보
//		CustomerTeethInfoVO webTeethInfoVO = new CustomerTeethInfoVO();
//
//		// 암호화 클래스
//		AES256Util aes256Util = new AES256Util();
//		
//		
//		String userType = paramMap.get("userType");
//		String isOverourteetn = paramMap.get("userAgeCheck");
//		
//		// 만 14세 미만일 경우 : 법정 대리인, 자녀의 정보 입력
//		if(userType.equals("ST")) {
//			
//			// 피측정자 파라미터
//			userName = paramMap.get("stUserName");
//			userSex = paramMap.get("stUserSex");
//			userBritday = paramMap.get("stUserBirthday");
//			// 법정대리인 파라미터
//			paUserName = paramMap.get("paUserName");
//			paUserTelNo1 = paramMap.get("paUserTelNo1");
//			paUserTelNo2 = paramMap.get("paUserTelNo2");
//			paUserTelNo3 = paramMap.get("paUserTelNo3");
//			paUserTelNo = paUserTelNo1+"-"+paUserTelNo2+"-"+paUserTelNo3;
//			// 학교 정보
//			schoolCode = paramMap.get("schoolCode");
//			classCode = paramMap.get("classCode1");
//			
//			// 기관 코드로 주소 조회 :: 피측정자 (학생) 주소 정보 입력 시 필요
//			webOrganVO = webOrganService.selectOrganInfo(schoolCode);
//			// 주소 정보 : 시도
//			organSidoNm = webOrganVO.getOrganSidoNm();
//			// 주소 정보 : 시군구
//			organSigunguNm = webOrganVO.getOrganSigunguNm();
//			// 주소 정보 : 읍면동
//			organEupmyeondongNm = webOrganVO.getOrganEupmyeondongNm();
//			// 상세 주소
//			addressDetail = organSidoNm+" "+organSigunguNm+" "+organEupmyeondongNm;
//			
//			// 피측정자 (학생) 회원 주소 (유치원 주소 기준)
//			countryCd = schoolCode.substring(0,2); 
//			// 나라 코드로 국가명 조회
//			webLocationVO = webLocationService.selectNationalInfo(countryCd);
//			countryNm = webLocationVO.getNationalNameKor();
//			// 기관 유형
//			organType = schoolCode.substring(2,4);
//			
//			// 피측정자 (학생) 회원 시퀀스
//			userSeqNo = webOrganVO.getUserSeqNo();
//			// 피측정자 아이디 생성
//			userId = countryCd+"ST"+schoolCode.substring(4, schoolCode.length())+String.format("%03d", userSeqNo);
//			// 피측정자 (학생) 회원 시퀀스 증가
//			userSeqNo++;
//
//			// 비밀번호 생성 규칙 2019-01-01 >> 190101 
//			strBirthday = userBritday.substring(2, userBritday.length()).replaceAll("-", "");
//
//			// 피측정자 회원 비밀번호 :: 생년월일(190101) 
//			userPwd = aes256Util.aesEncode(strBirthday);
//			
//			// 피측정자 회원 정보 VO
//			webStUserVO.setUserId(userId);
//			webStUserVO.setUserName(userName);
//			webStUserVO.setUserPwd(userPwd);
//			webStUserVO.setUserType("ST");
//			webStUserVO.setUserBirthday(userBritday);
//			webStUserVO.setUserCountry(countryCd);
//			webStUserVO.setUserTelNo(paUserTelNo);
// 			webStUserVO.setUserSex(userSex);
//			webStUserVO.setCountryNm(countryNm);
//			webStUserVO.setSidoNm(organSidoNm);
//			webStUserVO.setSigunguNm(organSigunguNm);
//			webStUserVO.setEupmyeondongNm(organEupmyeondongNm);
//			webStUserVO.setAddrDetail(addressDetail);
//			
//			// 피측정자 회원 치아 상태 등록
//			webTeethInfoVO.setUserId(userId);
//			webTeethInfoVO.setTeethStatus(userTeethStatus);
//			
//			// 법정대리인 회원 아이디 :: 자녀이름+생년월일(190101 :: stTmpUserPwd)+전화번호 뒷자리4자리
//			paUserId = userName+strBirthday+paUserTelNo3;
//			
//			// 법정대리인 회원 비밀번호 :: 휴대전화 번호 뒷자리 4자리 
//			paUserPwd = aes256Util.aesEncode(paUserTelNo3);
//			
//			// 법정대리인 회원 정보 VO
//			webPaUserVO.setUserId(paUserId);
//			webPaUserVO.setUserName(paUserName);
//			webPaUserVO.setUserPwd(paUserPwd);
//			webPaUserVO.setUserType("PR");
//			webPaUserVO.setUserTelNo(paUserTelNo);
//			webPaUserVO.setUserCountry(countryCd);
//			webPaUserVO.setCountryNm(countryNm);
//			webPaUserVO.setSidoNm(organSidoNm);
//			webPaUserVO.setSigunguNm(organSigunguNm);
//			webPaUserVO.setEupmyeondongNm(organEupmyeondongNm);
//			webPaUserVO.setAddrDetail(addressDetail);
//			
//			// 기관 정보 VO
//			webOrganVO.setUserSeqNo(userSeqNo);
//			webOrganVO.setSchoolCode(schoolCode);
//			
//			
//			// 계정 등록 (피측정자 회원) - ST_USER
//			webUserService.insertUserInfo(webStUserVO);
//			// 계정 상세 정보 등록 (피측정자 - 학생) - ST_STUDENT_USER_DETAL
//			webUserService.insertStUserDetail(userId, organType, classCode);
//			// 계정 등록 (법정대리인 회원) - ST_USER
//			webUserService.insertUserInfo(webPaUserVO);
//			// 계정 상세 정보 등록 (법정대리인) - ST_PARENT_USER_DETAL
//			webUserService.insertPaUserDetail(paUserId, userId);
//			// 치아 상태
//			webTeethService.insertUserTeethInfo(webTeethInfoVO);
//			// 피측정자 회원 시퀀스 증가
//			webOrganService.updateOrganUserSeqNo(webOrganVO);
//			
//		}else {
//			
//			// 만 14세 이상일 경우
//			// 피측정자 파라미터
//			userName = paramMap.get("userName");
//			userSex = paramMap.get("userSex");
//			userBritday = paramMap.get("userBirthday");
//			userTelNo1 = paramMap.get("userTelNo1");
//			userTelNo2 = paramMap.get("userTelNo2");
//			userTelNo3 = paramMap.get("userTelNo3");
//			userTelNo = userTelNo1+"-"+userTelNo2+"-"+userTelNo3;
//			// 기관 정보
//			schoolCode = paramMap.get("schoolCode");
//			// 기관 부서 정보
//			classCode = paramMap.get("classCode2");
//			
//			// 기관 코드로 주소 조회
//			webOrganVO = webOrganService.selectOrganInfo(schoolCode);
//			// 주소 정보 : 시도
//			organSidoNm = webOrganVO.getOrganSidoNm();
//			// 주소 정보 : 시군구
//			organSigunguNm = webOrganVO.getOrganSigunguNm();
//			// 주소 정보 : 읍면동
//			organEupmyeondongNm = webOrganVO.getOrganEupmyeondongNm();
//			// 상세 주소
//			addressDetail = organSidoNm+" "+organSigunguNm+" "+organEupmyeondongNm;
//			// 국가 코드
//			countryCd = schoolCode.substring(0,2); 
//			// 국가 코드로 국가명 조회
//			webLocationVO = webLocationService.selectNationalInfo(countryCd);
//			// 국가명
//			countryNm = webLocationVO.getNationalNameKor();
//			// 기관 유형
//			organType = schoolCode.substring(2,4);
//			
//			// 피측정자 시퀀스
//			userSeqNo = webOrganVO.getUserSeqNo();
//			// 피측정자 아이디 생성
//			userId = countryCd+"ST"+schoolCode.substring(4, schoolCode.length())+String.format("%03d", userSeqNo);
//			// 피측정자 시퀀스 증가
//			userSeqNo++;
//
//			// 비밀번호 생성 규칙 2019-01-01 >> 190101 
//			strBirthday = userBritday.substring(2, userBritday.length()).replaceAll("-", "");
//
//			// 피측정자 비밀번호 :: 생년월일(190101) 
//			userPwd = aes256Util.aesEncode(strBirthday);
//			
//			// 피측정자 정보 VO
//			webStUserVO.setUserId(userId);
//			webStUserVO.setUserName(userName);
//			webStUserVO.setUserPwd(userPwd);
//			webStUserVO.setUserType("PT");
//			webStUserVO.setUserBirthday(userBritday);
//			webStUserVO.setUserCountry(countryCd);
//			webStUserVO.setUserTelNo(userTelNo);
//			webStUserVO.setUserSex(userSex);
//			webStUserVO.setCountryNm(countryNm);
//			webStUserVO.setSidoNm(organSidoNm);
//			webStUserVO.setSigunguNm(organSigunguNm);
//			webStUserVO.setEupmyeondongNm(organEupmyeondongNm);
//			webStUserVO.setAddrDetail(addressDetail);
//			
//			// 피측정자 치아 상태 등록
//			webTeethInfoVO.setUserId(userId);
//			webTeethInfoVO.setTeethStatus(userTeethStatus);
//			
//			// 기관 정보 VO
//			webOrganVO.setUserSeqNo(userSeqNo);
//			webOrganVO.setSchoolCode(schoolCode);
//			
//			// 계정 등록 (피측정자) - ST_USER
//			webUserService.insertUserInfo(webStUserVO);
//			// 계정 상세 정보 등록 (피측정자) - ST_STUDENT_USER_DETAL
//			webUserService.insertStUserDetail(userId, organType, classCode);
//			// 치아 상태
//			webTeethService.insertUserTeethInfo(webTeethInfoVO);
//			// 피측정자 회원 시퀀스 증가
//			webOrganService.updateOrganUserSeqNo(webOrganVO);
//		}
//		
//		
//		
//		// 리다이렉션 시 파라미터를 전달
//		redirectAttributes.addFlashAttribute("msg", "제출이 완료 되었습니다. 감사합니다.");
//		
//		return "redirect:/web/user/agreement";
//
//	}
//	
//	
//	
//
}

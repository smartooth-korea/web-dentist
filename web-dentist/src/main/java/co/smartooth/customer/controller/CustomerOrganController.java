package co.smartooth.customer.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import co.smartooth.customer.service.CustomerAuthService;
import co.smartooth.customer.service.CustomerLogService;
import co.smartooth.customer.service.CustomerOrganService;
import co.smartooth.customer.service.CustomerTeethService;
import co.smartooth.customer.service.CustomerUserService;
import co.smartooth.customer.vo.CustomerAuthVO;
import co.smartooth.customer.vo.CustomerTeethMeasureVO;
import co.smartooth.customer.vo.CustomerUserVO;
import co.smartooth.web.utils.AES256Util;
import co.smartooth.web.utils.JwtTokenUtil;

/**
 * 작성자 : 정주현 
 * 작성일 : 2023. 08. 18
 * 수정일 : 2023. 08. 18
 * 서버분리 : 2023. 08. 01
 */
@Controller
public class CustomerOrganController {

	
	
	@Autowired(required = false)
	private CustomerOrganService customerOrganService;

	
	private static boolean tokenValidation = false;
	
	
	
	/**
	 * 기능   : 개인정보 동의/미동의 치과 전체 목록 조회 또는 검색 목록 조회
	 * 작성자 : 정주현 
	 * 작성일 : 2023. 08. 20
	 * 수정일 : 2023. 08. 21
	 */
	@PostMapping(value = {"/customer/organ/selectDentalHospitalList.do"})
	@ResponseBody
	public HashMap<String,Object> selectDentalHospitalList(HttpServletRequest request, @RequestBody HashMap<String, Object> paramMap) {
       
		
	    Logger logger = LoggerFactory.getLogger(getClass());

	    
	    logger.debug("========== Customer.OrganController ========== /customer/organ/selectDentalHospitalList.do ==========");
	    logger.debug("========== Customer.OrganController ========== /customer/organ/selectDentalHospitalList.do ==========");
	    logger.debug("========== Customer.OrganController ========== /customer/organ/selectDentalHospitalList.do ==========");
	    logger.debug("========== Customer.OrganController ========== /customer/organ/selectDentalHospitalList.do ==========");
	    
	    
	    // 언어팩
	    //String lang = (String)paramMap.get("lang");
	    // 회원 아이디
	    String userId = (String)paramMap.get("userId");
	    // 검색할 치과명
		String dentalHospitalNm = (String)paramMap.get("dentalHospitalNm");
		// 인증 토큰 
		String userAuthToken = (String)paramMap.get("userAuthToken");
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		List<HashMap<String, Object>> dentalHospitalList = new ArrayList<HashMap<String, Object>>();
		
		// 암호화 
		//AES256Util aes256Util = new AES256Util();
		
		// TOKEN 검증
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
		
		if(tokenValidation) {
			try {
				// 치과 목록 검색
				dentalHospitalList = customerOrganService.selectDentalHospitalList(userId, dentalHospitalNm);
			} catch (Exception e) {
				hm.put("code", "500");
				//if(lang.equals("ko")) {
				hm.put("msg", "치과 목록 조회 중 에러가 발생했습니다.\n관리자에게 문의해주시기 바랍니다.");
				//}else if(lang.equals("en")) {
				//	hm.put("msg", "Server Error");
				//}
				e.printStackTrace();
			}
		}else {
			hm.put("code", "400");
			hm.put("msg", "로그인 토큰이 유효하지 않습니다.\n다시 로그인 해주시기 바랍니다.");
		}
		
		hm.put("userAuthToken", userAuthToken);
		hm.put("dentalHospitalList", dentalHospitalList);
		// 메시지 RETURN
		hm.put("code", "000");
		//if(lang.equals("ko")) {
		hm.put("msg", "조회 성공");
		//}else if(lang.equals("en")) {
		//	hm.put("msg", "Login Success");
		//}
		return hm;
	}
	
	
	
	/**
	 * 기능   : 회원이 개인정보 동의한 치과 목록 조회
	 * 작성자 : 정주현 
	 * 작성일 : 2023. 08. 22
	 * 수정일 : 2023. 08. 22
	 */
	@PostMapping(value = {"/customer/organ/selectDentalHospitalAgreeList.do"})
	@ResponseBody
	public HashMap<String,Object> selectDentalHospitalAgreList(HttpServletRequest request, @RequestBody HashMap<String, Object> paramMap) {
       
	    
		Logger logger = LoggerFactory.getLogger(getClass());

	    
	    logger.debug("========== Customer.OrganController ========== /customer/organ/selectDentalHospitalAgreeList.do ==========");
	    logger.debug("========== Customer.OrganController ========== /customer/organ/selectDentalHospitalAgreeList.do ==========");
	    logger.debug("========== Customer.OrganController ========== /customer/organ/selectDentalHospitalAgreeList.do ==========");
	    logger.debug("========== Customer.OrganController ========== /customer/organ/selectDentalHospitalAgreeList.do ==========");
	    
	    
	    // 언어팩
	    //String lang = (String)paramMap.get("lang");
	    // 회원 아이디
	    String userId = (String)paramMap.get("userId");
		// 인증 토큰 
		String userAuthToken = (String)paramMap.get("userAuthToken");
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		List<HashMap<String, Object>> dentalHospitalAgreeList = new ArrayList<HashMap<String, Object>>();
		
		// 암호화 
		//AES256Util aes256Util = new AES256Util();
		
		// TOKEN 검증
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
		
		if(tokenValidation) {
			try {
				// 개인정보 동의한 치과 목록
				dentalHospitalAgreeList = customerOrganService.selectDentalHospitalAgreeList(userId);
			} catch (Exception e) {
				hm.put("code", "500");
				//if(lang.equals("ko")) {
				hm.put("msg", "개인정보에 동의한 치과 목록 조회에 실패했습니다.\n관리자에게 문의해주시기 바랍니다.");
				//}else if(lang.equals("en")) {
				//	hm.put("msg", "Server Error");
				//}
				e.printStackTrace();
			}
		}else {
			hm.put("code", "400");
			hm.put("msg", "로그인 토큰이 유효하지 않습니다.\n다시 로그인 해주시기 바랍니다.");
		}
		
		hm.put("userAuthToken", userAuthToken);
		hm.put("dentalHospitalAgreeList", dentalHospitalAgreeList);
		// 메시지 RETURN
		hm.put("code", "000");
		//if(lang.equals("ko")) {
		hm.put("msg", "조회 성공");
		//}else if(lang.equals("en")) {
		//	hm.put("msg", "Login Success");
		//}
		return hm;
	}
	
	
}

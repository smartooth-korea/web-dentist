package co.smartooth.customer.controller;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import co.smartooth.customer.service.CustomerReserveService;
import co.smartooth.customer.service.CustomerUserService;
import co.smartooth.customer.vo.CustomerUserVO;
import co.smartooth.web.utils.JwtTokenUtil;

/**
 * 기능 : 개인 정보 동의 및 예약에 관한 Controller 작성자 : 정주현 작성일 : 2023. 08. 23 수정일 : 2023.
 * 08. 23 서버분리 : 2023. 08. 01
 */

@Controller
public class CustomerReserveController {

	
	@Autowired(required = false)
	private CustomerUserService customerUserService;

	
	@Autowired(required = false)
	private CustomerReserveService customerReserveService;

	
	private static boolean tokenValidation = false;

	
	
	/**
	 * 기능 : 치과 선택 후 개인정보 동의 등록
	 * 작성자 : 정주현
	 * 작성일 : 2023. 08. 24
	 * 수정일 : 2023. 08. 24
	 */
	@PostMapping(value = { "/customer/reserve/insertCustomerInfomationAgree.do" })
	@ResponseBody
	public HashMap<String, Object> insertCustomerInfomationAgree(@RequestBody HashMap<String, Object> paramMap) throws Exception {

		Logger logger = LoggerFactory.getLogger(getClass());

		logger.debug("========== Customer.ReserveController ========== /customer/reserve/insertCustomerInfomationAgree.do ==========");
		logger.debug("========== Customer.ReserveController ========== /customer/reserve/insertCustomerInfomationAgree.do ==========");
		logger.debug("========== Customer.ReserveController ========== /customer/reserve/insertCustomerInfomationAgree.do ==========");
		logger.debug("========== Customer.ReserveController ========== /customer/reserve/insertCustomerInfomationAgree.do ==========");

		
		// 언어팩
		// String lang = (String)paramMap.get("lang");
		// String lang = "ko";

		// 회원 아이디 :: 하드코딩
		String userId = (String) paramMap.get("userId");
		// String userId = (String)paramMap.get("userId");
		// 치과 코드
		String dentalHospitalCd = (String) paramMap.get("dentalHospitalCd");

		// 개인정보 동의 여부
		int isExistInfomationAgree = 0;

		// 로그인 토큰
		String userAuthToken = (String) paramMap.get("userAuthToken");
		// 리턴 parameter
		HashMap<String, Object> hm = new HashMap<String, Object>();

		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		tokenValidation = jwtTokenUtil.validateToken(userAuthToken);

		if (tokenValidation) {
			try {
				// 개인정보 동의 등록 여부 확인
				isExistInfomationAgree = customerReserveService.isExistCustomerInfomationAgree(userId, dentalHospitalCd);

				if (isExistInfomationAgree == 0) {

					// 회원 정보 조회
					CustomerUserVO customerUserVO = customerUserService.selectUserInfo(userId);
					customerReserveService.insertCustomerInfoAgree(userId, dentalHospitalCd, customerUserVO.getUserTelNo(), customerUserVO.getUserEmail());

					hm.put("code", "000");
					hm.put("code", "등록 완료");

				} else {

					hm.put("code", "200");
					hm.put("code", "개인정보 동의 처리가 이미 완료된 치과입니다.");
				}
			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "개인정보 동의 처리에 실패했습니다.\n관리자에게 문의해주시기 바랍니다.");
				e.printStackTrace();
			}
		} else {
			hm.put("code", "400");
			hm.put("msg", "로그인 토큰이 유효하지 않습니다.\n다시 로그인 해주시기 바랍니다.");
		}

		return hm;
	}

	

	/**
	 * 기능 : 개인정보 동의 업데이트
	 * 작성자 : 정주현
	 * 작성일 : 2023. 08. 24
	 * 수정일 : 2023. 08. 24
	 */
	@PostMapping(value = {"/customer/reserve/updateCustomerInfomationAgree.do"})
	@ResponseBody
	public HashMap<String, Object> updateCustomerInfomationAgree(@RequestBody HashMap<String, Object> paramMap) throws Exception {

		Logger logger = LoggerFactory.getLogger(getClass());

		logger.debug("========== Customer.ReserveController ========== /customer/reserve/updateCustomerInfomationAgree.do ==========");
		logger.debug("========== Customer.ReserveController ========== /customer/reserve/updateCustomerInfomationAgree.do ==========");
		logger.debug("========== Customer.ReserveController ========== /customer/reserve/updateCustomerInfomationAgree.do ==========");
		logger.debug("========== Customer.ReserveController ========== /customer/reserve/updateCustomerInfomationAgree.do ==========");

		// 언어팩
		// String lang = (String)paramMap.get("lang");
		// String lang = "ko";

		// 회원 아이디 :: 하드코딩
		String userId = (String) paramMap.get("userId");
		// String userId = (String)paramMap.get("userId");
		// 치과 코드
		String dentalHospitalCd = (String) paramMap.get("dentalHospitalCd");
		// 로그인 토큰
		String userAuthToken = (String) paramMap.get("userAuthToken");
		// 리턴 parameter
		HashMap<String, Object> hm = new HashMap<String, Object>();

		// 개인정보 동의/미동의
		String infomationAgryn = (String) paramMap.get("infomationAgryn");
		// 개인정보 동의 여부
		int isExistInfomationAgree = 0;

		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		tokenValidation = jwtTokenUtil.validateToken(userAuthToken);

		 
		if(tokenValidation) {
			try {
				// 개인정보 동의 등록 여부 확인
				isExistInfomationAgree = customerReserveService.isExistCustomerInfomationAgree(userId,dentalHospitalCd);

				if (isExistInfomationAgree > 0) {
					// 회원 정보 조회
					customerReserveService.updateCustomerInfoAgree(userId, dentalHospitalCd, infomationAgryn);
					hm.put("code", "000");
					hm.put("code", "수정 완료");
				}else {
					CustomerUserVO customerUserVO = customerUserService.selectUserInfo(userId);
					customerReserveService.insertCustomerInfoAgree(userId, dentalHospitalCd, customerUserVO.getUserTelNo(), customerUserVO.getUserEmail());
					hm.put("code", "000");
					hm.put("code", "등록 완료");
				}
				
			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "개인정보 동의 처리에 실패했습니다.\n관리자에게 문의해주시기 바랍니다.");
				e.printStackTrace();
			}
		} else {
			hm.put("code", "400");
			hm.put("msg", "로그인 토큰이 유효하지 않습니다.\n다시 로그인 해주시기 바랍니다.");
		}

		
		return hm;
	}

	
	
	/**
	 * 기능 : 개인정보 동의 삭제
	 * 작성자 : 정주현 
	 * 작성일 : 2023. 08. 24 
	 * 수정일 : 2023. 08. 24
	 */
	@PostMapping(value = {"/customer/reserve/deleteCustomerInfomationAgree.do"})
	@ResponseBody
	public HashMap<String,Object> deleteCustomerInfomationAgree(@RequestBody HashMap<String, Object> paramMap) throws Exception {
       
	    Logger logger = LoggerFactory.getLogger(getClass());

	    logger.debug("========== Customer.ReserveController ========== /customer/reserve/deleteCustomerInfomationAgree.do ==========");
	    logger.debug("========== Customer.ReserveController ========== /customer/reserve/deleteCustomerInfomationAgree.do ==========");
	    logger.debug("========== Customer.ReserveController ========== /customer/reserve/deleteCustomerInfomationAgree.do ==========");
	    logger.debug("========== Customer.ReserveController ========== /customer/reserve/deleteCustomerInfomationAgree.do ==========");
	    
	    // 언어팩
	    // String lang = (String)paramMap.get("lang");
	    // String lang = "ko";
	    
	    // 회원 아이디 :: 하드코딩
	    String userId = "KRPA__________01-"+(String)paramMap.get("userId");
	    //String userId = (String)paramMap.get("userId");
	    // 치과 코드
	    String dentalHospitalCd = (String)paramMap.get("dentalHospitalCd");
	    // 로그인 토큰
		String userAuthToken = (String)paramMap.get("userAuthToken");
		// 리턴 parameter
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
		
		if(tokenValidation) {
			try {
					// 회원 정보 조회
					customerReserveService.deleteCustomerInfoAgree(userId, dentalHospitalCd);
					hm.put("code", "000");
					hm.put("code", "등록 완료");
					
			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "개인정보 동의 처리에 실패했습니다.\n관리자에게 문의해주시기 바랍니다.");
				e.printStackTrace();
			}
		}else {
			hm.put("code", "400");
			hm.put("msg", "로그인 토큰이 유효하지 않습니다.\n다시 로그인 해주시기 바랍니다.");
		}
		
		return hm;
	}

}

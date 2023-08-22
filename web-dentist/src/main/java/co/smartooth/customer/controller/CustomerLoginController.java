package co.smartooth.customer.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
public class CustomerLoginController {

	@Autowired(required = false)
	private CustomerLogService customerLogService;

	@Autowired(required = false)
	private CustomerAuthService customerAuthService;

	@Autowired(required = false)
	private CustomerUserService customerUserService;

	@Autowired(required = false)
	private CustomerTeethService customerTeethService;

	@Autowired(required = false)
	private CustomerOrganService customerOrganService;
	
	
	

	
	/**
	 * 기능   : 조회 앱 로그인 API
	 * 작성자 : 정주현 
	 * 작성일 : 2023. 07. 04
	 */
	@PostMapping(value = {"/customer/login.do"})
	@ResponseBody
	public HashMap<String,Object> appLogin(@RequestBody HashMap<String, Object> paramMap) throws Exception {
       
	    Logger logger = LoggerFactory.getLogger(getClass());

	    logger.debug("========== CustomerLoginController ========== /customer/login.do ==========");
	    logger.debug("========== CustomerLoginController ========== /customer/login.do ==========");
	    logger.debug("========== CustomerLoginController ========== /customer/login.do ==========");
	    logger.debug("========== CustomerLoginController ========== /customer/login.do ==========");
	    
	    
	    // 언어팩
	    //String lang = (String)paramMap.get("lang");
	    // String lang = "ko";
	    String userId = null;
		String userPwd = null;
		// String userType = null;
		String loginIp = null;
		String userAuthToken = null;
		
		int loginChkByIdPwd = 0;
		int isIdExist = 0;
		
		String startDt = null;
		String endDt = null;
		
		String measureStartDt = null;
		String measureEndDt = null;
		

		// 오늘 날짜 구하기 (SYSDATE)
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		// 1년 측정
		if(startDt == null || startDt.equals("")) {
			startDt = now.minusYears(1).toString();
			endDt = now.format(formatter);
		}
		
		measureEndDt = endDt;
		// 3개월전 날짜
		measureStartDt = now.minusMonths(3).toString();
		
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		List<HashMap<String, Object>> userTeethValueList = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> dentalHospitalVisitList = new ArrayList<HashMap<String, Object>>();
		
		// 유치 개수 20개
		int[] babyTeethValueArray = new int[20];
		// 영구치 개수 32개
		int[] permTeethValueArray = new int[32];
		
		// CAVITY_LEVEL 분류 부분 - 충치 단계별 수치 조회
		HashMap<String, Integer> cavityLevel = customerTeethService.selectCavityLevel();
		// 충치단계 값(주의)
		Integer cautionLevel = 0;
		// 충치단계 값(위험)
		Integer dangerLevel = 0;
		
		//List<HashMap<String, Object>> measureOranList = new ArrayList<HashMap<String, Object>>();
		
		// 로그인 인증 VO
		CustomerAuthVO customerAuthVO = new CustomerAuthVO();
		
		// 회원 정보 VO
		CustomerUserVO customerUserVO = new CustomerUserVO();
		
		// 오늘 일자 계산
		Date tmpDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		String sysDate = sdf.format(tmpDate);
		
		// 파라미터 >> 값 setting
		userId= (String)paramMap.get("userId");
		userId = "KRPA__________01-"+userId;
		loginIp = (String)paramMap.get("loginIp");
		
		
		// 비밀번호 암호화 
		AES256Util aes256Util = new AES256Util();
		userPwd = aes256Util.aesEncode((String)paramMap.get("userPwd"));
		
		if(userPwd.equals("false")) { // 암호화에 실패할 경우
			hm.put("code", "500");
			//if(lang.equals("ko")) {
				hm.put("msg", "서버 에러가 발생했습니다.\n관리자에게 문의해주시기 바랍니다.");
			//}else if(lang.equals("en")) {
			//	hm.put("msg", "Server Error");
			//}
			return hm;
		}
		
		// 회원 인증 VO
		customerAuthVO.setUserId(userId);
		customerAuthVO.setUserPwd(userPwd);
		customerAuthVO.setLoginDt(sysDate);
		customerAuthVO.setUserIp(loginIp);
		
		try {
			// 로그인 확인
			loginChkByIdPwd = customerAuthService.loginChkByIdPwd(customerAuthVO);
			if(loginChkByIdPwd == 0){ // 0일 경우는 Database에 ID와 비밀번호가 틀린 것
				isIdExist = customerAuthService.isIdExist(customerAuthVO.getUserId());
				if(isIdExist == 0) { // ID가 존재하지 않을 경우
				//	if(lang.equals("ko")) {
						hm.put("msg", "해당 아이디가 존재하지 않습니다.");
				//	}else if(lang.equals("en")) {
				//		hm.put("msg", "This ID does not exist");
				//	}
				}else { // PWD가 틀렸을 경우
					hm.put("code", "406");
				//	if(lang.equals("ko")) {
						hm.put("msg", "비밀번호가 틀렸습니다.");
				//	}else if(lang.equals("en")) {
				//		hm.put("msg", "The password is wrong");
				//	}
				}
			}else { 
				// ID와 PWD가 검증된 이후 JWT 토큰과 회원의 정보를 제공하고 LOG를 INSERT
				// JWT token 발행
				JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
				userAuthToken = jwtTokenUtil.createToken(customerAuthVO);
				
				// 로그인 일자 업데이트
				customerLogService.updateLoginDt(customerAuthVO);
				
				// 고객정보
				customerUserVO = customerUserService.selectUserInfo(userId);
				// 1년 측정 기록
				userTeethValueList = customerTeethService.selectUserTeethMeasureValue(userId, startDt, endDt);

				// 유치(주의,심각), 영구치(주의,심각) 개수 카운팅
				for (int i = 0; i < userTeethValueList.size(); i++) {

					int babyCautionCnt = 0;
					int babyDangerCnt = 0;
					int permCautionCnt = 0;
					int permDangerCnt = 0;
					
					
					babyTeethValueArray[0] = Integer.parseInt(userTeethValueList.get(i).get("t34").toString());
					babyTeethValueArray[1] = Integer.parseInt(userTeethValueList.get(i).get("t35").toString());
					babyTeethValueArray[2] = Integer.parseInt(userTeethValueList.get(i).get("t36").toString());
					babyTeethValueArray[3] = Integer.parseInt(userTeethValueList.get(i).get("t37").toString());
					babyTeethValueArray[4] = Integer.parseInt(userTeethValueList.get(i).get("t38").toString());
					babyTeethValueArray[5] = Integer.parseInt(userTeethValueList.get(i).get("t39").toString());
					babyTeethValueArray[6] = Integer.parseInt(userTeethValueList.get(i).get("t40").toString());
					babyTeethValueArray[7] = Integer.parseInt(userTeethValueList.get(i).get("t41").toString());
					babyTeethValueArray[8] = Integer.parseInt(userTeethValueList.get(i).get("t42").toString());
					babyTeethValueArray[9] = Integer.parseInt(userTeethValueList.get(i).get("t43").toString());
					babyTeethValueArray[10] = Integer.parseInt(userTeethValueList.get(i).get("t46").toString());
					babyTeethValueArray[11] = Integer.parseInt(userTeethValueList.get(i).get("t47").toString());
					babyTeethValueArray[12] = Integer.parseInt(userTeethValueList.get(i).get("t48").toString());
					babyTeethValueArray[13] = Integer.parseInt(userTeethValueList.get(i).get("t49").toString());
					babyTeethValueArray[14] = Integer.parseInt(userTeethValueList.get(i).get("t50").toString());
					babyTeethValueArray[15] = Integer.parseInt(userTeethValueList.get(i).get("t51").toString());
					babyTeethValueArray[16] = Integer.parseInt(userTeethValueList.get(i).get("t52").toString());
					babyTeethValueArray[17] = Integer.parseInt(userTeethValueList.get(i).get("t53").toString());
					babyTeethValueArray[18] = Integer.parseInt(userTeethValueList.get(i).get("t54").toString());
					babyTeethValueArray[19] = Integer.parseInt(userTeethValueList.get(i).get("t55").toString());

					// 영구치 상악
					permTeethValueArray[0] = Integer.parseInt(userTeethValueList.get(i).get("t01").toString());
					permTeethValueArray[1] = Integer.parseInt(userTeethValueList.get(i).get("t02").toString());
					permTeethValueArray[2] = Integer.parseInt(userTeethValueList.get(i).get("t03").toString());
					permTeethValueArray[3] = Integer.parseInt(userTeethValueList.get(i).get("t04").toString());
					permTeethValueArray[4] = Integer.parseInt(userTeethValueList.get(i).get("t05").toString());
					permTeethValueArray[5] = Integer.parseInt(userTeethValueList.get(i).get("t06").toString());
					permTeethValueArray[6] = Integer.parseInt(userTeethValueList.get(i).get("t07").toString());
					permTeethValueArray[7] = Integer.parseInt(userTeethValueList.get(i).get("t08").toString());
					permTeethValueArray[8] = Integer.parseInt(userTeethValueList.get(i).get("t09").toString());
					permTeethValueArray[9] = Integer.parseInt(userTeethValueList.get(i).get("t10").toString());
					permTeethValueArray[10] = Integer.parseInt(userTeethValueList.get(i).get("t11").toString());
					permTeethValueArray[11] = Integer.parseInt(userTeethValueList.get(i).get("t12").toString());
					permTeethValueArray[12] = Integer.parseInt(userTeethValueList.get(i).get("t13").toString());
					permTeethValueArray[13] = Integer.parseInt(userTeethValueList.get(i).get("t14").toString());
					permTeethValueArray[14] = Integer.parseInt(userTeethValueList.get(i).get("t15").toString());
					permTeethValueArray[15] = Integer.parseInt(userTeethValueList.get(i).get("t16").toString());
					// 영구치 하악
					permTeethValueArray[16] = Integer.parseInt(userTeethValueList.get(i).get("t17").toString());
					permTeethValueArray[17] = Integer.parseInt(userTeethValueList.get(i).get("t18").toString());
					permTeethValueArray[18] = Integer.parseInt(userTeethValueList.get(i).get("t19").toString());
					permTeethValueArray[19] = Integer.parseInt(userTeethValueList.get(i).get("t20").toString());
					permTeethValueArray[20] = Integer.parseInt(userTeethValueList.get(i).get("t21").toString());
					permTeethValueArray[21] = Integer.parseInt(userTeethValueList.get(i).get("t22").toString());
					permTeethValueArray[22] = Integer.parseInt(userTeethValueList.get(i).get("t23").toString());
					permTeethValueArray[23] = Integer.parseInt(userTeethValueList.get(i).get("t24").toString());
					permTeethValueArray[24] = Integer.parseInt(userTeethValueList.get(i).get("t25").toString());
					permTeethValueArray[25] = Integer.parseInt(userTeethValueList.get(i).get("t26").toString());
					permTeethValueArray[26] = Integer.parseInt(userTeethValueList.get(i).get("t27").toString());
					permTeethValueArray[27] = Integer.parseInt(userTeethValueList.get(i).get("t28").toString());
					permTeethValueArray[28] = Integer.parseInt(userTeethValueList.get(i).get("t29").toString());
					permTeethValueArray[29] = Integer.parseInt(userTeethValueList.get(i).get("t30").toString());
					permTeethValueArray[30] = Integer.parseInt(userTeethValueList.get(i).get("t31").toString());
					permTeethValueArray[31] = Integer.parseInt(userTeethValueList.get(i).get("t32").toString());

					// 충치 단계 조회 (주의, 충치)
					cautionLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_CAUTION")));
					dangerLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_DANGER")));

					/** 유치 정상, 주의, 충치 개수 저장 **/
					for (int j = 0; j < babyTeethValueArray.length; j++) { // 측정자가 입력한 주의나 충치 값의 -1000
						if (babyTeethValueArray[j] > 1000) {
							babyTeethValueArray[j] = (int) babyTeethValueArray[j] - 1000;
						}
						if (babyTeethValueArray[j] >= cautionLevel && babyTeethValueArray[j] < dangerLevel) {
							babyCautionCnt++;
						} else if (babyTeethValueArray[j] >= dangerLevel) {
							babyDangerCnt++;
						}
					}

					/** 영구치 상악 하악 정상, 주의, 충치 개수 저장 **/
					for (int k = 0; k < permTeethValueArray.length; k++) { // 측정자가 입력한 주의나 충치 값의 -1000
						if (permTeethValueArray[k] > 1000) {
							permTeethValueArray[k] = (int) permTeethValueArray[k] - 1000;
						}
						if (permTeethValueArray[k] >= cautionLevel && permTeethValueArray[k] < dangerLevel) {
							permCautionCnt++;
						} else if (permTeethValueArray[k] >= dangerLevel) {
							permDangerCnt++;
						}
					}
					userTeethValueList.get(i).put("babyCautionCnt", Integer.toString(babyCautionCnt));
					userTeethValueList.get(i).put("babyDangerCnt", Integer.toString(babyDangerCnt));
					userTeethValueList.get(i).put("permCautionCnt", Integer.toString(permCautionCnt));
					userTeethValueList.get(i).put("permDangerCnt", Integer.toString(permDangerCnt));
				}
				
				try {
					// 3개월 방문 병원 리스트
					dentalHospitalVisitList = customerOrganService.selectDentalHospitalVisitList(userId, measureStartDt, measureEndDt);
				} catch (Exception e) {
					hm.put("dentalHospitalVisitList", null);
				}
				
				// 데이터 RETURN
				hm.put("userAuthToken", userAuthToken);
				hm.put("userInfo", customerUserVO);
				hm.put("userTeethValueList", userTeethValueList);
				hm.put("dentalHospitalVisitList", dentalHospitalVisitList);

				// 메시지 RETURN
				hm.put("code", "000");
				
				//if(lang.equals("ko")) {
					hm.put("msg", "로그인 성공");
				//}else if(lang.equals("en")) {
				//	hm.put("msg", "Login Success");
				//}
				
				// 로그인 Log 등록
				customerLogService.insertUserLoginHistory(customerAuthVO);
			}
		} catch (Exception e) {
			hm.put("code", "500");
			//if(lang.equals("ko")) {
				hm.put("msg", "서버 에러가 발생했습니다.\n관리자에게 문의해주시기 바랍니다.");
			//}else if(lang.equals("en")) {
			//	hm.put("msg", "Server Error");
			//}
			e.printStackTrace();
		}
		return hm;
	}
	
	
	
}

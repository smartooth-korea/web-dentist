package co.smartooth.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import co.smartooth.web.service.WebDiagnosisService;
import co.smartooth.web.service.WebOrganService;
import co.smartooth.web.service.WebTeethService;
import co.smartooth.web.service.WebUserService;
import co.smartooth.web.vo.WebAdviceVO;
import co.smartooth.web.vo.WebTeethMeasureVO;
import co.smartooth.web.vo.WebUserVO;

/**
 * 작성자 : 정주현
 * 작성일 : 2022. 11. 28
 * 수정일 : 2023. 08. 14
 * 
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class WebStatisticsController {

	@Value("${loginUrl}")
	private String loginUrl;

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired(required = false)
	private WebUserService webUserService;

	@Autowired(required = false)
	private WebTeethService webTeethService;

	@Autowired(required = false)
	private WebDiagnosisService webDiagnosisService;

	@Autowired(required = false)
	private WebOrganService webOrganService;

	
	
	/**
	 * 작성자 : 정주현
	 * 작성일 : 2022. 11. 28
	 * 수정일 : 2023. 07. 20
	 * 기능 : 결과 진단지 화면
	 */
	@GetMapping(value = { "/web/statistics/diagnosis" })
	public String webDiagnosis(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		// 세션 값
		WebUserVO sessionVO = (WebUserVO) session.getAttribute("userInfo");
		if (sessionVO == null) {
			return "/web/statistics/login/loginForm";
		}
		return "redirect:/web/statistics/diagnosis.do";
		
	}
	
	
//	/**
//	 * 작성자 : 정주현
//	 * 작성일 : 2022. 11. 28
//	 * 수정일 : 2023. 07. 20
//	 * 기능 : 그래프 화면
//	 */
//	@GetMapping(value = { "/web/statistics/graph" })
//	public String webGraph(HttpServletRequest request, HttpSession session, Model model) throws Exception {
//
//		// 세션 값
//		WebUserVO sessionVO = (WebUserVO) session.getAttribute("userInfo");
//		if (sessionVO == null) {
//			return "/web/statistics/login/loginForm";
//		}
//		return "redirect:/web/statistics/graph.do";
//		
//	}
	
	
	
	/**
	 * 작성자 : 정주현
	 * 작성일 : 2022. 11. 28
	 * 수정일 : 2023. 07. 24
	 * 기능 : 로그인 >> 결과 진단지 화면
	 *         유치원 단독 기능
	 */
	@GetMapping(value = { "/web/statistics/diagnosis.do"})
	public String diagnosis(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		WebUserVO sessionVO = (WebUserVO) session.getAttribute("userInfo");
		
		String returnUrl = null;
		
		if (sessionVO == null) {
			return "/web/statistics/login/loginForm";
		}
		
		String userId = sessionVO.getUserId();
		String userName = null;
		String teethType = null;
		String schoolCode = sessionVO.getSchoolCode();
		
		WebUserVO webUserVO = new WebUserVO();
		
		// 본인이 직접 로그인한 경우
		webUserVO = webUserService.selectUserInfo(userId);
		teethType = webUserVO.getTeethType();
		userName = sessionVO.getUserName();
		
		// 최근 측정일
		String measureDt = null;
		// 유치 개수 20개
		int[] babyTeethValueArray = null;
		// 영구치 개수 8개
		int[] permTeethValueArray = null;
		// 영구치 어금니 개수 4개
		int[] permanentMolarsValueArray = null;
		// 임시 유치 배열 20개
		int[] tmpBabyTeethValueArray = null;
		
		// 충치단계 값(주의)
		Integer cautionLevel = 0;
		// 충치단계 값(위험)
		Integer dangerLevel = 0;
	
		// 정상 유치 개수
		int babyCvNormalCnt = 0;
		// 주의 유치 개수
		int babyCvCautionCnt = 0;
		// 충치 유치 개수
		int babyCvDangerCnt = 0;
	
		// 정상 영구치 개수
		int pmCvNomalCnt = 0;
		// 주의 영구치 개수
		int pmCvCautionCnt = 0;
		// 충치 영구치 개수
		int pmCvDangerCnt = 0;
	
		// 유치+영구치 개수
		int sheetNormalCnt = 0;
		int sheetCautionCnt = 0;
		int sheetdangerCnt = 0;
		
		// 진단 코드
		String userDiagCd = null;
		// 진단 코드 분리한 배열
		String[] diagCdArray;
		// 진단 코드 문자열
		String diagCdStr = null;
		// 진단 코드 별 제목 배열
		String[] diagCdTitleArray;
		// 진단 코드 설명
		String diagDescript = "";
		// 진단 코드 카운트
		int isDiagCnt = 0;
	
		// 결제 정보에 따라서 보여지는 양을 다르게 조정 :: 기본 값은 3
		int limit = 100;
		
		WebTeethMeasureVO webTeethMeasureVO = new WebTeethMeasureVO();
		WebAdviceVO webAdviceVO = new WebAdviceVO();
		List<String> measureDtList = new ArrayList<String>();
		
		try {

			// 유치 개수 20개
			babyTeethValueArray = new int[20];
			// 영구치 개수 12개
			permTeethValueArray = new int[8];
			// 영구치 어금니 4개
			permanentMolarsValueArray = new int[4];
			// 영구치 위치에 해당하는 임시 유치 배열
			tmpBabyTeethValueArray = new int[8];

			// CAVITY_LEVEL 분류 부분 - 충치 단계별 수치 조회
			HashMap<String, Integer> cavityLevel = webTeethService.selectCavityLevel();

			// 치아 측정 VO
			webTeethMeasureVO.setUserId(userId);
			// 측정 값 보이는 개수
			webTeethMeasureVO.setLimit(limit);
			// 측정일 목록 조회
			measureDtList = webTeethService.selectUserMeasureDtList(webTeethMeasureVO);

			if (measureDtList.size() > 0) {
				// 최근 측정일
				measureDt = measureDtList.get(0);
			} else {
				
				model.addAttribute("loginUrl", loginUrl + "/logout.do");
				model.addAttribute("msg", "측정 기록이 없습니다.");
				return "/common/alertMessage";
				
			}

			// 피측정자 최근 치아 측정 값 조회
			webTeethMeasureVO = webTeethService.selectUserMeasureValue(userId, measureDt);

			// 치아 측정 값 배열 (혼합치 배열)
			// 변경전 유치 배열 34~53까지 20개
			babyTeethValueArray[0] = webTeethMeasureVO.getT34();
			babyTeethValueArray[1] = webTeethMeasureVO.getT35();
			babyTeethValueArray[2] = webTeethMeasureVO.getT36();
			// 영구치 T07
			babyTeethValueArray[3] = webTeethMeasureVO.getT37();
			// 영구치 T08
			babyTeethValueArray[4] = webTeethMeasureVO.getT38();
			// 영구치 T09
			babyTeethValueArray[5] = webTeethMeasureVO.getT39();
			// 영구치 T10
			babyTeethValueArray[6] = webTeethMeasureVO.getT40();
			babyTeethValueArray[7] = webTeethMeasureVO.getT41();
			babyTeethValueArray[8] = webTeethMeasureVO.getT42();
			babyTeethValueArray[9] = webTeethMeasureVO.getT43();
			babyTeethValueArray[10] = webTeethMeasureVO.getT46();
			babyTeethValueArray[11] = webTeethMeasureVO.getT47();
			babyTeethValueArray[12] = webTeethMeasureVO.getT48();
			// 영구치 T23
			babyTeethValueArray[13] = webTeethMeasureVO.getT49();
			// 영구치 T24
			babyTeethValueArray[14] = webTeethMeasureVO.getT50();
			// 영구치 T25
			babyTeethValueArray[15] = webTeethMeasureVO.getT51();
			// 영구치 T26
			babyTeethValueArray[16] = webTeethMeasureVO.getT52();
			babyTeethValueArray[17] = webTeethMeasureVO.getT53();
			babyTeethValueArray[18] = webTeethMeasureVO.getT54();
			babyTeethValueArray[19] = webTeethMeasureVO.getT55();

			// 영구치 어금니 - 16, 26, 36, 46
			permanentMolarsValueArray[0] = webTeethMeasureVO.getT33();
			permanentMolarsValueArray[1] = webTeethMeasureVO.getT44();
			permanentMolarsValueArray[2] = webTeethMeasureVO.getT45();
			permanentMolarsValueArray[3] = webTeethMeasureVO.getT56();
			// 영구치 상악
			permTeethValueArray[0] = webTeethMeasureVO.getT07();
			permTeethValueArray[1] = webTeethMeasureVO.getT08();
			permTeethValueArray[2] = webTeethMeasureVO.getT09();
			permTeethValueArray[3] = webTeethMeasureVO.getT10();

			// 영구치 하악
			permTeethValueArray[4] = webTeethMeasureVO.getT23();
			permTeethValueArray[5] = webTeethMeasureVO.getT24();
			permTeethValueArray[6] = webTeethMeasureVO.getT25();
			permTeethValueArray[7] = webTeethMeasureVO.getT26();

			// 갯수 카운팅을 위한 임시 배열
			tmpBabyTeethValueArray[0] = webTeethMeasureVO.getT37();
			tmpBabyTeethValueArray[1] = webTeethMeasureVO.getT38();
			tmpBabyTeethValueArray[2] = webTeethMeasureVO.getT39();
			tmpBabyTeethValueArray[3] = webTeethMeasureVO.getT40();
			tmpBabyTeethValueArray[4] = webTeethMeasureVO.getT49();
			tmpBabyTeethValueArray[5] = webTeethMeasureVO.getT50();
			tmpBabyTeethValueArray[6] = webTeethMeasureVO.getT51();
			tmpBabyTeethValueArray[7] = webTeethMeasureVO.getT52();

			// 피측정자 진단 코드 조회
			userDiagCd = webTeethMeasureVO.getDiagCd();

			// 충치 단계 조회 (주의, 충치)
			cautionLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_CAUTION")));
			dangerLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_DANGER")));

			/** 유치 정상, 주의, 충치 개수 저장 **/
			for (int i = 0; i < babyTeethValueArray.length; i++) { // 측정자가 입력한 주의나 충치 값의 -1000
				if (babyTeethValueArray[i] > 1000) {
					babyTeethValueArray[i] = (int) babyTeethValueArray[i] - 1000;
				}
				if (babyTeethValueArray[i] < cautionLevel) { // 정상 치아는 -99이상 체크
					babyCvNormalCnt++;
				} else if (babyTeethValueArray[i] >= cautionLevel && babyTeethValueArray[i] < dangerLevel) {
					babyCvCautionCnt++;
				} else if (babyTeethValueArray[i] >= dangerLevel) {
					babyCvDangerCnt++;
				}
			}

			/** 영구치 상악 하악 정상, 주의, 충치 개수 저장 **/
			for (int i = 0; i < permTeethValueArray.length; i++) { // 측정자가 입력한 주의나 충치 값의 -1000
				if (permTeethValueArray[i] > 1000) {
					permTeethValueArray[i] = (int) permTeethValueArray[i] - 1000;
				}
				if (permTeethValueArray[i] >= cautionLevel && permTeethValueArray[i] < dangerLevel) {
					pmCvCautionCnt++;
				} else if (permTeethValueArray[i] >= dangerLevel) {
					pmCvDangerCnt++;
				}
			}

			for (int i = 0; i < tmpBabyTeethValueArray.length; i++) {
				if (tmpBabyTeethValueArray[i] > 1000) {
					tmpBabyTeethValueArray[i] = (int) tmpBabyTeethValueArray[i] - 1000;
				}
			}

			/** 영구치와 유치 두 개 다 값이 있을 경우 **/
			for (int i = 0; i < 8; i++) {
				if (permTeethValueArray[i] > 0 && tmpBabyTeethValueArray[i] > 0) {

					if (tmpBabyTeethValueArray[i] >= cautionLevel && tmpBabyTeethValueArray[i] < dangerLevel) {
						// 유치의 값이 주의 단계와 같거나 크고 주의 단계보다 작을때 :: 범위는 주의 단계이므로 유치의 주의 단계의 개수를 차감
						babyCvCautionCnt--;
					} else if (tmpBabyTeethValueArray[i] > dangerLevel) {
						// 유치의 값이 위험 단계보다 클때 :: 범위는 위험 단계 이므로 유치의 위험 단계의 개수를 차감
						babyCvDangerCnt--;
					}
				}

				// 영구치 측정 값이 있고 유치 측정값이 0이거나 -99일때 정상치아 갯수를 -1해준다
				if (permTeethValueArray[i] > 0 && tmpBabyTeethValueArray[i] <= 0) {
					babyCvNormalCnt--;
				}
			}

			/** 영구치 어금니 정상, 주의, 충치 개수 저장 **/
			for (int i = 0; i < permanentMolarsValueArray.length; i++) { // 측정자가 입력한 주의나 충치 값의 -1000
				if (permanentMolarsValueArray[i] > 1000) {
					permanentMolarsValueArray[i] = (int) permanentMolarsValueArray[i] - 1000;
				}
				if (permanentMolarsValueArray[i] >= cautionLevel && permanentMolarsValueArray[i] < dangerLevel) {
					pmCvCautionCnt++;
				} else if (permanentMolarsValueArray[i] >= dangerLevel) {
					pmCvDangerCnt++;
				}
			}

			if (userDiagCd != null && !"".equals(userDiagCd)) {
				diagCdArray = userDiagCd.split("\\|");
				List<String> list = new ArrayList<String>();
				diagDescript = "";
				for (int i = 0; i < diagCdArray.length; i++) {
					String[] diagCdStrArray = diagCdArray[i].split(":");
					if ("1".equals(diagCdStrArray[2])) {
						diagCdStr = diagCdStrArray[0] + diagCdStrArray[1];
						list.add(diagCdStr);
						if (diagCdStr.startsWith("A00") || diagCdStr.equals("B006") || diagCdStr.equals("E003")) {
							list.add("GA1");
						}
						// 진단 코드 존재 유무
						isDiagCnt++;
					}
				}
				// GA1 중복제거
				list = list.stream().distinct().collect(Collectors.toList());
				if (list.contains("GA1")) {
					// 중복 제거 후 마지막에 다시 붙여주는 작업
					list.remove("GA1");
					list.add("GA1");
				}

				for (int i = 0; i < list.size(); i++) {
					diagDescript += "&nbsp;" + webDiagnosisService.selectDiagDescript(list.get(i), teethType);
				}

				// 진단 키워드를 누르지 않으면 카운팅이 없으므로 이상없음
				if (isDiagCnt == 0) { // 하드코딩
					String diagTitle = webDiagnosisService.selectDiagTitle("", teethType);
					userDiagCd = userDiagCd.replaceAll("E:003:0", "E:003:1");
					diagDescript += "&nbsp;" + webDiagnosisService.selectDiagDescript("E003", teethType) + "<br/>";
					diagDescript = diagDescript.replaceAll("			", "");
					webTeethMeasureVO.setDiagTitle(diagTitle);
					webTeethMeasureVO.setDiagCd(userDiagCd);
				}
				diagDescript = diagDescript.replaceAll("			", "");
				webTeethMeasureVO.setDiagDescript(diagDescript);
			}

			// 진단 설명 : 진단 코드로 진단명을 검색하여 진단에 대한 설명 등록
			if (userDiagCd != null && !"".equals(userDiagCd)) {
				diagCdTitleArray = userDiagCd.split("\\|");
				for (int i = 0; i < diagCdTitleArray.length; i++) {
					String diagTitle = webDiagnosisService.selectDiagTitle(diagCdTitleArray[i], teethType);
					if (diagTitle != null) {
						webTeethMeasureVO.setDiagTitle(diagTitle);
						break;
					}
				}
				webTeethMeasureVO.setDiagCd(userDiagCd);
			}

			sheetNormalCnt = babyCvNormalCnt + pmCvNomalCnt;
			// 유치 주의 개수
			sheetCautionCnt = babyCvCautionCnt + pmCvCautionCnt;
			// 유치 충치 개수
			sheetdangerCnt = babyCvDangerCnt + pmCvDangerCnt;

			// 자녀 이름 등록
			webTeethMeasureVO.setUserName(userName);

			// 유치 정상, 주의, 충치 개수 입력
			webTeethMeasureVO.setCavityNormal(babyCvNormalCnt);
			webTeethMeasureVO.setCavityCaution(babyCvCautionCnt);
			webTeethMeasureVO.setCavityDanger(babyCvDangerCnt);
			// 영구치 정상, 주의, 충치 개수 입력
			webTeethMeasureVO.setPermCavityNormal(pmCvNomalCnt);
			webTeethMeasureVO.setPermCavityCaution(pmCvCautionCnt);
			webTeethMeasureVO.setPermCavityDanger(pmCvDangerCnt);

			// 진단 상세 정보 업데이트
			webTeethService.updateDiagDescript(userId, measureDt, diagDescript);
			// ST_STUDENT_USER_DETAIL 테이블에 CavityCnt 업데이트
			webTeethService.updateUserCavityCntByMeasureDt(webTeethMeasureVO);

			returnUrl = "/web/statistics/diagnosis_main";

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		//model.addAttribute("urlType", urlType);
		model.addAttribute("dataList", webTeethMeasureVO);
		model.addAttribute("schoolCode", schoolCode);
		model.addAttribute("measureDtList", measureDtList);
		model.addAttribute("cautionLevel", cautionLevel);
		model.addAttribute("dangerLevel", dangerLevel);
		model.addAttribute("cavityNormal", sheetNormalCnt);
		model.addAttribute("cavityCaution", sheetCautionCnt);
		model.addAttribute("cavityDanger", sheetdangerCnt);
		
		
		// 자문 치과 조회
		try {
			webAdviceVO = webOrganService.selectAdviceDentistInfo(schoolCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (webAdviceVO != null) { // 자문 치과가 있을 경우
			model.addAttribute("adviceDentistInfo", webAdviceVO);
			returnUrl = "/web/statistics/diagnosis_advice";
		} 
		
		return returnUrl; 	   
		
	}

	
	
	/**
	 * 작성자 : 정주현
	 * 작성일 : 2022. 11. 28
	 * 수정일 : 2023. 03. 28
	 * 기능 : 진단지 조회 (날짜 선택)
	 */
	@PostMapping(value = { "/web/statistics/ajaxDiagnosis"})
	@ResponseBody
	public HashMap<String, Object> ajaxDiagnosis(HttpServletRequest request, HttpSession session, Model model, @RequestBody Map<String, String> paramMap) throws Exception {

		// 리턴 맵
		HashMap<String, Object> hm = new HashMap<String, Object>();
		// 세션 값
		WebUserVO sessionVO = (WebUserVO) session.getAttribute("userInfo");
		
		if(sessionVO == null) {
			hm.put("code", "999");
			return hm;
		}
		
		String userId = null;
		String userName = null;
		String teethType = null;
		String schoolCode = sessionVO.getSchoolCode();
		// 측정일
		String measureDt = paramMap.get("measureDt");
		
		WebUserVO webUserVO = new WebUserVO();
		
		// 본인이 직접 로그인한 경우
		userId = sessionVO.getUserId();
		webUserVO = webUserService.selectUserInfo(userId);
		teethType = webUserVO.getTeethType();
		userName = sessionVO.getUserName();
		

		// 유치 개수 20개
		int[] babyTeethValueArray = null;
		// 영구치 개수 8개
		int[] permTeethValueArray = null;
		// 영구치 어금니 개수 4개
		int[] permanentMolarsValueArray = null;
		// 임시 유치 배열 20개
		int[] tmpBabyTeethValueArray = null;
		
		// 충치단계 값(주의)
		Integer cautionLevel = 0;
		// 충치단계 값(위험)
		Integer dangerLevel = 0;
	
		// 정상 유치 개수
		int babyCvNormalCnt = 0;
		// 주의 유치 개수
		int babyCvCautionCnt = 0;
		// 충치 유치 개수
		int babyCvDangerCnt = 0;
	
		// 정상 영구치 개수
		int pmCvNomalCnt = 0;
		// 주의 영구치 개수
		int pmCvCautionCnt = 0;
		// 충치 영구치 개수
		int pmCvDangerCnt = 0;
	
		// 유치+영구치 개수
		int sheetNormalCnt = 0;
		int sheetCautionCnt = 0;
		int sheetdangerCnt = 0;
		
		// 진단 코드
		String userDiagCd = null;
		// 진단 코드 분리한 배열
		String[] diagCdArray;
		// 진단 코드 문자열
		String diagCdStr = null;
		// 진단 코드 별 제목 배열
		String[] diagCdTitleArray;
		// 진단 코드 설명
		String diagDescript = "";
		// 진단 코드 카운트
		int isDiagCnt = 0;
	
		// 결제 정보에 따라서 보여지는 양을 다르게 조정 :: 기본 값은 3
		int limit = 100;
		
		WebTeethMeasureVO webTeethMeasureVO = new WebTeethMeasureVO();
		List<String> measureDtList = new ArrayList<String>();
		
		
			
		try {

			// 유치 개수 20개
			babyTeethValueArray = new int[20];
			// 영구치 개수 12개
			permTeethValueArray = new int[8];
			// 영구치 어금니 4개
			permanentMolarsValueArray = new int[4];
			// 영구치 위치에 해당하는 임시 유치 배열
			tmpBabyTeethValueArray = new int[8];

			// CAVITY_LEVEL 분류 부분 - 충치 단계별 수치 조회
			HashMap<String, Integer> cavityLevel = webTeethService.selectCavityLevel();

			// 치아 측정 VO
			webTeethMeasureVO.setUserId(userId);
			// 측정 값 보이는 개수
			webTeethMeasureVO.setLimit(limit);

			// 피측정자 최근 치아 측정 값 조회
			webTeethMeasureVO = webTeethService.selectUserMeasureValue(userId, measureDt);

			// 변경전 유치 배열 34~53까지 20개
			babyTeethValueArray[0] = webTeethMeasureVO.getT34();
			babyTeethValueArray[1] = webTeethMeasureVO.getT35();
			babyTeethValueArray[2] = webTeethMeasureVO.getT36();
			// 영구치 T07
			babyTeethValueArray[3] = webTeethMeasureVO.getT37();
			// 영구치 T08
			babyTeethValueArray[4] = webTeethMeasureVO.getT38();
			// 영구치 T09
			babyTeethValueArray[5] = webTeethMeasureVO.getT39();
			// 영구치 T10
			babyTeethValueArray[6] = webTeethMeasureVO.getT40();

			babyTeethValueArray[7] = webTeethMeasureVO.getT41();
			babyTeethValueArray[8] = webTeethMeasureVO.getT42();
			babyTeethValueArray[9] = webTeethMeasureVO.getT43();

			babyTeethValueArray[10] = webTeethMeasureVO.getT46();
			babyTeethValueArray[11] = webTeethMeasureVO.getT47();
			babyTeethValueArray[12] = webTeethMeasureVO.getT48();
			// 영구치 T23
			babyTeethValueArray[13] = webTeethMeasureVO.getT49();
			// 영구치 T24
			babyTeethValueArray[14] = webTeethMeasureVO.getT50();
			// 영구치 T25
			babyTeethValueArray[15] = webTeethMeasureVO.getT51();
			// 영구치 T26
			babyTeethValueArray[16] = webTeethMeasureVO.getT52();

			babyTeethValueArray[17] = webTeethMeasureVO.getT53();
			babyTeethValueArray[18] = webTeethMeasureVO.getT54();
			babyTeethValueArray[19] = webTeethMeasureVO.getT55();

			// 영구치 어금니 - 16, 26, 36, 46
			permanentMolarsValueArray[0] = webTeethMeasureVO.getT33();
			permanentMolarsValueArray[1] = webTeethMeasureVO.getT44();
			permanentMolarsValueArray[2] = webTeethMeasureVO.getT45();
			permanentMolarsValueArray[3] = webTeethMeasureVO.getT56();

			// 영구치 상악
			permTeethValueArray[0] = webTeethMeasureVO.getT07();
			permTeethValueArray[1] = webTeethMeasureVO.getT08();
			permTeethValueArray[2] = webTeethMeasureVO.getT09();
			permTeethValueArray[3] = webTeethMeasureVO.getT10();

			// 영구치 하악
			permTeethValueArray[4] = webTeethMeasureVO.getT23();
			permTeethValueArray[5] = webTeethMeasureVO.getT24();
			permTeethValueArray[6] = webTeethMeasureVO.getT25();
			permTeethValueArray[7] = webTeethMeasureVO.getT26();

			// 갯수 카운팅을 위한 임시 배열
			tmpBabyTeethValueArray[0] = webTeethMeasureVO.getT37();
			tmpBabyTeethValueArray[1] = webTeethMeasureVO.getT38();
			tmpBabyTeethValueArray[2] = webTeethMeasureVO.getT39();
			tmpBabyTeethValueArray[3] = webTeethMeasureVO.getT40();
			tmpBabyTeethValueArray[4] = webTeethMeasureVO.getT49();
			tmpBabyTeethValueArray[5] = webTeethMeasureVO.getT50();
			tmpBabyTeethValueArray[6] = webTeethMeasureVO.getT51();
			tmpBabyTeethValueArray[7] = webTeethMeasureVO.getT52();

			// 피측정자 진단 코드 조회
			userDiagCd = webTeethMeasureVO.getDiagCd();

			// 충치 단계 조회 (주의, 충치)
			cautionLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_CAUTION")));
			dangerLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_DANGER")));

			/** 유치 정상, 주의, 충치 개수 저장 **/
			for (int i = 0; i < babyTeethValueArray.length; i++) { // 측정자가 입력한 주의나 충치 값의 -1000
				if (babyTeethValueArray[i] > 1000) {
					babyTeethValueArray[i] = (int) babyTeethValueArray[i] - 1000;
				}
				if (babyTeethValueArray[i] < cautionLevel) { // 정상 치아는 -99이상 체크
					babyCvNormalCnt++;
				} else if (babyTeethValueArray[i] >= cautionLevel && babyTeethValueArray[i] < dangerLevel) {
					babyCvCautionCnt++;
				} else if (babyTeethValueArray[i] >= dangerLevel) {
					babyCvDangerCnt++;
				}
			}

			/** 영구치 상악 하악 정상, 주의, 충치 개수 저장 **/
			for (int i = 0; i < permTeethValueArray.length; i++) { // 측정자가 입력한 주의나 충치 값의 -1000
				if (permTeethValueArray[i] > 1000) {
					permTeethValueArray[i] = (int) permTeethValueArray[i] - 1000;
				}
				if (permTeethValueArray[i] >= cautionLevel && permTeethValueArray[i] < dangerLevel) {
					pmCvCautionCnt++;
				} else if (permTeethValueArray[i] >= dangerLevel) {
					pmCvDangerCnt++;
				}
			}

			for (int i = 0; i < tmpBabyTeethValueArray.length; i++) {
				if (tmpBabyTeethValueArray[i] > 1000) {
					tmpBabyTeethValueArray[i] = (int) tmpBabyTeethValueArray[i] - 1000;
				}
			}

			/** 영구치와 유치 두 개 다 값이 있을 경우 **/
			for (int i = 0; i < 8; i++) {
				if (permTeethValueArray[i] > 0 && tmpBabyTeethValueArray[i] > 0) {

					if (tmpBabyTeethValueArray[i] >= cautionLevel && tmpBabyTeethValueArray[i] < dangerLevel) { 
						// 유치의 값이 주의 단계와 같거나 크고 주의 단계보다 작을 때 :: 범위는 주의 단계 이므로 유치의 주의 단계의 개수를 차감
						babyCvCautionCnt--;
					} else if (tmpBabyTeethValueArray[i] > dangerLevel) { 
						// 유치의 값이 위험 단계보다 클때 :: 범위는 위험 단계 이므로 유치의 위험 단계의 개수를 차감
						babyCvDangerCnt--;
					}
				}

				// 영구치 측정 값이 있고 유치 측정값이 0이거나 -99일때 정상치아 갯수를 -1해준다
				if (permTeethValueArray[i] > 0 && tmpBabyTeethValueArray[i] <= 0) {
					babyCvNormalCnt--;
				}
			}

			/** 영구치 어금니 정상, 주의, 충치 개수 저장 **/
			for (int i = 0; i < permanentMolarsValueArray.length; i++) { // 측정자가 입력한 주의나 충치 값의 -1000
				if (permanentMolarsValueArray[i] > 1000) {
					permanentMolarsValueArray[i] = (int) permanentMolarsValueArray[i] - 1000;
				}
				if (permanentMolarsValueArray[i] >= cautionLevel && permanentMolarsValueArray[i] < dangerLevel) {
					pmCvCautionCnt++;
				} else if (permanentMolarsValueArray[i] >= dangerLevel) {
					pmCvDangerCnt++;
				}
			}


			if (userDiagCd != null && !"".equals(userDiagCd)) {
				diagCdArray = userDiagCd.split("\\|");
				List<String> list = new ArrayList<String>();
				diagDescript = "";
				for (int i = 0; i < diagCdArray.length; i++) {
					String[] diagCdStrArray = diagCdArray[i].split(":");
					if ("1".equals(diagCdStrArray[2])) {
						diagCdStr = diagCdStrArray[0] + diagCdStrArray[1];
						list.add(diagCdStr);
						if (diagCdStr.startsWith("A00") || diagCdStr.equals("B006") || diagCdStr.equals("E003")) {
							list.add("GA1");
						}
						// 진단 코드 존재 유무
						isDiagCnt++;
					}
				}
				// GA1 중복제거
				list = list.stream().distinct().collect(Collectors.toList());
				if (list.contains("GA1")) {
					// 중복 제거 후 마지막에 다시 붙여주는 작업
					list.remove("GA1");
					list.add("GA1");
				}

				for (int i = 0; i < list.size(); i++) {
					diagDescript += "&nbsp;" + webDiagnosisService.selectDiagDescript(list.get(i), teethType);
				}

				// 진단 키워드를 누르지 않으면 카운팅이 없으므로 이상없음
				if (isDiagCnt == 0) { // 하드코딩
					String diagTitle = webDiagnosisService.selectDiagTitle("", teethType);
					userDiagCd = userDiagCd.replaceAll("E:003:0", "E:003:1");
					diagDescript += "&nbsp;" + webDiagnosisService.selectDiagDescript("E003", teethType) + "<br/>";
					diagDescript = diagDescript.replaceAll("			", "");
					webTeethMeasureVO.setDiagTitle(diagTitle);
					webTeethMeasureVO.setDiagCd(userDiagCd);
				}
				diagDescript = diagDescript.replaceAll("			", "");
				webTeethMeasureVO.setDiagDescript(diagDescript);
			}

			// 진단 설명 : 진단 코드로 진단명을 검색하여 진단에 대한 설명 등록
			if (userDiagCd != null && !"".equals(userDiagCd)) {
				diagCdTitleArray = userDiagCd.split("\\|");
				for (int i = 0; i < diagCdTitleArray.length; i++) {
					String diagTitle = webDiagnosisService.selectDiagTitle(diagCdTitleArray[i], teethType);
					if (diagTitle != null) {
						webTeethMeasureVO.setDiagTitle(diagTitle);
						break;
					}
				}
				webTeethMeasureVO.setDiagCd(userDiagCd);
			}

			sheetNormalCnt = babyCvNormalCnt + pmCvNomalCnt;
			// 유치 주의 개수
			sheetCautionCnt = babyCvCautionCnt + pmCvCautionCnt;
			// 유치 충치 개수
			sheetdangerCnt = babyCvDangerCnt + pmCvDangerCnt;

			// 자녀 이름 등록
			webTeethMeasureVO.setUserName(userName);

			// 유치 정상, 주의, 충치 개수 입력
			webTeethMeasureVO.setCavityNormal(babyCvNormalCnt);
			webTeethMeasureVO.setCavityCaution(babyCvCautionCnt);
			webTeethMeasureVO.setCavityDanger(babyCvDangerCnt);
			// 영구치 정상, 주의, 충치 개수 입력
			webTeethMeasureVO.setPermCavityNormal(pmCvNomalCnt);
			webTeethMeasureVO.setPermCavityCaution(pmCvCautionCnt);
			webTeethMeasureVO.setPermCavityDanger(pmCvDangerCnt);

			// 진단 상세 정보 업데이트
			webTeethService.updateDiagDescript(userId, measureDt, diagDescript);
			// ST_STUDENT_USER_DETAIL 테이블에 CavityCnt 업데이트
			webTeethService.updateUserCavityCntByMeasureDt(webTeethMeasureVO);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		hm.put("dataList", webTeethMeasureVO);
		hm.put("schoolCode", schoolCode);
		hm.put("measureDtList", measureDtList);
		hm.put("cautionLevel", cautionLevel);
		hm.put("dangerLevel", dangerLevel);
		hm.put("cavityNormal", sheetNormalCnt);
		hm.put("cavityCaution", sheetCautionCnt);
		hm.put("cavityDanger", sheetdangerCnt);
		hm.put("code", "000");

		return hm;

	}
	
	
	
	/**
	 * 작성자 : 정주현
	 * 작성일 : 2023. 08. 11
	 * 수정일 : 2023. 08. 14
	 * 기능 : 관리자 페이지 >> 진단 결과지 (호출용)
	 */
	@GetMapping(value = { "/admin/statistics/diagnosis.do"})
	public String adminDiagnosis(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		WebUserVO webUserVO = new WebUserVO();

		// 피측정자 아이디
		String userId = request.getParameter("userId");
		// 등록번호
		String userNo = userId.substring(userId.lastIndexOf("-")+1, userId.length());
		// 치과 코드
		String dentalHospitalCd = userId.substring(0, userId.lastIndexOf("-"));
		// 최근 측정일
		String measureDt = request.getParameter("measureDt");
		
		webUserVO = webUserService.selectUserInfo(userId);
		// 피측정자 치아 형태
		String teethType = webUserVO.getTeethType();
		// 피측정자 이름
		String userName = webUserVO.getUserName();
		
		// 유치 개수 20개
		int[] babyTeethValueArray = null;
		// 영구치 개수 8개
		int[] permTeethValueArray = null;
		// 임시 유치 배열 20개
		int[] tmpBabyTeethValueArray = null;
		
		// 충치단계 값(주의)
		Integer cautionLevel = 0;
		// 충치단계 값(위험)
		Integer dangerLevel = 0;
	
		// 정상 유치 개수
		int babyCvNormalCnt = 0;
		// 주의 유치 개수
		int babyCvCautionCnt = 0;
		// 충치 유치 개수
		int babyCvDangerCnt = 0;
	
		// 정상 영구치 개수
		int pmCvNomalCnt = 0;
		// 주의 영구치 개수
		int pmCvCautionCnt = 0;
		// 충치 영구치 개수
		int pmCvDangerCnt = 0;
	
		// 진단 코드
		String userDiagCd = null;
		// 진단 코드 분리한 배열
		String[] diagCdArray;
		// 진단 코드 문자열
		String diagCdStr = null;
		// 진단 코드 별 제목 배열
		String[] diagCdTitleArray;
		// 진단 코드 설명
		String diagDescript = "";
		// 진단 코드 카운트
		int isDiagCnt = 0;
	
		// 결제 정보에 따라서 보여지는 양을 다르게 조정 :: 기본 값은 3
		int limit = 100;
		
		String returnUrl = null;
		
		WebTeethMeasureVO teethMeasureVO = new WebTeethMeasureVO();
		// List<String> measureDtList = new ArrayList<String>();
		
		try {
			// 유치 개수 20개
			babyTeethValueArray = new int[20];
			// 영구치 개수 12개
			permTeethValueArray = new int[32];
			// 영구치 위치에 해당하는 임시 유치 배열
			tmpBabyTeethValueArray = new int[20];

			// CAVITY_LEVEL 분류 부분 - 충치 단계별 수치 조회
			HashMap<String, Integer> cavityLevel = webTeethService.selectCavityLevel();

			// 치아 측정 VO
			teethMeasureVO.setUserId(userId);
			// 측정 값 보이는 개수
			teethMeasureVO.setLimit(limit);

			// 피측정자 최근 치아 측정 값 조회
			teethMeasureVO = webTeethService.selectUserMeasureValue(userId, measureDt);
			
			if(teethMeasureVO == null) {
				String year = measureDt.substring(0, measureDt.indexOf("-"));
				String month = measureDt.substring(measureDt.indexOf("-")+1, measureDt.lastIndexOf("-"));
				String date = measureDt.substring(measureDt.lastIndexOf("-")+1, measureDt.length());
				model.addAttribute("userNo", userNo);
				model.addAttribute("userName", userName);
				model.addAttribute("measureDt", year+"년 "+month+"월 "+date+"일");
				return "/error/noData";
			}

			
			// 치아 측정 값 배열 (혼합치 배열)
			// 변경전 유치 배열 34~53까지 20개 :: 33, 44, 45, 56 은 사용하지 않는다
			babyTeethValueArray[0] = teethMeasureVO.getT34();
			babyTeethValueArray[1] = teethMeasureVO.getT35();
			babyTeethValueArray[2] = teethMeasureVO.getT36();
			babyTeethValueArray[3] = teethMeasureVO.getT37();
			babyTeethValueArray[4] = teethMeasureVO.getT38();
			babyTeethValueArray[5] = teethMeasureVO.getT39();
			babyTeethValueArray[6] = teethMeasureVO.getT40();
			babyTeethValueArray[7] = teethMeasureVO.getT41();
			babyTeethValueArray[8] = teethMeasureVO.getT42();
			babyTeethValueArray[9] = teethMeasureVO.getT43();
			babyTeethValueArray[10] = teethMeasureVO.getT46();
			babyTeethValueArray[11] = teethMeasureVO.getT47();
			babyTeethValueArray[12] = teethMeasureVO.getT48();
			babyTeethValueArray[13] = teethMeasureVO.getT49();
			babyTeethValueArray[14] = teethMeasureVO.getT50();
			babyTeethValueArray[15] = teethMeasureVO.getT51();
			babyTeethValueArray[16] = teethMeasureVO.getT52();
			babyTeethValueArray[17] = teethMeasureVO.getT53();
			babyTeethValueArray[18] = teethMeasureVO.getT54();
			babyTeethValueArray[19] = teethMeasureVO.getT55();


			// 영구치 상악
			permTeethValueArray[0] = teethMeasureVO.getT01();
			permTeethValueArray[1] = teethMeasureVO.getT02();
			permTeethValueArray[2] = teethMeasureVO.getT03();
			permTeethValueArray[3] = teethMeasureVO.getT04();
			permTeethValueArray[4] = teethMeasureVO.getT05();
			permTeethValueArray[5] = teethMeasureVO.getT06();
			permTeethValueArray[6] = teethMeasureVO.getT07();
			permTeethValueArray[7] = teethMeasureVO.getT08();
			permTeethValueArray[8] = teethMeasureVO.getT09();
			permTeethValueArray[9] = teethMeasureVO.getT10();
			permTeethValueArray[10] = teethMeasureVO.getT11();
			permTeethValueArray[11] = teethMeasureVO.getT12();
			permTeethValueArray[12] = teethMeasureVO.getT13();
			permTeethValueArray[13] = teethMeasureVO.getT14();
			permTeethValueArray[14] = teethMeasureVO.getT15();
			permTeethValueArray[15] = teethMeasureVO.getT16();

			// 영구치 하악
			permTeethValueArray[16] = teethMeasureVO.getT17();
			permTeethValueArray[17] = teethMeasureVO.getT18();
			permTeethValueArray[18] = teethMeasureVO.getT19();
			permTeethValueArray[19] = teethMeasureVO.getT20();
			permTeethValueArray[20] = teethMeasureVO.getT21();
			permTeethValueArray[21] = teethMeasureVO.getT22();
			permTeethValueArray[22] = teethMeasureVO.getT23();
			permTeethValueArray[23] = teethMeasureVO.getT24();
			permTeethValueArray[24] = teethMeasureVO.getT25();
			permTeethValueArray[25] = teethMeasureVO.getT26();
			permTeethValueArray[26] = teethMeasureVO.getT27();
			permTeethValueArray[27] = teethMeasureVO.getT28();
			permTeethValueArray[28] = teethMeasureVO.getT29();
			permTeethValueArray[29] = teethMeasureVO.getT30();
			permTeethValueArray[30] = teethMeasureVO.getT31();
			permTeethValueArray[31] = teethMeasureVO.getT32();
			
			
			// 갯수 카운팅을 위한 임시 배열
			tmpBabyTeethValueArray[0] = teethMeasureVO.getT34(); 
			tmpBabyTeethValueArray[1] = teethMeasureVO.getT35(); 
			tmpBabyTeethValueArray[2] = teethMeasureVO.getT36(); 
			tmpBabyTeethValueArray[3] = teethMeasureVO.getT37(); 
			tmpBabyTeethValueArray[4] = teethMeasureVO.getT38(); 
			tmpBabyTeethValueArray[5] = teethMeasureVO.getT39(); 
			tmpBabyTeethValueArray[6] = teethMeasureVO.getT40(); 
			tmpBabyTeethValueArray[7] = teethMeasureVO.getT41(); 
			tmpBabyTeethValueArray[8] = teethMeasureVO.getT42(); 
			tmpBabyTeethValueArray[9] = teethMeasureVO.getT43(); 
			tmpBabyTeethValueArray[10] = teethMeasureVO.getT46();
			tmpBabyTeethValueArray[11] = teethMeasureVO.getT47();
			tmpBabyTeethValueArray[12] = teethMeasureVO.getT48();
			tmpBabyTeethValueArray[13] = teethMeasureVO.getT49();
			tmpBabyTeethValueArray[14] = teethMeasureVO.getT50();
			tmpBabyTeethValueArray[15] = teethMeasureVO.getT51();
			tmpBabyTeethValueArray[16] = teethMeasureVO.getT52();
			tmpBabyTeethValueArray[17] = teethMeasureVO.getT53();
			tmpBabyTeethValueArray[18] = teethMeasureVO.getT54();
			tmpBabyTeethValueArray[19] = teethMeasureVO.getT55();

			// 영구치 어금니 - 16, 26, 36, 46
			//tmpBabyTeethValueArray[0] = webTeethMeasureVO.getT33();
			//tmpBabyTeethValueArray[1] = webTeethMeasureVO.getT44();
			//tmpBabyTeethValueArray[2] = webTeethMeasureVO.getT45();
			//tmpBabyTeethValueArray[3] = webTeethMeasureVO.getT56();

			// 피측정자 진단 코드 조회
			userDiagCd = teethMeasureVO.getDiagCd();

			// 충치 단계 조회 (주의, 충치)
			cautionLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_CAUTION")));
			dangerLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_DANGER")));

			/** 유치 정상, 주의, 충치 개수 저장 **/
			for (int i = 0; i < babyTeethValueArray.length; i++) { // 측정자가 입력한 주의나 충치 값의 -1000
				if (babyTeethValueArray[i] > 1000) {
					babyTeethValueArray[i] = (int) babyTeethValueArray[i] - 1000;
				}
				if (babyTeethValueArray[i] < cautionLevel) { // 정상 치아는 -99이상 체크
					babyCvNormalCnt++;
				} else if (babyTeethValueArray[i] >= cautionLevel && babyTeethValueArray[i] < dangerLevel) {
					babyCvCautionCnt++;
				} else if (babyTeethValueArray[i] >= dangerLevel) {
					babyCvDangerCnt++;
				}
			}

			/** 영구치 상악 하악 정상, 주의, 충치 개수 저장 **/
			for (int i = 0; i < permTeethValueArray.length; i++) { // 측정자가 입력한 주의나 충치 값의 -1000
				if (permTeethValueArray[i] > 1000) {
					permTeethValueArray[i] = (int) permTeethValueArray[i] - 1000;
				}
				if (permTeethValueArray[i] >= cautionLevel && permTeethValueArray[i] < dangerLevel) {
					pmCvCautionCnt++;
				} else if (permTeethValueArray[i] >= dangerLevel) {
					pmCvDangerCnt++;
				} else {
					pmCvNomalCnt++;
				}
			}

			for (int i = 0; i < tmpBabyTeethValueArray.length; i++) {
				if (tmpBabyTeethValueArray[i] > 1000) {
					tmpBabyTeethValueArray[i] = (int) tmpBabyTeethValueArray[i] - 1000;
				}
			}

			
			// 진단 코드 관련 
			if (userDiagCd != null && !"".equals(userDiagCd)) {
				diagCdArray = userDiagCd.split("\\|");
				List<String> list = new ArrayList<String>();
				diagDescript = "";
				for (int i = 0; i < diagCdArray.length; i++) {
					String[] diagCdStrArray = diagCdArray[i].split(":");
					if ("1".equals(diagCdStrArray[2])) {
						diagCdStr = diagCdStrArray[0] + diagCdStrArray[1];
						list.add(diagCdStr);
						if (diagCdStr.startsWith("A00") || diagCdStr.equals("B006") || diagCdStr.equals("E003")) {
							list.add("GA1");
						}
						// 진단 코드 존재 유무
						isDiagCnt++;
					}
				}
				// GA1 중복제거
				list = list.stream().distinct().collect(Collectors.toList());
				if (list.contains("GA1")) {
					// 중복 제거 후 마지막에 다시 붙여주는 작업
					list.remove("GA1");
					list.add("GA1");
				}

				for (int i = 0; i < list.size(); i++) {
					diagDescript += "&nbsp;" + webDiagnosisService.selectDiagDescript(list.get(i), teethType);
				}

				// 진단 키워드를 누르지 않으면 카운팅이 없으므로 이상없음
				if (isDiagCnt == 0) { // 하드코딩
					String diagTitle = webDiagnosisService.selectDiagTitle("", teethType);
					userDiagCd = userDiagCd.replaceAll("E:003:0", "E:003:1");
					diagDescript += "&nbsp;" + webDiagnosisService.selectDiagDescript("E003", teethType) + "<br/>";
					diagDescript = diagDescript.replaceAll("			", "");
					teethMeasureVO.setDiagTitle(diagTitle);
					teethMeasureVO.setDiagCd(userDiagCd);
				}
				diagDescript = diagDescript.replaceAll("			", "");
				teethMeasureVO.setDiagDescript(diagDescript);
			}

			// 진단 설명 : 진단 코드로 진단명을 검색하여 진단에 대한 설명 등록
			if (userDiagCd != null && !"".equals(userDiagCd)) {
				diagCdTitleArray = userDiagCd.split("\\|");
				for (int i = 0; i < diagCdTitleArray.length; i++) {
					String diagTitle = webDiagnosisService.selectDiagTitle(diagCdTitleArray[i], teethType);
					if (diagTitle != null) {
						teethMeasureVO.setDiagTitle(diagTitle);
						break;
					}
				}
				teethMeasureVO.setDiagCd(userDiagCd);
			}

			// 자녀 이름 등록
			teethMeasureVO.setUserName(userName);
			// 유치 정상, 주의, 충치 개수 입력
			teethMeasureVO.setCavityNormal(babyCvNormalCnt);
			teethMeasureVO.setCavityCaution(babyCvCautionCnt);
			teethMeasureVO.setCavityDanger(babyCvDangerCnt);
			// 영구치 정상, 주의, 충치 개수 입력
			teethMeasureVO.setPermCavityNormal(pmCvNomalCnt);
			teethMeasureVO.setPermCavityCaution(pmCvCautionCnt);
			teethMeasureVO.setPermCavityDanger(pmCvDangerCnt);

			// 진단 상세 정보 업데이트
			webTeethService.updateDiagDescript(userId, measureDt, diagDescript);
			// ST_STUDENT_USER_DETAIL 테이블에 CavityCnt 업데이트
			webTeethService.updateUserCavityCntByMeasureDt(teethMeasureVO);

			returnUrl = "/web/statistics/diagnosis_main";

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		model.addAttribute("userNo", userNo);
		model.addAttribute("dataList", teethMeasureVO);
		model.addAttribute("schoolCode", dentalHospitalCd);
		model.addAttribute("measureDt", measureDt);
		model.addAttribute("cautionLevel", cautionLevel);
		model.addAttribute("dangerLevel", dangerLevel);
		model.addAttribute("pmCvDangerCnt", pmCvDangerCnt);
		model.addAttribute("pmCvCautionCnt", pmCvCautionCnt);
		model.addAttribute("babyCvDangerCnt", babyCvDangerCnt);
		model.addAttribute("babyCvCautionCnt", babyCvCautionCnt);
		
		return returnUrl; 	   
		
	}
	
	
	
	
	
	
	/**
	 * 기능   : 외부에서 측정 데이터 호출 시 반환해주는 API
	 * 작성자 : 정주현 
	 * 작성일 : 2023. 08. 14
	 */
	@PostMapping(value = {"/statistics/diagnosis.do"})
	@ResponseBody
	public HashMap<String,Object> duplicateChkId(@RequestBody HashMap<String, String> paramMap) throws Exception{

		
		logger.debug("========== Web-dentist.StatisticsController ========== /statistics/diagnosis.do ==========");
		logger.debug("========== Web-dentist.StatisticsController ========== /statistics/diagnosis.do ==========");
		logger.debug("========== Web-dentist.StatisticsController ========== /statistics/diagnosis.do ==========");
		logger.debug("========== Web-dentist.StatisticsController ========== /statistics/diagnosis.do ==========");
		
		// 리턴 맵
		HashMap<String,Object> hm = new HashMap<String,Object>();

		// 피측정자 등록 번호
		// 해당 아이디의 경우 치과 코드와 치과 등록번호가 합쳐진 것으로 외부에서 아이디를 파라미터로 전달할 때 치과 코드도 같이 넘겨줘야하며, 등록번호도 동일하게 하는 것으로 해야 외부에서 API를 호출할 수 있음
		String userId = paramMap.get("userId");
		// 최근 측정일
		String measureDt = paramMap.get("measureDt");
		// 치과 코드
		String dentalHospitalCd = paramMap.get("dentalHospitalCd");
		//String dentalHospitalCd = userId.substring(0, userId.lastIndexOf("-"));
		// 치과 코드 + 등록번호 = 피측정자 아이디
		userId = dentalHospitalCd+"-"+userId;
		
		// Parameter = userEmail 값 검증 (Null 체크 및 공백 체크)
		if(userId == null || userId.equals("")) {
			hm.put("code", "401");
			hm.put("msg", "아이디(등록번호)가 전달되지 않았습니다.");
			return hm;
		}
		if(measureDt == null || measureDt.equals("")) {
			hm.put("code", "401");
			hm.put("msg", "측정일이 전달되지 않았습니다.");
			return hm;
		}
		if(dentalHospitalCd == null || dentalHospitalCd.equals("")) {
			hm.put("code", "401");
			hm.put("msg", "치과코드가 전달되지 않았습니다.");
			return hm;
		}
		
		// 유치 개수 20개
		int[] babyTeethValueArray = new int[20];
		// 영구치 개수 32개
		int[] permTeethValueArray = new int[32];
		
		// CAVITY_LEVEL 분류 부분 - 충치 단계별 수치 조회
		HashMap<String, Integer> cavityLevel = webTeethService.selectCavityLevel();
		// 충치단계 값(주의)
		Integer cautionLevel = 0;
		// 충치단계 값(위험)
		Integer dangerLevel = 0;
	
		// 정상 유치 개수
		int babyNormalCnt = 0;
		// 주의 유치 개수
		int babyCautionCnt = 0;
		// 충치 유치 개수
		int babyDangerCnt = 0;
		// 정상 영구치 개수
		int pmNomalCnt = 0;
		// 주의 영구치 개수
		int pmCautionCnt = 0;
		// 충치 영구치 개수
		int pmDangerCnt = 0;
		
		// WebTeethMeasureVO teethMeasureVO = new WebTeethMeasureVO();
		HashMap<String, Object> teethMeasureVO = new HashMap<String, Object>();
		
		try {
			
			// 피측정자 최근 치아 측정 값 조회
			teethMeasureVO = webTeethService.selectUserMeasureValueAPI(userId, measureDt);
			
			if(teethMeasureVO == null) {
				// 해당 측정일에 측정 자료가 없을 경우
				hm.put("code", "402");
				hm.put("msg", "측정 기록이 없습니다.");
			}
			
			// 유치 - 33, 44, 45, 56 은 사용하지 않는다 
			babyTeethValueArray[0] =  Integer.parseInt(teethMeasureVO.get("T55").toString());
			babyTeethValueArray[1] = Integer.parseInt(teethMeasureVO.get("T54").toString());
			babyTeethValueArray[2] = Integer.parseInt(teethMeasureVO.get("T53").toString());
			babyTeethValueArray[3] = Integer.parseInt(teethMeasureVO.get("T52").toString());
			babyTeethValueArray[4] = Integer.parseInt(teethMeasureVO.get("T51").toString());
			babyTeethValueArray[5] = Integer.parseInt(teethMeasureVO.get("T61").toString());
			babyTeethValueArray[6] = Integer.parseInt(teethMeasureVO.get("T62").toString());
			babyTeethValueArray[7] = Integer.parseInt(teethMeasureVO.get("T63").toString());
			babyTeethValueArray[8] = Integer.parseInt(teethMeasureVO.get("T64").toString());
			babyTeethValueArray[9] = Integer.parseInt(teethMeasureVO.get("T65").toString());
			babyTeethValueArray[10] = Integer.parseInt(teethMeasureVO.get("T75").toString());
			babyTeethValueArray[11] = Integer.parseInt(teethMeasureVO.get("T74").toString());
			babyTeethValueArray[12] = Integer.parseInt(teethMeasureVO.get("T73").toString());
			babyTeethValueArray[13] = Integer.parseInt(teethMeasureVO.get("T72").toString());
			babyTeethValueArray[14] = Integer.parseInt(teethMeasureVO.get("T71").toString());
			babyTeethValueArray[15] = Integer.parseInt(teethMeasureVO.get("T81").toString());
			babyTeethValueArray[16] = Integer.parseInt(teethMeasureVO.get("T82").toString());
			babyTeethValueArray[17] = Integer.parseInt(teethMeasureVO.get("T83").toString());
			babyTeethValueArray[18] = Integer.parseInt(teethMeasureVO.get("T84").toString());
			babyTeethValueArray[19] = Integer.parseInt(teethMeasureVO.get("T85").toString());

			// 영구치 상악
			permTeethValueArray[0] = Integer.parseInt(teethMeasureVO.get("T18").toString());
			permTeethValueArray[1] = Integer.parseInt(teethMeasureVO.get("T17").toString());
			permTeethValueArray[2] = Integer.parseInt(teethMeasureVO.get("T16").toString());
			permTeethValueArray[3] = Integer.parseInt(teethMeasureVO.get("T15").toString());
			permTeethValueArray[4] = Integer.parseInt(teethMeasureVO.get("T14").toString());
			permTeethValueArray[5] = Integer.parseInt(teethMeasureVO.get("T13").toString());
			permTeethValueArray[6] = Integer.parseInt(teethMeasureVO.get("T12").toString());
			permTeethValueArray[7] = Integer.parseInt(teethMeasureVO.get("T11").toString());
			permTeethValueArray[8] = Integer.parseInt(teethMeasureVO.get("T21").toString());
			permTeethValueArray[9] = Integer.parseInt(teethMeasureVO.get("T22").toString());
			permTeethValueArray[10] = Integer.parseInt(teethMeasureVO.get("T23").toString());
			permTeethValueArray[11] = Integer.parseInt(teethMeasureVO.get("T24").toString());
			permTeethValueArray[12] = Integer.parseInt(teethMeasureVO.get("T25").toString());
			permTeethValueArray[13] = Integer.parseInt(teethMeasureVO.get("T26").toString());
			permTeethValueArray[14] = Integer.parseInt(teethMeasureVO.get("T27").toString());
			permTeethValueArray[15] = Integer.parseInt(teethMeasureVO.get("T28").toString());
			// 영구치 하악
			permTeethValueArray[16] = Integer.parseInt(teethMeasureVO.get("T38").toString()); 
			permTeethValueArray[17] = Integer.parseInt(teethMeasureVO.get("T37").toString()); 
			permTeethValueArray[18] = Integer.parseInt(teethMeasureVO.get("T36").toString()); 
			permTeethValueArray[19] = Integer.parseInt(teethMeasureVO.get("T35").toString()); 
			permTeethValueArray[20] = Integer.parseInt(teethMeasureVO.get("T34").toString()); 
			permTeethValueArray[21] = Integer.parseInt(teethMeasureVO.get("T33").toString()); 
			permTeethValueArray[22] = Integer.parseInt(teethMeasureVO.get("T32").toString()); 
			permTeethValueArray[23] = Integer.parseInt(teethMeasureVO.get("T31").toString()); 
			permTeethValueArray[24] = Integer.parseInt(teethMeasureVO.get("T41").toString()); 
			permTeethValueArray[25] = Integer.parseInt(teethMeasureVO.get("T42").toString()); 
			permTeethValueArray[26] = Integer.parseInt(teethMeasureVO.get("T43").toString());
			permTeethValueArray[27] = Integer.parseInt(teethMeasureVO.get("T44").toString());
			permTeethValueArray[28] = Integer.parseInt(teethMeasureVO.get("T45").toString());
			permTeethValueArray[29] = Integer.parseInt(teethMeasureVO.get("T46").toString());
			permTeethValueArray[30] = Integer.parseInt(teethMeasureVO.get("T47").toString());
			permTeethValueArray[31] = Integer.parseInt(teethMeasureVO.get("T48").toString());
			
			
			// 충치 단계 조회 (주의, 충치)
			cautionLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_CAUTION")));
			dangerLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_DANGER")));
			
			
			/** 유치 정상, 주의, 충치 개수 저장 **/
			for (int i = 0; i < babyTeethValueArray.length; i++) { // 측정자가 입력한 주의나 충치 값의 -1000
				if (babyTeethValueArray[i] > 1000) {
					babyTeethValueArray[i] = (int) babyTeethValueArray[i] - 1000;
				}
				if (babyTeethValueArray[i] < cautionLevel) { // 정상 치아는 -99이상 체크
					babyNormalCnt++;
				} else if (babyTeethValueArray[i] >= cautionLevel && babyTeethValueArray[i] < dangerLevel) {
					babyCautionCnt++;
				} else if (babyTeethValueArray[i] >= dangerLevel) {
					babyDangerCnt++;
				}
			}

			/** 영구치 상악 하악 정상, 주의, 충치 개수 저장 **/
			for (int i = 0; i < permTeethValueArray.length; i++) { // 측정자가 입력한 주의나 충치 값의 -1000
				if (permTeethValueArray[i] > 1000) {
					permTeethValueArray[i] = (int) permTeethValueArray[i] - 1000;
				}
				if (permTeethValueArray[i] >= cautionLevel && permTeethValueArray[i] < dangerLevel) {
					pmCautionCnt++;
				} else if (permTeethValueArray[i] >= dangerLevel) {
					pmDangerCnt++;
				} else {
					pmNomalCnt++;
				}
			}
			
		} catch (Exception e) {
			hm.put("code", "500");
			hm.put("msg", "조회 실패\n관리자에게 문의해주시기 바랍니다.");
			e.printStackTrace();
		}
		
		hm.put("babyNormalCnt", babyNormalCnt);
		hm.put("babyCautionCnt", babyCautionCnt);
		hm.put("babyDangerCnt", babyDangerCnt);
		hm.put("pmNomalCnt", pmNomalCnt);
		hm.put("pmCautionCnt", pmCautionCnt);
		hm.put("pmDangerCnt", pmDangerCnt);
		hm.put("dataList", teethMeasureVO);
		hm.put("code", "000");
		hm.put("msg", "조회 성공");
		
		return hm;
	}
	
	
	
	
	
	
//	/**
//	 * 작성자 : 정주현
//	 * 작성일 : 2022. 11. 28
//	 * 수정일 : 2023. 03. 28
//	 * 기능 : 진단지 >> 그래프 조회 (최근 측정일)
//	 * @throws Exception 
//	 */
//	@SuppressWarnings("unused")
//	// location.href로 요청이 들어옴
//	@GetMapping(value = { "/web/statistics/graph.do" })
//	public String graph(HttpServletRequest request, HttpSession session, Model model) {
//
//		// 세션 값
//		WebUserVO sessionVO = (WebUserVO) session.getAttribute("userInfo");
//		// 언어팩
//		String lang = (String)session.getAttribute("lang");
//		
//		// return url
//		String returnUrl = null;
//		
//		if (sessionVO == null) {
//			if(lang.equals("ko")) {
//				return "/web/statistics/login/loginForm";
//			}else if(lang.equals("en")) {
//				return "web/statistics/login/statisticsLoginForm";
//			}
//		}
//		 
//		try {
//
//			String paUserId = null;
//			String userId = null;
//			String userName = null;
//			String userType = sessionVO.getUserType();
//			String schoolCode = sessionVO.getSchoolCode();
//			
//			if(userType.equals("PR")) {
//				paUserId = sessionVO.getUserId();
//				// 법정대리인 아이디로 피측정자 아이디 조회
//				userId = webUserService.selectStUserId(paUserId);
//				// 법정대리인 아이디로 피측정자 이름 조회
//				userName = webUserService.selectChUserName(userId);
//			}else {
//				userId = sessionVO.getUserId();
//				userName = sessionVO.getUserName();
//			}
//			
//			String measureDt = null;
//			String schoolName = null;
//
//			double deteriorateMaxScore = 0;
//
//
//			// 결제 정보에 따라서 보여지는 양을 다르게 조정 :: 기본 값은 3
//			int limit = 100;
//
//			WebTeethMeasureVO webTeethMeasureVO = new WebTeethMeasureVO();
//			List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
//			List<String> measureDtList = new ArrayList<String>();
//			List<Double> deteriorateScordList = new ArrayList<Double>();
//			List<Integer> cavityCntList = new ArrayList<Integer>();
//			List<String> userNameList = new ArrayList<String>();
//			List<Double> userFearScoreList = new ArrayList<Double>();
//
//			// 기관 코드
//			schoolCode = sessionVO.getSchoolCode();
//			// 치아 측정 VO
//			webTeethMeasureVO.setUserId(userId);
//			webTeethMeasureVO.setLimit(limit);
//			// 피측정자 치아 측정일 목록
//			measureDtList = webTeethService.selectUserMeasureDtList(webTeethMeasureVO);
//			// 최근 측정일
//			measureDt = measureDtList.get(0);
//			// 기관 내 피측정자 데이터 통계 작업 후 조회
//			dataList = webTeethService.selectUserMeasureStatisticsList(schoolCode, measureDt);
//			// 기관명 
//			schoolName = (String)dataList.get(0).get("SCHOOL_NAME");
//			
//			for (int i = 0; i < dataList.size(); i++) {
//				// 악화 지수 초기화
//				double fearScore = 0;
//				// 피측정자 아이디
//				String stUserId = (String) dataList.get(i).get("USER_ID");
//				// 피측정자 이름
//				String stUserName = (String) dataList.get(i).get("USER_NAME");
//				// 피측정자 진단 태그 항목
//				String diagCd = (String) dataList.get(i).get("DIAG_CD");
//				// 피측정자 개월 수
//				long monthcount = (long) dataList.get(i).get("MONTH_COUNT");
//				// 피측정자 악화지수
//				double deteriorateScore = 0;
//
//				// 유치 및 영구치 >>  주의(caution) 치아 및 충치(danger) 치아 개수
//				int cavityCautionCnt = Integer.parseInt(dataList.get(i).get("CAVITY_CAUTION").toString());
//				int cavityDangerCnt = Integer.parseInt(dataList.get(i).get("CAVITY_DANGER").toString());
//				int permCavityCautionCnt = Integer.parseInt(dataList.get(i).get("PERM_CAVITY_CAUTION").toString());
//				int permCavityDangerCnt = Integer.parseInt(dataList.get(i).get("PERM_CAVITY_DANGER").toString());
//				
//				// 악화 지수
//				deteriorateScore = (double) dataList.get(i).get("DETERIORATE_SCORE");
//				// 악화 지수 최대값 저장
//				if (deteriorateMaxScore < deteriorateScore) {
//					deteriorateMaxScore = deteriorateScore;
//				}
//
//				// 진단지 조회한 회원과 이름리스트의 이름이 일치하지 않을 경우 = 001~999 로 표기
//				if (!userName.equals(stUserName)) {
//					stUserName = "\'" + String.format("%03d", i + 1) + "(" + monthcount + ")\'";
//				} else {
//					stUserName = "\'" + stUserName + "(" + monthcount + ")\'";
//				}
//
//				if (deteriorateScore > 0 && diagCd.contains("A:22:1")) {
//					// 측정 두려움 항목이 있을 경우 *별표 표시
//					stUserName = stUserName.replaceAll("'", "");
//					stUserName = "\'" + stUserName + "*\'";
//					fearScore = 0;
//				} else if (diagCd.contains("A:22:1")) {
//					// 측정 두려움 항목만 있을 경우 MAX 값을 지정
//					fearScore = Math.round(deteriorateMaxScore);
//				}
//
//				if(fearScore % 5 != 0) {
//					int calFearScore = (int)fearScore % 5;
//					switch(calFearScore) {
//					case 1: fearScore = fearScore+4.0;
//						break;
//					case 2: fearScore = fearScore+3.0;
//						break;
//					case 3: fearScore = fearScore+2.0;
//						break;
//					case 4: fearScore = fearScore+1.0;
//						break;
//					}
//				}
//				userNameList.add(stUserName);
//				userFearScoreList.add(fearScore);
//				cavityCntList.add(cavityDangerCnt + permCavityDangerCnt);
//				deteriorateScordList.add(deteriorateScore);
//			}
//			
//			
//			// 피측정자 아이디
//			model.addAttribute("userId", userId);
//			// 피측정자 이름
//			model.addAttribute("userName", userName);
//			// 피측정자 소속 기관 코드
//			model.addAttribute("schoolCode", schoolCode);
//			// 피측정자 소속 기관 이름
//			model.addAttribute("schoolName", schoolName);
//			
//			// 기관 내 측정 인원 수
//			model.addAttribute("userCount", dataList.size());
//			// 치아 측정일 목록
//			model.addAttribute("measureDtList", measureDtList);
//			// 기관 내 측정 인원 이름 목록
//			model.addAttribute("userNameList", userNameList);
//			// 기관 내 측정 인원 악화 지수 목록
//			model.addAttribute("deteriorateScordList", deteriorateScordList);
//			// 기관 내 측정 인원 충치 개수 목록
//			model.addAttribute("cavityCntList", cavityCntList);
//			// 기관 내 측정 두려움 인원 목록
//			model.addAttribute("userFearScoreList", userFearScoreList);
//			
////			if(lang.equals("ko")) {
//				returnUrl =  "/web/statistics/graph_main_ko";
////			}else if(lang.equals("en")){
////				returnUrl =  "/web/statistics/graph_main_en";
////			}
//
//		} catch (Exception e) {
//			model.addAttribute("msg", "세션시간이 만료되었습니다. 다시 로그인해주시기 바랍니다.");
//			model.addAttribute("loginUrl", loginUrl + "/login");
//			returnUrl = "/common/alertMessage";
//		}
//		
//		return returnUrl;
//		
//	}


	
	
//	/**
//	 * 작성자 : 정주현
//	 * 작성일 : 2022. 11. 28
//	 * 수정일 : 2023. 03. 28
//	 * 기능 : 셀렉트 박스 >> 그래프 조회 (파라미터 : 측정일)
//	 * @throws Exception 
//	 */
//	@SuppressWarnings("unused")
//	@PostMapping(value = { "/web/statistics/ajaxGraph"})
//	@ResponseBody
//	public HashMap<String, Object> ajaxGraph(HttpServletRequest request, HttpSession session, Model model, @RequestBody Map<String, String> paramMap) throws Exception {
//
//		// 세션 값
//		WebUserVO sessionVO = (WebUserVO) session.getAttribute("userInfo");
//		HashMap<String, Object> hm = new HashMap<String, Object>();
//		
//		try {
//			
//			String selectType = (String)paramMap.get("selectType");
//			
//			/** 파라미터 **/
//			// 피측정자 아이디
//			String userId = (String)paramMap.get("userId");
//			// 측정일
//			String measureDt = (String)paramMap.get("measureDt");
//			// 기관코드
//			String schoolCode = sessionVO.getSchoolCode();
//			
//			// 피측정자 이름
//			String userName = null;
//			// 악화지수 최대 값
//			double deteriorateMaxScore = 0;
//
//			WebTeethMeasureVO webTeethMeasureVO = new WebTeethMeasureVO();
//			List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
//			List<Double> deteriorateScordList = new ArrayList<Double>();
//			List<Integer> cavityCntList = new ArrayList<Integer>();
//			List<String> userNameList = new ArrayList<String>();
//			List<Double> userFearScoreList = new ArrayList<Double>();
//			
//			// 법정대리인 아이디로 피측정자 이름 조회
//			userName = webUserService.selectChUserName(userId);
//			// 기관 내 피측정자 데이터 통계 작업 후 조회
//			dataList = webTeethService.selectUserMeasureStatisticsList(schoolCode, measureDt);
//
//			for (int i = 0; i < dataList.size(); i++) {
//				// 두려움 지수
//				double fearScore = 0;
//				// 악화지수 초기화
//				double deteriorateScore = 0;
//				// 피측정자 아이디
//				String stUserId = (String) dataList.get(i).get("USER_ID");
//				// 피측정자 이름
//				String stUserName = (String) dataList.get(i).get("USER_NAME");
//				// 피측정자 진단 태그 항목
//				String diagCd = (String) dataList.get(i).get("DIAG_CD");
//				// 피측정자 개월 수
//				long monthcount = (long) dataList.get(i).get("MONTH_COUNT");
//
//				// 유치 및 영구치 >>  주의(caution) 치아 및 충치(danger) 치아 개수
//				int cavityCautionCnt = Integer.parseInt(dataList.get(i).get("CAVITY_CAUTION").toString());
//				int cavityDangerCnt = Integer.parseInt(dataList.get(i).get("CAVITY_DANGER").toString());
//				int permCavityCautionCnt = Integer.parseInt(dataList.get(i).get("PERM_CAVITY_CAUTION").toString());
//				int permCavityDangerCnt = Integer.parseInt(dataList.get(i).get("PERM_CAVITY_DANGER").toString());
//
//				// 악화지수
//				deteriorateScore = (double) dataList.get(i).get("DETERIORATE_SCORE");
//				// 악화지수 최대값 저장
//				if (deteriorateMaxScore < deteriorateScore) {
//					deteriorateMaxScore = deteriorateScore;
//				}
//
//				if(selectType != null && !selectType.equals("")) {
//					stUserName = stUserName + "(" + monthcount + ")";
//				}else {
//					// 진단지 조회한 회원과 이름리스트의 이름이 일치하지 않을 경우 = 001~999로 표기
//					if (!userName.equals(stUserName)) {
//						stUserName = String.format("%03d", i + 1) + "(" + monthcount + ")";
//					} else {
//						stUserName = stUserName + "(" + monthcount + ")";
//					}
//				}
//				
//				if (deteriorateScore > 0 && diagCd.contains("A:22:1")) {
//					// 측정 두려움 항목이 있을 경우 *별표 표시
//					stUserName = stUserName.replaceAll("'", "");
//					stUserName = "\'" + stUserName + "*\'";
//					fearScore = 0;
//				} else if (diagCd.contains("A:22:1")) {
//					// 측정 두려움 항목만 있을 경우 MAX 값을 지정
//					fearScore = Math.round(deteriorateMaxScore);
//				}
//
//				if(fearScore % 5 != 0) {
//					int calFearScore = (int)fearScore % 5;
//					switch(calFearScore) {
//					case 1: fearScore = fearScore+4.0;
//						break;
//					case 2: fearScore = fearScore+3.0;
//						break;
//					case 3: fearScore = fearScore+2.0;
//						break;
//					case 4: fearScore = fearScore+1.0;
//						break;
//					}
//				}
//				userNameList.add(stUserName);
//				userFearScoreList.add(fearScore);
//				cavityCntList.add(cavityDangerCnt + permCavityDangerCnt);
//				deteriorateScordList.add(deteriorateScore);
//			}
//			
//			// 피측정자 아이디
//			hm.put("userId", userId);
//			// 피측정자 이름
//			hm.put("userName", userName);
//			// 피측정자 소속 기관 코드
//			hm.put("schoolCode", schoolCode);
//			// 기관 내 측정 인원 수
//			hm.put("userCount", dataList.size());
//			// 기관 내 측정 인원 이름 목록
//			hm.put("userNameList", userNameList);
//			// 기관 내 측정 인원 악화 지수 목록
//			hm.put("deteriorateScordList", deteriorateScordList);
//			// 기관 내 측정 인원 충치 개수 목록
//			hm.put("cavityCntList", cavityCntList);
//			// 기관 내 측정 두려움 인원 목록
//			hm.put("userFearScoreList", userFearScoreList);
//			
//			hm.put("code", "000");
//			hm.put("msg", "success");
//			return hm;
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			hm.put("code", "500");
//			hm.put("msg", "Server Error");
//			return hm;
//			
//		}
//	}

	
	
//	/**
//	 * 작성자 : 정주현
//	 * 작성일 : 2022. 03. 28
//	 * 기능 : 로그인 >> 진단지 & 그래프 (기관장용)
//	 */
//	@GetMapping(value = { "/web/statistics/integrateStatistics.do"})
//	public String integrateStatistics(HttpServletRequest request, HttpSession session, Model model) throws Exception {
//		
//		// 세션 값
//		WebUserVO sessionVO = (WebUserVO) session.getAttribute("userInfo");
//		// 언어팩
//		String lang = (String)session.getAttribute("lang");
//		// Url 타입
//		String urlType = (String) model.getAttribute("urlType");
//		if(lang == null || lang.equals("")) {
//			lang = "ko";
//		}
//		
//		// 모든 쿠키 가져오기
//		Cookie[] cookies = request.getCookies(); 
//	    
//		if(cookies!=null){
//	        for (Cookie c : cookies) {
//	        	// 쿠키 이름 가져오기
//	            String name = c.getName();
//	            // 쿠키 값 가져오기
//	            String value = c.getValue();
//	            if (name.equals("lang")) {
//	                lang =  value;
//	            }
//	            if (name.equals("urlType")) {
//	            	urlType =  value;
//	            }
//	        }
//	    }
//		
//		// String returnUrl = null;
//		
//		if (sessionVO == null) {
//			model.addAttribute("msg", "세션시간이 만료되었습니다. 다시 로그인해주시기 바랍니다.");
//			model.addAttribute("loginUrl", loginUrl+"/director/login");
//			return "/common/alertMessage";
//		}
//		
//		boolean isMeasureDt = false;
//		
//		String userId = null;
//		String userName = null;
//		String paUserId = sessionVO.getUserId();
//		
//		String schoolCode = sessionVO.getSchoolCode();
//		String schoolName = null;
//		String className = null;
//		
//		String classCode = null;
//		
//		int userCount = 0;
//
//		// 유치 개수 24개
//		int[] babyTeethValueArray = new int[20];
//		// 영구치 개수 8개
//		int[] permanentTeethValueArray = new int[8];
//		// 영구치 어금니 4개
//		int[] permanentMolarsValueArray = new int[4];
//		// 영구치 위치에 해당하는 임시 유치 배열
//		int[] tmpBabyTeethValueArray = new int[8];
//		
//		
//		// 충치단계 값(주의)
//		Integer cautionLevel = 0;
//		// 충치단계 값(위험)
//		Integer dangerLevel = 0;
//
//		// 정상 유치 개수
//		int babyCvNormalCnt = 0;
//		// 주의 유치 개수
//		int babyCvCautionCnt = 0;
//		// 충치 유치 개수
//		int babyCvDangerCnt = 0;
//		
//		// 정상 영구치 개수
//		int pmCvNomalCnt = 0;
//		// 주의 영구치 개수
//		int pmCvCautionCnt = 0;
//		// 충치 영구치 개수
//		int pmCvDangerCnt = 0;
//
//		// 유치+영구치 개수
//		int sheetNormalCnt = 0;
//		int sheetCautionCnt = 0;
//		int sheetdangerCnt = 0;
//
//		/** 진단 코드 및 진단 내용 관련 변수 **/
//		// 회원 진단 코드
//		String userDiagCd = null;
//		// 임시 진단 코드
//		String tmpUserDiagCd = null;
//		// 진단 코드 분리 배열
//		String[] diagCdArray;
//		// 진단 코드 문자열
//		String diagCdStr = null;
//		// 진단 코드 별 제목 배열
//		String[] diagCdTitleArray;
//		// 진단 코드 설명
//		String diagDescript = "";
//		// 진단 항목
//		String diagList = "";
//		// 진단 항목 배열
//		String[] diagListArray = null;
//		// 진단 코드 카운트
//		int isDiagCnt = 0;
//
//		/**
//		 * 결제 정보에 따라서 보여지는 양을 다르게 조정 :: 기본 값은 3
//		 **/
//		int limit = 100;
//		
//		// 영구치 상악의 수치값 존재 여부
//		boolean isExistPermMaxillaryValue = false;
//		// 영구치 하악의 수치값 존재 여부
//		boolean isExistPermMandibularValue = false;
//		
//		// CAVITY_LEVEL 분류 부분 - 충치 단계별 수치 조회
//		HashMap<String, Integer> cavityLevel = webTeethService.selectCavityLevel();
//
//		
//		WebTeethMeasureVO webTeethMeasureVO = new WebTeethMeasureVO();
//		WebAdviceVO webAdviceVO = new WebAdviceVO();
//		List<String> measureDtList = new ArrayList<String>();
//		
//		List<HashMap<String, Object>> measureDtListMap = new ArrayList<HashMap<String, Object>> ();
//		List<String> organMeasureDtList = new ArrayList<String>();
//		
//		List<HashMap<String, Object>> departList = new ArrayList<HashMap<String, Object>>();
//		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
//		Map<Integer, List<HashMap<String, Object>>> departUserList = new HashMap<Integer, List<HashMap<String, Object>>>();
//		
//		List<String> userNameList = new ArrayList<String>();
//		
//
//		// 데이터베이스의 충치 단계 조회 (주의, 충치)
//		cautionLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_CAUTION")));
//		dangerLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_DANGER")));
//
//		
//		// 측정일 목록
//		measureDtListMap = webOrganService.selectOrganMeasureDtList(schoolCode);
//		for(int i=0; i<measureDtListMap.size(); i++) {
//			organMeasureDtList.add((String) measureDtListMap.get(i).get("MEASURE_DT"));
//		}
//		// 최근 측정일
//		String measureDt = request.getParameter("measureDt");
//		if(measureDt == null || measureDt.equals("")) {
//			if( measureDtListMap.size() > 0) {
//				measureDt = (String)measureDtListMap.get(0).get("MEASURE_DT");
//			}else {
//				// 측정일, 측정인원이 없을 경우
//				model.addAttribute("msg", "측정 기록이 없습니다.");
//				model.addAttribute("loginUrl", loginUrl+"/web/logout.do");
//				return "/common/alertMessage";
//			}
//		}else {
//			isMeasureDt = true;
//		}
//		
//		try {
//			// 부서 목록
//			departList = webOrganService.selectDepartmentList("SCHOOL_CODE", schoolCode);
//			// 부서 이름
//			schoolName = (String)departList.get(0).get("SCHOOL_NAME");
//			// 피측정자 인원 수
//			userCount = departList.size();
//
//			for(int i=0; i<departList.size(); i++) {
//				// 부서 코드
//				classCode = (String) departList.get(i).get("CLASS_CODE");
//				// 부서 내 피측정자 인원 목록
//				list = webUserService.selectDepartUserList(classCode, measureDt);
//				if(list != null || list.size() != 0) {
//					for(int j=0; j<list.size(); j++) {
//						userNameList.add((String) list.get(j).get("USER_NAME"));
//					}
//					departUserList.put(i, list);
//				}
//			}
//			
//			// 피측정자 아이디
//			userId = (String)departUserList.get(0).get(0).get("USER_ID");
//			// 피측정자 이름
//			userName = (String)departUserList.get(0).get(0).get("USER_NAME");
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
//
//		// 첫번째 반 첫번째 학생 정보
//		model.addAttribute("userId", userId);
//		model.addAttribute("userName", userName);
//		
//		if(isMeasureDt) {
//			model.addAttribute("measureDt", measureDt);
//		}
//		
//		
//		// 메뉴 탭 :: 반목록
//		model.addAttribute("departList", departList);
//		// 메뉴 탭 :: 학생 목록
//		model.addAttribute("departUserList", departUserList);
//		
//		// 그래프 파라미터
//		model.addAttribute("schoolName", schoolName);
//		model.addAttribute("userNameList", userNameList);
//		model.addAttribute("userCount", userCount);
//		
//		// 측정일 목록
//		model.addAttribute("organMeasureDtList", organMeasureDtList);
//		
//		// 결과지 파라미터
//		model.addAttribute("cautionLevel", cautionLevel);
//		model.addAttribute("dangerLevel", dangerLevel);
//
//		// 자문 치과 조회
//		try {
//			webAdviceVO = webOrganService.selectAdviceDentistInfo(schoolCode);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return "/web/statistics/integrateStatistics";
//
//	}

}

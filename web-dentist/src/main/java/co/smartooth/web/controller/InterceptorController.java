package co.smartooth.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2022. 11. 16
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Slf4j
@Controller
public class InterceptorController {
    
	
	/** 진단 결과지 호출 API **/
	@GetMapping(value = {"/admin/statistics/diagnosis"})
	public String adminDiagnosisAPI() {
		return "/test/admin_diagnosis_api";
	}

	
	/** 외부용 :: 진단 결과지 호출 API **/
	@GetMapping(value = {"/statistics/diagnosis"})
	public String diagnosisAPI() {
		return "/test/diagnosis_api";
	}
	
	
	/** 치과 목록 검색 (정보제공 동의/미동의 확인) **/
	@GetMapping(value = {"/selectDentalHospitalList"})
	public String selectDentalHospitalList() {
		return "/test/selectDentalHospitalList";
	}
	
	
	/** 회원이 개인정보 동의한 치과 목록 조회 **/
	@GetMapping(value = {"/selectDentalHospitalAgreeList"})
	public String selectDentalHospitalAgreeList() {
		return "/test/selectDentalHospitalAgreeList";
	}
	
	
	/** 개인정보 동의 등록 **/
	@GetMapping(value = {"/insertCustomerInfoAgree"})
	public String insertCustomerInfoAgree() {
		return "/test/insertCustomerInfoAgree";
	}

	
	/** 개인정보 동의 등록 **/
	@GetMapping(value = {"/updateCustomerInfoAgree"})
	public String updateCustomerInfoAgree() {
		return "/test/updateCustomerInfoAgree";
	}

	
	/** 개인정보 동의 등록 **/
	@GetMapping(value = {"/deleteCustomerInfoAgree"})
	public String deleteCustomerInfoAgree() {
		return "/test/deleteCustomerInfoAgree";
	}
	
	
	
	
}

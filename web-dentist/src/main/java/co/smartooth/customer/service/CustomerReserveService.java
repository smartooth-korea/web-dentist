package co.smartooth.customer.service;
import org.apache.ibatis.annotations.Param;



/**
 * 작성자 : 정주현 
 * 작성일 : 2023. 08. 23
 * 수정일 : 2023. 08. 23
 */
public interface CustomerReserveService {	
	
	
	// 개인정보 동의 등록 여부 확인
	public int isExistCustomerInfomationAgree(@Param("userId") String userId, @Param("dentalHospitalCd") String dentalHospitalCd) throws Exception;
	
	
	// 개인정보 동의 등록
	public void insertCustomerInfoAgree(@Param("userId") String userId, @Param("dentalHospitalCd") String dentalHospitalCd, @Param("userTelNo") String userTelNo, @Param("userEmail") String userEmail) throws Exception;
	
	
	// 개인정보 동의 여부 업데이트
	public void updateCustomerInfoAgree(@Param("userId") String userId, @Param("dentalHospitalCd") String dentalHospitalCd, @Param("infomationAgryn") String infomationAgryn) throws Exception;
	
	
	// 개인정보 동의 여부 삭제
	public void deleteCustomerInfoAgree(@Param("userId") String userId, @Param("dentalHospitalCd") String dentalHospitalCd) throws Exception;
	

	
}

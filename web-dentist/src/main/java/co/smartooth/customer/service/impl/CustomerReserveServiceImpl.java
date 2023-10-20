package co.smartooth.customer.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.smartooth.customer.mapper.CustomerReserveMapper;
import co.smartooth.customer.service.CustomerReserveService;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 07. 15
 * 수정일 : 2023. 08. 22
 */
@Service
public class CustomerReserveServiceImpl implements CustomerReserveService{
	
	
	
	@Autowired(required = false)
	CustomerReserveMapper customerReserveMapper;

	
	
	// 개인정보 동의 등록 여부 확인
	@Override
	public int isExistCustomerInfomationAgree(@Param("userId") String userId, @Param("dentalHospitalCd") String dentalHospitalCd) throws Exception{
		return customerReserveMapper.isExistCustomerInfomationAgree(userId, dentalHospitalCd);
	}

	
	
	// 개인정보 동의 등록
	@Override
	public void insertCustomerInfoAgree(@Param("userId") String userId, @Param("dentalHospitalCd") String dentalHospitalCd, @Param("userTelNo") String userTelNo, @Param("userEmail") String userEmail) throws Exception {
		customerReserveMapper.insertCustomerInfoAgree(userId, dentalHospitalCd, userTelNo, userEmail);
	}
	
	
	
	// 개인정보 동의 여부 업데이트
	@Override
	public void updateCustomerInfoAgree(@Param("userId") String userId, @Param("dentalHospitalCd") String dentalHospitalCd, @Param("infomationAgryn") String infomationAgryn) throws Exception {
		customerReserveMapper.updateCustomerInfoAgree(userId, dentalHospitalCd, infomationAgryn);
	}



	// 개인정보 동의 여부 삭제
	@Override
	public void deleteCustomerInfoAgree(@Param("userId") String userId, @Param("dentalHospitalCd") String dentalHospitalCd) throws Exception {
		customerReserveMapper.deleteCustomerInfoAgree(userId, dentalHospitalCd);
	}
		
	
	
}
package co.smartooth.customer.service.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.smartooth.customer.mapper.CustomerOrganMapper;
import co.smartooth.customer.service.CustomerOrganService;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 07. 15
 * 수정일 : 2023. 08. 22
 */
@Service
public class CustomerOrganServiceImpl implements CustomerOrganService{
	
	
	@Autowired(required = false)
	CustomerOrganMapper customerOrganMapper;

	
	
	// 최근 3개월 방문 병원 리스트
	@Override
	public List<HashMap<String, Object>> selectDentalHospitalVisitList(@Param("userId") String userId, @Param("measureStartDt") String measureStartDt, @Param("measureEndDt") String measureEndDt) throws Exception {
		return customerOrganMapper.selectDentalHospitalVisitList(userId, measureStartDt, measureEndDt);
	}



	// 개인정보 동의/미동의 치과 전체 목록 조회 또는 검색 목록 조회
	@Override
	public List<HashMap<String, Object>> selectDentalHospitalList(@Param("userId") String userId, @Param("dentalHospitalNm") String dentalHospitalNm) throws Exception {
		return customerOrganMapper.selectDentalHospitalList(userId, dentalHospitalNm);
	}



	// 회원이 개인정보 동의한 치과 목록 조회
	@Override
	public List<HashMap<String, Object>> selectDentalHospitalAgreeList(@Param("userId") String userId) throws Exception {
		return customerOrganMapper.selectDentalHospitalAgreeList(userId);
	} 

	
	
	
	
		
	
	
}
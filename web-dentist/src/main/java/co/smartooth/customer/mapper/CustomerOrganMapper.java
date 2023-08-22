package co.smartooth.customer.mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



/**
 * 작성자 : 정주현 
 * 작성일 : 2023. 07. 07
 * 수정일 : 2023. 08. 22
 */
@Mapper
public interface CustomerOrganMapper {
	
	
	// 조회 앱 - 최근 3개월 방문 병원 리스트
	List<HashMap<String, Object>> selectDentalHospitalVisitList(@Param("userId") String userId, @Param("measureStartDt") String measureStartDt, @Param("measureEndDt") String measureEndDt) throws Exception;
	
	
	// 개인정보 동의/미동의 치과 전체 목록 조회 또는 검색 목록 조회
	List<HashMap<String, Object>> selectDentalHospitalList(@Param("userId") String userId, @Param("dentalHospitalNm") String dentalHospitalNm) throws Exception;

	
	// 회원이 개인정보 동의한 치과 목록 조회
	List<HashMap<String, Object>> selectDentalHospitalAgreeList(@Param("userId") String userId) throws Exception;
	
	
	
}
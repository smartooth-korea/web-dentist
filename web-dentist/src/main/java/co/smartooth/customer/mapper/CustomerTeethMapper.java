package co.smartooth.customer.mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import co.smartooth.customer.vo.CustomerTeethInfoVO;

/**
 * 작성자 : 정주현
 * 작성일 : 2022. 04. 28
 * 수정일 : 2023. 05. 24
 */
@Mapper
public interface CustomerTeethMapper {
	
	
	// 충치 단계별 수치 조회
	public HashMap<String, Integer> selectCavityLevel() throws Exception;
	
	
	// 조회 앱 - 회원 치아 상태 값 INSERT
	public void insertUserTeethInfo(CustomerTeethInfoVO customerTeethInfoVO) throws Exception;
	
	
	// 조회 앱 - 회원 치아 측정 값 조회 (기간 : default 1년)
	public List<HashMap<String, Object>> selectUserTeethMeasureValue(@Param("userId") String userId,  @Param("startDt") String startDt, @Param("endDt") String endDt) throws Exception;
	
	
}
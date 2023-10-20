package co.smartooth.customer.service.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.smartooth.customer.mapper.CustomerTeethMapper;
import co.smartooth.customer.service.CustomerTeethService;
import co.smartooth.customer.vo.CustomerTeethInfoVO;

/**
 * 작성자 : 정주현 
 * 수정일 : 2023. 07. 05
 */
@Service
public class CustomerTeethServiceImpl implements CustomerTeethService{
	
	
	
	@Autowired(required = false)
	CustomerTeethMapper customerTeethMapper;

	

	// 충치 단계별 수치 조회
	@Override
	public HashMap<String, Integer> selectCavityLevel() throws Exception {
		return customerTeethMapper.selectCavityLevel();
	}
	
	
	
	// 조회 앱 - 회원 치아 상태 값 INSERT
	@Override
	public void insertUserTeethInfo(CustomerTeethInfoVO customerTeethInfoVO) throws Exception {
		customerTeethMapper.insertUserTeethInfo(customerTeethInfoVO);
	}

	
	// 조회 앱 - 회원 치아 측정 값 조회 (기간 : default 1년)
	@Override
	public List<HashMap<String, Object>> selectUserTeethMeasureValue(@Param("userId") String userId,  @Param("startDt") String startDt, @Param("endDt") String endDt) throws Exception{
		return customerTeethMapper.selectUserTeethMeasureValue(userId, startDt, endDt);
	}


	
	// 조회 앱 - 치과에서 측정한 같은 사용자가 있을 경우 측정 기록 조회
	@Override
	public List<HashMap<String, Object>> selectUserTeethMeasureValueInDentist(@Param("userId") String userId,  @Param("startDt") String startDt, @Param("endDt") String endDt) throws Exception {
		return customerTeethMapper.selectUserTeethMeasureValueInDentist(userId, startDt, endDt);
	}


	

}
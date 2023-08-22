package co.smartooth.customer.mapper;

import org.apache.ibatis.annotations.Mapper;
import co.smartooth.customer.vo.CustomerAuthVO;



/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2022. 08. 18
 */
@Mapper
public interface CustomerLogMapper {

	// 회원 로그인 기록 INSERT
	public void insertUserLoginHistory(CustomerAuthVO customerAuthVO) throws Exception;
	
	
	// 회원 접속일 UPDATE
	public void updateLoginDt(CustomerAuthVO customerAuthVO) throws Exception;
	
}

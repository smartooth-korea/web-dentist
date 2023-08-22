package co.smartooth.customer.service;

import org.springframework.stereotype.Service;
import co.smartooth.customer.vo.CustomerAuthVO;



/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 */
@Service
public interface CustomerLogService {
	
	// 회원 로그인 기록 INSERT
	public void insertUserLoginHistory(CustomerAuthVO customerAuthVO) throws Exception;
	
	// 회원 접속일 UPDATE
	public void updateLoginDt(CustomerAuthVO customerAuthVO) throws Exception;

	
}
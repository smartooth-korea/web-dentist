package co.smartooth.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.customer.mapper.CustomerLogMapper;
import co.smartooth.customer.service.CustomerLogService;
import co.smartooth.customer.vo.CustomerAuthVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 */
@Service
public class CustomerLogServiceImpl implements CustomerLogService{

	
	@Autowired(required = false)
	CustomerLogMapper customerLogMapper;
	
	
	// 회원 로그인 기록 INSERT
	@Override
	public void insertUserLoginHistory(CustomerAuthVO customerAuthVO) throws Exception {
		customerLogMapper.insertUserLoginHistory(customerAuthVO);
	}
	
	// 회원 접속일 UPDATE
	@Override
	public void updateLoginDt(CustomerAuthVO customerAuthVO) throws Exception {
		customerLogMapper.updateLoginDt(customerAuthVO);
	}
	
}

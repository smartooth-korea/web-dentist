package co.smartooth.customer.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.smartooth.customer.mapper.CustomerAuthMapper;
import co.smartooth.customer.service.CustomerAuthService;
import co.smartooth.customer.vo.CustomerAuthVO;



/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 */
@Service
public class CustomerAuthServiceImpl implements CustomerAuthService{

	
	@Autowired(required = false)
	CustomerAuthMapper customerAuthMapper;
	
	
	// 회원 아이디와 비밀번호로 존재 여부 확인 :: true = 1, false = 0
	@Override
	public int loginChkByIdPwd(CustomerAuthVO customerAuthVO) throws Exception {
		return customerAuthMapper.loginChkByIdPwd(customerAuthVO);
	}

	// 회원 아이디가 존재하는지 여부 확인 :: true = 1, false = 0
	@Override
	public int isIdExist(@Param("userId") String userId) throws Exception {
		return customerAuthMapper.isIdExist(userId);
	}
	
}

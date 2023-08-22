package co.smartooth.customer.service;
import org.apache.ibatis.annotations.Param;

import co.smartooth.customer.vo.CustomerMailAuthVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28
 */
public interface CustomerMailAuthService {
	
	
	// 메일 발송
	public void sendMail(String userId) throws Exception;

	
	// 비밀번호 재설정 이메일 발송
	public void sendResetPasswordEmail(@Param("userId") String userId, @Param("emailAuthKey") String emailAuthKey) throws Exception;
	
	
	// UserId로 메일 인증 번호 발행 후 Database 에 입력
	void insertAuthKeyById(CustomerMailAuthVO customerMailAuthVO) throws Exception;

	
	// 메일 인증 번호 업데이트
	public void updateAuthKeyById(CustomerMailAuthVO customerMailAuthVO) throws Exception;
	
	
	// 인증 메일 클릭 시 인증 상태를 'Y' 로 업데이트
	public void updateAuthStatusY(@Param("userId") String userId, @Param("emailAuthKey") String emailAuthKey) throws Exception;

	
	// 인증 메일 요청 시 인증 상태를 'N' 로 업데이트
	public void updateAuthStatusN(@Param("userId") String userId) throws Exception;
	
	
	// 사용자 메일 인증 여부 확인
	public String isEmailAuthEnabled(@Param("userId") String userId) throws Exception;


	
}

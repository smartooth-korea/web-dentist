package co.smartooth.customer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import co.smartooth.customer.vo.CustomerMailAuthVO;



/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 */
@Mapper
public interface CustomerMailAuthMapper {

	
	// UserId로 메일 인증키 발행 후 Database에 입력
	public void insertAuthKeyById(CustomerMailAuthVO customerMailAuthVO) throws Exception;

	
	// 메일 인증을 요청한 사용자 DB row에 인증키(AuthKey) 업데이트
	public void updateAuthKeyById(CustomerMailAuthVO customerMailAuthVO) throws Exception;

	
	// 메일 인증 테이블에 USER_ID가 있는지 조회
	public int selectMailAuthInfo(CustomerMailAuthVO customerMailAuthVO) throws Exception;
	
	
	// 회원가입 시 입력된 아이디(이메일)을 통해 DB에 있는지 조회
	public int selectIdWhetherOrNot(@Param("userId") String userId) throws Exception;
	
	
	// 인증 메일 클릭 시 AuthStatus 'Y'로 변경
	public void updateAuthStatusY(@Param("userId") String userId, @Param("emailAuthKey") String emailAuthKey) throws Exception;

	
	// 인증 메일 재 요청 시 AuthStatus 'N'로 변경
	public void updateAuthStatusN(@Param("userId") String userId) throws Exception;
	
	
	// 사용자 메일 인증 여부
	public String isEmailAuthEnabled(@Param("userId") String userId) throws Exception;
	
	
}

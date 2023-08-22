package co.smartooth.customer.service;

import org.apache.ibatis.annotations.Param;
import co.smartooth.customer.vo.CustomerUserVO;


/**
 * 작성자 : 정주현
 * 작성일 : 2022. 04. 28 ~
 * 수정일 : 2023. 03. 17
 */
public interface CustomerUserService {
	
	
	// 조회 앱 이메일 등록 여부 확인 (0: 없음, 1: 있음)
	public int isExistId(@Param("userId") String userId) throws Exception;

	
	// 조회 앱 회원 계정 등록
	public void insertUserInfo(CustomerUserVO customerUserVO) throws Exception;
	
	
	// 조회 앱 회원 상세 정보 등록
	public void insertCustomerUserDetail(CustomerUserVO customerUserVO) throws Exception;
	
	
	// 조회 앱 계정 정보 조회
	public CustomerUserVO selectUserInfo(@Param("userId") String userId) throws Exception;
	
	
	// 조회 앱 계정 정보 업데이트
	public void updateUserInfo(CustomerUserVO customerUserVO) throws Exception;
	
	
	// 푸시토큰 업데이트
	public void updatePushToken(@Param("userId") String userId, @Param("pushToken") String pushToken) throws Exception;
	

	
	
	//	// 계정 삭제
//	public void deleteUserInfo(@Param("userId") String userId) throws Exception;
//	
//	
//	
	
//
//	
//	
//	
//	
//	
//	
//	/** COMMON **/
//	
//	// 피측정자 아이디 조회 - ST_PARENT_USER_DEATAIL
//	public String selectChUserId(@Param("userId") String userId) throws Exception;
//
//	
//	
//	// 피측정자 이름 조회 - ST_USER
//	public String selectChUserName(@Param("userId") String userId) throws Exception;
//
//	
//	
//	
//	
//	
//	/** WEB **/
//	
//	// 피측정자 상세 정보 등록 
//	public void insertStUserDetail(@Param("userId") String userId, @Param("schoolType") String schoolType, @Param("classCode") String classCode) throws Exception;
//	
//	
//	
//	// 피측정자 정보 및 측정 데이터 조회 (회원 한명)
//	public List<HashMap<String, Object>> selectStUserInfo(String userId) throws Exception;
//	
//	
//	
//	// 피측정자 아이디 목록 조회 - ST_PARENT_USER_DEATAIL
//	public List<HashMap<String, Object>> selectChUserList(@Param("schoolCode") String schoolCode, @Param("measureDt") String measureDt) throws Exception;
//	
//	
//	
//	// 피측정자 법정대리인 상세 정보 등록 
//	public void insertPaUserDetail(@Param("userId") String userId, @Param("childId") String childId) throws Exception;
//	
//	
//	
//	// 피측정자 법정대리인 비밀번호 변경
//	public void updatePaUserPwd(@Param("userId") String userId, @Param("userPwd") String userPwd) throws Exception;
//	
//	
//	
//	// 기관 내 피측정자 목록 조회
//	public List<HashMap<String, Object>> selectDepartUserList(@Param("classCode") String classCode, @Param("measureDt") String measureDt) throws Exception;


}

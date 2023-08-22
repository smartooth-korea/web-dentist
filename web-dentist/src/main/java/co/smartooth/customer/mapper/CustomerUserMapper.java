package co.smartooth.customer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.smartooth.customer.vo.CustomerUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2023. 05. 16
 */
@Mapper
public interface CustomerUserMapper {
	
	

	// 조회 앱 이메일 등록 여부 확인 (0: 없음, 1: 있음)
	public int isExistId(@Param("userId") String userId) throws Exception;
	
	
	// 조회 앱 계정 등록
	public void insertUserInfo(CustomerUserVO customerUserVO) throws Exception;
	
	
	// 조회 앱 상세 정보 등록
	public void insertCustomerUserDetail(CustomerUserVO customerUserVO) throws Exception;
	
	
	// 조회 앱 계정 정보 조회
	public CustomerUserVO selectUserInfo(@Param("userId") String userId) throws Exception;
	
	
	// 조회 앱 계정 정보 업데이트
	public void updateUserInfo(CustomerUserVO customerUserVO) throws Exception;
	
	
	// 푸시토큰 업데이트
	public void updatePushToken(@Param("userId") String userId, @Param("pushToken") String pushToken) throws Exception;
	
	
	
	
//	// 사용자 아이디 중복 체크
//	public int duplicateChkId(String userId) throws Exception;
//
//	
//	// 사용자 정보 업데이트
//	public void updateUserInfo(DentalUserVO dentistUserVO) throws Exception;
//	
//	
//	// 사용자 시퀀스 조회 ( 생성 전 SEQ_STR)
//	public Integer selectUserSeqNo(@Param("userType") String userType) throws Exception;
//
//	
//	// 사용자 시퀀스 조회 ( 생성 전 SEQ_STR)
//	public int selectUserSeqStr() throws Exception;
//
//	
//	// 사용자 시퀀스 생성 후 SEQ_NO 업데이트
//	public void updateUserSeqNo(@Param("userType") String userType, @Param("seqNo") int seqNo) throws Exception;
//
//	
//	// 사용자 시퀀스 업데이트  
//	public void updateUserSeqStr(@Param("seqStr") int seqStr) throws Exception;
//
//	
//	// 사용자 정보 조회
//	public DentalUserVO selectUserInfo(@Param("userId") String userId) throws Exception;
//
//	
//	// 비밀번호 변경(찾기)
//	public void updateUserPwd(DentalUserVO dentistUserVO) throws Exception;
//	
//	
//	// 회원 삭제 (임시)
//	// public void deleteUser(String userId) throws Exception;
//	
//	
//	// 기관 목록 조회
//	public List<DentalSchoolClassInfoVO> selectSchoolList() throws Exception;
//	
//	
//	// 부서(반) 회원 리스트 조회
//	public List<DentalUserVO> selectDepartmentList(@Param("schoolCode") String schoolCode) throws Exception;
//
//	
//	// 부서(반) ID로 해당 피측정자 목록 조회
//	public List<DentalUserVO> selectMeasuredUserList(@Param("userId") String userId, @Param("orderBy") String orderBy) throws Exception;
//	
//	
//	// 피측정자 상세 정보 등록
//	public void insertUserDetail(DentalUserVO dentistUserVO) throws Exception;
//	
//	
//	// 부서(반) ID로 피측정자 목록 조회
//	public List<DentalUserVO> selectTestUserListByTc(@Param("userId") String userId, @Param("userName") String userName) throws Exception;
//	
//	
//	// 피측정자 이름 변경
//	public void updateTestUserName(@Param("userId") String userId, @Param("userName") String userName) throws Exception;
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
//	
//	
//	// 피측정자 상세 정보 조회
//	public DentalUserVO selectUserDetail(@Param("userId")  String userId) throws Exception;
//	
//	
//	// 피측정자 아이디 중복 체크 (ID와 기관코드)
////	public int duplicateChkPaUserId(@Param("userId") String userId, @Param("schoolCode") String schoolCode) throws Exception;
//	
//	
//	
//	
//	
//	
//	// 치과 소속 의사 등록
//	public void insertDentistInfo(DentalDentistInfoVO dentalDentistInfoVO) throws Exception;
//	
//	
//	// 치과 소속 의사 정보 수정
//	public void updateDentistInfo(DentalDentistInfoVO dentalDentistInfoVO) throws Exception;
//	
//	
//	// 치과 소속 의사 목록 조회
//	public List<HashMap<String, Object>> selectDentistList(@Param("dentalHospitalCd") String dentalHospitalCd) throws Exception;
//		
//	
//	// 최근 등록한 의사 아이디 조회
//	public String selectDentistId(@Param("dentalHospitalCd") String dentalHospitalCd) throws Exception;
	
	
	}
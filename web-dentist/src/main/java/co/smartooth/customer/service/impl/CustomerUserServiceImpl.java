package co.smartooth.customer.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.customer.mapper.CustomerUserMapper;
import co.smartooth.customer.service.CustomerUserService;
import co.smartooth.customer.vo.CustomerUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2022. 08. 17
 */
@Service
public class CustomerUserServiceImpl implements CustomerUserService{
	
	
	@Autowired(required = false)
	CustomerUserMapper customerUserMapper;


	
	// 조회 앱 이메일 등록 여부 확인 (0: 없음, 1: 있음)
	@Override
	public int isExistId(@Param("userId") String userId) throws Exception {
		return customerUserMapper.isExistId(userId);
	}

	
	
	// 조회 앱 회원 계정 등록	
	@Override
	public void insertUserInfo(CustomerUserVO customerUserVO) throws Exception {
		customerUserMapper.insertUserInfo(customerUserVO);
	}

	

	// 조회 앱 회원 상세 정보 등록
	@Override
	public void insertCustomerUserDetail(CustomerUserVO customerUserVO) throws Exception {
		customerUserMapper.insertCustomerUserDetail(customerUserVO);
	}


	
	// 조회 앱 계정 정보 조회
	@Override
	public CustomerUserVO selectUserInfo(@Param("userId") String userId) throws Exception {
		return customerUserMapper.selectUserInfo(userId);
	}



	// 조회 앱 계정 정보 업데이트
	@Override
	public void updateUserInfo(CustomerUserVO customerUserVO) throws Exception {
		customerUserMapper.updateUserInfo(customerUserVO);
	}


	
	// 푸시토큰 업데이트// 푸시토큰 업데이트
	@Override
	public void updatePushToken(@Param("userId") String userId, @Param("pushToken") String pushToken) throws Exception {
		customerUserMapper.updatePushToken(userId, pushToken);
	}
	
	
	
	
	
	
	
	
	
	
//	// 사용자 아이디 중복 체크
//	@Override
//	public int duplicateChkId(String userId) throws Exception {
//		return dentalUserMapper.duplicateChkId(userId);
//	}
//
//	
//	
//	// 사용자 정보 업데이트
//	@Override
//	public void updateUserInfo(DentalUserVO userVo) throws Exception {
//		dentalUserMapper.updateUserInfo(userVo);
//	}
//	
//	
//	
//	// 사용자 시퀀스 조회 ( 생성 전 SEQ_STR)
//	@Override
//	public Integer selectUserSeqNo(@Param("userType") String userType) throws Exception {
//		return dentalUserMapper.selectUserSeqNo(userType);
//	}
//	
//	
//	
//	// 사용자 시퀀스 조회 ( 생성 전 SEQ_STR)
//	@Override
//	public int selectUserSeqStr() throws Exception {
//		return dentalUserMapper.selectUserSeqStr();
//	}
//	
//	
//	
//	// 사용자 시퀀스 생성 후 SEQ_NO
//	@Override
//	public void updateUserSeqNo(@Param("userType") String userType, @Param("seqNo") int seqNo) throws Exception {
//		dentalUserMapper.updateUserSeqNo(userType, seqNo);
//	}
//
//	
//	
//	// 사용자 시퀀스 업데이트  
//	@Override
//	public void updateUserSeqStr(@Param("seqStr") int seqStr) throws Exception {
//		dentalUserMapper.updateUserSeqStr(seqStr);
//	}
//
//	
//	
//	// 비밀번호 변경(찾기)
//	@Override
//	public void updateUserPwd(DentalUserVO dentistUserVO) throws Exception {
//		dentalUserMapper.updateUserPwd(dentistUserVO);
//	}
//	
//	
//	
//    // 회원의 삭제
////     @Override
////     public void deleteUser(String userId) throws Exception {
////    		dentalUserMapper.deleteUser(userId);
////     }
//    
//
//	
//	// 기관 목록 조회
////	@Override
////	public List<DentalSchoolClassInfoVO> selectSchoolList() throws Exception {
////		return dentalUserMapper.selectSchoolList();
////	}
//	
//	
//    
//    // 부서(반) 회원 리스트 조회
////	@Override
////	public List<DentalUserVO> selectDepartmentList(@Param("schoolCode") String schoolCode) throws Exception {
////		return dentalUserMapper.selectDepartmentList(schoolCode);
////	}
//
//	
//	
//	// 부서(반) ID로 해당 피측정자 목록 조회
//	@Override
//	public List<DentalUserVO> selectMeasuredUserList(@Param("userId") String userId, @Param("orderBy") String orderBy) throws Exception {
//		return dentalUserMapper.selectMeasuredUserList(userId, orderBy);
//	}
//
//
//	
//	// 피측정자 상세 정보 등록
//	@Override
//	public void insertUserDetail(DentalUserVO dentistUserVO) throws Exception {
//		dentalUserMapper.insertUserDetail(dentistUserVO);
//	}
//
//
//	
//	// 부서(반) ID로 피측정자 목록 조회
////	@Override
////	public List<DentalUserVO> selectTestUserListByTc(@Param("userId") String userId, @Param("userName") String userName) throws Exception {
////		return dentalUserMapper.selectTestUserListByTc(userId, userName);
////	}
//
//
//	// 피측정자 이름 변경
////	@Override
////	public void updateTestUserName(@Param("userId")  String userId, @Param("userName")  String userName) throws Exception {
////		dentalUserMapper.updateTestUserName(userId, userName);
////	}
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
//	
//	
//	
//	// 피측정자 상세 정보 조회
//	@Override
//	public DentalUserVO selectUserDetail(@Param("userId")  String userId) throws Exception {
//		return dentalUserMapper.selectUserDetail(userId);
//	}
//
//
//
//	// 피측정자 아이디 중복 체크 (ID와 기관코드)
////	@Override
////	public int duplicateChkPaUserId(@Param("userId") String userId, @Param("schoolCode") String schoolCode) throws Exception {
////		return dentalUserMapper.duplicateChkPaUserId(userId, schoolCode);
////	}
//	
//
//	
//	// 치과 소속 의사 정보 등록
//	@Override
//	public void insertDentistInfo(DentalDentistInfoVO dentalDentistInfoVO) throws Exception{
//		dentalUserMapper.insertDentistInfo(dentalDentistInfoVO);
//	}
//	
//	
//	
//	// 치과 소속 의사 정보 등록
//	@Override
//	public void updateDentistInfo(DentalDentistInfoVO dentalDentistInfoVO) throws Exception{
//		dentalUserMapper.updateDentistInfo(dentalDentistInfoVO);
//	}
//	
//	
//	
//	// 치과 소속 의사 목록 조회
//	@Override
//	public List<HashMap<String, Object>>  selectDentistList(@Param("dentalHospitalCd") String dentalHospitalCd) throws Exception {
//		return dentalUserMapper.selectDentistList(dentalHospitalCd);
//				
//	}
//	
//	
//	// 최근 등록한 의사 아이디 조회
//	public String selectDentistId(@Param("dentalHospitalCd") String dentalHospitalCd) throws Exception{
//		return dentalUserMapper.selectDentistId(dentalHospitalCd);
//	}


	
	
	

	
}
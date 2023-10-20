package co.smartooth.customer.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import co.smartooth.customer.vo.CustomerTeethInfoVO;

/**
 * 작성자 : 정주현
 * 작성일 : 2022. 04. 28
 * 수정일 : 2023. 09. 05
 */
@Mapper
public interface CustomerTeethMapper {

	
	// 충치 단계별 수치 조회
	public HashMap<String, Integer> selectCavityLevel() throws Exception;
	
	
	// 조회 앱 - 회원 치아 상태 값 INSERT
	public void insertUserTeethInfo(CustomerTeethInfoVO customerTeethInfoVO) throws Exception;
	
	
	// 조회 앱 - 회원 치아 측정 값 조회 (기간 : default 1년)
	public List<HashMap<String, Object>> selectUserTeethMeasureValue(@Param("userId") String userId,  @Param("startDt") String startDt, @Param("endDt") String endDt) throws Exception;
	
	
	// 조회 앱 - 치과에서 측정한 같은 사용자가 있을 경우 측정 기록 조회
	public List<HashMap<String, Object>> selectUserTeethMeasureValueInDentist(@Param("userId") String userId,  @Param("startDt") String startDt, @Param("endDt") String endDt) throws Exception;
	
	
	
//	// 피측정자 치아 상태 값 조회
//	public List<DentalTeethInfoVO> selectUserTeethInfo(DentalUserVO dentistUserVO) throws Exception; 
//	
//	
//	
//	// 피측정자 치아 측정 값 INSERT
//	public void insertUserTeethMeasureValue(DentalTeethMeasureVO dentistTeethMeasureVO) throws Exception;
//	
//	
//	
//	/**피측정자 치아 측정 값 INSERT(치과용)**/
//	public void insertPaUserTeethMeasureValue(DentalTeethMeasureVO dentistTeethMeasureVO) throws Exception;
//
//	
//	
//	// 피측정자 치아 측정 값 UPDATE
//	public void updateUserTeethMeasureValue(DentalTeethMeasureVO dentistTeethMeasureVO) throws Exception;
//
//	
//	
//	/** 피측정자 치아 측정 값 UPDATE(치과용) **/
//	public void updatePaUserTeethMeasureValue(DentalTeethMeasureVO dentistTeethMeasureVO) throws Exception;
//	
//	
//	
//	// 피측정자 개별 치아 측정 값 INSERT
//	public void insertUserToothMeasureValue(DentalToothMeasureVO dentistToothMeasureVO) throws Exception;
//
//	
//	
//	// 피측정자 개별 치아 측정 값 UPDATE
//	public void updateUserToothMeasureValue(DentalToothMeasureVO dentistToothMeasureVO) throws Exception;
//	
//	
//	
//	// 피측정자 충치 개수 UPDATE (최근) - ST_STUDENT_USER_DETAIL
//	public void updateUserCavityCnt(DentalTeethMeasureVO dentistTeethMeasureVO) throws Exception;
//	
//	
//	
//	// 피측정자 충치 개수 UPDATE (측정일별)
//	public void updateUserCavityCntByMeasureDt(DentalTeethMeasureVO dentistTeethMeasureVO) throws Exception;
//	
//	
//	
//	// 피측정자의 SYSDATE(오늘)의 측정 값이 있는지 여부 확인 (0 : 오늘X, 1: 오늘)
//	public Integer isExistSysDateRow(@Param("userId") String userId) throws Exception;
//
//	
//	
//	// 피측정자의 기존 치아 측정 값 있는지 여부 반환(0 : 없음 / 1이상: 있음)
//	public Integer isExistOldRow(@Param("userId") String userId) throws Exception;
//
//	
//	
//	/**피측정자 치아 측정 값 조회 (기간) (치과용) **/
//	// public DentalTeethMeasureVO selectUserTeethMeasureValue(DentalTeethMeasureVO dentistTeethMeasureVO) throws Exception;
//	public List<DentalTeethMeasureVO> selectPaUserTeethMeasureValue(DentalTeethMeasureVO dentistTeethMeasureVO) throws Exception;
//	
//	
//	
//	// 피측정자 치아 개별 측정 값 조회 (기간)
//	// public DentalToothMeasureVO selectUserToothMeasureValue(DentalToothMeasureVO dentistToothMeasureVO) throws Exception;
//	public List<DentalToothMeasureVO> selectUserToothMeasureValue(DentalToothMeasureVO dentistToothMeasureVO) throws Exception;
//
//
//	
//	// 피측정자 치아 측정 상태 목록 조회 (IsMeasuring) 
//    public List<DentalUserVO> selectStUserIsMeasuring(DentalUserVO dentistUserVO) throws Exception;
//    
//    
//    
//  // 피측정자 치아 측정 상태 업데이트
//  public void updateStUserIsMeasuring(DentalUserVO dentistUserVO) throws Exception;
//    
//    
//    
//	// 피측정자 치아 측정 값 목록 조회 (최근 3개)
//  public List<DentalTeethMeasureVO> selectUserMeasureValueList(@Param("userId") String userId, @Param("startDt") String startDt, @Param("endDt") String endDt) throws Exception;
//
//    
//    
//	
//	// 피측정자 진단 정보 조회 (측정일)
//	public DentalTeethMeasureVO selectDiagCd(@Param("userId") String userId, @Param("measureDt") String measureDt) throws Exception;
//	
//	
//	
//	// 피측정자 진단 정보 업데이트
//	public void updateDiagCd(@Param("userId") String userId, @Param("diagCd") String diagCd , @Param("measureDt") String measureDt) throws Exception;
//	
//	
//	
//	// 피측정자 비고(메모) 정보 조회 (측정일)
//	public DentalTeethMeasureVO selectMemo(@Param("userId") String userId, @Param("measureDt") String measureDt) throws Exception;
//
//	
//	
//	// 피측정자 비고(메모) 정보 업데이트
//	public void updateMemo(@Param("userId") String userId, @Param("memo") String memo, @Param("measureDt") String measureDt) throws Exception;
//	
//	
//	
//	// 피측정자 치아 정보 조회
//	public String selectTeethStatus(@Param("userId") String userId) throws Exception;
//	  
//	
//	
//	// 피측정자 치아 정보 업데이트
//	public void updateTeethStatus(@Param("userId") String userId, @Param("teethStatus") String teethStatus, @Param("recordDt") String recordDt) throws Exception;
//	  
//	
//	
//	// 피측정자 치아 정보 등록
//	public void insertTeethStatus(@Param("userId") String userId, @Param("teethStatus") String teethStatus) throws Exception;
//	
//	
//	
//	// 피측정자 치아 정보 갯수 조회
//	public int selectCountTeethInfo(@Param("userId") String userId, @Param("recordDt") String recordDt) throws Exception;
	
	
}
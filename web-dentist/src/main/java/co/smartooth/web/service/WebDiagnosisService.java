package co.smartooth.web.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import co.smartooth.web.vo.WebDiagnosisVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 02. 02
 */
@Service
public interface WebDiagnosisService {
	
	
	// 최상위 진단 정보 조회
	public List<WebDiagnosisVO> selectDiagDept1List() throws Exception;

	
	// 중위 진단 정보 조회 
	public List<WebDiagnosisVO> selectDiagDept2List() throws Exception;

	
	// 진단 키워드 DESCRIPT 조회 - teethType : M, B, P
	public String selectDiagDescript(@Param("descCd") String descCd, @Param("teethType") String teethType) throws Exception;
	
	
	// 진단 키워드 제목 조회
	public String selectDiagTitle(@Param("diagCd") String diagCd, @Param("teethType") String teethType) throws Exception;
	
	
	// 진단 코드 내용의 Count 조회
	public int selectDiagDescriptCount(@Param("descCd") String descCd) throws Exception;
	
	
	
	
	
}
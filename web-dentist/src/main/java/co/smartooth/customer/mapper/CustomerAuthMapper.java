package co.smartooth.customer.mapper;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 */
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import co.smartooth.customer.vo.CustomerAuthVO;



@Mapper
public interface CustomerAuthMapper {

	// 회원 아이디 존재 여부 :: true = 1, false = 0
	public int loginChkByIdPwd(CustomerAuthVO customerAuthVO) throws Exception;
	
	// 회원 아이디가 존재하는지 여부 확인 :: true = 1, false = 0
	public int isIdExist(@Param("userId") String userId) throws Exception;

}

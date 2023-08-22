package co.smartooth.customer.vo;

import java.io.Serializable;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 03
 */
public class CustomerUserVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int seqNo;
	private String userNo;
	private String userId;
	private String userEmail;
	private String userName;
	private String userPwd;
	private String userType;
	private String userBirthday;
	private String userTelNo;
	private String userSex;
	private String userRgstDt;
	private String pushToken;
	private String userDeleteYn;
	private String userDeleteDt;
	private String loginDt;
	private String userEmailYn;
	private int loginCk;
	private String userAuthToken;
	
	private String userCountry;
	private String userState;

	private String countryNm;
	private String stateNm;
	private String sidoNm;
	private String sigunguNm;
	private String eupmyeondongNm;
	private String addrDetail;
	
	// ST_CUSTOMER_USER_DETAIL
	private String prUserName;
	private String prUserTelNo;
	private String prUserEmail;
	
	private String agreYn;

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserTelNo() {
		return userTelNo;
	}

	public void setUserTelNo(String userTelNo) {
		this.userTelNo = userTelNo;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserRgstDt() {
		return userRgstDt;
	}

	public void setUserRgstDt(String userRgstDt) {
		this.userRgstDt = userRgstDt;
	}

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public String getUserDeleteYn() {
		return userDeleteYn;
	}

	public void setUserDeleteYn(String userDeleteYn) {
		this.userDeleteYn = userDeleteYn;
	}

	public String getUserDeleteDt() {
		return userDeleteDt;
	}

	public void setUserDeleteDt(String userDeleteDt) {
		this.userDeleteDt = userDeleteDt;
	}

	public String getLoginDt() {
		return loginDt;
	}

	public void setLoginDt(String loginDt) {
		this.loginDt = loginDt;
	}

	public String getUserEmailYn() {
		return userEmailYn;
	}

	public void setUserEmailYn(String userEmailYn) {
		this.userEmailYn = userEmailYn;
	}

	public int getLoginCk() {
		return loginCk;
	}

	public void setLoginCk(int loginCk) {
		this.loginCk = loginCk;
	}

	public String getUserAuthToken() {
		return userAuthToken;
	}

	public void setUserAuthToken(String userAuthToken) {
		this.userAuthToken = userAuthToken;
	}

	public String getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getCountryNm() {
		return countryNm;
	}

	public void setCountryNm(String countryNm) {
		this.countryNm = countryNm;
	}

	public String getStateNm() {
		return stateNm;
	}

	public void setStateNm(String stateNm) {
		this.stateNm = stateNm;
	}

	public String getSidoNm() {
		return sidoNm;
	}

	public void setSidoNm(String sidoNm) {
		this.sidoNm = sidoNm;
	}

	public String getSigunguNm() {
		return sigunguNm;
	}

	public void setSigunguNm(String sigunguNm) {
		this.sigunguNm = sigunguNm;
	}

	public String getEupmyeondongNm() {
		return eupmyeondongNm;
	}

	public void setEupmyeondongNm(String eupmyeondongNm) {
		this.eupmyeondongNm = eupmyeondongNm;
	}

	public String getAddrDetail() {
		return addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}

	public String getPrUserName() {
		return prUserName;
	}

	public void setPrUserName(String prUserName) {
		this.prUserName = prUserName;
	}

	public String getPrUserTelNo() {
		return prUserTelNo;
	}

	public void setPrUserTelNo(String prUserTelNo) {
		this.prUserTelNo = prUserTelNo;
	}

	public String getPrUserEmail() {
		return prUserEmail;
	}

	public void setPrUserEmail(String prUserEmail) {
		this.prUserEmail = prUserEmail;
	}

	public String getAgreYn() {
		return agreYn;
	}

	public void setAgreYn(String agreYn) {
		this.agreYn = agreYn;
	}
	

}

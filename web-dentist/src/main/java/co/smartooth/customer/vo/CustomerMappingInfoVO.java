package co.smartooth.customer.vo;

import java.io.Serializable;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 03
 */
public class CustomerMappingInfoVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userNo;
	private String userId;
	private String userTelNo;
	private String userEmail;
	private String dentalHospitalCd;
	private String measureDueDt;
	private String infomationAgryn;
	private String infomationAgrynDt;
	
	
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
	public String getUserTelNo() {
		return userTelNo;
	}
	public void setUserTelNo(String userTelNo) {
		this.userTelNo = userTelNo;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getDentalHospitalCd() {
		return dentalHospitalCd;
	}
	public void setDentalHospitalCd(String dentalHospitalCd) {
		this.dentalHospitalCd = dentalHospitalCd;
	}
	public String getMeasureDueDt() {
		return measureDueDt;
	}
	public void setMeasureDueDt(String measureDueDt) {
		this.measureDueDt = measureDueDt;
	}
	public String getInfomationAgryn() {
		return infomationAgryn;
	}
	public void setInfomationAgryn(String infomationAgryn) {
		this.infomationAgryn = infomationAgryn;
	}
	public String getInfomationAgrynDt() {
		return infomationAgrynDt;
	}
	public void setInfomationAgrynDt(String infomationAgrynDt) {
		this.infomationAgrynDt = infomationAgrynDt;
	}
	
}

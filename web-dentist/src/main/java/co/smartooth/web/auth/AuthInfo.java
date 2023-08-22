package co.smartooth.web.auth;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 */
public class AuthInfo {

	private String id;
	private String name;

	public AuthInfo(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
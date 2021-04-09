package poly.dto;

/**
 * @author 이협건
 * @version 1.1 공지사항 DTO
 */
public class UserDTO {

	private String id; // 아이디
	private String pwd; // 비번
	private String name; // 이름
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

package lab.web.model;

public class EmailVO {
	private String username;
	private String email;
	private int no;
	
	public EmailVO() {
		super();
	
	}
	public EmailVO(String username, String email, int no) {
		super();
		this.username = username;
		this.email = email;
		this.no=no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}

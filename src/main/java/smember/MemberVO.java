package smember;

public class MemberVO {
	private String user_ID;
	private String user_Pw;
	private String user_Name;
	private String user_Email;
	
	public MemberVO(){
	
	}
	public MemberVO(String user_ID, String user_Pw, String user_Name, String user_Email) {
		super();
		this.user_ID = user_ID;
		this.user_Pw = user_Pw;
		this.user_Name = user_Name;
		this.user_Email = user_Email;
	}
	public String getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}
	public String getUser_Pw() {
		return user_Pw;
	}
	public void setUser_Pw(String user_Pw) {
		this.user_Pw = user_Pw;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public String getUser_Email() {
		return user_Email;
	}
	public void setUser_Email(String user_Email) {
		this.user_Email = user_Email;
	}
	
}

package bean;

public class User {
	private long userId;//AI PK
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userSchool;
	private String userMajor;
	private String userHead;
	private int userSex;
	private String userSign;
	
	public User(){};
	public User(long userId, String userName, String userPassword, String userEmail, String userSchool,
			String userMajor, String userHead, int userSex, String userSign) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userSchool = userSchool;
		this.userMajor = userMajor;
		this.userHead = userHead;
		this.userSex = userSex;
		this.userSign = userSign;
	}
	public User(long userId, String userName, String userEmail, String userSchool, String userMajor, int userSex,
			String userSign) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userSchool = userSchool;
		this.userMajor = userMajor;
		this.userSex = userSex;
		this.userSign = userSign;
		
	}
	public long getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserSchool() {
		return userSchool;
	}
	public void setUserSchool(String userSchool) {
		this.userSchool = userSchool;
	}
	public String getUserMajor() {
		return userMajor;
	}
	public void setUserMajor(String userMajor) {
		this.userMajor = userMajor;
	}
	public String getUserHead() {
		return userHead;
	}
	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}
	public int getUserSex() {
		return userSex;
	}
	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}
	public String getUserSign() {
		return userSign;
	}
	public void setUserSign(String userSign) {
		this.userSign = userSign;
	}
	
	

}

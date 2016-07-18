package bean;

public class Focus {
	private long focusId;//AI PK
	private int userId;
	private int focusedUserId;
	
	public Focus(){}
	public Focus(long focusId, int userId, int focusedUserId) {
		super();
		this.focusId = focusId;
		this.userId = userId;
		this.focusedUserId = focusedUserId;
	}
	public long getFocusId() {
		return focusId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFocusedUserId() {
		return focusedUserId;
	}
	public void setFocusedUserId(int focusedUserId) {
		this.focusedUserId = focusedUserId;
	}
	

}

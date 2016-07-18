package bean;

public class TaskJudgement {
	private long taskId;
	private long userId;
	private long judgementId;//AI PK
	private int thumbOrStep;
	
	public TaskJudgement(){};
	public TaskJudgement(long taskId, long userId, long judgementId, int thumbOrStep) {
		super();
		this.taskId = taskId;
		this.userId = userId;
		this.judgementId = judgementId;
		this.thumbOrStep = thumbOrStep;
	}
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getJudgementId() {
		return judgementId;
	}
	public int getThumbOrStep() {
		return thumbOrStep;
	}
	public void setThumbOrStep(int thumbOrStep) {
		this.thumbOrStep = thumbOrStep;
	}
	
	

}

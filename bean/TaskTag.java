package bean;

public class TaskTag {
	private long taskId;//AI PK
	private long tagId;
	
	public TaskTag(){};
	public TaskTag(long taskId, long tagId) {
		super();
		this.taskId = taskId;
		this.tagId = tagId;
	}
	public long getTaskId() {
		return taskId;
	}
	
	public long getTagId() {
		return tagId;
	}
	public void setTagId(long tagId) {
		this.tagId = tagId;
	}
	

}

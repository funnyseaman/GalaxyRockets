package bean;

import java.sql.Timestamp;


public class Task {
	private long taskId;//AI PK
	private int taskAuthor;
	private Timestamp taskDate;
	private String taskText;
	private String taskPic;
	private long parentTaskId;
	private int treeLevel;
	private String taskTitle;
	
	public Task(){};
	public Task(long taskId, int taskAuthor, Timestamp taskDate, String taskText, String taskPic, long parentTaskId,
			int treeLevel, String taskTitle) {
		super();
		this.taskId = taskId;
		this.taskAuthor = taskAuthor;
		this.taskDate = taskDate;
		this.taskText = taskText;
		this.taskPic = taskPic;
		this.parentTaskId = parentTaskId;
		this.treeLevel = treeLevel;
		this.taskTitle = taskTitle;
	}
	public long getTaskId() {
		return taskId;
	}
	public int getTaskAuthor() {
		return taskAuthor;
	}
	public void setTaskAuthor(int taskAuthor) {
		this.taskAuthor = taskAuthor;
	}
	public Timestamp getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(Timestamp taskDate) {
		this.taskDate = taskDate;
	}
	public String getTaskText() {
		return taskText;
	}
	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}
	public String getTaskPic() {
		return taskPic;
	}
	public void setTaskPic(String taskPic) {
		this.taskPic = taskPic;
	}
	public long getParentTaskId() {
		return parentTaskId;
	}
	public void setParentTaskId(long parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	public int getTreeLevel() {
		return treeLevel;
	}
	public void setTreeLevel(int treeLevel) {
		this.treeLevel = treeLevel;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	
	

}

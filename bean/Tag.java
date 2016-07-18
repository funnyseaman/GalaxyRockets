package bean;

public class Tag {
	private long tagId;//AI PK
	private String tagName;
	
	public Tag(){};
	public Tag(long tagId, String tagName) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
	}
	
	public long getTagId() {
		return tagId;
	}	
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	

}

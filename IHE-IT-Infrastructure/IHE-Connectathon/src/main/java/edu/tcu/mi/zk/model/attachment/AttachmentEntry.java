/**
 * 
 */
package edu.tcu.mi.zk.model.attachment;

/**
 * @author Gaduo
 * 
 */
public class AttachmentEntry implements Comparable<AttachmentEntry> {
	public static final int DOCUMENT = 0;
	public static final int FOLDER = 1;

	private String title;
	private String description;
	private String content = "";
	private int type;

	public AttachmentEntry(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String toString() {
		return title + "," + description + "," + content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int compareTo(AttachmentEntry o) {
		return o.content.compareTo(this.content);
	}

}

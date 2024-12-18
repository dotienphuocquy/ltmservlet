package model.bean;

import java.sql.Date;

public class Video {
	private String id;
	private String username;
	private String name;
	private Date created_at;
	public Video(String id, String username, String name, Date created_at) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.created_at = created_at;
	}
	
	public Video(String id, String username, String name) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	
}

package model.bean;

import java.io.InputStream;

import javax.servlet.http.Part;

public class SaveVideo {
	public String id;
	public String username;
	public String name;
	public String uploadPath;
	public InputStream inputStream;
	public long size;

	public SaveVideo(String id, String username, String name, String uploadPath, InputStream inputStream, long size) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.uploadPath = uploadPath;
		this.inputStream = inputStream;
		this.size = size;
	}
	
	@Override
	public String toString() {
		return id + " " + username + " " + name + " " + uploadPath + " " + inputStream + " " + size;
	}
}

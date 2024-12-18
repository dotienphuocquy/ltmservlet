package controller;

import java.io.InputStream;

import javax.servlet.http.Part;

public class SaveVideoEntity {
	public String id;
	public String username;
	public String name;
	public String uploadPath;
	public InputStream inputStream;
	public long size;
	public Part filePart;

	public SaveVideoEntity(String id, String username, String name, String uploadPath, InputStream inputStream, long size, Part filePart) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.uploadPath = uploadPath;
		this.inputStream = inputStream;
		this.size = size;
		this.filePart = filePart;
	}
	
	@Override
	public String toString() {
		return id + " " + username + " " + name + " " + uploadPath + " " + inputStream + " " + size;
	}
}

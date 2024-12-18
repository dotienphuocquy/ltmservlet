package model.bo;

import java.util.List;

import model.bean.Video;
import model.dao.VideoDAO;

public class VideoBO {
	public static List<Video> getAllVideo(){
		return VideoDAO.getAllVideo();
	}
	
	public static List<Video> getUserVideo(String user_id){
		return VideoDAO.getUserVideo(user_id);
	}
	
	public static Video getVideoById(String id) {
		return VideoDAO.getVideoById(id);
	}
	
	public static Integer them(Video video) {
		return VideoDAO.them(video);
	}
	
	public static void xoa(String id) {
		VideoDAO.xoa(id);
	}
	
	public static void sua(Video video) {
		VideoDAO.sua(video);
	}
}

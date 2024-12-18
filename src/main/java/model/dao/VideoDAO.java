package model.dao;

import java.util.List;

import model.bean.Video;

public class VideoDAO {
	private static final GenericDAO<Video> genericDAO = new GenericDAO<Video>(
			rs -> new Video(rs.getString("id"), rs.getString("username"), rs.getString("name"), rs.getDate("created_at")));
	
	public static List<Video> getAllVideo(){
		String sql = "select * from video";
		return genericDAO.list(sql);
	}
	
	public static List<Video> getUserVideo(String username){
		String sql = "select * from video where username = ?";
		return genericDAO.list(sql, username);
	}
	
	public static Video getVideoById(String id) {
		String sql = "select * from video where id = ?";
		List<Video> data = genericDAO.list(sql, id);
		if(data.size() > 0) return data.get(0);
		else return null;
	}
	
	public static Integer them(Video video) {
		String sql = "insert into video(id,username,name) values(?,?,?)";
		return genericDAO.add(sql, video.getId(), video.getUsername(), video.getName());
	}
	
	public static void sua(Video video) {
		String sql = "update video set name=? where id=?";
		genericDAO.updateOrDelete(sql, video.getName(), video.getId());
	}
	
	public static void xoa(String id) {
		String sql = "delete from video where id=?";
		genericDAO.updateOrDelete(sql, id);
	}
}

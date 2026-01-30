package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONObject;

import utils.DBManager;
import model.Mood;

public class MoodDAO {
	public static final Connection con = DBManager.getConnection();
	
	public static Mood mood(JSONObject json) {
		try {
			return new Mood(
				json.getString("name"), 
				json.getString("description"), 
				json.getInt("mood")
			);
		} catch(Exception e) {
			return null;
		}
	}
	public static Mood mood(String json) {
		return mood(new JSONObject(json));
	}
	
	public static boolean logMood(String data) throws Exception {
		Mood m = mood(data);
		if (m==null)throw new Exception("Invalid JSON / Missing Required Fields");
		try {
			PreparedStatement ps = con.prepareStatement("insert into table mood (mood) values (?);");
			ps.setString(1, String.valueOf(m.getEmotion()));
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
//			return false;
		}

	}
	public static Mood[] getMood() throws Exception {
		try {
			PreparedStatement ps = con.prepareStatement("select * from mood where user=");
			ResultSet rs = ps.executeQuery();
			Mood[] moods = 
			while(rs.next()) {
				
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
}

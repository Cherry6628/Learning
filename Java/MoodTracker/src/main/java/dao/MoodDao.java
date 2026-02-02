package dao;

import model.Mood;
import java.sql.*;
import java.util.*;
import utils.db.DBManager;

public class MoodDao {

    public boolean saveMood(Mood mood) {
        String sql = "INSERT INTO moods (name, description, mood) VALUES (?, ?, ?)";

        try (PreparedStatement ps = DBManager.getConnection().prepareStatement(sql)) {

            ps.setString(1, mood.getName());
            ps.setString(2, mood.getDescription());
            ps.setInt(3, mood.getMood());
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Mood> getAllMoods() {
        List<Mood> list = new ArrayList<>();
        
        String sql = "SELECT name, description, mood FROM moods";

        try (Statement st = DBManager.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Mood m = new Mood();
                m.setName(rs.getString("name"));
                m.setDescription(rs.getString("description"));
                m.setMood(rs.getInt("mood"));
                list.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
        
        
    }
}
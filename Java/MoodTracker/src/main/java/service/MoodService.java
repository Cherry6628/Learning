package service;

import dao.MoodDao;
import model.Mood;
import java.util.List;

public class MoodService {

    private final MoodDao dao = new MoodDao();

    public boolean logMood(String name, String desc, int moodValue) {
//    	System.out.println(name);
//    	System.out.println(desc);
//    	System.out.println(moodValue);
        if (name == null || name.isBlank()) return false;
        if (desc == null) desc = "";
        if (moodValue < 0 || moodValue > 4) return false;

        Mood mood = new Mood();
        mood.setName(name);
        mood.setDescription(desc);
        mood.setMood(moodValue);

        return dao.saveMood(mood);
    }

    public List<Mood> fetchAllMoods() {
        return dao.getAllMoods();
    }
}
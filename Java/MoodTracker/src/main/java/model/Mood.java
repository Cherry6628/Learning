package model;

public class Mood{
	private Emotion emotion;
	private String name, description;
	
	
	public int getEmotion() {
		return Emotion.toInt(emotion);
	}
	public void setEmotion(int level) {
		this.emotion = Emotion.fromInt(level);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	public Mood(String name, String description, int level){
		this.name=name;
		this.description=description;
		this.emotion=Emotion.fromInt(level);
	}
	
	
	
}
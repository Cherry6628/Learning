package model;

public class Mood{
	private int mood;
	private String name, description;
	
	
	public int getMood() {
		return mood;
	}
	public void setMood(int level) {
		this.mood = level;
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
		this.mood=level;
	}
	public Mood() {
		
	}
	public String toString() {
		return this.mood+"";
	}
	
}
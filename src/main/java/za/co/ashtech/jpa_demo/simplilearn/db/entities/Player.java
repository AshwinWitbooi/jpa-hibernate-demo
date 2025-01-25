package za.co.ashtech.jpa_demo.simplilearn.db.entities;

import javax.persistence.Id;
import javax.persistence.Table;

@Table
public class Player {
	
	@Id
	private int id;
	private String fullname;
	private String teamName;
	private int age;
	
	public Player() {
		super();
	}
	
	public Player(int id, String fullname, String teamName, int age) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.teamName = teamName;
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Player [id=" + id + ", fullname=" + fullname + ", teamName=" + teamName + ", age=" + age + "]";
	}
	

}

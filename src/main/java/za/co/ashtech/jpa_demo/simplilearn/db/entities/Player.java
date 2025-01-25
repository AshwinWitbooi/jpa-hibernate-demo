package za.co.ashtech.jpa_demo.simplilearn.db.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String fullname;
	@OneToOne
	@JoinColumn(name = "id") 
	private Team team;
	private int age;
	
	public Player() {
		super();
	}

	public Player(String fullname, Team team, int age) {
		super();
		this.fullname = fullname;
		this.team = team;
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

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", fullname=" + fullname + ", team=" + team.toString() + ", age=" + age + "]";
	}
	
	
}

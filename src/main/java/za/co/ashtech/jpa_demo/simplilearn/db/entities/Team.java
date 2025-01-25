package za.co.ashtech.jpa_demo.simplilearn.db.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String teamName;
	
	
	public Team() {
		super();
	}
	
	public Team(String teamName) {
		super();
		this.teamName = teamName;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTeamName() {
		return teamName;
	}


	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", teamName=" + teamName + "]";
	}	

}

package application;

public class Course {
	private String name;
	private int creditNum;
	private String grade;
	private double pointEarned;
	
	/**
	 * Course constructor
	 * @param name
	 * @param creditNum
	 * @throws Exception 
	 */
	public Course(String name, int creditNum, String grade) throws Exception {
		this.name = name;
		this.creditNum = creditNum;
		this.grade = grade;
		switch(grade) {
			case "A":
				pointEarned = 4.0;
				break;
			case "AB":
				pointEarned = 3.5;
				break;
			case "B":
				pointEarned = 3.0;
				break;
			case "BC":
				pointEarned = 2.5;
				break;
			case "C":
				pointEarned = 2.0;
				break;
			case "D":
				pointEarned = 1.0;
				break;
			case "F":
				pointEarned = 0.0;
				break;
			default:
				throw (new Exception("Grade input not valid"));
		}
			
	}
	
	public String getName() {
		return name;
	}
	
	public int getCreditNum() {
		return creditNum;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String newGrade) {
		grade = newGrade;
	}
	
	public double getPointEarned() {
		return pointEarned;
	}
	
	public void setPointEarned(double newPointEarned) {
		pointEarned = newPointEarned;
	}
	
	
}


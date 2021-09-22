package application;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
		Course c0 = new Course("CS252", 1, "F");
		Course c1 = new Course("CS354", 2, "F");
		Course c2 = new Course("CS240", 3, "F");
		//Course c3 = new Course("HIS246", 3, "F");
		//Course c4 = new Course("PSYCH202", 4, "F");
		
		ArrayList<Course> c = new ArrayList<Course>();
		c.add(c0);
		c.add(c1);
		c.add(c2);
		//c.add(c3);
		//c.add(c4);
		Schedule sche = new Schedule(c);
		sche.setDesiredGPA(3.5);
		sche.printSchedule();
		sche.waysToGetGoal();
		//sche.printSchedule();
		
	}
}

package application;

import java.util.ArrayList;

public class Schedule {
	private ArrayList<Course> courseList;
	private ArrayList<Course> backUpCourseList = new ArrayList<Course>();
	private int totalCredits = 0;
	private double GPA = 0.0;
	private double desiredGPA = 0.0;
	private boolean failed = false;

	public Schedule(ArrayList<Course> courseList) {
		this.courseList = courseList;
		for (int i = 0; i < courseList.size(); i++) {
			totalCredits += courseList.get(i).getCreditNum();
		}

		for (int i = 0; i < courseList.size(); i++) {
			String name = courseList.get(i).getName();
			int creditNum = courseList.get(i).getCreditNum();
			String grade = courseList.get(i).getGrade();
			try {
				backUpCourseList.add(new Course(name, creditNum, grade));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Course> getCourseList() {
		return courseList;

	}

	public int getTotalCredit() {
		return totalCredits;
	}

	public void calcGPA() {
		GPA = 0.0;
		for (int i = 0; i < courseList.size(); i++) {
			GPA += courseList.get(i).getPointEarned() * courseList.get(i).getCreditNum();
		}
		GPA /= (double) totalCredits;

	}

	public void setDesiredGPA(double dGpa) {
		desiredGPA = dGpa;
	}

	private void upGrade(Course c) throws Exception {
		String grade = c.getGrade();
		switch (grade) {
		case "A":
			break;
		case "AB":
			c.setGrade("A");
			c.setPointEarned(4.0);
			break;
		case "B":
			c.setGrade("AB");
			c.setPointEarned(3.5);
			break;
		case "BC":
			c.setGrade("B");
			c.setPointEarned(3.0);
			break;
		case "C":
			c.setGrade("BC");
			c.setPointEarned(2.5);
			break;
		case "D":
			c.setGrade("C");
			c.setPointEarned(2.0);
			break;
		case "F":
			c.setGrade("D");
			c.setPointEarned(1.0);
			break;
		default:
			throw (new Exception("Grade input not valid"));
		}

	}

	private void resetCourse() {
		for (int i = 0; i < courseList.size(); i++) {
			String name = backUpCourseList.get(i).getName();
			int creditNum = backUpCourseList.get(i).getCreditNum();
			String grade = backUpCourseList.get(i).getGrade();
			try {
				courseList.set(i, new Course(name, creditNum, grade));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private boolean isGoalFailed() {
		if (GPA >= desiredGPA) {
			failed = false;
		} else {
			failed = true;
		}
		return failed;
	}

	public void printGPA() {
		calcGPA();
		System.out.printf("%25s", "Your GPA: ");
		System.out.printf("%.3f%n", GPA);
		System.out.println("-------------------------------------------------");
	}
	
	public void waysToGetGoal() throws Exception {
		System.out.println("In order to achieve your desired GPA, you need to get at least: ");
		// maxing out first courses and check gpa and gradually skipping each course
		for (int j = 0; j < courseList.size(); j++) {
			for (int i = j; i < courseList.size(); i++) {
				Course tmp = courseList.get(i);

				do {
					upGrade(tmp);
					calcGPA();
				} while (isGoalFailed() == true && !tmp.getGrade().equals("A"));
				if (isGoalFailed() == false) {
					// printSchedule();
					if (j != courseList.size() - 1) {
						System.out.print("OR, ");
					}
					for (int z = 0; z < courseList.size(); z++) {
						tmp = courseList.get(z);
						System.out
								.println("	" + tmp.getGrade() + " for your " + tmp.getCreditNum() + " credit course.");

					}

					printGPA();

					break;
				}
			}

			// reset course list
			resetCourse();
		}
		failed = true;

		// same method but reverse order
		for (int j = courseList.size() - 1; j >= 0; j--) {
			for (int i = j; i >= 0; i--) {
				Course tmp = courseList.get(i);

				do {
					upGrade(tmp);
					calcGPA();
				} while (isGoalFailed() == true && !tmp.getGrade().equals("A"));
				if (isGoalFailed() == false) {
					// printSchedule();
					if (j != courseList.size() - 1) {
						System.out.print("OR, ");
					}
					for (int z = 0; z < courseList.size(); z++) {
						tmp = courseList.get(z);
						System.out
								.println("	" + tmp.getGrade() + " for your " + tmp.getCreditNum() + " credit course.");

					}

					printGPA();

					break;
				}
			}

			// reset course list
			resetCourse();
		}
		failed = true;
		// uniform increase
		for (int i = 0; i < courseList.size(); i++) {
			Course tmp = courseList.get(i);
			upGrade(tmp);
			calcGPA();
			
			if (isGoalFailed() == false) {
				// printSchedule();
				if (i != courseList.size() - 1) {
					System.out.print("OR, ");
				}
				for (int z = 0; z < courseList.size(); z++) {
					tmp = courseList.get(z);
					System.out
							.println("	" + tmp.getGrade() + " for your " + tmp.getCreditNum() + " credit course.");

				}

				printGPA();

				break;
			}
			if (i == courseList.size()-1) {
				i = -1;
			}
		}
		resetCourse();

		// upgrade
		// ***************
		if (isGoalFailed() == true) {
			System.out.println("Otherwise, there is no (other) way to achieve your desired GPA.");
		}

	}

	public void printSchedule() {
		calcGPA();
		System.out.println("Your Courses:");
		System.out.println("*******************************************");
		for (int i = 0; i < courseList.size(); i++) {
			Course tmp = courseList.get(i);
			System.out.printf("Name:  %8s", tmp.getName());
			System.out.printf("%10s", "|Credit:");
			System.out.printf("%4d", tmp.getCreditNum());
			System.out.printf("%10s", "|Grade:");
			System.out.printf("%4s", tmp.getGrade());
			System.out.println();
		}
		System.out.println("     ---------------------------------     ");
		System.out.printf("%25s", "Your GPA: ");
		System.out.printf("%.3f%n", GPA);

		System.out.printf("%25s", "Your desired GPA: ");
		System.out.printf("%.3f%n", desiredGPA);
		if (isGoalFailed() == true) {
			System.out.printf("%31s%n", "You Failed B*tch!");
		} else {
			System.out.printf("%31s%n", "Goal Accomplished!");
		}
		System.out.println("*******************************************");
		System.out.println();
	}
}

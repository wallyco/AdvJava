package edu.waketech.mygrade.calculations;

public class AverageCalculator {
	private static final double QUIZZES = .15;
	private static final double MID_TERM = .10;
	private static final double LABS = .25;
	private static final double PROJECT_ONE = .20;
	private static final double PROJECT_TWO = .20;
	private static final double FINAL = .10;
	private double quiz = 0;
	private double midterm = 0;
	private double assignments = 0;
	private double projectone = 0;
	private double projecttwo = 0;
	private double finaltest = 0;
	private static double calculatedAverage;
	
	
	public AverageCalculator(double quiz, double midterm, double assignments, 
				double projectone, double projecttwo, double finaltest) {
		this.quiz = quiz;
		this.midterm = midterm;
		this.assignments = assignments;
		this.projectone = projectone;
		this.projecttwo = projecttwo;
		this.finaltest = finaltest;
	}
	
	public void calculateAverage() {	
		calculatedAverage = (QUIZZES * quiz)
							+(MID_TERM * midterm)
							+(LABS * assignments)
							+(PROJECT_ONE * projectone)
							+(PROJECT_TWO * projecttwo)
							+(FINAL * finaltest);
	}
	
	@Override
	public String toString() {
		return String.format("%.2f", calculatedAverage);
	}


	public static double getWeightQuizzes() {
		return QUIZZES;
	}

	public static double getWeightMidTerm() {
		return MID_TERM;
	}

	public static double getWeightLabs() {
		return LABS;
	}

	public static double getWeightProjectOne() {
		return PROJECT_ONE;
	}

	public static double getWeightProjectTwo() {
		return PROJECT_TWO;
	}

	public static double getWeightFinal() {
		return FINAL;
	}

}

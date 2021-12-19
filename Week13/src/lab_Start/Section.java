package lab_Start;

import java.util.Objects;

public class Section {
	private String department;
	private String instructor;
	private String title;
	private int courseNumber;
	private int sectionNumber;
	
	public Section(String dept,
			int course,
			int section,
			String instructor,
			String title ) {
		department = dept;
		courseNumber = course;
		sectionNumber = section;
		this.instructor = instructor;
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	public int getSectionNumber() {
		return sectionNumber;
	}

	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseNumber, department, sectionNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Section other = (Section) obj;
		return courseNumber == other.courseNumber && Objects.equals(department, other.department)
				&& sectionNumber == other.sectionNumber;
	}

	@Override
	public String toString() {
		return "Section [department=" + department + ", instructor=" + instructor + ", title=" + title
				+ ", courseNumber=" + courseNumber + ", sectionNumber=" + sectionNumber + "]\n";
	}
	
	

}

package edu.waketech.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.waketech.academic.Assignment;
import edu.waketech.academic.Course;
import edu.waketech.common.Student;
import edu.waketech.common.StudentBody;
import edu.waketech.GradebookApp;

class GradebookAppTest {


	// add for grading
	@BeforeEach
	void testClearStudentBody() {
		StudentBody.getInstance().clear();
	}

	@Test
	void testCreateAssignment() {
		Course course = new Course(new String("CSC"), 251);
		Student s1 = new Student(new String("last1"), new String("first1"), 2);
		Student s2 = new Student(new String("last2"), new String("first2"), 3);
		StudentBody sb = StudentBody.getInstance();
		sb.add(s1);
		sb.add(s2);
		
		course.addStudent(s1.getId());
		course.addStudent(s2.getId());
		
		GradebookApp.createAssignment(course, new String("lab1"), 50);
		List<Assignment> labList = course.getAssignment(s1.getId(), new String("lab1"));
		Assignment foundLab = labList.get(0);
		assertEquals(new String("lab1"), foundLab.getName());
		assertEquals(50, foundLab.getPossiblePoints());
		assertTrue(foundLab.getScore() >= 35);
	}
	
	@Test
	void testCreateAssignment1() {
		Course course = new Course(new String("CSC"), 251);
		Student s1 = new Student(new String("last1"), new String("first1"), 2);
		Student s2 = new Student(new String("last2"), new String("first2"), 3); 
		StudentBody sb = StudentBody.getInstance();
		sb.add(s1);
		sb.add(s2);
		
		course.addStudent(s1.getId());
		course.addStudent(s2.getId());
		
		GradebookApp.createAssignment(course, new String("lab1"), 50);
		List<Assignment> labList = course.getAssignment(s2.getId(), new String("lab1"));
		Assignment foundLab = labList.get(0);
		assertEquals(new String("lab1"), foundLab.getName());
		assertEquals(50, foundLab.getPossiblePoints());
		assertTrue(foundLab.getScore() >= 35);
	}
	
	@Test
	void testCalculateStudentAverageInOneCourse() {
		Course course = new Course(new String("CSC"), 251);
		Student s1 = new Student(new String("last1"), new String("first1"), 2);
		StudentBody sb = StudentBody.getInstance();
		sb.add(s1);
		course.addAssignment(s1.getId(), new Assignment("lab", 100, 70));
		course.addAssignment(s1.getId(), new Assignment("lab", 100, 75));
		assertEquals(72.5, GradebookApp.calculateStudentAverageInOneCourse(course, s1.getId()));

	}


}

////////////////////////////////////////////////////////////////////////////////
//  Course:   CSC 251 Fall 2019
//  Section:  0001
// 
//  Project:  Unit04
//  File:     Exercise_19_5_and_tester.java
//  
//  Name:     Anthony Flackus
//  Email:    aflackus@my.waketech.edu
////////////////////////////////////////////////////////////////////////////////
package lab_start;

import java.util.Arrays;

public class Exercise_19_5_and_tester {

	public static void main(String[] args) {
		String[] list1 = { "T", "To", "Tonyf", "Ton", "Tony" };
		Integer[] list2 = { 1, 6, 3, 4, 5 };
		Double[] list3 = { 9.5, 6.7, 3.4, 1.9, 8.9 };
		System.out.println("The max of " + Arrays.asList(list1) + " is: " + max(list1));
		System.out.println("The max of " + Arrays.asList(list2) + " is: " + max(list2));
		System.out.println("The max of " + Arrays.asList(list3) + " is: " + max(list3));
	}

	public static <E extends Comparable<E>> E max(E[] list) {
		int maxIndex = 0;

		return list[maxIndex];
	}

}

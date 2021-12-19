package edu.waketech.measurable;

public class MeasurableTester {
	
	public static void main(String[] args) {
		DataSetMeasurable data = new DataSetMeasurable();
		Car objCar = new Car();
		Dog objDog = new Dog();
		Human objHuman = new Human();
		
		data.add(objCar);
		data.add(objDog);
//		Only adds Measurable
//		data.add(objHuman); 
		
		System.out.println(data.toString());
	}
}



class Car implements Measurable{
	int length = 100;
	
	@Override
	public int getMeasure() {
		return length;
	}
	
	@Override
	public String toString() {
		return "Class: " + getClass().getName().substring(24) 
				+ " - Length: " + length;
	}
}

class Dog implements Measurable{
	int length = 50;
	
	@Override
	public int getMeasure() {
		return length;
	}
	
	@Override
	public String toString() {
		return "Class: " + getClass().getName().substring(24) 
				+ " - Length: " + length;
	}
}

class Human {

}

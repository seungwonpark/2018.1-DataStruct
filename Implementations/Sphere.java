import java.util.*;
public class Sphere{
	// main function
	public static void main(String[] args){
		Sphere test = new Sphere(2.0);
		test.displayStatistics();
	}

	// variables
	private double theRadius;

	// constructors
	public Sphere(){
		setRadius(1.0);
	}
	public Sphere(double initialRadius){
		setRadius(initialRadius);
	}
	public void setRadius(double newRadius){
		if(newRadius >= 0.0){
			theRadius = newRadius;
		}
	}
	public double radius(){
		return theRadius;
	}
	public double diameter(){
		return 2.0 * theRadius;
	}
	public double circumference(){
		return 2.0 * Math.PI * theRadius;
	}
	public double area(){
		return Math.PI * theRadius * theRadius;
	}
	public double volume(){
		return (4.0 * Math.PI * Math.pow(theRadius, 3.0)) / 3.0;
	}
	public void displayStatistics(){
		System.out.println("Radius = " + radius());
		System.out.println("Diameter = " + diameter());
		System.out.println("Circumference = " + circumference());
		System.out.println("Area = " + area());
		System.out.println("Volume = " + volume());
	}
}
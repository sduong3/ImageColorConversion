/**
 * Program #3b
 * Driver program. Tests the Prog3a class
 * CS108-2
 * 3/4/2016
 * @author Steven Duong
 */
import java.util.Scanner;

public class Prog3b {
	public static void main (String args[]) {
		Scanner scnr = new Scanner (System.in);
		String fileName = scnr.nextLine();
		
		System.out.println("Program 3, Steven Duong, masc1528");
		Prog3a file = new Prog3a(fileName);
		
		file.readImage();						//read original file
		file.test(60, 60);
		
		file.readImage();						//convert to gray
		file.toGrayscale();
		file.writeImage();
		
		file.readImage();						//convert to red
		file.toRed();
		file.writeImage();
		
		file.readImage();						//convert to green
		file.toGreen();
		file.writeImage();
		
		file.readImage();						//convert to blue
		file.toBlue();
		file.writeImage();
	}
}

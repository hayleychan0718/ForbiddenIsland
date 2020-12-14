package utility;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Class uses to hold utility functions
 * @author Liam Fitzgerald
 *
 */

public class Utility {
	
	/**
	 * Ensures the users enters an integer within the acceptable range
	 * @param min , minimum option
	 * @param max , maximum option
	 * @param inputScanner TODO
	 * @return , returns a valid input
	 */
	public static int acceptableInput(int min,int max, Scanner inputScanner) { //Checks if input is acceptable by using the size of the actions lists //Maybe interface
		int userInput=-1;
		boolean acceptableInput = false;
		while(acceptableInput == false) {

			if(inputScanner.hasNextInt() == true) {	//Checks if what is entered is an integer
				userInput = inputScanner.nextInt();

				if((userInput>=min && userInput <=max)) {	//Checks if it is within the acceptable range
					acceptableInput=true;	//exits method
				}
				else {
					System.out.println("Please enter a number of the range " + min+ "-" + max); //Will be invoked if an int is entered but not int the right range
					continue;
				}
			}
			else {
				System.out.println("Please enter a number of the range " + min + "-" + max); //Will be invoked if a character is entered
				inputScanner.next();
				continue;
			}
		}
		//inputScanner.close();
		return userInput;
	}
	
	/**
	 * Prints out any list of options in the desired format
	 * @param <T>
	 * @param optionList
	 */
	public static <T> void printOptions(ArrayList<T> optionList) {
		int i = 0;
		for(Object o: optionList) {
			System.out.println(o.toString() + " [" + i + "]");
			i++;
		}
	}
	
	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

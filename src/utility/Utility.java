package utility;

import java.util.Scanner;

public class UtilityFuniton {
	
	public static int acceptableInput(int min,int max) { //Checks if input is acceptable by using the size of the actions lists //Maybe interface
		int userInput=-1;
		Scanner inputScanner = new Scanner(System.in);	//Close scanner possibly
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
		inputScanner.close();
		return userInput;
	}

}

package headortail;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {	
		String input = null;
		Scanner console = new Scanner(System.in);				//initializing Scanner for user input
		System.out.println("Welcome to the game of Head or Tail where you will flip your life away!");
		System.out.print("Press the F key and flip your luck: ");
		input = console.nextLine();
		if (input.equalsIgnoreCase("f")) {									//checking user input
			game();
		} else {
			System.out.println("Ok Goodbye.");
		}
		while (input.equalsIgnoreCase("f")) {		//allowing user to continue the program until they quit
		input =	playAgain(input);
		}
	}
	
	public static void game() {
		headortail coinFlip = () -> {								//generates and returns random number of 1 or 2
			double rand = Math.floor((Math.random() * 2) + 1);
			return (int)rand;
		};
		
		coinFace coinShow = (result) -> {							//assigns variable a specific set of data depending
			String coinFace = null;									//the variable it was passed
			if (result == 1) {
				coinFace = "Heads";
			} else if (result == 2) {
				coinFace = "Tails";
			}
			return coinFace;
		};
		
		int result = coinFlip.getRan();								//initializing variable with result of function
		System.out.println("Coin Flip Program");
		String coinFace = coinShow.face(result);						//initializing variable with result of function 
		System.out.println("The Coin Flip is: " + coinFace);		//and passing a variable
	}
	
	public static String playAgain(String input) {
		Scanner console = new Scanner(System.in);				//initializing Scanner for user input
		System.out.println("Would you like to play again?\nPress f then Enter. Or Q then Enter to quit: ");
		input = console.nextLine();
		if (input.equalsIgnoreCase("f")) {			//checking user input
			game();	
		} else {
			System.out.println("Thank you for playing.");
		}
		return input;
	}
}
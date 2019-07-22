package gameofdice;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class gdGrid implements ActionListener {
	  private JFrame mainFrame;
	  private JLabel headerLabel;
	  private JLabel statusLabelP;
	  private JLabel statusLabelE;
	  private JPanel controlPanel;
	  static int gameCounter = 0;

	   public gdGrid(){
	      prepareGUI();
	   }
	   
	   private void prepareGUI(){
	      mainFrame = new JFrame("Head or Tail");
	      mainFrame.setSize(600,400);
	      mainFrame.setLayout(new GridLayout(4, 1));
	      headerLabel = new JLabel("",JLabel.CENTER );
	      statusLabelP = new JLabel("",JLabel.CENTER); 
	      statusLabelE = new JLabel("", JLabel.CENTER);
	      
	      // old school way to close a window
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	 if (gameCounter > 0) {
	        		 System.out.println("Thanks for being a sport! laters!");	 
	        	 } else {
	        		 System.out.println("See ya later alligator!");
	        	 }
	             // this is the way to force a java application to gracefully exit
	        	 // when not going to end via the main method entry point
	        	 System.exit(0);
	         }        
	      });    
	      controlPanel = new JPanel();
	      controlPanel.setLayout(new FlowLayout());

	      mainFrame.add(headerLabel);
	      mainFrame.add(controlPanel);
	      mainFrame.add(statusLabelP);
	      mainFrame.add(statusLabelE);
	      mainFrame.setVisible(true);  
	   }
	   //Create the layout for the game space
	   private void showGridLayoutDemo() throws NullPointerException {
	      headerLabel.setText("Welcome to the dice throw challenge! Do you feel lucky? Punk� Do ya?");      
	      statusLabelP.setText("waiting...");
	      statusLabelE.setText("");
	      JPanel panel = new JPanel();
	      panel.setSize(300,300);
	      GridLayout layout = new GridLayout(2,3);
	      layout.setHgap(10);
	      layout.setVgap(10);
	      panel.setLayout(layout);
	      //add the flip and reset buttons
	      JButton flip = new JButton("Click to Roll!");
	      JButton reset = new JButton("Reset");
	      flip.addActionListener(this);
	      reset.addActionListener(this);
	      panel.add(flip);
	      panel.add(reset);
	      
	      controlPanel.add(panel);
	      mainFrame.setVisible(true);  
	   }
	 //Check which button is pressed and perform necessary tasks based on that selection
	   public void actionPerformed(ActionEvent ae) {
		   //Game play functionality
			play gamePlay = () -> {
				int playerDie1 = dieRoll();					//rolling player dice
				int playerDie2 = dieRoll();
				int eDie1 = dieRoll();						//rolling computer dice
				int eDie2 = dieRoll();
				int playerTotal = playerDie1 + playerDie2;			//get totals of dice
				int eTotal = eDie1 + eDie2;
				String pTag = getTag(playerDie1, playerDie2);		//get tag lines for dice rolls
				String eTag = getTag(eDie1, eDie2);
				//Get the results of the rolls into the labels
				statusLabelP.setText(getOutputLine("You", playerDie1, playerDie2, pTag));
				statusLabelE.setText(getOutputLine("I", eDie1, eDie2, eTag));
				
				//checking for the winner and loser
				if (playerTotal > eTotal) {
					headerLabel.setText("You Win!! Did you cheat?? Gimme another try, c�mon ");
				} else if (eTotal > playerTotal) {
					headerLabel.setText("I Win!!! Wanna try again? ");
				} else if (playerTotal == eTotal) {							//on a tie a check for doubles is performed
					int pDCheck = doublesCheck(playerDie1, playerDie2);
					int eDCheck = doublesCheck(eDie1, eDie2);
					if (pDCheck == 1 & eDCheck == 0) {						//the double check also checks that the other player does not have a double
						headerLabel.setText("Damn! You win on a Double! What Luck! Did you cheat?? Gimme another try, c�mon ");
					} else if (eDCheck == 1 & pDCheck == 0) {
						headerLabel.setText("Damn! I win on a Double! Ah! I pity you fool! Wanna try again? ");
					} else {
						headerLabel.setText("Stalemate! You�re tough, let�s try for a tie-breaker ");
					}
				}
			};
			//Check which button is pressed and perform necessary tasks based on that selection
		   String checker = ae.getActionCommand();
		   if (checker.equals("Click to Roll!")) {
			   gameCounter++;
			   gamePlay.game();
		   } else if (checker.equals("Reset")) {
		      headerLabel.setText("Welcome to the dice throw challenge! Do you feel lucky? Punk� Do ya?");      
		      statusLabelP.setText("waiting...");
		      statusLabelE.setText("");
		   }
	   }
	   
		public static void main(String[] args) {	
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					gdGrid gridLayoutDemo = new gdGrid();  
				    gridLayoutDemo.showGridLayoutDemo();
				}
			});
		}
		//generate a random int from 1-6
		public static int dieRoll() {								//generates and returns random number of 1 or 6
			double rand = Math.floor((Math.random() * 6) + 1);
			return (int)rand;
		}
		//take values and return a String of results
		public static String getOutputLine(String rollerID, int die1, int die2, String tag) {
			String outputLine = rollerID + " have rolled " + die1 + " and " + die2 + " > the " + tag;
			return outputLine;
		}
		//returns the necessary tag for the pair of dice rolled
		public static String getTag(int check1, int check2) {
			String[][] multi = new String[][] {										//initializing the string matrix
				{ "", "1", "2", "3", "4", "5", "6" },
				{ "1", "Snake Eyes", "Australian Yo", "Little Joe From Kokomo", "No field five", "Easy Six", "Six one you're done" },
				{ "2", "Ace caught a deuce", "Ballerina", "The Fever", "Jimmie Hicks", "Benny Blue", "Easy Eight" },
				{ "3", "Easy Four", "OJ", "Brooklyn Fores", "Big Red", "Eighter from Decatur", "Nina from Pasadena" },
				{ "4", "Little Phoebe", "Easy Six", "Skinny McKinney", "Square Pair", "Railroad nine", "Big one on the end" },
				{ "5", "Sixic from Dixie", "Skinny Dugan", "Easy Eight", "Jesse James", "Puppy paws", "Yo" },
				{ "6", "The Devil", "Easy Eight", "Lou Brown", "Tennessee", "Six five no jive", "Midnight" }
			};
			return multi[check1][check2];										//return tag line for dice roll
		}
		//Check's for doubles if there is a tie
		public static int doublesCheck(int check1, int check2) {			//doubles check returns a value for later purpose
			if (check1 == check2) {
				return 1;
			} else {
				return 0;
			} 
		}
}
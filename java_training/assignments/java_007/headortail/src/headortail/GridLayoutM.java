package headortail;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GridLayoutM implements ActionListener {
	  private JFrame mainFrame;
	  private JLabel headerLabel;
	  private JLabel statusLabel;
	  private JPanel controlPanel;
	  static int gameCounter = 0;

	   public GridLayoutM(){
	      prepareGUI();
	   }
	   
	   private void prepareGUI(){
	      mainFrame = new JFrame("Head or Tail");
	      mainFrame.setSize(600,400);
	      mainFrame.setLayout(new GridLayout(3, 1));
	      headerLabel = new JLabel("",JLabel.CENTER );
	      statusLabel = new JLabel("",JLabel.CENTER);        
	      statusLabel.setSize(350,100);
	      
	      // old school way to close a window
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	 if (gameCounter > 0) {
	        		 System.out.println("Thank you for playing.");	 
	        	 } else {
	        		 System.out.println("Ok goodbye!");
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
	      mainFrame.add(statusLabel);
	      mainFrame.setVisible(true);  
	   }
	   //Create the layout for the game space
	   private void showGridLayoutDemo() throws NullPointerException {
	      headerLabel.setText("Welcome to the game of Head or Tail where you will flip your life away!");      
	      statusLabel.setText("waiting...");
	      JPanel panel = new JPanel();
	      panel.setSize(300,300);
	      GridLayout layout = new GridLayout(2,3);
	      layout.setHgap(10);
	      layout.setVgap(10);
	      panel.setLayout(layout);
	      //add the flip and reset buttons
	      JButton flip = new JButton("Click to Flip!");
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
		   String checker = ae.getActionCommand();
		   if (checker.equals("Click to Flip!")) {
			   gameCounter++;
			   String coinFace = game();
			   statusLabel.setText("The coin flip is " + coinFace);
			   headerLabel.setText("Would you like to play again?");
		   } else if (checker.equals("Reset")) {
		      headerLabel.setText("Welcome to the game of Head or Tail where you will flip your life away!");      
		      statusLabel.setText("waiting...");
		   }
	   }

	
	public static void main(String[] args) {	
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GridLayoutM gridLayoutDemo = new GridLayoutM();  
			    gridLayoutDemo.showGridLayoutDemo();
			}
		});
	}
	
	public static String game() {
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
		String coinFace = coinShow.face(result);					//initializing variable with result of function 
		return coinFace;											//and passing a variable
	}
}
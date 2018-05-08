package lessons;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Loops {
    	//create list of strings for user input
    	static ArrayList<String> usersInput = new ArrayList<String>();
    	//set blank response message
    	static int msg = 0;
    	//set robot starting x and y 
    	//FOR SOME REASON Y and X are reversed, probably in the display method
    	static int rx = 0;
    	static int ry = 3;
	
    public static void main(String[] args) {
    	//initialize a 4x4 grid full of 0, or "empty space"
    	int[][] loops = new int[4][4];

    	//set robot position
    	loops[ry][rx] = 1;
    	//set wall position(s)
    	loops[0][2] = 2;
    	loops[2][2] = 2;
    	//set goal position
    	loops[0][3] = 3;
   	
    	// check user input
         userInput();
    	//if there's user input run till an error is found
         if (usersInput.size() != 0){
         loops = userLogic(loops);        	 
         }

    	//Draw the grid
    	drawGrid(loops);
    }
     
	public static void drawGrid(int[][] loop) {
    	//display result
    	JFrame frame = new JFrame("Loops");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(320, 340);
    	JPanel panel = new JPanel();
    	panel.setLayout(new GridLayout(4, 4));

    	//Get images for display
    	ImageIcon space = new ImageIcon("src/images/space.jpg");
    	ImageIcon robot = new ImageIcon("src/images/robot.jpg");
    	ImageIcon wall = new ImageIcon("src/images/wall.jpg");
    	ImageIcon goal = new ImageIcon("src/images/goal.jpg");

    	//assign images to correct places in grid
    	JLabel[] labels=new JLabel[16];
    	int num;
    	int labelnum = 0;
    		for (int x=0;x<4;x++){
        		for (int y=0;y<4;y++){
        			num = loop[x][y];
        			switch (num) {
                    case 0: 
        	        	labels[labelnum]=new JLabel(space);
        	        	labelnum++;
                             break;
                    case 1:
        	        	labels[labelnum]=new JLabel(robot);
        	        	labelnum++;
                             break;
                    case 2:
        	        	labels[labelnum]=new JLabel(wall);
        	        	labelnum++;
                             break;
                    case 3:
        	        	labels[labelnum]=new JLabel(goal);
        	        	labelnum++;
                             break;
        						}
        			}
    	        }

    	//put labels on the panel
    	for (int i=0;i<16;i++){
            panel.add(labels[i]);
        }

    	//display panel after labels are added
    	frame.add(panel);
    	frame.pack();
    	frame.setVisible(true);
    	
		//Displays a user message if error/completion
    	//doesn't display anything if 0
		switch (msg) {
        case 1:
		//out of bounds
		JOptionPane.showMessageDialog(frame, "Error, can't move out of bounds.");
                 break;
        case 2:
		//collision with wall
    	JOptionPane.showMessageDialog(frame, "Error, can't move through walls.");
                 break;
        case 3:
		//successful 
    	JOptionPane.showMessageDialog(frame, "Congrats! You got 1 point!.");
                 break;
					}  
	}
	
	public static int[][] userLogic(int[][] loop) {
		//Move the robot till user has errors, display last working location before error
		//If there's an error change msg to the correct message number
		
		boolean error = false;
		//try catch for if the robot goes out of the bounds of the array
        try {
        for (int i = 0; i < usersInput.size(); i++) {
        	String in = usersInput.get(i);
            System.out.println(in);
			switch (in) {
            case "up": 
            		//if it's open space move up
            		if (loop[ry-1][rx] == 0) {
            			loop[ry-1][rx] = 1;
            			loop[ry][rx] = 0;
            			ry--;
            		//if it's a wall don't move and put an error
            		} else if (loop[ry-1][rx] == 2) {
            			msg = 2;
            		    error = true;
            		// it's a goal you win
            		} else {
            			msg = 3;
            			error = true;
            		}
                     break;
            case "down":
        		//if it's open space move down
        		if (loop[ry+1][rx] == 0) {
        			loop[ry+1][rx] = 1;
        			loop[ry][rx] = 0;
        			ry++;
        		//if it's a wall don't move and put an error
        		} else if (loop[ry+1][rx] == 2) {
        			msg = 2;
        		    error = true;
        		// it's a goal you win
        		} else {
        			msg = 3;
        			error = true;
        		}
                     break;
            case "left":
        		//if it's open space move left
        		if (loop[ry][rx-1] == 0) {
        			loop[ry][rx-1] = 1;
        			loop[ry][rx] = 0;
        			rx--;
        		//if it's a wall don't move and put an error
        		} else if (loop[ry][rx-1] == 2) {
        			msg = 2;
        		    error = true;
        		// it's a goal you win
        		} else {
        			msg = 3;
        			error = true;
        		}
                     break;
            case "right":
        		//if it's open space move right
        		if (loop[ry][rx+1] == 0) {
        			loop[ry][rx+1] = 1;
        			loop[ry][rx] = 0;
        			rx++;
        		//if it's a wall don't move and put an error
        		} else if (loop[ry][rx+1] == 2) {
        			msg = 2;
        		    error = true;
        		// it's a goal you win
        		} else {
        			msg = 3;
        			error = true;
        		}
                     break;
						}
			if(error)break;
			}
        }catch (IndexOutOfBoundsException e) {
    	    System.err.println("IndexOutOfBoundsException: " + e.getMessage());
    	    msg = 1;
    		return loop;
    	}
        
        return loop;
	}
	
	public static void roboUp() {
		//Sets the user to move up
    	usersInput.add("up");
	}
	public static void roboDown() {
		//Sets the user to move down
    	usersInput.add("down");
	}
	public static void roboLeft() {
		//Sets the user to move left
    	usersInput.add("left");
	}
	public static void roboRight() {
		//Sets the user to move right
    	usersInput.add("right");
	}
	
	/*
	 * 
	 * -----------------------User Input ---------------------------
	 *
	 */
	public static void userInput() {
		//The roboUp(), roboDown(), roboLeft, roboRight() methods move your robot one space
		//Use loop(s) to move the robot to the goal.	

		
		
		
		
		
	}
	/*
	 * 
	 * -----------------------User Input ---------------------------
	 *
	 */
}

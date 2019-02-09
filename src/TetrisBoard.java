/**
 * @author Cecily Page
 * Sierra McConnell
 * CS 110
 * Tetris Project
 * this class creates the board that tetris is played on
 * it changes the dimentions too so its in terms of 
 * tetris squares instead of pixels.
 */


import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 * A Pane in which tetris squares can be displayed.
 * 
 * @author pipWolfe
 */
public class TetrisBoard extends Pane{
    // The size of the side of a tetris square
    public static final int SQUARE_SIZE = 20;
    // The number of squares that fit on the screen in the x and y dimensions
    public static final int X_DIM_SQUARES = 15;
    public static final int Y_DIM_SQUARES = 30; // change this constant if the
    // board is too big for your screen
    private TetrisSquare[][] squares = new TetrisSquare[Y_DIM_SQUARES][X_DIM_SQUARES];
    int newY = 0;
    public static int score = 0;
    /**
     * Sizes the board to hold the specified number of squares in the x and y
     * dimensions.
     */
    
    public TetrisBoard(TetrisBoard board) {
    	
        this.setPrefHeight(Y_DIM_SQUARES*SQUARE_SIZE);
        this.setPrefWidth(X_DIM_SQUARES*SQUARE_SIZE);
        BackgroundFill myBF = new BackgroundFill(Color.BLACK, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));// or null for the padding
        setBackground(new Background(myBF));
       

    }
    
    public boolean canIMove(TetrisSquare[] origSquare, int whereAmILooking, int[] xMaxima, int ymax){
    	boolean hugs = false;
    	int x = 0;
    	int y = 0;
    	int xmax = xMaxima[1];
    	int xmin = xMaxima[0];
    	int count = 0;
  	     for(int i = 0; i < origSquare.length; i++){
    		x = origSquare[i].getX();
    		y = origSquare[i].getY();
    		//System.out.println("y = " + y);
    		//System.out.println("x = " + x);
    		switch (whereAmILooking){
    		case -1: //left
   			if(x >= xmin){
    				if(squares[y][x-1] == null && x > 0){
    					count++;
    				}
    			 if(count == 4){
  				hugs = true;
    			}
   			}
    			break;
    		case 0: //down
    			if(y <= ymax) {
    			if(squares[y+1][x] == null){
    				count++;
				}
    				if(count == 4){
    					hugs =  true;
    				}
    			}
    			break;
    		case 1: //right
    			if(x <= xmax){
    			if(squares[y][x+1] == null){
    				count++;
				}
    			if(count == 4){
						hugs =  true;
    				}
    			}
    			break;
    		case 2: //all the way down
    			int row = 29;
    			for(int j = 29; j > 0; j--){
    				while(squares[j][i] == null){
    					row--;
    				}
    			}
    			break;
    		case 3: //rotate
    			if(x == xmin + 1 || x == xmax -1 || y == ymax -1){
    				return false;
    			}
    			break;
    		
    		}
    }
    	return hugs;
    	
}
 
    
    public int getNewY(){
    	return newY;
    }
    
    public void addToBoard(TetrisSquare[] arr){
    	for(int i = 0; i < arr.length; i++){
    		System.out.println("this method was called");
    		int xSpot = arr[i].getX();
    		int ySpot = arr[i].getY();
    		squares[ySpot][xSpot] = arr[i];
    	}
    	score++;
    }
    
    public TetrisSquare[][] getSquares(){
    	return squares;
    }
 
    public void rowCheck(){
    	int rowRemoved = 0;
    	for(int i = 0; i < squares.length; i++){ //Cycle through rows
    		int squareCounter = 0;					//Initialize variable which will count squares in each row in the data structure	
    		for(int j = 0; j < squares[i].length; j++){	//Cycle through columns
    			if(squares[i][j] != null){		//If a square is present increment the squareCounter.
    				squareCounter++;
    			}
    		}
    			if(squareCounter == 15){	//Once all columns have been cycled through test to see if every spot was full.
    				System.out.println("Row " + i + " is full");	//If its full tell the user
    				for(int col = 0; col < squares[i].length; col++){	//Cycle through the columns of the soon-to-be deleted row
    					squares[i][col].removeFromDrawing();		// Remove them from the drawing
    					squares[i][col] = null;					//Remove them from the data structure
    					rowRemoved = i;							// Record the number of the row that was removed as rowRemoved
    				}

    				System.out.println("rowRemoved is " + rowRemoved);
   				 //Move the rows above the deleted row down one. 
   				for(int rowsToMove = rowRemoved-1; rowsToMove >= 0; rowsToMove--){ //Cycle through all rows above rowRemoved starting at the row above the one removed
   					System.out.println("rowsToMovi is" + rowsToMove);
   					for(int c = 0; c < 15; c++){ // Cycle through square objects of each row. (we needed to make this to go up instead of starting at 0)
   						if(squares[rowsToMove][c] != null){    				
   							squares[rowsToMove][c].moveToTetrisLocation(squares[rowsToMove][c].getX(), squares[rowsToMove][c].getY()+1);   	
   							squares[rowsToMove + 1][c] = squares[rowsToMove][c];
   							squares[rowsToMove][c] = null;   	
   							}
   						}score = score + 10;	
   					}
   				
   				}	
   			}
   		}
    
    	public static int getScore(){
    		return score;
    	}
   	}
//public void rowCheck(){
//	int rowRemoved = 0;
//	for(int i = 0; i < squares.length; i++){ //Cycle through rows
//		int squareCounter = 0;					//Initialize variable which will count squares in each row in the data structure	
//		for(int j = 0; j < squares[i].length; j++){	//Cycle through columns
//			if(squares[i][j] != null){		//If a square is present increment the squareCounter.
//				squareCounter++;
//			}
//		}
//			if(squareCounter == 15){	//Once all columns have been cycled through test to see if every spot was full.
//				System.out.println("Row " + i + " is full");	//If its full tell the user
//				for(int col = 0; col < squares[i].length; col++){	//Cycle through the columns of the soon-to-be deleted row
//					squares[i][col].removeFromDrawing();		// Remove them from the drawing
//					squares[i][col] = null;					//Remove them from the data structure
//					rowRemoved = i;							// Record the number of the row that was removed as rowRemoved
//				}
//				System.out.println("rowRemoved is " + rowRemoved);
//				 //Move the rows above the deleted row down one. 
//				for(int rowsToMove = rowRemoved-1; rowsToMove >= 0; rowsToMove--){ //Cycle through all rows above rowRemoved starting at the row above the one removed
//					System.out.println("rowsToMovi is" + rowsToMove);
//				
//					for(int c = 0; c < 15; c++){ // Cycle through square objects of each row. (we needed to make this to go up instead of starting at 0)
//					
//						if(squares[rowsToMove][c] != null){    				
//							squares[rowsToMove][c].moveToTetrisLocation(squares[rowsToMove][c].getX(), squares[rowsToMove][c].getY()+1);   	
//							squares[rowsToMove + 1][c] = squares[rowsToMove][c];
//							squares[rowsToMove][c] = null;   	
//							
//							}
//						}	
//					}
//				}	
//			}
//		}
//	}
//

//CanIMove second idea



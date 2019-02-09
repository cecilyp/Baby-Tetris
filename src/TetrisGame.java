/**
@author Cecily Page
 * Sierra McConnell
 * CS 110
 * Tetris Project
 * this class creates essential methods like drop and turn
 */


import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * This initializes the variables and creates arrays
 *
 */
public class TetrisGame {
    private final Tetris tetrisApp;
    GamePiece randomObj = new GamePiece();
    GamePiece newObj = new GamePiece();
    TetrisSquare[] original = new TetrisSquare[4];
    TetrisSquare[] newPiece = new TetrisSquare[4];
    boolean atBottom = false;
    boolean atRightSide = false;
    boolean atLeftSide = false;
    boolean moveRight = true;
	boolean moveLeft = true;
    Pane pane= new Pane();
    int[] xmaxima = {0,0};
    int ymaxima = 0;
//    ArrayList <TetrisSquare> squares = new ArrayList<>();
//    ArrayList<Integer> xLocations = new ArrayList<>();
//	ArrayList<Integer> yLocations = new ArrayList<>();
	int[] coordinates = new int[6];
	int randomIndex = 0;

    
    int coord = 4;		//No objects require a coordinate change of 4 so if these
    int c1 = 4;			//are 4 we'll know something went wrong in the code.
    int c2 = 4;
    int c3 = 4;
    int c4 = 4;
    int c5 = 4;
    int originalX = TetrisBoard.X_DIM_SQUARES/2;
    int originalY = 3;
    /**
     * Initialize the game. 
     */
    public TetrisGame(Tetris tetrisApp, TetrisBoard board) {
        this.tetrisApp = tetrisApp;
        
        // You can use this to show the score, etc.
        tetrisApp.setMessage("Game has started!");
        
        // creates the array of pieces
       LLeftPiece lleft = new LLeftPiece(board);
       LRightPiece lright = new LRightPiece(board);
       SquarePiece square= new SquarePiece(board);
       LZPiece leftZ = new LZPiece(board);
       RZPiece rightZ = new RZPiece(board);
       Line line = new Line(board);
       TShape tPiece = new TShape(board);
       ExplodingBlock explodingBlock = new ExplodingBlock(board);
       HeavyBlock heavyBlock = new HeavyBlock(board);
       
       GamePiece[] pieces = {lleft, lright, square, leftZ, rightZ, line, tPiece, explodingBlock, heavyBlock};
       
       	   //starts the game off with a random piece
       	   randomIndex = (int)(Math.random()*7);
       		System.out.println("randomIndex = " + randomIndex);
    	   this.randomObj = pieces[randomIndex];//
    	   //creates a new Tetris piece at specified coordinates 
           original = createTetrisPiece(randomObj, board, originalX, originalY);
           //Call setCount here to reinitialize count for each piece
           setCount(10000);

        }
    	int[] getPieceCoord(){
    		int[] arr = randomObj.makePiece();
    		for(int i = 0; i < arr.length; i++){
    			switch (i){
    			case 0: coord = arr[i];
    			break;
    			case 1: c1 = arr[i];
    			break;
    			case 2: c2 = arr[i];
    			break;
    			case 3: c3 = arr[i];
    			break;
    			case 4: c4 = arr[i];
    			break;
    			case 5: c5 = arr[i];
    			default: break;
    			}
    		}
    		int[] a = {coord, c1, c2,c3,c4,c5};
    		return a;
    }
    /*
     * these create the other three pieces in relation
     * to the center square and places them on the board
     * 
     */
    TetrisSquare[] createTetrisPiece(GamePiece randomObj, TetrisBoard board, int originalX, int originalY){
    	coordinates = getPieceCoord();
    	TetrisSquare centerSquare = new TetrisSquare(board);
        centerSquare.moveToTetrisLocation(originalX, originalY);
        centerSquare.setColor(randomObj.getColor());
        
        TetrisSquare s1 = new TetrisSquare(board);
        s1.moveToTetrisLocation(centerSquare.getX() + coordinates[0], centerSquare.getY() + coordinates[1]);
        s1.setColor(randomObj.getColor());
        
        TetrisSquare s2 = new TetrisSquare(board);
        s2.moveToTetrisLocation(centerSquare.getX() + coordinates[2], centerSquare.getY() + coordinates[3]);
        s2.setColor(randomObj.getColor());
        
        TetrisSquare s3 = new TetrisSquare(board);
        s3.moveToTetrisLocation(centerSquare.getX() + coordinates[4], centerSquare.getY() + coordinates[5]);
        s3.setColor(randomObj.getColor());
    	TetrisSquare[] arr = {centerSquare, s1, s2, s3};
        return arr;
    }
    
    void moveTetrisPiece(GamePiece randomObj, TetrisBoard board, int originalX, int originalY){
    	//call pieceBelow here 
        original[0].moveToTetrisLocation(originalX, originalY);
        original[0].setColor(randomObj.getColor());
       
        original[1].moveToTetrisLocation(original[0].getX() + coordinates[0], original[0].getY() + coordinates[1]);
        original[1].setColor(randomObj.getColor());
        
        original[2].moveToTetrisLocation(original[0].getX() + coordinates[2], original[0].getY() + coordinates[3]);
        original[2].setColor(randomObj.getColor());
        
        original[3].moveToTetrisLocation(original[0].getX() + coordinates[4], original[0].getY() + coordinates[5]);
        original[3].setColor(randomObj.getColor());
    	
    }
    
    /**
     * Animate the game, by moving the current tetris piece down with a timer (hasn't happened yet) 
     */
    
    
    
    /**
     * this takes the coordinates of the piece that was dropped to the bottom and 
     * makes them stationary where they landed
     */
    
    void update(GamePiece[] pieces, TetrisBoard board, int divisor) {
    	ymaxima = randomObj.yMax(divisor);
    	
        if(atBottom == true || ymaxima == originalY){
        	board.addToBoard(original);
        	displayScore();
        	board.rowCheck();
        	randomIndex = (int)(Math.random()*7);
        	this.randomObj = pieces[randomIndex];
        	this.originalX = TetrisBoard.X_DIM_SQUARES/2;
        	this.originalY = 3;
        	this.original = createTetrisPiece(randomObj, board, originalX, originalY);
        	setCount(10000);
        	atBottom = false;
        	System.out.println("randomIndex = " + randomIndex);
        }else{
        	int[] x = randomObj.xMaxAndMin(divisor);
            int xmax = x[1];
        	if(originalY < ymaxima && board.canIMove(original, 0, x, ymaxima) == true){
        		if(randomObj instanceof HeavyBlock){
        			originalY = originalY + 2;
        			moveTetrisPiece(randomObj, board, originalX, originalY);
        		}else{
        			originalY++;
            		moveTetrisPiece(randomObj, board, originalX, originalY);
        		}
        	}else{
        		atBottom = true;
        		}
        	}
        }
    

    
    
    /**
     * Moves the current tetris piece left and sets the boundaries for each piece.
     */
    void left(GamePiece obj, TetrisBoard board, int divisor) {
    		// take this code out and replace with your code
        //tetrisApp.setMessage("left key was pressed!");
        int[] xmaxima = obj.xMaxAndMin(divisor);
        int xMin = xmaxima[0];
        if(originalX != xMin && board.canIMove(original, -1, xmaxima, ymaxima) == true){
        	originalX--;
        }else if(originalX == xMin){
        	atLeftSide = true;
        }else {
            	atLeftSide = false;
            
        }
    }

    /**
     * moves right and sets the boundaries for the current Tetris piece on the right.
     * @param
     * GamePiece obj - the random object thats generated
     * 
     */
    void right(GamePiece obj, TetrisBoard board, int divisor) {
		// take this code out and replace with your code
       // tetrisApp.setMessage("right key was pressed!");
        int[] maxima = obj.xMaxAndMin(divisor);
        int rightMax = maxima[1];
        if(originalX != rightMax && board.canIMove(original, 1, maxima, ymaxima) == true){// && atRightSide == false){
        	originalX++;
        }else if(originalX == rightMax){
        	atRightSide = true;
        }else{
        	atRightSide = false;
        }
        
    }
   
    /**
     * Drops the current Tetris piece.
     * accounts for all the different orientations of every piece. 
     * sets boolean at bottom true or false.
     * Divisor is the count variable thats updated in the switch case in Tetris
     * When up is increased the piece turns right, and when its decreased it turns left
     * These positions are found using the mod function
     */
    void drop(GamePiece obj, TetrisBoard board, int divisor) {
    	if(originalY != ymaxima){
    	originalY++;
    	moveTetrisPiece(obj, board, originalX, originalY);
    	}
       // tetrisApp.setMessage("drop key was pressed!");
//        ymaxima = obj.yMax(divisor);
//      //  System.out.println("ymaxima: " + ymaxima);
//        int[] x = obj.xMaxAndMin(divisor);
//        int xmax = x[1];
////        for(int i = TetrisBoard.X_DIM_SQUARES; i > 0; i--){
////        	for(int j = 0; j < TetrisBoard.Y_DIM_SQUARES; j++){
////        		
////        	}
////        }
//        if(board.canIMove(original, 2)){
//        	moveTetrisPiece(obj,board,originalX, ymaxima);
//        	board.addToBoard(original);
//        	displayScore();
//        	System.out.println("ymaxima = " + ymaxima);
//        }
    }

    /**
     * Rotate the current piece counter-clockwise.
     */
    void rotateLeft(GamePiece obj, TetrisBoard board, int divisor) {
       // tetrisApp.setMessage("rotate left key was pressed!");
        int[] x = obj.xMaxAndMin(divisor);
        int xmin = x[0];
        int xmax = x[1];
   //     if(atLeftSide == false || atRightSide == false){
        if(originalX != xmin || board.canIMove(original, 3, x, ymaxima) == true || originalX != xmax){
        	coordinates = obj.turn(divisor);
        	moveTetrisPiece(randomObj, board, originalX, originalY);
        }
     //   }
        
    }
        
    /**
     * Rotate the current piece clockwise.
     */
    void rotateRight(GamePiece obj, TetrisBoard board, int divisor) {
        //tetrisApp.setMessage("rotate right key was pressed!");
        int[] x = obj.xMaxAndMin(divisor);
        int xmin = x[0];
        int xmax = x[1];
        //if(atRightSide == false || atLeftSide == false){ || 
        if(board.canIMove(original, 3, x, ymaxima) == false){
            coordinates = obj.turn(divisor);
    		moveTetrisPiece(randomObj, board, originalX, originalY);
    		System.out.println("OriginalX = " + originalX);
    		System.out.println("xmax = " +xmax);
    		System.out.println("xmin = " + xmin);
            }
          // }
    }  
    
   /**
    * returns the piece that was randomly generated
    * @return GamePiece randomObj 
    */
    GamePiece getRandom(){
    	return randomObj;
    }
    
   /**
    *  returns boolean at bottom that says weather or not the piece is at the bottom..
    * @return boolean atBottom, true when piece is at bottom of screen false otherwise
    */
    boolean whereAreWe(){
    	return atBottom;
    }
    
    void getSpecial(){
    	if(randomObj instanceof HeavyBlock || randomObj instanceof ExplodingBlock){
			//tetrisApp.setMessage("EXPLODE!");
		}else{
			//tetrisApp.setMessage("Heavy block dropped.");
		}
    }
    
    //This method updates the array maxima each time the piece is rotated
    
    void setMaxima(GamePiece obj, int newDivisor){
    	int[] temp = obj.xMaxAndMin(newDivisor);
    	xmaxima[0] = temp[0];
    	xmaxima[1] = temp[1];    
    	}

    void setCount(int newCount){
    	Tetris.setCount(newCount);
    }
    
    void displayScore(){
    	int score = TetrisBoard.getScore();
    	tetrisApp.setMessage("Score = " + score);
    }
    
}


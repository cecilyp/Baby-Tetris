import java.util.ArrayList;

import javafx.scene.paint.Color;

public class LLeftPiece extends GamePiece{
	String name = "LLeft";

	public LLeftPiece(TetrisBoard board) {
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public Color getColor(){
		return Color.ORANGE;
	}
	
	@Override
	public int[] makePiece(){
		int[] arr = {0, 1, 0, -1, -1, -1}; //down, above, diagonally above
		return arr;
	}
	
	public int[] turn(int divisor){
		if(divisor % 4 == 0){
			int[] arr = {0, 1, 0, -1, -1, -1};
			return arr;
		}else if(divisor % 4 == 1){
			int[] arr = {-1, 0, 1, 0, 1, -1};
			return arr;
		}else if(divisor % 4 == 2){
			int[] arr = {0, -1, 0, 1, 1, 1};
			return arr;
		}else{
			int[] arr = {-1, 0, -1, 1, 1, 0};
			return arr;
		}
	}
	/*
	 * thismethod creates an array with the rotation and the x max and min for each rotation 
	 * of the piece
	 */
	public int[] xMaxAndMin(int divisor){
		if(divisor % 4 == 0){
			int[] arr = {1,14};
			return arr;
		}else if(divisor % 4 == 1){
			int[] arr = {1,13};
			return arr;
		}else if(divisor % 4 == 2){
			int[] arr = {0,13};
			return arr;
		}else{
			int[] arr = {1,13};
			return arr;
		} 
	}
	
	public int yMax(int divisor){
		if(divisor % 4 == 1){
			return 29;
		}else{
			return 28;
		}
	}
}

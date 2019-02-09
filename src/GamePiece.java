import javafx.scene.paint.Color;
public class GamePiece{
	
	public void removeGamePiece(TetrisSquare obj){
		obj.removeFromDrawing();
	}
	public String getName(){
		return "GamePiece";
	}
	public Color getColor(){
		return Color.BLACK;
	}

	public int[] makePiece(){
		int[] arr = new int[6];
		return arr;
	}
	
	public int[] turn(int divisor){
		int[] arr = new int[6];
		System.out.println("divisor (count) = " + divisor);
		System.out.println("divisor mod = " + divisor % 4);
	
		return arr;
		}
	/*
	 * this method creates an array with the rotation and the x max and min for each rotation 
	 * of the piece
	 */
	public int[] xMaxAndMin(int divisor){
		int[] arr = new int[2];
		return arr;
	}
	
	public int yMax(int divisor){
		int ymax = 0;
		return ymax;
	}
	
}

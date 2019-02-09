import javafx.scene.paint.Color;

public class SquarePiece extends GamePiece {

	public SquarePiece(TetrisBoard board) {
			}
	public String getName(){
		return "Square";
	}
	@Override
	public Color getColor(){
		return Color.YELLOW;
	}
	
	@Override
	public int[] makePiece(){
		int[] arr = {-1,0,0,-1,-1,-1}; //first piece is to left, then directly above, then diagonally away
		return arr;
	}
	

	public int[] turn(int divisor){
		
		if(divisor % 4 == 0){
			int[] arr = {-1,0,0,-1,-1,-1};
			return arr;
		}else if(divisor % 4 == 1){
			int[] arr = {-1,0,0,-1,-1,-1};
			return arr;
		}else if(divisor % 4 == 2){
			int[] arr = {-1,0,0,-1,-1,-1};
			return arr;
		}else{
			int[] arr = {-1,0,0,-1,-1,-1};
			return arr;
		}
	}


	public int[] xMaxAndMin(int divisor){
		
			int[] arr = {1,14};
			return arr;
	}	
	
	public int yMax(int divisor){
		
			return 29;
		
	}
}
	
	



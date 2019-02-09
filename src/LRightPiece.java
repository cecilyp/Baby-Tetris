import javafx.scene.paint.Color;

public class LRightPiece extends GamePiece{
	String name = "LRight";

	public LRightPiece(TetrisBoard board) {
		
	}
	public String getName(){
		return name;
	}
	@Override
	public Color getColor(){
		return Color.BLUE;
	}
	
	@Override
	public int[] makePiece(){
		int[] arr = {0, 1, 0, -1, 1, -1}; //first piece is down, then above, diagonally above
		return arr;
	}
	
	public int[] turn(int divisor){
		System.out.println("divisor (count) = " + divisor);
		System.out.println("divisor mod = " + divisor % 4);
		if(divisor % 4 == 0){
			int[] arr = {0,1,0,-1,1,-1};
			return arr;
		}else if(divisor % 4 == 1 || divisor % 4 == -3){
			int[] arr = {-1,0,1,0,1,1};
			return arr;
		}else if(divisor % 4 == 2 || divisor % 4 == -2){
			int[] arr = {0,-1,0,1,-1,1};
			return arr;
		}else if(divisor % 4 == -1 || divisor % 4 == 3){
			int[] arr = {1,0,-1,0,-1,-1};
			return arr;
		}else{
			int[] arr = {3,2,1,3,2,1};
			return arr;
		}
		
	}
	
	public int[] xMaxAndMin(int divisor){
		if(divisor % 4 == 0){
			int[] arr = {0,13};
			return arr;
		}else if(divisor % 4 == 1){
			int[] arr = {1,13};
			return arr;
		}else if(divisor % 4 == 2){
			int[] arr = {1,14};
			return arr;
		}else{
			int[] arr = {1,13};
			return arr;
		} 
	}
	
	public int yMax(int divisor){
		if(divisor % 4 == 3){
			return 29;
		}else{
			return 28;
		}
	}
}




import javafx.scene.paint.Color;

public class TShape extends GamePiece{
	public TShape(TetrisBoard board){
	}
	
	public String getName(){
		return "TShape";
	}
	
	@Override
	public Color getColor(){
		return Color.PURPLE;
	}
	
	@Override
	public int[] makePiece(){
		int[] arr = {-1, 0, 0, 1, 1, 0}; //left, down, right
		return arr;
	}
	
public int[] turn(int divisor){
		
		if(divisor % 4 == 0){
			int[] arr = {1,0,-1,0,0,1};
			return arr;
		}else if(divisor % 4 == 1){
			int[] arr = {0,-1,0,1,-1,0};
			return arr;
		}else if(divisor % 4 == 2){
			int[] arr = {1,0,-1,0,0,-1};
			return arr;
		}else{
			int[] arr = {0,-1,0,1,1,0};
			return arr;
		}
	}
	public int[] xMaxAndMin(int divisor){
		if(divisor % 4 == 0){
			int[] arr = {1,13};
			return arr;
		}else if(divisor % 4 == 1){
			int[] arr = {1,14};
			return arr;
		}else if(divisor % 4 == 2){
			int[] arr = {1,13};
			return arr;
		}else{
			int[] arr = {0,13};
			return arr;
		} 
	}
	
	public int yMax(int divisor){
		if(divisor % 4 == 2){
			return 29;
		}else{
			return 28;
		}
	}
}

import javafx.scene.paint.Color;

public class ExplodingBlock extends GamePiece{
	
	public ExplodingBlock (TetrisBoard board){
		
	}
	public String getName(){
		String[] arrayNames = {"Z","Square","LLeft","LRight","TShape","Line"};
		int index = (int)(Math.random()*6);
		return arrayNames[index];
	}
	
	@Override
	public Color getColor(){
		return Color.CORNFLOWERBLUE;
	}	
	
	@Override
	public int[] makePiece(){
		int[] arr = {1, 0, 0, -1, -1, -1}; //right, above, diagonally above (left)
		return arr;
	}
	
	public int[] turn(int divisor){
		
		if(divisor % 4 == 0){
			int[] arr = {1, 0, 0, -1, -1, -1};
			return arr;
		}else if(divisor % 4 == 1){
			int[] arr = {1, 0,1,-1,0,1};
			return arr;
		}else if(divisor % 4 == 2){
			int[] arr = {1, 0, 0, -1, -1, -1};
			return arr;
		}else{
			int[] arr = {1,0,1,-1,0,1};
			return arr;
		}
}
}
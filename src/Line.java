import javafx.scene.paint.Color;

public class Line extends GamePiece {
	String name = "Line";

	public Line(TetrisBoard board) {

	}

	public String getName() {
		return name;
	}

	@Override
	public Color getColor() {
		return Color.CYAN;
	}

	@Override
	public int[] makePiece() {
		int[] arr = { 0, 1, 0, -1, 0, -2 }; // below, above, two above
		return arr;
	}

	public int[] turn(int divisor) {

		if (divisor % 4 == 0) {
			int[] arr = { 0, 1, 0, -1, 0, -2 };
			return arr;
		} else if (divisor % 4 == 1) {
			int[] arr = { 1, 0, -1, 0, -2, 0 };
			return arr;
		} else if (divisor % 4 == 2) {
			int[] arr = { 0, 1, 0, -1, 0, -2 };
			return arr;
		} else {
			int[] arr = { 1, 0, -1, 0, -2, 0 };
			return arr;
		}
	}

	public int[] xMaxAndMin(int divisor) {
		if (divisor % 4 == 0 || divisor % 4 == 2) {
			int[] arr = { 0, 14 };
			return arr;
		} else {
			int[] arr = { 2, 13 };
			return arr;

		}
	}
	
	public int yMax(int divisor){
		if(divisor % 4 == 0 || divisor % 4 == 2){
			return 28;
		}else{
			return 29;
		}
	}

}

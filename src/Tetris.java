/**
 * @author Cecily Page
 * Sierra McConnell
 * CS 110
 * Tetris Project
 * This class runs the actual game. 
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.HBox;


/**
 * The Tetris Application, which contains the board and a message label.
 * 
 */
public class Tetris extends Application {
	static int count = 0;
    private static final double MILLISEC = 200;
    private TetrisGame game;
    private TetrisBoard tetrisBoard;
    private Timeline animation;
    private Label statusLabel;
    private LLeftPiece lleft = new LLeftPiece(tetrisBoard);
    private LRightPiece lright = new LRightPiece(tetrisBoard);
    private SquarePiece square= new SquarePiece(tetrisBoard);
    private LZPiece leftZ = new LZPiece(tetrisBoard);
    private RZPiece rightZ = new RZPiece(tetrisBoard);
    private Line line = new Line(tetrisBoard);
    private TShape tPiece = new TShape(tetrisBoard);
    private ExplodingBlock explodingBlock = new ExplodingBlock(tetrisBoard);
    private HeavyBlock heavyBlock = new HeavyBlock(tetrisBoard);
    
    private GamePiece[] pieces = {lleft, lright, square, leftZ, rightZ, line, tPiece, explodingBlock, heavyBlock};
   

    /**
     * Launches the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Sets up the tetris board and game, as well as a status label
     * that can be used to display scores and messages.
     * 
     * Enables key events for the arrow keys and space bar, as well
     * as an animation.
     * 
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        tetrisBoard = new TetrisBoard(tetrisBoard);
        
        statusLabel = new Label("Tetris");
        statusLabel.setTextFill(Color.RED);
        
        HBox nextPiece = new HBox(8);
   //     nextPiece.getChildren().add(game.getRandom());
        
        BorderPane pane = new BorderPane();
        pane.setCenter(tetrisBoard);
        pane.setTop(statusLabel);
        pane.getChildren().add(nextPiece);
        
        
        

        Scene scene = new Scene(pane);

        game = new TetrisGame(this, tetrisBoard);

        setUpAnimation();

        setUpKeyPresses();

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * Changes the message in the status label at the top of the screen.
     * @param message 
     */
    public void setMessage(String message) {
        statusLabel.setText(message);
    }

    /**
     * Sets up an animation timeline that calls update on the game every
     * MILLISEC milliseconds.
     */
    private void setUpAnimation() {
        // Create a handler
        EventHandler<ActionEvent> eventHandler = (ActionEvent e) -> {
            this.pause();
            game.update(pieces, tetrisBoard, count);
           /* if(game.whereAreWe() == false){
            	game.drop(game.getRandom(), tetrisBoard, count);
            }*/
            this.resume();
        };
        // Create an animation for alternating text
        animation = new Timeline(new KeyFrame(Duration.millis(MILLISEC), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    /**
     * Sets up key events for the arrow keys and space bar. All keys send 
     * messages to the game, which should react appropriately.
     */
    private void setUpKeyPresses() {
    	
        tetrisBoard.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                	if(game.whereAreWe() == false){
                	game.left(game.getRandom(), tetrisBoard, count);
                	}
                    break;
                case RIGHT:
                	if(game.whereAreWe() == false){
                    game.right(game.getRandom(), tetrisBoard, count);
                	}
                    break;
                case DOWN:
                	if(game.whereAreWe() == false){
                	count = count - 1;
                	System.out.println("divisor (count) = " + count);
            		System.out.println("divisor mod = " + count % 4);
                    game.rotateLeft(game.getRandom(), tetrisBoard, count);
                    game.setMaxima(game.getRandom(), count);
                	}else{
                		this.count = 10000;
                	}
                    break;
                case UP:
                	if(game.whereAreWe() == false){
                	count = count + 1;
                	System.out.println("divisor (count) = " + count);
            		System.out.println("divisor mod = " + count % 4);
                    game.rotateRight(game.getRandom(), tetrisBoard, count);
                    game.setMaxima(game.getRandom(), count);
                	}else{
                		this.count = 10000;
                	}
                    break;
                case SPACE:
                    game.drop(game.getRandom(), tetrisBoard, count);
                    break;

            }
            // the following line is needed only if you add other elements
            // to the scene: "consuming" the event prevents it from having other
            // side effects, like moving the focus to another part of the scene
            e.consume();
        });
        tetrisBoard.requestFocus(); // board is focused to receive key input
        

    }

    /**
     * Pauses the animation.
     */
    private void pause() {
        animation.pause();
    }

    /** 
     * Resumes the animation.
     */
    private void resume() {
        animation.play();
    }
    
    private void removePiece(){
    	
    }
    
    public static void setCount(int newCount){
    	count = newCount;
    }
}

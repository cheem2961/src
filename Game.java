import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {
    private static final double WINDOW_WIDTH = 800f;
    private static final double WINDOW_HEIGHT = 800f;
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 800;
    private Canvas canvas;
    private int rez = 16;


    public Game(){}

    public void start(Stage primaryStage) {


        GameController gameController = new GameController();
        gameController.setup();
        gameController.getCurrentActor();



        Scene scene = new Scene(this.buildScene(), WINDOW_WIDTH, WINDOW_HEIGHT);


        this.drawGame(gameController.getCurrentActor());
        this.gameLoop(scene, gameController.getCurrentActor());

        primaryStage.setScene(scene);
        primaryStage.show();



    }

    private void gameLoop(Scene scene, Actor actor) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case A:
                    actor.getCurrentPlayer().swapHands(Direction.LEFT);
                    System.out.println(actor.getCurrentPlayer());
                    break;
                case D:
                    actor.getCurrentPlayer().swapHands(Direction.RIGHT);
                    System.out.println(actor.getCurrentPlayer());
                    break;
                default:
                    break;
            }

            // Optional: redraw after input
            this.drawGame(actor);
        });
    }


    public void drawGame(Actor actor) {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.clearRect((double)0.0F, (double)0.0F, this.canvas.getWidth(), this.canvas.getHeight());
        gc.setFill(Color.GRAY);
        gc.fillRect((double)0.0F, (double)0.0F, this.canvas.getWidth(), this.canvas.getHeight());

        //current player's finger values
        gc.setFill(Color.WHITE); // Text color
        gc.setFont(javafx.scene.text.Font.font("Arial", 48));
        gc.fillText(actor.getCurrentPlayer().toString(), 100, 700);

        // display current player's (actors) name
        gc.setFill(Color.WHITE); // Text color
        gc.setFont(javafx.scene.text.Font.font("Arial", 30));
        gc.fillText("player: " + actor.getCurrentPlayer().getDisplayName(), 350, 740);

        // display other player's finger values


        // display other player's name

    }


    private Pane buildScene() {
        BorderPane root = new BorderPane();
        this.canvas = new Canvas((double)800.0F, (double)800.0F);
        root.setCenter(this.canvas);
        root.requestFocus();
        return root;
    }


    public static void main(String[] args) {
        launch(args);
    }
}

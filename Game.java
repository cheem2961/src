import javafx.animation.AnimationTimer;
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


    public Game(){}

    public void start(Stage primaryStage) {


        GameController gameController = new GameController();
        gameController.setup();

        Scene scene = new Scene(this.buildScene(), WINDOW_WIDTH, WINDOW_HEIGHT);

        this.gameLoop(scene, gameController);

        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void gameLoop(Scene scene, GameController gameController) {

        // Game loop using AnimationTimer
        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //INSIDE THIS IS THE MAIN LOOP ------------------------

                //all game events are updated in here
                //e.g. drawing, listening for keys...
                //in future, if we want a menu, there will be a menuController.update()
                gameController.update(scene,canvas);



                //END HERE --------------------------------------------
            }
        };
        loop.start();
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

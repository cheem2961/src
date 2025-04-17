import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

//will coordinate all game events, such as start procedure, turns etc..
public class GameController {
    private ArrayList<Player> players = new ArrayList<>();
    private int playerCount = 2;
    private int modFingers = 5;

    private int turnIndex = 0;
    private Player currentPlayer;

    public GameController() {
        //empty constructor for now
    }

    //making the players
    public void setup() {
        for (int i = 1; i <= this.playerCount; i++) {
            this.players.add(new Player(String.valueOf(i),this.modFingers));
        }
        currentPlayer = this.players.get(turnIndex);
    }

    public void update(Scene scene, Canvas canvas) {
        //Listening for keys and performs their dedicated function
        this.keyListen(scene);

        //draws
        this.draw(scene, canvas);
    }

    public void draw(Scene scene, Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //refreshes screen
        gc.clearRect((double)0.0F, (double)0.0F, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.GRAY);
        gc.fillRect((double)0.0F, (double)0.0F, canvas.getWidth(), canvas.getHeight());

        //current player's finger values
        gc.setFill(Color.WHITE); //Text color
        gc.setFont(javafx.scene.text.Font.font("Arial", 48));
        gc.fillText(currentPlayer.toString(), 100, 700);

        // display current player's (actors) name
        gc.setFill(Color.WHITE); //Text color
        gc.setFont(javafx.scene.text.Font.font("Arial", 30));
        gc.fillText("player: " + currentPlayer.getDisplayName(), 350, 740);

        // display other player's finger values
        //...

        // display other player's name
        //...

    }

    public void endTurn(){
        //check for deaths
        //update prev fingers

        //switch players
        this.turnIndex = (this.turnIndex + 1) % this.playerCount;
    }

    public void keyListen(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case A:
                    this.currentPlayer.swapHands(Direction.LEFT);
                    System.out.println("Swapped hands LEFT");
                    break;
                case D:
                    this.currentPlayer.swapHands(Direction.RIGHT);
                    System.out.println("Swapped hands RIGHT");
                    break;
                case Q:
                    //tap wth current player's left hand
                    //check no swaps have been made
                    //turn on tapping mode
                    //tapping mode causes active hand to be highlights and watch for user input to choose what hand to tap
                    //find number of fingers being tapped with
                case E:
                    //tap wth current player's right hand
                    //same as above
                case SPACE:
                    //confirm
                    //check final swap is valid
                    //run end of turn procedure:

                default:
                    break;
            }

            // Optional: redraw after input
            //this.drawGame(actor);
        });
    }
}

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

//will coordinate all game events, such as start procedure, turns etc..
public class GameController {
    private ArrayList<Player> players = new ArrayList<>();
    private int playerCount = 2;
    private int modFingers = 5;

    private Player currentPlayer;
    private Image[] playerHandImages;

    public GameController() {
        //empty constructor for now
    }

    //making the players and setting the current actor (likely player 1)
    public void setup() {
        for (int i = 1; i <= this.playerCount; i++) {
            this.players.add(new Player(String.valueOf(i),this.modFingers));
        }
        currentPlayer = this.players.get(0);


        playerHandImages = new Image[] {
                new Image("L0.png"),
                new Image("L1.png"),
                new Image("L2.png"),
                new Image("L3.png"),
                new Image("L4.png"),
                new Image("L5.png"),
                new Image("R0.png"),
                new Image("R1.png"),
                new Image("R2.png"),
                new Image("R3.png"),
                new Image("R4.png"),
                new Image("R5.png")
        };
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




        //DRAW IMAGES
        gc.drawImage(playerHandImages[currentPlayer.getLeftHandAmount()], -260, 350, 650 * 1.4,650);
        gc.drawImage(playerHandImages[currentPlayer.getRightHandAmount() + 6], 150, 350, 650 * 1.4,650);


        // display current player's (actors) name
        gc.setFill(Color.WHITE); //Text color
        gc.setFont(javafx.scene.text.Font.font("Arial", 30));
        gc.fillText("player: " + currentPlayer.getDisplayName(), 350, 740);

        // display other player's finger values
        //...

        // display other player's name
        //...

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
                    //check for deaths
                    //update prev fingers
                    //switch players
                default:
                    break;
            }

            // Optional: redraw after input
            //this.drawGame(actor);
        });
    }
}

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

    private Actor actor;

    public GameController() {
        //empty constructor for now
    }

    //making the players and setting the current actor (likely player 1)
    public void setup() {
        for (int i = 1; i <= this.playerCount; i++) {
            this.players.add(new Player(String.valueOf(i),this.modFingers));
        }
        actor = new Actor(players.get(0));
    }

    public void update(Scene scene, Canvas canvas) {
        //Listening for keys and performs their dedicated function
        actor.keyListen(scene);

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
        gc.fillText(actor.getCurrentPlayer().toString(), 100, 700);

        // display current player's (actors) name
        gc.setFill(Color.WHITE); //Text color
        gc.setFont(javafx.scene.text.Font.font("Arial", 30));
        gc.fillText("player: " + actor.getCurrentPlayerName(), 350, 740);

        // display other player's finger values
        //...

        // display other player's name
        //...

    }
}

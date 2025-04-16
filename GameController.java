import java.util.ArrayList;

//will coordinate all game events, such as start procedure, turns etc..
public class GameController {
    private ArrayList<Player> players = new ArrayList<>();
    private int playerCount = 2;
    private int modFingers = 5;

    private Actor currentActor;

    public GameController() {

    }

    //making the players
    public void setup() {
        for (int i = 1; i <= this.playerCount; i++) {
            this.players.add(new Player(String.valueOf(i),this.modFingers));
        }
        currentActor = new Actor(players.get(0));
    }



    public Actor getCurrentActor() {
        return currentActor;
    }

}

import java.util.ArrayList;

//will coordinate all game events, such as start procedure, turns etc..
public class GameController {
    private ArrayList<Player> players = new ArrayList<>();
    private int playerCount = 2;

    private Actor currentActor;

    public GameController() {

    }

    public void setup() {
        for (int i = 1; i <= this.playerCount; i++) {
            this.players.add(new Player(String.valueOf(i)));
        }
        currentActor = new Actor(players.get(0));
    }



    public Player getCurrentActor() {
        return currentActor.getCurrentActor();
    }

}

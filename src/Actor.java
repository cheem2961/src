
//idea: actor being a reserved space, meaning only one play can act at a time
public class Actor {
    private Player currentPlayer;

    public Actor(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


    public Player getCurrentActor() {
        return currentPlayer;
    }
    public void setCurrentActor(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }



}

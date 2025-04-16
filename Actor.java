import java.util.ArrayList;

//idea: actor being a reserved space, meaning only one play can act at a time
//actor is the active parts of the player, only accessible when its their turn
public class Actor {
    private Player currentPlayer;

    public Actor(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void swapHands(Direction aDirection){
        Hand handFrom;
        Hand handTo;
        ArrayList<Hand> handArrayList = currentPlayer.getHandArrayList();
        switch(aDirection){
            case LEFT: //move 1 from right hand to left hand
                handFrom = handArrayList.get(1);
                handTo = handArrayList.get(0);

                if(checkValidSwap(handFrom, handTo)){
                    handFrom.removeOne();
                    handTo.addOne();
                }
                break;
            case RIGHT: //move 1 from left hand to right hand
                handFrom = handArrayList.get(0);
                handTo = handArrayList.get(1);

                if(checkValidSwap(handFrom, handTo)){
                    handFrom.removeOne();
                    handTo.addOne();
                }
                break;
            default:
                break;
        }
    }

    // check a finger swap does not result in an empty or more than full hand
    public boolean checkValidSwap(Hand handFrom, Hand handTo){
        return !(handFrom.getCurFingers() <= 0 || handTo.getCurFingers() >= currentPlayer.getModFingers() - 1 );
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}

import javafx.scene.Scene;

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

    public void keyListen(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case A:
                    this.swapHands(Direction.LEFT);
                    System.out.println("Swapped hands LEFT");
                    break;
                case D:
                    this.swapHands(Direction.RIGHT);
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
    public String getCurrentPlayerName() {
        return this.currentPlayer.getDisplayName();
    }
}

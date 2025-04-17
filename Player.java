import java.util.ArrayList;

public class Player {
    private static int currentPlayer = 1;
    private int playerNumber;
    private String displayName;
    private int handAmount = 2;
    private int modFingers;
    private boolean isAlive;

    
    private ArrayList<Hand> handArrayList = new ArrayList<>();


    public Player(String aDisplayName, int aModFingers) {
        this.playerNumber = currentPlayer;
        currentPlayer++;
        this.displayName = aDisplayName;
        this.modFingers = aModFingers;
        this.isAlive = true;

        // create hands and add to handArrayList
        for(int i = 0; i <= handAmount; i++){
            handArrayList.add(new Hand(this.modFingers,2));
        }
    }


    public void swapHands(Direction aDirection){
        Hand handFrom;
        Hand handTo;
        ArrayList<Hand> handArrayList = getHandArrayList();
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
        return !(handFrom.getCurFingers() <= 0 || handTo.getCurFingers() >= modFingers - 1 );
    }


    // returns true if current hands are equal to prev
    public boolean checkHandsChanged() {
        for(Hand hand : handArrayList){
            if (hand.getCurFingers() != hand.getPrevFingers()){
                return false;
            }
        }
        return true;
    }

    // add up current fingers of all hands, if it = 0, player is dead
    public boolean isAlive() {
        int totalFingers = 0;
        for(Hand hand : handArrayList){
            totalFingers += hand.getCurFingers();
        }
        if (totalFingers == 0){
            isAlive = false;
        }
        return isAlive;
    }

    public int getLeftHandAmount() {
        return handArrayList.get(0).getCurFingers();
    }
    public int getRightHandAmount() {
        return handArrayList.get(1).getCurFingers();
    }


    //getters
    public String getDisplayName() {
        return displayName;
    }
    public ArrayList<Hand> getHandArrayList() {
        return handArrayList;
    }
    public int getModFingers() {
        return modFingers;
    }

    @Override
    public String toString() { //returns current finger values in string
        String output = "";
        output += "Left Hand: " + handArrayList.get(0).getCurFingers() + " Right Hand: " + handArrayList.get(1).getCurFingers() + "\n";
        return output;
    }





}

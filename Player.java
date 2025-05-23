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
        for(int i = 0; i < handAmount; i++){
            handArrayList.add(new Hand(this.modFingers,1));
        }
    }


    public void turnEnd(){
        for(Hand hand : handArrayList){
            hand.turnEnd();
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
                System.out.println("Invalid direction");
                break;
        }
    }

    // check a finger swap does not result in an empty or more than full hand
    public boolean checkValidSwap(Hand handFrom, Hand handTo){
        return !(handFrom.getCurFingers() <= 0 || handTo.getCurFingers() >= modFingers - 1 );
    }


    // returns true if current hands are equal to prev
    public boolean checkHandsChanged() {
        // check hands have changed at all
        for(Hand hand : handArrayList){
            if (hand.getCurFingers() != hand.getPrevFingers() ){
                return true;
            }
        }
        return false;
    }

    public boolean checkSymetricSwap(){
        // check hands havent just ben swapped into symetrical position. only works for 2 hands
        return (handArrayList.get(0).getCurFingers() == handArrayList.get(1).getPrevFingers());
    }

    // add up current fingers of all hands, if it = 0, player is dead
    public int totalFingers() {
        int totalFingers = 0;
        for(Hand hand : handArrayList){
            totalFingers += hand.getCurFingers();
        }
        return totalFingers;
    }

    public int getLeftHandAmount() {
        return handArrayList.get(0).getCurFingers();
    }
    public int getRightHandAmount() {
        return handArrayList.get(1).getCurFingers();
    }

    public void setLeftHandAmount(int amount) {
        handArrayList.get(0).setCurFingers(amount);
    }
    public void setRightHandAmount(int amount) {
        handArrayList.get(1).setCurFingers(amount);
    }


    //getters
    public String getDisplayName() {
        return displayName;
    }
    public int getPlayerNumber() {
        return playerNumber;
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

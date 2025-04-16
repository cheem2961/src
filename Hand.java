public class Hand {
    private int modFingers;
    private int curFingers;
    private int prevFingers;

    public Hand(int aMaxFingers) {
        this.modFingers = aMaxFingers;
        this.curFingers = 1;
        this.prevFingers = curFingers;
    }

    public int getModFingers() {
        return modFingers;
    }
    public int getCurFingers() {
        return curFingers;
    }
    public int getPrevFingers() {
        return prevFingers;
    }

    public void turnEnd(){
        prevFingers = curFingers;

    }
    public void recall(){
        curFingers = prevFingers;
    }
    public void tap(int aValue){
        curFingers += aValue;
        curFingers %= modFingers;
    }
    public void addOne(){
        curFingers ++;
    }
    public void removeOne(){
        curFingers --;
    }
}

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
    private int modFingers = 6;

    private int turnIndex = 0;
    private boolean tappingMode = false; //tapping mode causes active hand to be highlights and watch for user input to choose what hand to tap
    private boolean swappingMode = false;
    private int tapAmount;
    private Player currentPlayer;
    private Player opponentPlayer;
    private Image[] playerHandImages;
    private Image[] backgroundImages;


    private GraphicsContext gc;

    public GameController() {
        //empty constructor for now
    }

    //making the players
    public void setup() {
        for (int i = 1; i <= this.playerCount; i++) {
            this.players.add(new Player(String.valueOf(i), this.modFingers));
        }
        currentPlayer = this.players.get(turnIndex);
        opponentPlayer = this.players.get(turnIndex + 1 % this.playerCount);


        playerHandImages = new Image[]{
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

        backgroundImages = new Image[]{
                new Image("carpet.jpg")
        };
    }


    public void update(Scene scene, Canvas canvas) {
        //Listening for keys and performs their dedicated function
        this.keyListen(scene);

        //draws
        this.gc = canvas.getGraphicsContext2D();
        this.drawGame(scene, canvas);
        this.drawUI(scene,canvas);


    }

    public void drawUI(Scene scene, Canvas canvas) {

        if (tappingMode) {
            // display current player's (actors) name
            gc.setFill(Color.RED); //Text color
            gc.setFont(javafx.scene.text.Font.font("Arial", 30));
            gc.fillText("TAPPING MODE!", 280, 420);
            gc.setFill(Color.WHITE);
            gc.setFont(javafx.scene.text.Font.font("Arial", 15));
            gc.fillText("Click 'A' to select opponents left finger!\nClick 'd' to select opponents right finger!", 280, 435);
        }
        else if (swappingMode) {
            // display current player's (actors) name
            gc.setFill(Color.RED); //Text color
            gc.setFont(javafx.scene.text.Font.font("Arial", 30));
            gc.fillText("SWAPPING MODE!", 280, 420);
            gc.setFill(Color.WHITE);
            gc.setFont(javafx.scene.text.Font.font("Arial", 15));
            gc.fillText("Click 'SPACEBAR' to confirm your swap!", 280, 435);
        } else{
            //neither modes, just options
            gc.setFill(Color.RED); //Text color
            gc.setFont(javafx.scene.text.Font.font("Arial", 30));
            gc.fillText("CHOOSE YOUR ACTION!", 250, 420);
            gc.setFill(Color.WHITE);
            gc.setFont(javafx.scene.text.Font.font("Arial", 15));
            gc.fillText("Click 'Q' or 'A' to enter tapping mode\nClick 'A' or 'D' to enter swapping mode", 280, 435);
        }
    }

    public void drawGame(Scene scene, Canvas canvas) {


        //refreshes screen
        gc.clearRect((double) 0.0F, (double) 0.0F, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.DARKSLATEGRAY);
        gc.fillRect((double) 0.0F, (double) 0.0F, canvas.getWidth(), canvas.getHeight());


        //DRAW IMAGES

        //background
        //hallway
        //gc.drawImage(backgroundImages[0], -400, -400,1600,1600);

        //curent players hands
        gc.drawImage(playerHandImages[currentPlayer.getLeftHandAmount()], -260, 350, 650 * 1.4, 650); //left hand
        gc.drawImage(playerHandImages[currentPlayer.getRightHandAmount() + 6], 150, 350, 650 * 1.4, 650); //right hand

        //opponents hands
        gc.drawImage(playerHandImages[opponentPlayer.getLeftHandAmount()], -260, 440, (650 * 1.4), -650); //right hand (top left of the screen)
        gc.drawImage(playerHandImages[opponentPlayer.getRightHandAmount() + 6], 140, 440, 650 * 1.4, -650); //(left hand (top right of the screen


        // display current player's (actors) name
        gc.setFill(Color.WHITE); //Text color
        gc.setFont(javafx.scene.text.Font.font("Arial", 30));
        gc.fillText("player: " + currentPlayer.getDisplayName(), 350, 740);

        // display opponents player's (actors) name
        gc.setFill(Color.WHITE); //Text color
        gc.setFont(javafx.scene.text.Font.font("Arial", 30));
        gc.fillText("player: " + opponentPlayer.getDisplayName(), 350, 200);
    }

    public void tryEndTurn() {
        boolean validMove = currentPlayer.checkHandsChanged() & !currentPlayer.checkSymetricSwap();
        if (validMove) {
            this.endTurn();
        } else {
            System.out.println("invalid move, turn could not be ended");
        }
    }

    public void endTurn() {
        //loop through players
        for (Player player : players) {
            //update prev fingers
            player.turnEnd();
        }
        //draw eveything

        wait(400); //pause
        //switch players
        this.turnIndex = (this.turnIndex + 1) % this.playerCount;
        this.currentPlayer = this.players.get(turnIndex);
        this.opponentPlayer = this.players.get((turnIndex + 1) % this.playerCount);
        this.swappingMode = false;
        System.out.println("turn ended. Player turn index: " + this.turnIndex);
    }

    public void checkOpponentDeath() {
        System.out.println("Opponent death has been checked. total fingers = " + opponentPlayer.totalFingers());
        if (opponentPlayer.totalFingers() == 0) {
            System.out.println("player " + opponentPlayer.getDisplayName() + " is dead, player " + currentPlayer.getDisplayName() + "has won");
            endTurn();
        }
    }

    public void endRound() {
        System.out.println("Round ended");
    }

    public void tapLeft() {
        if (opponentPlayer.getLeftHandAmount() != 0) {
            int newFingers = (opponentPlayer.getLeftHandAmount() + tapAmount) % modFingers;
            opponentPlayer.setLeftHandAmount(newFingers);
            System.out.println("tapping opponents left hand to give new value of " + newFingers);
            tappingMode = false;
            checkOpponentDeath();
            endTurn();
        } else {
            System.out.println("couldnt tap opponent's left hand becuase it is empty");
        }
    }

    public void tapRight() {
        if (opponentPlayer.getRightHandAmount() != 0) {
            int newFingers = (opponentPlayer.getRightHandAmount() + tapAmount) % modFingers;
            opponentPlayer.setRightHandAmount(newFingers);
            System.out.println("tapping opponents right hand to give new value of " + newFingers);
            tappingMode = false;
            checkOpponentDeath();
            endTurn();
        } else {
            System.out.println("couldnt tap opponent's right hand becuase it is empty");
        }
    }

    public void keyListen(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case A:
                    if (tappingMode) {
                        tapLeft();
                    } else {

                        this.currentPlayer.swapHands(Direction.LEFT);
                        System.out.println("Swapped hands LEFT");

                        if (currentPlayer.checkHandsChanged()) {
                            swappingMode = true;
                            System.out.println(swappingMode);
                        }else {
                            swappingMode = false;
                            System.out.println(swappingMode);
                        }
                    }
                    break;
                case D:
                    if (tappingMode) {
                        tapRight();
                    } else {


                        this.currentPlayer.swapHands(Direction.RIGHT);
                        System.out.println("Swapped hands RIGHT");

                        if (currentPlayer.checkHandsChanged()) {
                            swappingMode = true;
                            System.out.println(swappingMode);
                        }else {
                            swappingMode = false;
                            System.out.println(swappingMode);
                        }
                    }
                    break;
                case Q:
                    //tap wth current player's left hand
                    if (!currentPlayer.checkHandsChanged() && !(currentPlayer.getLeftHandAmount() == 0)) { //check no swaps have been made
                        tappingMode = true; //turn on tapping mode
                        tapAmount = currentPlayer.getLeftHandAmount();
                        System.out.println("tapping mode with left hand and " + tapAmount + " fingers");
                    }
                    break;
                case E:
                    //tap wth current player's right hand
                    if (!currentPlayer.checkHandsChanged() && !(currentPlayer.getRightHandAmount() == 0)) { //check no swaps have been made
                        tappingMode = true; //turn on tapping mode
                        tapAmount = currentPlayer.getRightHandAmount();
                        System.out.println("tapping mode with right hand and " + tapAmount + " fingers");
                    }
                    break;
                case SPACE:
                    //confirm
                    //check final swap is valid
                    //run end of turn procedure
                    tryEndTurn();
                    break;
                case R:
                    currentPlayer.getHandArrayList().get(0).recall();
                    currentPlayer.getHandArrayList().get(1).recall();
                    swappingMode = false;
                    tappingMode = false;

                    break;

                default:
                    break;
            }

            // Optional: redraw after input
            //this.drawGame(actor);
        });
    }


    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
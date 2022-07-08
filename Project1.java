import java.util.Scanner;
import java.util.Random;
import java.time.Instant;
import  java.lang.Math;
public class Project1 {
    public static boolean win;
    public static boolean mov;
    public static boolean gameOver;
    public static int score = 0;
    public static double gametime = 0;
    public static int n;
    public static char move;
    public static char inputmenue;
    public static int[][] arr;
    public static int i;
    public static int j;
    public static int s;
    public static int[] secondArr;
    public static long start;
    public static long end;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        menue();
    }
    public static void menue() {
        systemcls();
        System.out.print("1. Start Game\n");
        System.out.print("2. Credits\n");
        System.out.print("3. Exit \n");
        System.out.print(" Enter a number:\n");
        inputmenue = scanner.next().charAt(0);
        switch (inputmenue) {
            case '1':
                playGame();
                break;
            case '2':
                secondmenue();
                break;
            case '3':
                break;
            default: {
                System.out.print("command was not valid\n");
                menue();
            }
        }
    }
    public static void secondmenue() {
        systemcls();
        System.out.print("name: \n\t\"zahra sadat moosavi\"\n");
        System.out.println("student number:\n \t<990122680005>");
        System.out.println("Professor Mirroshandel");
        System.out.println("press any key to back menu");
        char b =scanner.next().charAt(0);
       if(b == 'b') {
           menue();
       }
           else {
           menue();
       }
    }
    public static void playGame() {
        systemcls();
        setGlobal();
        System.out.println("please enter an integer for game...");
        firstPlay();
        render();
        while (!gameOver) {
            start = Instant.now().toEpochMilli();//for start time in game
            gameLoop();
            System.out.println(gametime);
        }
        if (gameOver) {
            scoreCheck();
            menue();
        }
    }
    public static void input_Move() {
        move = scanner.next().charAt(0);
        //for get string to move l or r or u or d
    }
    public static void setGlobal() {
        win = false;
        mov = false;
        gameOver = false;
        //For the first variable value
    }
    public static void gameName() {
        // random number for game number
        Random gamen = new Random();
        int name = gamen.nextInt(100);
        System.out.println(name);
    }
    public static void gameLoop() {
        input_Move();
        swap();
        render();
    }
    public static void firstPlay() {
    /* to get input n
    and make random matrix
     */
        n = scanner.nextInt();
        Random homematrix = new Random();
        arr = new int[n][n];
        for (s = 1; s < n * n; s++) {
            boolean tmp = false;
            while (!tmp) {
                i = homematrix.nextInt(n);
                j = homematrix.nextInt(n);
                if (arr[i][j] == 0) {
                    arr[i][j] = s;
                    tmp = true;
                }
            }
        }
    }
    public static void swap() {
        // get char for move
        if (move == 'e' || move == 'E') {
            scoreCheck();
            menue();
        } else if (move == 'a' || move == 'A') {
            System.out.println("please enter an integer for game...");
            firstPlay();
        } else if (move == 'l' || move == 'L') {
            leftMove();
        } else if (move == 'r' || move == 'R') {
            rightMove();
        } else if (move == 'U' || move == 'u') {
            upMove();
        } else if (move == 'd' || move == 'D') {
            downMove();
        } else {
            System.out.println(" the command is not valid\n please enter a valid command...");
        }
        end = Instant.now().toEpochMilli();//for end time in game
        gametime = (end - start) / 1000 + gametime;
        winCheck();

    }
    public static void rightMove() {
        //sharp go right
        mov = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) { // because of j+1
                if (arr[i][j] == 0) {
                    int replace = arr[i][j + 1];
                    arr[i][j + 1] = arr[i][j];
                    arr[i][j] = replace;
                    mov = true;
                    break;
                }
            }
            if (mov) {
                movement();
                break;
            }
        }
    }
    public static void leftMove() {
        //sharp go left
        mov = false;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {// because of j-1
                if (arr[i][j] == 0) {
                    int replace = arr[i][j - 1];
                    arr[i][j - 1] = arr[i][j];
                    arr[i][j] = replace;
                    mov = true;
                    break;
                }
            }
            if (mov) {
                movement();
                break;
            }
        }
    }
    public static void downMove() {
        //sharp go down
        mov = false;
        for (int i = 0; i < n - 1; i++) {// because of i+1
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) {
                    int replace = arr[i + 1][j];
                    arr[i + 1][j] = arr[i][j];
                    arr[i][j] = replace;
                    mov = true;
                    break;
                }
            }
            if (mov) {
                movement();
                break;
            }
        }
    }
    public static void upMove() {
        //sharp go up
        mov = false;
        for (int i = 1; i < n; i++) { // because of i-1
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) {
                    int replace = arr[i - 1][j];
                    arr[i - 1][j] = arr[i][j];
                    arr[i][j] = replace;
                    mov = true;
                    break;
                }
            }
            if (mov) {
                movement();
                break;
            }
        }
    }
    public static void movement() {
        // count how much move
        score = ++score;
    }
    public static void render() {
        // for output
        systemcls();
        System.out.println("==============================");
        System.out.print("\"GAME NUMBER\" :   ");
        gameName();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == 0) {
                    System.out.print("[ " + '#' + " ]" + "\t");
                } else if (arr[i][j] < 10) {
                    System.out.print("[ " + arr[i][j] + " ]" + "\t");

                } else {
                    System.out.print("[ " + arr[i][j] + "]" + "\t");
                }
            }
            System.out.println();
        }
        System.out.println("move: " + score);
        System.out.println("game time:  " + gametime);
        System.out.println("\t \t");
        System.out.println("PRESS L , R , U , D TO MOVE");
        System.out.println("(press 'E' to leave game and press 'A' to start again)");
        System.out.println("==============================");
    }
    public static void systemcls() {
        // to have clean code and game
        for (int m = 0; m < 25; m++) {
            System.out.println("\n");
        }
    }
    public static void scoreCheck() {
        // for check our score in end of the game
        int sorat = 1000 * n * n;
        double scoreLog = Math.log10(score); // get log from our move until end
        double radical = Math.pow(gametime, 1 / 3); //Abstraction on the basis of 3
        double makhraj = scoreLog * radical;
        double scoreGame = sorat / makhraj;
        System.out.println(" YOUR SCORE IS:  " + scoreGame);
    }
    public static boolean winCheck() {
        gameOver=false;
        int m=0;
        secondArr =new int[n*n];
            for( i = 0; i < n ; i++) { // convert a 2 dimensional array into a 1 dimensional array
                for ( j = 0; j < n ; j++) {
                        secondArr[m] = arr[i][j];
                        m++;
                }
            }
            int checkeven=0 ,checkodd=0;
            int h;
             if((n)%2==0) { // for even matrix
                for (  h = 0; h < ((n * n - 1) / 2) - 1; h++) { // for check correctly arrange even numbers
                    if (secondArr[h] % 2 == 0&& secondArr[h+1] == secondArr[h] + 2){
                              checkeven++;
                         }
                    }
                }
                if(checkeven == ((n * n - 1) / 2)-1 ){
                        for (h = ((n * n - 1) / 2); h < n * n-1; h++) {
                            if (secondArr[h] % 2 == 1 && secondArr[h+1] == secondArr[h]+2){
                                    checkodd++;
                            }
                        }
                        if(checkodd == ((n * n - 1) / 2) )
                            gameOver=true;
                    }
             checkeven=0;
             if((n)%2==1 ) { // for odd matrix
                for ( h = 0; h < ((n * n - 1) / 2); h++) { // for check correctly arrange even numbers
                    if (secondArr[h] % 2 == 0 && secondArr[h] + 2 == secondArr[ h + 1]) {
                        checkeven++;
                    }
                }
                if(checkeven==((n * n - 1) / 2)-1){
                        for (h = ((n * n - 1) / 2)+1 ; h < n * n; h++) {
                            if (secondArr[h] % 2 == 1 && secondArr[h+1] == secondArr[h] + 2)
                                // for check correctly arrange odd numbers
                                checkodd++;
                        }
                }
                if(checkodd == ((n * n - 1) / 2))
                    gameOver=true;
            }
            return gameOver;
    }
}
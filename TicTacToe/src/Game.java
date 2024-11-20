import java.util.Random;
import java.util.Scanner;

public class Game {

    private char[][] board = {{' ', ' ', ' '}, // row 1
                              {' ', ' ', ' '}, // row 2
                              {' ', ' ', ' '}}; // row 3

    public static void main(String[] args) {
        new Game();
    }

    public Game(){
        Scanner input = new Scanner(System.in);
        int selection = -1;

        System.out.println("Welcome to Tic Tac Toe Game");
        System.out.println("Please select an option:");

        do {
            System.out.println("1. Single Player");
            System.out.println("2. Multiplayer");
            System.out.println("3. Exit");

            try {
                selection = input.nextInt();
            } catch (Exception e) {
                input.next();
            }

            if(selection < 1 || selection > 3){
                System.out.println("Invalid Selection try again");
            }

            switch (selection){
                case 1:
                    singlePlayer();
                    break;
                case 2:
                    multiplayer();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
            }

        } while(selection != 3);
    }

    public void singlePlayer(){
        int player = 1;
        char symbol = 'X';

        System.out.println("Welcome to Tic Tac Toe Multiplayer");
        printMap();

        do{
            System.out.println("Select where to play (1-9):");

            if(player == 1){
                symbol = 'X';
                MultiplayerTurns(symbol, 1);
                printMap();

                if(winCondition('X')){
                    System.out.println("Player 1 wins!");
                    cleanBoard();
                    break;
                }
            }

            player++;

            if(player == 2){
                symbol = 'O';
                int AISelection = singlePlayerAI();
                AITurn(symbol, AISelection);
                printMap();

                if(winCondition('O')){
                    System.out.println("AI wins!");
                    cleanBoard();
                    break;
                }
            }

            player--;

        } while (!winCondition(symbol));
    }

    public void multiplayer(){
        int player = 1;
        char symbol = 'X';

        System.out.println("Welcome to Tic Tac Toe Multiplayer");
        printMap();

        do{
            System.out.println("Select where to play (1-9):");

            if(player == 1){
                symbol = 'X';
                MultiplayerTurns(symbol, 1);
                printMap();

                if(winCondition('X')){
                    System.out.println("Player 1 wins!");
                    cleanBoard();
                    break;
                }
            }

            player++;

            if(player == 2){
                symbol = 'O';
                MultiplayerTurns(symbol, 2);
                printMap();

                if(winCondition('O')){
                    System.out.println("Player 2 wins!");
                    cleanBoard();
                    break;
                }
            }

            player--;

        } while (!winCondition(symbol));
    }


    public boolean invalidSelection(int selection){
        return selection < 1 || selection > 9 || spaceTaken(selection);
    }

    public int singlePlayerAI(){
        Random rand = new Random();
        int selection = rand.nextInt(1, 10);

        while (invalidSelection(selection)){
            selection = rand.nextInt(1, 10);
        }

        return selection;
    }

    public void AITurn(char symbol, int selection){
        System.out.println("Machine's turn:");
        selectionPlay(symbol, selection);
    }


    public void MultiplayerTurns(char symbol, int pNum){
        Scanner input = new Scanner(System.in);
        System.out.println("Player" + pNum + "(X) turn:");
        int player = -1;

        try {
            player = input.nextInt();
        } catch (Exception e) {
            input.next();
        }

        while(invalidSelection(player)){
            System.out.println("Invalid Selection try again");

            try {
                player = input.nextInt();
            } catch (Exception e) {
                input.next();
            }

            printMap();
            System.out.println("---------------------------------------------");
        }

        selectionPlay(symbol, player);
    }

    public boolean spaceTaken(int selection){
        switch (selection){
            case 1:
                if(board[0][0] != ' ') return true; break;
            case 2:
                if(board[0][1] != ' ') return true; break;
            case 3:
                if(board[0][2] != ' ') return true; break;
            case 4:
                if(board[1][0] != ' ') return true; break;
            case 5:
                if(board[1][1] != ' ') return true; break;
            case 6:
                if(board[1][2] != ' ') return true; break;
            case 7:
                if(board[2][0] != ' ') return true; break;
            case 8:
                if(board[2][1] != ' ') return true; break;
            case 9:
                if(board[2][2] != ' ') return true; break;
        }

        return false;

    }

    public void cleanBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = ' ';
            }
        }
    }

    public boolean winCondition(char symbol){
        return (board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol ||
                board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol ||
                board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol ||

                board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol ||
                board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol ||
                board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol ||

                board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol ||
                board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    public void selectionPlay(char symbol, int selection){
        switch (selection){
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][1] = symbol;
                break;
            case 3:
                board[0][2] = symbol;
                break;
            case 4:
                board[1][0] = symbol;
                break;
            case 5:
                board[1][1] = symbol;
                break;
            case 6:
                board[1][2] = symbol;
                break;
            case 7:
                board[2][0] = symbol;
                break;
            case 8:
                board[2][1] = symbol;
                break;
            case 9:
                board[2][2] = symbol;
                break;
        }
    }

    public void printMap(){
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("-+-+-");
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("-+-+-");
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }
}
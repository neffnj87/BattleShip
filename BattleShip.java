import java.util.Scanner;

public class BattleShip {
    public static void main(String[] args) {
        String[][] playerBoard = new String[10][10];
        String[][] computerBoard = new String[10][10];
        String[][] battleBoard = new String[20][10];
        setPlayerBoard(playerBoard);
        setComputerBoard(computerBoard);
        combineBoards(playerBoard,computerBoard,battleBoard);
        battle(battleBoard);
    }
    public static void setPlayerBoard(String[][] playerBoard) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                playerBoard[i][j] = Integer.toString(0);
            for (int i = 0; i < 5; i++) {
                System.out.println("Enter the x coordinate for ship between 0 and 10 (inclusive) #" + (i + 1));
                int x = input.nextInt();
                System.out.println("Enter the y coordinate for ship between 0 and 10 (inclusive) #" + (i + 1));
                int y = input.nextInt();
                if(playerBoard[x][y].equals("@")) {
                    System.out.println("You already have a ship there, try again");
                    i--;
                } else  if (x > 10 || x < 0 || y > 10 || y < 0 ){
                    System.out.println("Invalid coordinates, all values are between 0 and 10 (inclusive)");
                    i--;
                } else {
                    playerBoard[x][y] = "@";
                }
            }
    }
    public static void setComputerBoard(String[][] computerBoard) {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                computerBoard[i][j] = Integer.toString(0);
        for (int i = 0; i < 5; i++) {
            int x = (int) (Math.random() * (10 - 0));
            int y = (int) (Math.random() * (10 - 0));
            if(computerBoard[x][y].equals("@")) {
                i--;
            } else {
                computerBoard[x][y] = "@";
            }
        }
    }
    public static void combineBoards(String[][] playerBoard, String[][] computerBoard, String[][] battleBoard) {
        int i;
        for (i = 0; i < playerBoard.length; i++) {
            battleBoard[i] = playerBoard[i];
        }
        for (int j = 0; j < computerBoard.length; j++) {
            battleBoard[i++] = computerBoard[j];
        }
    }
    public static void battle(String[][] battleBoard)  {
        Scanner input = new Scanner(System.in);
        int playerScore = 5;
        int computerScore = 5;
        for (int round = 1; playerScore > 0 && computerScore > 0; round++) {
            if (round % 2 != 0) {
                System.out.println("Round " + round + " Enter the x coordinate for shot between 0 and 10 (inclusive)");
                int x = input.nextInt();
                System.out.println("Enter the y coordinate for shot between 0 and 10 (inclusive)");
                int y = input.nextInt();
                if(battleBoard[(x + 10)][y].equals("@")) {
                    computerScore--;
                    System.out.println("Player Direct hit " + computerScore + " enemy ships left");
                } else {
                    System.out.printf("Computer Missed");
                    battleBoard[(x + 10)][y] = "x";
                }
            } else {
                for (int i = 0; i < 1; i++) {
                    int x = (int) (Math.random() * (10 - 0));
                    int y = (int) (Math.random() * (10 - 0));
                    if (battleBoard[x][y].equals("@")) {
                        playerScore--;
                        System.out.println("Computer Direct hit " + playerScore + " ships left");
                    } else if (battleBoard[x][y] == "x") {
                        System.out.println("You Missed");
                        i--;
                    } else {
                        battleBoard[x][y] = "x";
                    }
                }
        }
        if (playerScore == 0) {
            System.out.println("You Lose, Better Luck Next Time");
        } else if (computerScore == 0){
            System.out.println("Player Wins, Good Job");
        }
        }
    }
}

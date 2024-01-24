import java.util.Scanner;

public class App {

    public static Scanner inputReader = new Scanner(System.in);

    public static int[] player1Location = { 4, 0 };
    public static int[] player2Location = { 4, 8 };

    public static int numberOfWalls1 = 12;
    public static int numberOfWalls2 = 12;

    // public static int[][] createMatrix(String[][] boardOfGame, int rowOfGoal) {
    //     int[][] matrix = new int[9][9];
    //     for (int i = 0; i < 9; i++) {
    //         for (int j = 0; j < 9; j++) {
    //             if (boardOfGame[i][j].equals("/ ")) {
    //                 matrix[i][j] = 0;
    //             } else if (i == rowOfGoal) {
    //                 matrix[i][j] = 2;
    //             } else if (boardOfGame[i][j].equals("")) {
    //                 matrix[i][j] = 3;
    //             } else if (boardOfGame[i][j].equals("1 ") || boardOfGame[i][j].equals("2 ")) {
    //                 matrix[i][j] = 1;
    //             }
    //             System.out.print(matrix[i][j]);
    //         }
    //         System.out.println();
    //     }
    //     return matrix;
    // }

    // public static boolean validateExistanceOfRow(String[][] boardOfGame, int rowOfGoal) {
    //     int[][] matrix = createMatrix(boardOfGame, rowOfGoal);
    //     int n = 9;
    //     boolean visited[][] = new boolean[n][n];
    //     boolean flag = false;

    //     for (int i = 0; i < n; i++) {
    //         for (int j = 0; j < n; j++) {
    //             if (matrix[i][j] == 1 && !visited[i][j]){
    //                 if (isThereAPath(matrix, i, j, visited)) {
    //                     flag = true;
    //                     break;
    //                 }
    //             }
    //         }
    //     }
    //     return flag;
    // }

    // public static boolean pathIsSafe(int i, int j,
    //         int matrix[][]) {

    //     if (i >= 0 && i < matrix.length && j >= 0
    //             && j < matrix[0].length){
    //         return true;
    //     }
    //     return false;
    // }

    // public static boolean isThereAPath(int matrix[][], int i,
    //         int j, boolean visited[][]) {

    //     if (pathIsSafe(i, j, matrix) && matrix[i][j] != 0
    //             && !visited[i][j]) {
    //         visited[i][j] = true;
    //         if (matrix[i][j] == 2)
    //             return true;
    //         boolean up = isThereAPath(matrix, i - 1, j, visited);
    //         if (up)
    //             return true;
    //         boolean left = isThereAPath(matrix, i, j - 1, visited);
    //         if (left)
    //             return true;
    //         boolean down = isThereAPath(matrix, i + 1, j, visited);
    //         if (down)
    //             return true;
    //         boolean right = isThereAPath(matrix, i , j + 1, visited);
    //         if (right)
    //             return true;
    //     }
    //     return false;
    // }

    public static void printGamrBoard() {
        for (int i = 0; i < 19; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 19; j++) {
                    System.out.print(" = ");
                }
                System.out.println();
            } else if (i == 9) {
                for (int j = 0; j < 19; j++) {
                    if (j == 1) {
                        System.out.print(" 1 ");
                    } else if (j == 17) {
                        System.out.print(" 2 ");
                    } else if (j % 2 == 0) {
                        System.out.print(" | ");
                    } else {
                        System.out.print("   ");
                    }
                }
                System.out.println();
            } else {
                for (int j = 0; j < 19; j++) {
                    if (j % 2 == 0) {
                        System.out.print(" | ");
                    } else {
                        System.out.print("   ");
                    }
                }
                System.out.println();
            }
        }
    }

    public static void printPlayerToPlay(String playerTurn) {
        System.out.println("it's the turn of: " + playerTurn);
    }

    public static String handlePlayerChange(String playerTurn) {
        if (playerTurn.equals("player1")) {
            playerTurn = "player2";
        } else {
            playerTurn = "player1";
        }
        return playerTurn;
    }

    public static String getActions(String[][] boardOfGame, String playerTurn) {
        String actionType = "";

        System.out.println("if  you want to place a wall enter 1, if you want to move enter 2");
        int firstAnswerOfUser = inputReader.nextInt();
        inputReader.nextLine();

        if (firstAnswerOfUser == 1) {
            actionType = "wall";
        } else if (firstAnswerOfUser == 2) {
            actionType = "move";
        } else {
            System.out.println("invalid input");
            handleAction(boardOfGame, playerTurn);
        }
        return actionType;
    }

    public static int handlNumberOfWallsChange(int numberOfWalls) {
        numberOfWalls--;
        return numberOfWalls;
    }

    public static boolean validateWallsMove(String actionOfUser, int x1, int y1, int x2, int y2, int numberOfWalls,
            String[][] boardOfGame, int[] playerLocation, String playerTurn) {
        boolean inputIsValid = true;

        boolean cell1IsValid = 0 <= x1 && x1 <= 8 && 0 <= y1 && y1 <= 8 && boardOfGame[x1][y1].equals("")
                && numberOfWalls >= 0;
        boolean cell2IsValid = 0 <= x2 && x2 <= 8 && 0 <= y2 && y2 <= 8 && boardOfGame[x2][y2].equals("")
                && numberOfWalls >= 0;
        boolean wallIsValid = (x1 == x2 && (y1 - y2 == 1 || y2 - y1 == 1))
                || (y1 == y2 && (x1 - x2 == 1 || x2 - x1 == 1));
        
        // boardOfGame[x1][y1] = "/ ";
        // boardOfGame[x2][y2] = "/ ";

        // boolean rowIsValid1 = validateExistanceOfRow(boardOfGame, 8);

        // boolean rowIsValid2 = validateExistanceOfRow(boardOfGame, 0);
        // boardOfGame[x1][y1] = "";
        // boardOfGame[x2][y2] = "";

        if (cell1IsValid && cell2IsValid && wallIsValid ) {
            inputIsValid = true;
        } else {
            inputIsValid = false;
        }

        if (!inputIsValid) {
            System.out.println("input not valid, please try again...");
            numberOfWalls++;
            handleAction(boardOfGame, playerTurn);
        }
        System.out.println("number of your walls is: " + numberOfWalls);
        return inputIsValid;
    }

    public static boolean validateMove(int xDestination, int yDestination, String[][] boardOfGame, String playerTurn,
            int[] playerLocation) {
        boolean inputIsValid = true;
        int numberOfMovesX;
        int numberOfMovesY;
        int numberOfMoves;

        if (xDestination >= playerLocation[1]) {
            numberOfMovesX = xDestination - playerLocation[1];
        } else {
            numberOfMovesX = playerLocation[1] - xDestination;
        }
        if (yDestination >= playerLocation[0]) {
            numberOfMovesY = yDestination - playerLocation[0];
        } else {
            numberOfMovesY = playerLocation[0] - yDestination;
        }

        numberOfMoves = numberOfMovesX + numberOfMovesY;

        if (yDestination >= 0 && xDestination >= 0 && xDestination <= 8 && yDestination <= 8 && numberOfMoves == 1
                && boardOfGame[yDestination][xDestination].equals("")) {
            inputIsValid = true;
        } else {
            inputIsValid = false;
            System.out.println("input not valid");
            handleAction(boardOfGame, playerTurn);
        }
        return inputIsValid;
    }

    public static boolean checkForWinning(int[] playerLocation, String playerTurn) {
        boolean isGameOver = false;
        if (playerTurn.equals("player1")) {
            if (playerLocation[1] == 8) {
                isGameOver = true;
            }
        } else {
            if (playerLocation[1] == 0) {
                isGameOver = true;
            }
        }

        return isGameOver;
    }

    public static void printUpdatedBoard(String[][] boardOfGame, String playerTurn) {
        for (int i = 0; i < 19; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 19; j++) {
                    System.out.print(" = ");
                }
                System.out.println();
            } else {
                for (int j = 0; j < 19; j++) {
                    if (j % 2 == 0) {
                        System.out.print(" | ");
                    } else {
                        if (boardOfGame[(i - 1) / 2][(j - 1) / 2].length() > 0) {
                            System.out.print(" " + boardOfGame[(i - 1) / 2][(j - 1) / 2]);
                        } else {
                            System.out.print("   ");
                        }

                    }
                }
                System.out.println();
            }
        }

    }

    public static void printWinningMessage(String playerTurn) {
        handlePlayerChange(playerTurn);
        System.out.println(playerTurn + " has won");
    }

    public static void handleAction(String[][] boardOfGame, String playerTurn) {
        int[] playerLocation;
        int numberOfWalls;
        if (playerTurn.equals("player1")) {
            playerLocation = player1Location;
            numberOfWalls = numberOfWalls1;
        } else {
            playerLocation = player2Location;
            numberOfWalls = numberOfWalls2;
        }
        printPlayerToPlay(playerTurn);

        boolean isGameOver = checkForWinning(playerLocation, playerTurn);
        if (!isGameOver) {
            String actionOfUser = getActions(boardOfGame, playerTurn);
            if (actionOfUser.equals("wall")) {
                System.out.println("please enter the x of the first cell you want to put the wall in: ");
                int x1 = inputReader.nextInt();
                inputReader.nextLine();
                System.out.println("please enter the y of the first cell you want to put the wall in: ");
                int y1 = inputReader.nextInt();
                inputReader.nextLine();
                numberOfWalls = handlNumberOfWallsChange(numberOfWalls);
                System.out.println("please enter the x of the second cell you want to put the wall in: ");
                int x2 = inputReader.nextInt();
                inputReader.nextLine();
                System.out.println("please enter the y of the second cell you want to put the wall in: ");
                int y2 = inputReader.nextInt();
                inputReader.nextLine();
                numberOfWalls = handlNumberOfWallsChange(numberOfWalls);

                if (validateWallsMove(actionOfUser, x1, y1, x2, y2, numberOfWalls, boardOfGame, playerLocation,
                        playerTurn)) {
                    boardOfGame[y1][x1] = "/ ";
                    boardOfGame[y2][x2] = "/ ";
                    if (playerTurn.equals("player1")) {
                        numberOfWalls1 = numberOfWalls;
                    } else {
                        numberOfWalls2 = numberOfWalls;
                    }
                    playerTurn = handlePlayerChange(playerTurn);
                    printUpdatedBoard(boardOfGame, playerTurn);
                    handleAction(boardOfGame, playerTurn);
                }

            } else if (actionOfUser.equals("move")) {
                System.out.println("please enter the desired cell to go to: ");
                System.out.println("please enter the x value: ");
                int xDestination = inputReader.nextInt();
                inputReader.nextLine();
                System.out.println("please enter the y value: ");
                int yDestination = inputReader.nextInt();
                inputReader.nextLine();
                if (validateMove(xDestination, yDestination, boardOfGame, playerTurn, playerLocation)) {
                    boardOfGame[playerLocation[0]][playerLocation[1]] = "";

                    playerLocation[0] = yDestination;
                    playerLocation[1] = xDestination;

                    if (playerTurn.equals("player1")) {
                        boardOfGame[playerLocation[0]][playerLocation[1]] = "1 ";
                    } else {
                        boardOfGame[playerLocation[0]][playerLocation[1]] = "2 ";
                    }
                    printUpdatedBoard(boardOfGame, playerTurn);
                    isGameOver = checkForWinning(playerLocation, playerTurn);
                    if (isGameOver) {
                        printWinningMessage(playerTurn);
                    } else {
                        playerTurn = handlePlayerChange(playerTurn);
                        handleAction(boardOfGame, playerTurn);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String[][] boardOfGame = {
                { "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "" },
                { "1 ", "", "", "", "", "", "", "", "2 " },
                { "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "", "" }
        };

        String playerTurn = "player1";

        printGamrBoard();

        handleAction(boardOfGame, playerTurn);
    }
}

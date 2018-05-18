////////////////////////////////////////////////////////////////////////////
//
// Nathaniel McDonald
// March,10 2016
//
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//  DESCRIPTION: This program runs a game of tic tac toe between 2 people on one computer.
// 
// INPUTS:  Players move choices.
//    
//
// OUTPUTS:  Game board displaying moves and a notification when someone wins or if the game is a draw.
//   
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.*;
public class TicTacToe
{
  private char[][] gameBoard = new char[3][3];
  private final char player1 = 'X';
  private final char player2 = 'O';
  
  //Sets the initial values for the board as blank spaces.
  public void initialBoard()
  {
    for (int x = 0; x < 3; x++)
    {
      for (int y = 0; y < 3; y++)
      {
        gameBoard[x][y] = ' ';
      }
    }
  }
  
  //Inputs a users row and column selection and sets the corresponding array value to X or O depending on the player.
  public void addMove(int x,int y, char player)
  {
   gameBoard[x][y] = player;
  }
  
  //Checks to see if a player has won by comparing the individual rows, columns and diagonals for equality.
  public boolean checkForWin(int row, int col)
  {
    if (gameBoard[row][0]==gameBoard[row][1] && gameBoard[row][0]==gameBoard[row][2])
      return true;
    else if (gameBoard[0][col]==gameBoard[1][col] && gameBoard[0][col]==gameBoard[2][col])
      return true;
    else if (gameBoard[0][0]==gameBoard[1][1] && gameBoard[0][0]==gameBoard[2][2])
      return true;
    else if (gameBoard[0][2]==gameBoard[1][1] && gameBoard[0][2]==gameBoard[2][0])
      return true;
    else
      return false;
  }
  
  //Displays game board, asks players for move input and alternates players.
  //Inputs: Row and column of move selection
  //Outputs: Current game board and winner of the game or draw if it's a tie. After the game is over
  //         asks users if they would like to play again.
  public void readInput()
  {
    Scanner keyboard = new Scanner(System.in);
    initialBoard();
    //Provides extra instruction and runs the first 2 turns since nobody can win until at least the 3rd turn.
    for (int turn = 1; turn < 3; turn++)
    {
      printGameBoard();
      System.out.println("Player 1, please enter a row first and then a colummn number for your move on 2 seperate lines\n"+
                       "for example:\n 1\n 3\n corresponds to the top right corner.");
      int row = keyboard.nextInt();
      int col = keyboard.nextInt();
     //Checks that row and column values are in the appropriate range and displays an error message if they're not.
      while (checkRowCol(row, col))
    {
      System.out.println("Error, please enter a value between 1 and 3.");
      row = keyboard.nextInt();
      col = keyboard.nextInt();
    }
      //Checks that the chosen move hasn't been played already and prompts the user to go again
      //if the chosen move has already been made.
      while (checkBoxValue(row-1, col-1))
      {
        System.out.println("Sorry that spot is already taken, please choose again.");
        row = keyboard.nextInt();
        col = keyboard.nextInt();
      }
    addMove(row-1, col-1, player1);
    printGameBoard();
    //Prompts player 2 to for move selection and checks the entry for validity.
    System.out.println("Player 2, please enter a row first and then a colummn number for your move on 2 seperate lines\n"+
                       "for example:\n 1\n 3\n corresponds to the top right corner.");
    row = keyboard.nextInt();
    col = keyboard.nextInt();
    while (checkRowCol(row, col))
    {
      System.out.println("Error, please enter a value between 1 and 3.");
      row = keyboard.nextInt();
      col = keyboard.nextInt();
    }
     while (checkBoxValue(row-1, col-1))
      {
        System.out.println("Sorry that spot is already taken, please choose again.");
        row = keyboard.nextInt();
        col = keyboard.nextInt();
      }
    addMove(row-1, col-1, player2);
    }
    int gameOver = 0;
    printGameBoard();
    int turnCount = 4;
    do
   {
    //When 9 turns have passed without a winner the board is full and the game ends in a draw.
    turnCount++;
    System.out.println("Player 1, please enter a row first and then a colummn number for your move on 2 seperate lines.");
    int row = keyboard.nextInt();
    int col = keyboard.nextInt();
    while (checkRowCol(row, col))
    {
      System.out.println("Error, please enter a value between 1 and 3.");
      row = keyboard.nextInt();
      col = keyboard.nextInt();
    }
     while (checkBoxValue(row-1, col-1))
      {
        System.out.println("Sorry that spot is already taken, please choose again.");
        row = keyboard.nextInt();
        col = keyboard.nextInt();
      }
    addMove(row-1, col-1, player1);
    printGameBoard();
    if (checkForWin(row-1, col-1))
    {
      System.out.println("Player 1 Wins!");
      gameOver = 1;
    }
    //Ends the game in a draw if 9 turns have passed with no winner.
    else if (turnCount == 9)
    {
      System.out.println("The game is a draw!");
      gameOver = 1;
    }
    else
    {
    System.out.println("Player 2, please enter a row first and then a colummn number for your move on 2 seperate lines.");
      row = keyboard.nextInt();
      col = keyboard.nextInt();
    while (checkRowCol(row, col))
    {
      System.out.println("Error, please enter a value between 1 and 3.");
      row = keyboard.nextInt();
      col = keyboard.nextInt();
    }
     while (checkBoxValue(row-1, col-1))
      {
        System.out.println("Sorry that spot is already taken, please choose again.");
        row = keyboard.nextInt();
        col = keyboard.nextInt();
      }
    addMove(row-1, col-1, player2);
    printGameBoard();
    if (checkForWin(row-1, col-1))
    {
      System.out.println("Player 2 Wins");
      gameOver = 1;
    }
    else
      turnCount++;
    }
   }
    while (gameOver <= 0);
    playAgain();
  }
  
  //Asks the users if they would like to play another game. Displays an error message for a response other than y or n.
  public void playAgain()
  {
    String answer;   
    do
    {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Would you like to play again? Please enter y for yes or n or no.");
    answer = keyboard.next();
    if (answer.equalsIgnoreCase("y"))
          readInput();
    else if (answer.equalsIgnoreCase("n"))
      System.exit(0);
    else
     {
      System.out.println("Error please select y or n.");
      answer = "error";
     }
    }
    while (answer.equals("error"));
  }
  
  //Checks the row and column values to make sure they are in the appropriate range.
  //Inputs: row and column selection.
  //Outputs: true if the values are acceptable, false if they are outside the appropriate range.
  public boolean checkRowCol(int row, int col)
  {
    if (row < 0 || row > 3 || col < 0 || col > 3)
      return true;
    else
      return false;
  }
  
  //Checks if a move has already been played
  //Inputs: selected gameBoard char
  //Outputs: true if it already contains a move, false if it doesn't.
  public boolean checkBoxValue(int row, int col)
  {
    if (gameBoard[row][col] == 'X' || gameBoard[row][col] == 'O')
      return true;
    else
      return false;
  }
  
  //Prints out the current gameBoard
  public void printGameBoard()
  {
    System.out.println("-------------");
    for (int x = 0; x < 3; x++)
    {
      System.out.print("| ");
      for (int y = 0; y < 3; y++)
      {
        System.out.print(gameBoard[x][y] + " | ");
      }
      System.out.println("\n-------------");
    }
  }
  
  //Runs the game
  public static void main(String[] args)
   {
     TicTacToe newGame = new TicTacToe();
     newGame.readInput();
    }
}

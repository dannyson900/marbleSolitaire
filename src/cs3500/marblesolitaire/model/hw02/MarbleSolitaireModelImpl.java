package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

/**
 * MarbleSolitaire model represented with implemented methods.
 */

public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {


  private final int sRow;
  private final int sCol;
  private final int rowSize;
  private  int armThickness;
  private ArrayList<ArrayList<GamePiece>> gameBoard;

  /**
   * Constructs a MarbleSolitaireModel with no parameters, values are default.
   */
  public MarbleSolitaireModelImpl() {
    this.sCol = 3;
    this.sRow = 3;
    this.armThickness = 3;
    this.rowSize = (armThickness * 3) - 3;
    this.gameBoard = createBoardGame();
  }

  /**
   * Constructs a MarbleSolitaireModel with a given index to place the EmptySlot.
   * @param sRow the number representing which row our EmptySlot is in
   * @param sCol the number representing which column our EmptySlot is in
   * @throws IllegalArgumentException if any of our Parameters is out of bounds
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    this.armThickness = 3;
    this.rowSize = (armThickness * 3) - 3;
    if (isPositionOutOfBounds(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position ("
          + sRow + "," + sCol + ")");
    }
    else {
      this.sRow = sRow;
      this.sCol = sCol;
      this.gameBoard = createBoardGame();
    }
  }

  /**
   * Constructs a MarbleSolitairModel with a given number for our armThickness.
   * @param armThickness the number telling us how thick the arms of our gameBoard are
   * @throws  IllegalArgumentException if armThickness is even, or less than 3
   */
  public MarbleSolitaireModelImpl(int armThickness) {
    if (armThickness < 3 || (armThickness % 2 == 0)) {
      throw new IllegalArgumentException("ArmThickness is Invalid");
    }
    else {
      this.armThickness = armThickness;
      this.rowSize = (armThickness * 3) - 3;
      this.sRow = rowSize / 2;
      this.sCol = rowSize / 2;
      this.gameBoard = createBoardGame();
    }
  }

  /**
   * Constructs a MarbleSolitaireModel with a given number for armThickness.
   * and indices to position our EmptySlot.
   * @param armThickness the number telling us how thick the arms of our gameBoard are
   * @param sRow the number representing which row our EmptySlot is in
   * @param sCol the number representing which column our EmptySlot is in
   * @throws  IllegalArgumentException if armThickness is invalid or the if indices are invalid
   */
  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    if ((armThickness < 3) || (armThickness % 2 == 0)) {
      throw new IllegalArgumentException("ArmThickness invalid");
    }
    else {
      this.armThickness = armThickness;
      this.rowSize = (armThickness * 3) - 3;
    }
    if (isPositionOutOfBounds(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid cell");
    }
    this.sRow = sRow;
    this.sCol = sCol;
    this.gameBoard = createBoardGame();

  }

  /**
   * Constructs a MarbleSolitaireModel for testing our gameBoard only.
   * @param gameBoard A list of a list of GamePieces
   * @param sCol the number representing which column our EmptySlot is in
   * @param sRow the number representing which row our EmptySlot is in
   * @param armThickness the number telling us how thick the arms of our gameBoard are
   */
  public MarbleSolitaireModelImpl(ArrayList<ArrayList<GamePiece>> gameBoard,
      int sCol, int sRow, int armThickness) {
    this.armThickness = 3;
    this.rowSize = (armThickness * 3) - 3;
    this.sCol = sCol;
    this.sRow = sRow;
    this.gameBoard = gameBoard;
  }

  /**
   * Helper method on restrictions for our constructors, as well as our Move method.
   * @param sRow the number representing which row our EmptySlot is in
   * @param sCol the number representing which column our EmptySlot is in
   * @return boolean value whether or not the given indices is considered out of bounds
   */
  private boolean isPositionOutOfBounds(int sRow, int sCol) {
    int outOfBoundsLeft = this.armThickness - 1;
    int topBottomWidth = (this.armThickness * 2) - 1;
    int middleWidth = (this.armThickness) + ((this.armThickness - 1) * 2);
    int endMiddleRow = this.armThickness + outOfBoundsLeft;
    return (
        ((sRow < outOfBoundsLeft) && (sCol < outOfBoundsLeft || sCol >= topBottomWidth))
        || ((sRow >= outOfBoundsLeft && sRow < endMiddleRow) && (sCol < 0 || sCol >= middleWidth))
        || (sRow >= endMiddleRow && (sCol < outOfBoundsLeft || sCol >= topBottomWidth))
        || (sRow > this.rowSize || sCol > middleWidth || sCol < 0 || sRow < 0));
  }


  /**
   * Constructs an initial 2d array of GamePiece for our MarbleSolitaireModel.
   * to be further mutated in our Move method
   * @return a list of a list of GamePiece
   */
  private ArrayList<ArrayList<GamePiece>> createBoardGame() {
    ArrayList<ArrayList<GamePiece>> board = new ArrayList<ArrayList<GamePiece>>();
    int row = 0;
    int topBottomWidth = (this.armThickness * 2) - 1;
    int middleWidth = (this.armThickness) + ((armThickness - 1) * 2);
    while (row <= this.rowSize) {
      ArrayList<GamePiece> list = new ArrayList<GamePiece>();
      int col = 0;
      //creates top and bottom rows of board
      if (row < armThickness - 1 || row > (armThickness * 2) - 2) {
        int emptySpace = 1;
        while (col < topBottomWidth) {
          if (col == sCol && row == sRow) {
            list.add(new EmptySlot());
            col++;
          }
          else if (emptySpace < this.armThickness) {
            list.add(new EmptyGP());
            col++;
          }
          else {
            list.add(new Marble());
            col++;
          }
          emptySpace++;
        }
      }
      //creates the middle of the board
      else {
        while (col < middleWidth) {
          if (col == sCol && row == sRow) {
            list.add(new EmptySlot());
          }
          else {
            list.add(new Marble());
          }
          col++;
        }
      }
      board.add(list);
      row++;
    }
    return board;
  }


  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    //checks if the move can even be valid before mutating the gameboard
    //out of bounds on either positions
    //move made on a square, move ending on a marble
    if (isPositionOutOfBounds(fromRow, fromCol) || isPositionOutOfBounds(toRow, toCol)) {
      throw new IllegalArgumentException("error 1");
    }
    if (!(gameBoard.get(fromRow).get(fromCol).isMarble())) {
      throw new IllegalArgumentException("error 2");
    }
    if (!(gameBoard.get(toRow).get(toCol).isEmptySlot())) {
      throw new IllegalArgumentException("error 3");
    }
    //upwards
    if (fromRow > toRow) {
      if (!(fromRow - toRow == 2 && fromCol == toCol
          && gameBoard.get(fromRow - 1).get(fromCol).isMarble())) {
        throw new IllegalArgumentException("error 4");
      }
    }
    //downwards
    if (toRow > fromRow) {
      if (!(toRow - fromRow == 2 && fromCol == toCol
          && gameBoard.get(fromRow + 1).get(fromCol).isMarble())) {
        throw new IllegalArgumentException("error 5");
      }
    }
    //left
    if (fromCol > toCol) {
      if (!(fromRow == toRow && fromCol - toCol == 2
          && gameBoard.get(fromRow).get(fromCol - 1).isMarble())) {
        throw new IllegalArgumentException("error 6");
      }
    }
    //right
    if (toCol > fromCol) {
      if (!(fromRow == toRow && toCol - fromCol == 2
          && gameBoard.get(fromRow).get(fromCol + 1).isMarble())) {
        throw new IllegalArgumentException("error 7");
      }
    }

    //do 3 operations, change initial marble to empty slot
    // change marble adjacent on both given position to empty slot
    //change initial emptySlot to marble

    //upwards
    if (fromRow - toRow == 2) {
      gameBoard.get(fromRow).set(toCol, new EmptySlot());
      gameBoard.get(fromRow - 1).set(toCol, new EmptySlot());
      gameBoard.get(toRow).set(toCol, new Marble());
    }
    //downwards
    if (toRow - fromRow == 2) {
      gameBoard.get(fromRow).set(toCol, new EmptySlot());
      gameBoard.get(fromRow + 1).set(toCol, new EmptySlot());
      gameBoard.get(toRow).set(toCol, new Marble());
    }

    //right
    if (toCol - fromCol == 2) {
      gameBoard.get(fromRow).set(fromCol, new EmptySlot());
      gameBoard.get(fromRow).set(fromCol + 1, new EmptySlot());
      gameBoard.get(fromRow).set(toCol, new Marble());
    }

    //left
    if (fromCol - toCol == 2) {
      gameBoard.get(fromRow).set(fromCol, new EmptySlot());
      gameBoard.get(fromRow).set(fromCol - 1, new EmptySlot());
      gameBoard.get(fromRow).set(toCol, new Marble());
    }
  }

  /**
   * Helper method for Move to determine if the given indexes can make a valid move.
   * @param fromRow initial Row of our BoardGame
   * @param toRow row index we want to move to in our BoardGame
   * @param fromCol initial Column of our BoardGame
   * @param toCol column index we want to move to in our BoardGame
   * @param currentWidth number telling us the size of the current row
   * @return boolean value if a move can be made
   */
  private boolean canMove(int fromRow, int toRow, int fromCol, int toCol, int currentWidth) {
    if ((fromRow < 0 || toRow < 0 || fromCol < 0 || toCol < 0)
        || (toCol >= currentWidth  || fromCol >= currentWidth)
        || (toRow >= rowSize || fromRow >= rowSize)) {
      return false;
    }

    //checks if given gamePiece on board can move to any of the 4 possible spots

    else {
      return (gameBoard.get(toRow).get(toCol).isEmptySlot()
          && moveValidMarbles(fromRow,toRow,fromCol,toCol, currentWidth));
    }
  }

  /**
   * Helper method for Move to determine if the spaces in between the given indexes have a marble.
   * from various locations, depending on the given parameters.
   * @param fromRow initial Row of our BoardGame
   * @param toRow row index we want to move to in our BoardGame
   * @param fromCol initial Column of our BoardGame
   * @param toCol column index we want to move to in our BoardGame
   * @param currentWidth number telling us the size of the current row
   * @return boolean value if a successful move can be made
   */
  private boolean moveValidMarbles(int fromRow, int toRow,
      int fromCol, int toCol,int currentWidth) {
    if (fromRow - 1 < 0 && fromCol - 1 < 0) {
      return (gameBoard.get(fromRow + 1).get(fromCol).isMarble())
          || (gameBoard.get(fromRow).get(fromCol + 1).isMarble());
    }
    else if (fromRow - 1 < 0 && fromCol + 1 > currentWidth) {
      return (gameBoard.get(fromRow + 1).get(fromCol).isMarble())
          || (gameBoard.get(fromRow).get(fromCol - 1).isMarble());
    }
    else if (fromRow - 1 < 0) {
      return (gameBoard.get(fromRow + 1).get(fromCol).isMarble())
          || (gameBoard.get(fromRow).get(fromCol - 1).isMarble())
          || (gameBoard.get(fromRow).get(fromCol + 1).isMarble());
    }
    else if (fromRow + 1 > rowSize && fromCol + 1 > currentWidth) {
      return (gameBoard.get(fromRow - 1).get(fromCol).isMarble())
          || (gameBoard.get(fromRow).get(fromCol - 1).isMarble());
    }
    else if (fromRow + 1 > rowSize && fromCol - 1 < 0) {
      return (gameBoard.get(fromRow - 1).get(fromCol).isMarble())
          || (gameBoard.get(fromRow).get(fromCol + 1).isMarble());
    }
    else if (fromRow + 1 > rowSize) {
      return (gameBoard.get(fromRow - 1).get(fromCol).isMarble())
          || (gameBoard.get(fromRow).get(fromCol - 1).isMarble())
          || (gameBoard.get(fromRow).get(fromCol + 1).isMarble());
    }
    else if (fromCol - 1 < 0) {
      return (gameBoard.get(fromRow - 1).get(fromCol).isMarble())
          || (gameBoard.get(fromRow + 1).get(fromCol).isMarble())
          || (gameBoard.get(fromRow).get(fromCol + 1).isMarble());
    }
    else if (fromCol + 1 > currentWidth) {
      return (gameBoard.get(fromRow - 1).get(fromCol).isMarble())
          || (gameBoard.get(fromRow + 1).get(fromCol).isMarble())
          || (gameBoard.get(fromRow).get(fromCol - 1).isMarble());
    }
    else {
      return (gameBoard.get(fromRow - 1).get(fromCol).isMarble())
          || (gameBoard.get(fromRow + 1).get(fromCol).isMarble())
          || (gameBoard.get(fromRow).get(fromCol - 1).isMarble())
          || (gameBoard.get(fromRow).get(fromCol + 1).isMarble()); }

  }


  @Override
  public boolean isGameOver() {
    //iterate through the whole gameBoard
    //if the current index is a marble, run canMove
    //if none of the marbles return false on canMove, then return false, else return true;
    boolean val = true;
    int row = 0;
    int middleWidth = (this.armThickness) + ((armThickness - 1) * 2);
    while (row < rowSize) {
      int col = 0;
      while (col < gameBoard.get(row).size()) {
        GamePiece piece = gameBoard.get(row).get(col);
        if (piece.isMarble()) {
          if (row < 2) {
            if ((canMove(row, row + 2, col, col, gameBoard.get(row + 2).size()))
                || (canMove(row, row, col, col + 2, gameBoard.get(row).size()))
                || (canMove(row, row, col, col - 2, gameBoard.get(row).size()))) {
              val = false;
              col = middleWidth;
              row = rowSize;
            }
          }
          else if (row > rowSize - 3) {
            if ((canMove(row, row - 2, col, col, gameBoard.get(row - 2).size()))
                || (canMove(row, row, col, col + 2, gameBoard.get(row).size()))
                || (canMove(row, row, col, col - 2, gameBoard.get(row).size()))) {
              val = false;
              col = middleWidth;
              row = rowSize;
            }
          }
          else {
            if ((canMove(row, row - 2, col, col, gameBoard.get(row - 2).size()))
                || (canMove(row, row + 2, col, col, gameBoard.get(row + 2).size()))
                || (canMove(row, row, col, col + 2, gameBoard.get(row).size()))
                || (canMove(row, row, col, col - 2, gameBoard.get(row).size()))) {
              val = false;
              col = middleWidth;
              row = rowSize;
            }
          }
        }
        col++;
      }
      row++;
    }
    return val;
  }



  @Override
  public String getGameState() {
    String gameState = "";
    int row = 0;
    while ((row <= rowSize)) {
      int col = 0;
      while (col < gameBoard.get(row).size()) {
        if (gameBoard.get(row).get(col).isEmptyGP()) {
          gameState = gameState + "  ";
        }
        if (gameBoard.get(row).get(col).isEmptySlot()) {
          gameState = gameState + "_ ";
        }
        if (gameBoard.get(row).get(col).isMarble()) {
          gameState = gameState + "O ";
        }
        if (col == gameBoard.get(row).size() - 1) {
          gameState = gameState.substring(0,gameState.length() - 1);
        }
        col++;
      }
      if (row != rowSize) {
        gameState = gameState + "\n";
      }
      row++;
    }
    return gameState;
  }

  @Override
  public int getScore() {
    int score = 0;
    for (ArrayList<GamePiece> list : gameBoard) {
      for (GamePiece obj : list) {
        if (obj.isMarble()) {
          score++;
        }
      }
    }
    return score;
  }

}

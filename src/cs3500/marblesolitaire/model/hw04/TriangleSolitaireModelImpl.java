package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.EmptyGP;
import cs3500.marblesolitaire.model.hw02.EmptySlot;
import cs3500.marblesolitaire.model.hw02.GamePiece;
import cs3500.marblesolitaire.model.hw02.Marble;
import java.util.ArrayList;

/**
 * TriangleSolitaireModel represented with implemented methods.
 */
public class TriangleSolitaireModelImpl extends AbstractMarbleSolitaireModelImpl {
  private final int dimensions;

  /**
   * Constructs a MarbleSolitaireModel with no parameters, values are default.
   */
  public TriangleSolitaireModelImpl() {
    this.sRow = 0;
    this.sCol = 0;
    this.dimensions = 5;
    this.gameBoard = createBoardGame();
  }

  /**
   * Constructs a MarbleSolitaireModel with a given index to place the EmptySlot.
   * @param sRow the number representing which row our EmptySlot is in
   * @param sCol the number representing which column our EmptySlot is in
   * @throws IllegalArgumentException if any of our Parameters is out of bounds
   */
  public TriangleSolitaireModelImpl(int sRow, int sCol) {
    this.dimensions = 5;
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
   * Constructs a MarbleSolitaireModel with a given number for our dimensions.
   * @param dimensions the number telling us how many pieces the bottom of our gameBoard has
   * @throws  IllegalArgumentException if dimensions is not a positive number
   */
  public TriangleSolitaireModelImpl(int dimensions) {
    if (dimensions < 1) {
      throw new IllegalArgumentException("Dimension has to be positive integer");
    }
    else {
      this.sRow = 0;
      this.sCol = 0;
      this.dimensions = dimensions;
      this.gameBoard = createBoardGame();
    }
  }

  /**
   * Constructs a MarbleSolitaireModel with a given number for dimensions.
   * and indices to position our EmptySlot.
   * @param dimensions the number telling us how many pieces the bottom of our gameBoard includes
   * @param sRow the number representing which row our EmptySlot is in
   * @param sCol the number representing which column our EmptySlot is in
   * @throws  IllegalArgumentException if dimensions is non-positive or the if indices are invalid
   */
  public TriangleSolitaireModelImpl(int dimensions, int sRow, int sCol) {
    if  (dimensions < 1) {
      throw new IllegalArgumentException("Dimension has to be positive integer");
    }
    else {
      this.dimensions = dimensions;
    }
    if (isPositionOutOfBounds(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid cell");
    }
    this.sRow = sRow;
    this.sCol = sCol;
    this.gameBoard = createBoardGame();
  }

  @Override
  protected boolean isPositionOutOfBounds(int sRow, int sCol) {
    ArrayList<ArrayList<GamePiece>> board = createBoardGame();
    int emptySpace = (dimensions - 1) - sRow;
    int colCoord = (sCol + emptySpace);
    try {
      return (board.get(sRow).get(colCoord).isEmptyGP());
    }
    catch (IndexOutOfBoundsException e) {
      return true;
    }
  }

  @Override
  protected ArrayList<ArrayList<GamePiece>> createBoardGame() {
    ArrayList<ArrayList<GamePiece>> board = new ArrayList<ArrayList<GamePiece>>();
    int row = 0;
    while (row < dimensions) {
      ArrayList<GamePiece> list = new ArrayList<GamePiece>();
      int emptySpace = (dimensions - 1) - row;
      int colCoord = (sCol + emptySpace);
      int col = 0;
      while (col < dimensions) {
        if (col < emptySpace) {
          list.add(new EmptyGP());
          col++;
        }
        else if (col == colCoord && row == sRow) {
          list.add(new EmptySlot());
          col++;
        }
        else {
          list.add(new Marble());
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
    int fromEmptySpace = (dimensions - 1) - fromRow;
    int toEmptySpace = (dimensions - 1) - toRow;
    int fromColCoord = (fromCol + fromEmptySpace);
    int toColCoord = (toCol + toEmptySpace);
    //checks if the move can even be valid before mutating the gameboard
    //out of bounds on either positions
    //move made on a square, move ending on a marble
    if (isPositionOutOfBounds(fromRow, fromCol) || isPositionOutOfBounds(toRow, toCol)) {
      throw new IllegalArgumentException("error 1");
    }
    if (!(gameBoard.get(fromRow).get(fromColCoord).isMarble())) {
      throw new IllegalArgumentException("error 2");
    }
    if (!(gameBoard.get(toRow).get(toColCoord).isEmptySlot())) {
      throw new IllegalArgumentException("error 3");
    }
    //upwards
    else if (fromRow > toRow) {
      //right
      if (fromCol == toCol) {
        if (!(fromRow - toRow == 2
            && gameBoard.get(fromRow - 1).get(fromColCoord + 1).isMarble())) {
          throw new IllegalArgumentException("error 4");
        }
        else {
          gameBoard.get(fromRow).set(fromColCoord, new EmptySlot());
          gameBoard.get(fromRow - 1).set(fromColCoord  + 1, new EmptySlot());
          gameBoard.get(toRow).set(toColCoord, new Marble());
        }
      }
      //left
      else if (fromCol - toCol == 2) {
        if (!(fromRow - toRow == 2 && gameBoard.get(fromRow - 1).get(fromColCoord)
            .isMarble())) {
          throw new IllegalArgumentException("error 4.2");
        }
        else {
          gameBoard.get(fromRow).set(fromColCoord, new EmptySlot());
          gameBoard.get(fromRow - 1).set(fromColCoord, new EmptySlot());
          gameBoard.get(toRow).set(fromColCoord, new Marble());
        }
      }
      else {
        throw new IllegalArgumentException("error 4.5");
      }
    }
    //downwards
    else if (toRow > fromRow) {
      //right
      if (toCol - fromCol == 2) {
        if (!(toRow - fromRow == 2 && gameBoard.get(fromRow + 1).get(fromColCoord)
            .isMarble())) {
          throw new IllegalArgumentException("error 5");
        }
        else {
          gameBoard.get(fromRow).set(fromColCoord, new EmptySlot());
          gameBoard.get(fromRow + 1).set(fromColCoord, new EmptySlot());
          gameBoard.get(toRow).set(fromColCoord, new Marble());
        }
      }
      //left
      else if (fromCol == toCol) {
        if (!(toRow - fromRow == 2
            && gameBoard.get(fromRow + 1).get(fromColCoord - 1).isMarble())) {
          throw new IllegalArgumentException("error 5.2");
        }
        else {
          gameBoard.get(fromRow).set(fromColCoord, new EmptySlot());
          gameBoard.get(fromRow + 1).set(fromColCoord - 1, new EmptySlot());
          gameBoard.get(toRow).set(toColCoord, new Marble());
        }
      }
      else {
        throw new IllegalArgumentException("error 5.5");
      }
    }
    //left
    else if (fromCol > toCol) {
      if (!(fromRow == toRow && fromCol - toCol == 2
          && gameBoard.get(fromRow).get(fromColCoord - 1).isMarble())) {
        throw new IllegalArgumentException("error 6");
      }
      else {
        gameBoard.get(fromRow).set(fromColCoord, new EmptySlot());
        gameBoard.get(fromRow).set(fromColCoord - 1, new EmptySlot());
        gameBoard.get(fromRow).set(toColCoord, new Marble());
      }
    }
    //right
    else if (toCol > fromCol) {
      if (!(fromRow == toRow && toCol - fromCol == 2
          && gameBoard.get(fromRow).get(fromColCoord + 1).isMarble())) {
        throw new IllegalArgumentException("error 7");
      }
      else {
        gameBoard.get(fromRow).set(fromColCoord, new EmptySlot());
        gameBoard.get(fromRow).set(fromColCoord + 1, new EmptySlot());
        gameBoard.get(fromRow).set(toColCoord, new Marble());
      }
    }
    else {
      throw new IllegalArgumentException("error 29");
    }
  }

  @Override
  public boolean isGameOver() {
    //iterate through the whole gameBoard
    //if the current index is a marble, run canMove
    //if none of the marbles return false on canMove, then return false, else return true;
    boolean val = true;
    int row = 0;
    while (row < dimensions) {
      int col = 0;
      while (col < dimensions) {
        GamePiece piece = gameBoard.get(row).get(col);
        if (piece.isMarble()) {
          //if one marble can move one out of the six possible moves, the game is not over
          if (canMove(row, row - 2, col, col)
              || canMove(row, row - 2, col, col - 2)
              || canMove(row, row + 2, col, col)
              || canMove(row, row + 2, col, col + 2)
              || canMove(row, row, col + 2, col)
              || canMove(row, row, col - 2, col)) {
            val = false;
            col = dimensions;
            row = dimensions;
          }
        }
        col++;
      }
      row++;
    }
    return val;
  }



  /**
   * Helper method for Move to determine if the given indexes can make a valid move.
   * @param fromRow initial Row of our BoardGame
   * @param toRow row index we want to move to in our BoardGame
   * @param fromCol initial Column of our BoardGame
   * @param toCol column index we want to move to in our BoardGame
   * @return boolean value if a move can be made
   */
  private boolean canMove(int fromRow, int toRow, int fromCol, int toCol) {
    if ((fromRow < 0 || toRow < 0 || fromCol < 0 || toCol < 0)
        || (toCol >= dimensions  || fromCol >= dimensions)
        || (toRow >= dimensions || fromRow >= dimensions)) {
      return false;
    }

    //checks if given gamePiece on board can move to any of the 4 possible spots
    else {
      return moveValidMarbles(fromRow,toRow,fromCol,toCol);
    }
  }

  /**
   * Helper method for Move to determine if the spaces in between the given indexes have a marble.
   * from various locations, depending on the given parameters.
   * @param fromRow initial Row of our BoardGame
   * @param toRow row index we want to move to in our BoardGame
   * @param fromCol initial Column of our BoardGame
   * @param toCol column index we want to move to in our BoardGame
   * @return boolean value if a successful move can be made
   */
  private boolean moveValidMarbles(int fromRow, int toRow,
      int fromCol, int toCol) {
    int fromEmptySpace = (dimensions - 1) - fromRow;
    int toEmptySpace = (dimensions - 1) - toRow;
    int fromColCoord = (fromCol + fromEmptySpace);
    int toColCoord = (toCol + toEmptySpace);
    //checks if the move can even be valid before mutating the gameboard
    //out of bounds on either positions
    //move made on a square, move ending on a marble
    if (isPositionOutOfBounds(fromRow, fromCol) || isPositionOutOfBounds(toRow, toCol)) {
      return false;
    }
    if (!(gameBoard.get(fromRow).get(fromColCoord).isMarble())) {
      return false;
    }
    if (!(gameBoard.get(toRow).get(toColCoord).isEmptySlot())) {
      return false;
    }
    //upwards
    if (fromRow > toRow) {
      //right
      if (fromCol == toCol) {
        return (fromRow - toRow == 2
            && gameBoard.get(fromRow - 1).get(fromColCoord + 1).isMarble());
      }

      //left
      if (fromCol - toCol == 2) {
        return (fromRow - toRow == 2
            && gameBoard.get(fromRow - 1).get(fromColCoord).isMarble());
      }
      else {
        return false;
      }
    }
    //downwards
    if (toRow > fromRow) {
      //right
      if (toCol - fromCol == 2) {
        return (toRow - fromRow == 2 && gameBoard.get(fromRow + 1).get(fromColCoord)
            .isMarble());
      }
      //left
      if (fromCol == toCol) {
        return (toRow - fromRow == 2
            && gameBoard.get(fromRow + 1).get(fromColCoord - 1).isMarble());
      }
      else {
        return false;
      }
    }
    //left
    if (fromCol > toCol) {
      return fromRow == toRow && fromCol - toCol == 2
          && gameBoard.get(fromRow).get(fromColCoord - 1).isMarble();
    }

    //right
    if (toCol > fromCol) {
      return fromRow == toRow && toCol - fromCol == 2
          && gameBoard.get(fromRow).get(fromColCoord + 1).isMarble();
    }
    return false;
  }

  @Override
  public String getGameState() {
    String gameState = "";
    int row = 0;
    while ((row < dimensions)) {
      int col = 0;
      while (col < dimensions) {
        if (gameBoard.get(row).get(col).isEmptyGP()) {
          gameState = gameState + " ";
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
      if (row != dimensions - 1) {
        gameState = gameState + "\n";
      }
      row++;
    }
    return gameState;
  }
}

package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.GamePiece;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import java.util.ArrayList;

/**
 * MarbleSolitaireModel represented, abstracts the implemented methods.
 */
abstract class AbstractMarbleSolitaireModelImpl implements MarbleSolitaireModel {
  protected int sCol;
  protected int sRow;
  protected int rowSize;
  protected int sideLength;
  protected ArrayList<ArrayList<GamePiece>> gameBoard;

  /**
   * Helper method on restrictions for our constructors, as well as our Move method.
   * @param sRow the number representing which row our EmptySlot is in
   * @param sCol the number representing which column our EmptySlot is in
   * @return boolean value whether or not the given indices is considered out of bounds
   */
  protected abstract boolean isPositionOutOfBounds(int sRow, int sCol);

  /**
   * Constructs an initial 2d array of GamePiece for our MarbleSolitaireModel.
   * to be further mutated in our Move method
   * @return a list of a list of GamePiece
   */
  protected abstract ArrayList<ArrayList<GamePiece>> createBoardGame();



  @Override
  public abstract void move(int fromRow, int fromCol, int toRow, int toCol);

  @Override
  public abstract boolean isGameOver();

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

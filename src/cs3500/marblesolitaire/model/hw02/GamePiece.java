package cs3500.marblesolitaire.model.hw02;

/**
 * GamePieces for our MarbleSolitaire Model.
 */
public interface GamePiece {

  /**
   * Boolean to determine if the game piece is a Marble.
   * @return boolean value
   */
  boolean isMarble();

  /**
   * Boolean to determine if the game piece is an Empty Slot.
   * @return boolean value
   */
  boolean isEmptySlot();

  /**
   * Boolean to determine if the game piece is an EmptyGP.
   * @return boolean value
   */
  boolean isEmptyGP();
}

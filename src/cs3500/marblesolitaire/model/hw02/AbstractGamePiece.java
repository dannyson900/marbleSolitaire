package cs3500.marblesolitaire.model.hw02;

/**
 * Abstract base class for implementations of {@link GamePiece}.
 */
abstract class AbstractGamePiece implements GamePiece {

  @Override
  public boolean isMarble() {
    return false;
  }

  @Override
  public boolean isEmptySlot() {
    return false;
  }

  @Override
  public boolean isEmptyGP() {
    return false;
  }
}

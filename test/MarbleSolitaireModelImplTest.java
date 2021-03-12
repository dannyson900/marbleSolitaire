
import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.EmptyGP;
import cs3500.marblesolitaire.model.hw02.EmptySlot;
import cs3500.marblesolitaire.model.hw02.GamePiece;
import cs3500.marblesolitaire.model.hw02.Marble;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/**
 * Class testing implemented methods of MarbleSolitaireModel.
 */
public class MarbleSolitaireModelImplTest {

  MarbleSolitaireModel model;

  private void init() {
    model = new MarbleSolitaireModelImpl();
  }

  //Examples of GamePiece

  GamePiece marble = new Marble();
  GamePiece slot = new EmptySlot();
  GamePiece mt = new EmptyGP();

  //Example of a gameBoard that is defined to be gameOver (only one marble left)
  ArrayList<GamePiece> topBottomRowSlots = new ArrayList<GamePiece>(Arrays.asList(
      mt,mt,slot,slot,slot));
  ArrayList<GamePiece> oneMarble = new ArrayList<GamePiece>(Arrays.asList(
      mt,mt,marble,slot,slot));
  ArrayList<GamePiece> middleRowSlot = new ArrayList<GamePiece>(Arrays.asList(
      slot,slot,slot,slot,slot,slot,slot));

  ArrayList<ArrayList<GamePiece>> board = new ArrayList<ArrayList<GamePiece>>(Arrays.asList(
      topBottomRowSlots,oneMarble,middleRowSlot,middleRowSlot,
          middleRowSlot,topBottomRowSlots,topBottomRowSlots));


  //testing isMarble
  @Test
  public void testIsMarble() {
    assertEquals(true, marble.isMarble());
    assertEquals(false, slot.isMarble());
    assertEquals(false, mt.isMarble());
  }

  //testing isEmptySlot
  @Test
  public void testIsEmptySlot() {
    assertEquals(true, slot.isEmptySlot());
    assertEquals(false, marble.isEmptySlot());
    assertEquals(false, mt.isEmptySlot());
  }

  //testing isEmptyGP
  @Test
  public void testIsEmptyGP() {
    assertEquals(true, mt.isEmptyGP());
    assertEquals(false, marble.isEmptyGP());
    assertEquals(false, slot.isEmptyGP());
  }




  //testing failed models
  //testing invalid positions of the second constructor
  @Test (expected = IllegalArgumentException.class)
  public void testSecondConstructor() {
    new MarbleSolitaireModelImpl(0,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSecondConstructor2() {
    new MarbleSolitaireModelImpl(10,10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSecondConstructor3() {
    new MarbleSolitaireModelImpl(-9,4);
  }

  //testing invalid armThickness of the third constructor
  @Test (expected = IllegalArgumentException.class)
  public void testThirdConstructor() {
    new MarbleSolitaireModelImpl(-9);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testThirdConstructor2() {
    new MarbleSolitaireModelImpl(2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testThirdConstructor3() {
    new MarbleSolitaireModelImpl(1);
  }

  //testing invalid armThickness or invalid positions of the fourth constructor
  @Test (expected = IllegalArgumentException.class)
  public void testFourthConstructor() {
    new MarbleSolitaireModelImpl(1,3,3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testFourthConstructor2() {
    new MarbleSolitaireModelImpl(-3,3,3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testFourthConstructor3() {
    new MarbleSolitaireModelImpl(5,0,0);
  }


  //Testing invalid move positions
  //Testing out of bounds positions
  @Test (expected = IllegalArgumentException.class)
  public void moveFail() {
    new MarbleSolitaireModelImpl().move(3,3,0,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveFail2() {
    new MarbleSolitaireModelImpl().move(0,0,3,4);
  }

  //testing inBound positions but are not either too far apart/ close, and diagonal
  @Test (expected = IllegalArgumentException.class)
  public void moveFail3() {
    new MarbleSolitaireModelImpl().move(0,3,3,3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveFail4() {
    new MarbleSolitaireModelImpl().move(2,3,3,3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveFail5() {
    new MarbleSolitaireModelImpl().move(1,2,3,3);
  }

  //testing correct move positions, but there is either:
  //marble at the position we are moving to
  //empty slot in between the two positions,
  //empty slot in the position we are initially moving
  //testing these restrictions horizontally
  @Test (expected = IllegalArgumentException.class)
  public void moveFail6() {
    new MarbleSolitaireModelImpl().move(3,0,3,2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveFail7() {
    new MarbleSolitaireModelImpl().move(3,2,3,4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveFail8() {
    new MarbleSolitaireModelImpl().move(3,3,3,5);
  }

  //testing these restrictions vertically
  @Test (expected = IllegalArgumentException.class)
  public void moveFail9() {
    new MarbleSolitaireModelImpl().move(0,3,2,3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveFail10() {
    new MarbleSolitaireModelImpl().move(2,3,4,2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveFail11() {
    new MarbleSolitaireModelImpl().move(3,3,1,3);
  }


  //testing isGameOver method initial state and after moves are made
  @Test
  public void isGameOver() {
    this.init();
    assertEquals(false, model.isGameOver());
    model.move(1,3,3,3);
    assertEquals(false, model.isGameOver());

  }

  //testing a case where the game is over
  @Test
  public void isGameOver2() {
    MarbleSolitaireModel model2 = new MarbleSolitaireModelImpl(board,3,3,3);
    assertEquals(true, model2.isGameOver());
  }

  @Test
  public void isGameOver3() {
    this.init();
    assertEquals(false, model.isGameOver());
    model.move(1,3,3,3);
    assertEquals(false, model.isGameOver());
    model.move(4,3,2,3);
    assertEquals(false, model.isGameOver());
    model.move(3,1,3,3);
    assertEquals(false, model.isGameOver());
    model.move(3,4,3,2);
    assertEquals(false, model.isGameOver());
    model.move(3,6,3,4);
    assertEquals(false, model.isGameOver());
    model.move(6,3,4,3);
    assertEquals(true, model.isGameOver());
  }

  //testing getGameState method
  //testing default gameStates no moves made
  @Test
  public void getGameState() {
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O",new MarbleSolitaireModelImpl().getGameState());
  }

  @Test
  public void getGameState2() {
    assertEquals("        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O",new MarbleSolitaireModelImpl(5).getGameState());
  }

  @Test
  public void getGameState3() {
    assertEquals("    O O O\n"
        + "    _ O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O", new MarbleSolitaireModelImpl(1,2).getGameState());
  }


  //testing gameState after a move has been made
  @Test
  public void getGameState4() {
    this.init();
    model.move(1,3,3,3);
    assertEquals("    O O O\n"
        + "    O _ O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O", model.getGameState());
  }

  //Testing score of wellFormed models of various Sizes
  @Test
  public void getScore() {
    assertEquals(32, new MarbleSolitaireModelImpl().getScore());
  }

  @Test
  public void getScore2() {
    assertEquals(104, new MarbleSolitaireModelImpl(5).getScore());
  }

  @Test
  public void getScore3() {
    assertEquals(216, new MarbleSolitaireModelImpl(7).getScore());
  }

  //Testing the score change after a move has been made (score should decrease by 1
  @Test
  public void moveScore() {
    this.init();
    assertEquals(32, model.getScore());
    model.move(1,3,3,3);
    assertEquals(31, model.getScore());
    model.move(2,1,2,3);
    assertEquals(30,model.getScore());
    model.move(4,2,2,2);
    assertEquals(29,model.getScore());
  }



}
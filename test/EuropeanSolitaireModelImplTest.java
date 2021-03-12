import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import org.junit.Test;


/**
 * Class testing implemented methods of EuropeanSolitaireModel.
 */
public class EuropeanSolitaireModelImplTest {

  //testing failed constructors
  @Test (expected = IllegalArgumentException.class)
  public void failModel() {
    new EuropeanSolitaireModelImpl(2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void failModel2() {
    new EuropeanSolitaireModelImpl(2,8);
  }

  @Test (expected = IllegalArgumentException.class)
  public void failModel3() {
    new EuropeanSolitaireModelImpl(5,-2,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void failModel4() {
    new EuropeanSolitaireModelImpl(-1,0,0);
  }

  //testing failed moves
  @Test (expected = IllegalArgumentException.class)
  public void moveFail() {
    new EuropeanSolitaireModelImpl().move(3,0,0,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveFail2() {
    new EuropeanSolitaireModelImpl().move(2,1,0,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveFail3() {
    new EuropeanSolitaireModelImpl().move(-2,1,0,1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveFail4() {
    new EuropeanSolitaireModelImpl().move(4,1,0,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveFail5() {
    new EuropeanSolitaireModelImpl().move(4,2,2,2);
  }

  //testing getScore method
  @Test
  public void score() {
    MarbleSolitaireModel model = new EuropeanSolitaireModelImpl();
    assertEquals(36, model.getScore());
  }

  @Test
  public void gameScore() {
    MarbleSolitaireModel model = new EuropeanSolitaireModelImpl(5);
    model.move(6,4,6,6);
    assertEquals(127, model.getScore());

  }


  //testing gameState as well as how our models are created
  @Test
  public void gameState() {
    MarbleSolitaireModel model = new EuropeanSolitaireModelImpl();
    assertEquals("    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", model.getGameState());
  }

  @Test
  public void gameState2() {
    MarbleSolitaireModel model = new EuropeanSolitaireModelImpl(1,1);
    assertEquals("    O O O\n"
        + "  _ O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", model.getGameState());
  }



  @Test
  public void gameState3() {
    MarbleSolitaireModel model = new EuropeanSolitaireModelImpl(3,2,2);
    assertEquals("    O O O\n"
        + "  O O O O O\n"
        + "O O _ O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", model.getGameState());
  }

  @Test
  public void gameState4() {
    MarbleSolitaireModel model = new EuropeanSolitaireModelImpl(5);
    assertEquals("        O O O O O\n"
        + "      O O O O O O O\n"
        + "    O O O O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "    O O O O O O O O O\n"
        + "      O O O O O O O\n"
        + "        O O O O O", model.getGameState());
  }

  //testing our move method from all directions
  @Test
  public void move() {
    MarbleSolitaireModel model = new EuropeanSolitaireModelImpl(5);
    model.move(6,4,6,6);
    assertEquals("        O O O O O\n"
        + "      O O O O O O O\n"
        + "    O O O O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O _ _ O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "    O O O O O O O O O\n"
        + "      O O O O O O O\n"
        + "        O O O O O", model.getGameState());
  }

  @Test
  public void move2() {
    MarbleSolitaireModel model = new EuropeanSolitaireModelImpl(5);
    model.move(6,8,6,6);
    assertEquals("        O O O O O\n"
        + "      O O O O O O O\n"
        + "    O O O O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O _ _ O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "    O O O O O O O O O\n"
        + "      O O O O O O O\n"
        + "        O O O O O", model.getGameState());
  }

  @Test
  public void move3() {
    MarbleSolitaireModel model = new EuropeanSolitaireModelImpl(5);
    model.move(4,6,6,6);
    assertEquals("        O O O O O\n"
        + "      O O O O O O O\n"
        + "    O O O O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "    O O O O O O O O O\n"
        + "      O O O O O O O\n"
        + "        O O O O O", model.getGameState());
  }

  @Test
  public void move4() {
    MarbleSolitaireModel model = new EuropeanSolitaireModelImpl(5);
    model.move(8,6,6,6);
    assertEquals("        O O O O O\n"
        + "      O O O O O O O\n"
        + "    O O O O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "    O O O O O O O O O\n"
        + "      O O O O O O O\n"
        + "        O O O O O", model.getGameState());
  }


  //testing isGameOver
  @Test
  public void gameOver() {
    MarbleSolitaireModel model = new EuropeanSolitaireModelImpl(3);
    assertEquals(false, model.isGameOver());
  }


}

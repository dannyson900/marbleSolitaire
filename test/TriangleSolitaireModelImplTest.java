

import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;
import org.junit.Test;


/**
 * Class testing implemented methods of TriangleSolitaireModel.
 */
public class TriangleSolitaireModelImplTest {

  //testing failed constructors
  @Test (expected = IllegalArgumentException.class)
  public void failModel() {
    new TriangleSolitaireModelImpl(0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void failModel2() {
    new TriangleSolitaireModelImpl(3,8);
  }

  @Test (expected = IllegalArgumentException.class)
  public void failModel3() {
    new TriangleSolitaireModelImpl(5,-2,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void failModel4() {
    new TriangleSolitaireModelImpl(-1,0,0);
  }

  //testing failed moves
  @Test (expected = IllegalArgumentException.class)
  public void moveFail() {
    new TriangleSolitaireModelImpl().move(3,0,0,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveFail2() {
    new TriangleSolitaireModelImpl().move(2,1,0,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveFail3() {
    new TriangleSolitaireModelImpl().move(-2,1,0,1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveFail4() {
    new TriangleSolitaireModelImpl().move(4,1,0,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveFail5() {
    new TriangleSolitaireModelImpl().move(4,2,2,2);
  }

  //testing getScore method
  @Test
  public void score() {
    MarbleSolitaireModel model = new TriangleSolitaireModelImpl();
    assertEquals(14, model.getScore());
  }

  @Test
  public void gameScore() {
    MarbleSolitaireModel model = new TriangleSolitaireModelImpl(5);
    model.move(2,0,0,0);
    model.move(2,2,2,0);
    model.move(4,1,2,1);
    model.move(4,3,4,1);
    model.move(4,0,4,2);
    model.move(4,4,2,2);
    model.move(1,1,3,3);
    model.move(4,2,2,2);
    model.move(3,0,1,0);
    model.move(3,3,1,1);
    model.move(0,0,2,0);
    model.move(2,0,2,2);
    model.move(2,2,0,0);
    assertEquals(1, model.getScore());

  }


  //testing gameState as well as how our models are created
  @Test
  public void gameState() {
    MarbleSolitaireModel model = new TriangleSolitaireModelImpl();
    assertEquals("    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O", model.getGameState());
  }

  @Test
  public void gameState2() {
    MarbleSolitaireModel model = new TriangleSolitaireModelImpl(1,1);
    assertEquals("    O\n"
        + "   O _\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O", model.getGameState());
  }



  @Test
  public void gameState3() {
    MarbleSolitaireModel model = new TriangleSolitaireModelImpl(4,1,1);
    assertEquals("   O\n"
        + "  O _\n"
        + " O O O\n"
        + "O O O O", model.getGameState());
  }

  @Test
  public void gameState4() {
    MarbleSolitaireModel model = new TriangleSolitaireModelImpl(2);
    assertEquals(" _\n"
        + "O O", model.getGameState());
  }

  //testing our move method from all directions
  @Test
  public void move() {
    MarbleSolitaireModel model = new TriangleSolitaireModelImpl(5);
    model.move(2,2,0,0);
    assertEquals("    O\n"
        + "   O _\n"
        + "  O O _\n"
        + " O O O O\n"
        + "O O O O O", model.getGameState());
  }

  @Test
  public void move2() {
    MarbleSolitaireModel model = new TriangleSolitaireModelImpl(5,4,2);
    model.move(2,0,4,2);
    assertEquals("    O\n"
        + "   O O\n"
        + "  _ O O\n"
        + " O _ O O\n"
        + "O O O O O", model.getGameState());
  }

  @Test
  public void move3() {
    MarbleSolitaireModel model = new TriangleSolitaireModelImpl(5,4,2);
    model.move(2,2,4,2);
    assertEquals("    O\n"
        + "   O O\n"
        + "  O O _\n"
        + " O O _ O\n"
        + "O O O O O", model.getGameState());
  }

  @Test
  public void move4() {
    MarbleSolitaireModel model = new TriangleSolitaireModelImpl(5,4,2);
    model.move(4,4,4,2);
    assertEquals("    O\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O _ _", model.getGameState());
  }

  @Test
  public void move5() {
    MarbleSolitaireModel model = new TriangleSolitaireModelImpl(5,4,2);
    model.move(4,0,4,2);
    assertEquals("    O\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "_ _ O O O", model.getGameState());
  }

  @Test
  public void move6() {
    MarbleSolitaireModel model = new TriangleSolitaireModelImpl(5);
    model.move(2,0,0,0);
    assertEquals("    O\n"
        + "   _ O\n"
        + "  _ O O\n"
        + " O O O O\n"
        + "O O O O O", model.getGameState());
  }

  //testing isGameOver
  @Test
  public void gameOver() {
    MarbleSolitaireModel model = new TriangleSolitaireModelImpl(2);
    assertEquals(true, model.isGameOver());
  }

  @Test
  public void gameOverTriangleFull() {
    MarbleSolitaireModel model = new TriangleSolitaireModelImpl(5);
    model.move(2,0,0,0);
    assertEquals(false, model.isGameOver());
    model.move(2,2,2,0);
    assertEquals(false, model.isGameOver());
    model.move(4,1,2,1);
    assertEquals(false, model.isGameOver());
    model.move(4,3,4,1);
    assertEquals(false, model.isGameOver());
    model.move(4,0,4,2);
    assertEquals(false, model.isGameOver());
    model.move(4,4,2,2);
    assertEquals(false, model.isGameOver());
    model.move(1,1,3,3);
    assertEquals(false, model.isGameOver());
    model.move(4,2,2,2);
    assertEquals(false, model.isGameOver());
    model.move(3,0,1,0);
    assertEquals(false, model.isGameOver());
    model.move(3,3,1,1);
    assertEquals(false, model.isGameOver());
    model.move(0,0,2,0);
    assertEquals(false, model.isGameOver());
    model.move(2,0,2,2);
    assertEquals(false, model.isGameOver());
    model.move(2,2,0,0);
    assertEquals(true, model.isGameOver());

  }

}

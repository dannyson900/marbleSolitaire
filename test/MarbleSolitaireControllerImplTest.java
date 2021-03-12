import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import java.io.StringReader;
import org.junit.Test;

/**
 * Represents our tester class for MarbleSolitaireControllerImpl.
 * Testing our controller in terms of how it outputs based on the inputs the user gives it.
 */

public class MarbleSolitaireControllerImplTest {


  Appendable out = new StringBuilder();

  //testing exceptions thrown at MarbleSolitaireControllerImpl
  @Test(expected = IllegalArgumentException.class)
  public void testFailedController() {
    new MarbleSolitaireControllerImpl(null, out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFailedController2() {
    new MarbleSolitaireControllerImpl(new StringReader(""), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFailedController3() {
    new MarbleSolitaireControllerImpl(null, null);
  }

  //testing exception thrown at playGame method
  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    new MarbleSolitaireControllerImpl(new StringReader("1 3 3 3"), out).playGame(null);
  }

  //testing playGame with an empty string as the input
  @Test
  public void testPlayGameBaseCase() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    Readable in = new StringReader("");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n", out.toString());
  }

  @Test
  public void testPlayGameBaseCase2() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl(5);
    Readable in = new StringReader("");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
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
        + "        O O O O O\n"
        + "Score: 104\n", out.toString());
  }

  @Test
  public void testPlayGameBaseCase3() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl(5, 0, 4);
    Readable in = new StringReader("");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("        _ O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "Score: 104\n", out.toString());
  }

  @Test
  public void testPlayGameBaseCase4() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl(0, 3);
    Readable in = new StringReader("");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O _ O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n", out.toString());
  }

  //testing PlayGame with quit function inputted
  @Test
  public void testPlayGameQuit() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    Readable in = new StringReader("q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n", out.toString());
  }

  @Test
  public void testPlayGameQuit2() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl(0, 3);
    Readable in = new StringReader("Q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O _ O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n", out.toString());
  }

  //testing playGame with input of invalid String or invalid Integers.
  @Test
  public void testInvalidStrings() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl(0, 3);
    Readable in = new StringReader("qQ");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O _ O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "re-enter the value again \n"
        + "    O _ O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n", out.toString());
  }

  @Test
  public void testInvalidStrings2() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl(0,3);
    Readable in = new StringReader("-10");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O _ O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "re-enter the value again \n"
        + "    O _ O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n", out.toString());
  }

  @Test
  public void testInvalidStrings3() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl(0, 3);
    Readable in = new StringReader("abc -2 0 q2");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O _ O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "re-enter the value again \n"
        + "re-enter the value again \n"
        + "re-enter the value again \n"
        + "re-enter the value again \n"
        + "    O _ O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n", out.toString());
  }

  //testing PlayGame solely on one move in all directions
  @Test
  public void testOneMove() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    Readable in = new StringReader("2 4 4 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "    O O O\n"
        + "    O _ O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n", out.toString());
  }

  @Test
  public void testOneMove2() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    Readable in = new StringReader("4 2 4 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O _ _ O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n", out.toString());
  }

  @Test
  public void testOneMove3() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    Readable in = new StringReader("4 6 4 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n", out.toString());
  }

  @Test
  public void testOneMove4() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    Readable in = new StringReader("6 4 4 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n", out.toString());
  }

  //testing invalid moves
  @Test
  public void testInvalidMoves() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    Readable in = new StringReader("5 4 4 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Invalid move. Play again. inputs have to be non negative and 2 positions away"
        + "either by row or by columns\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n", out.toString());
  }

  @Test
  public void testInvalidMoves2() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl(2, 3);
    Readable in = new StringReader("5 6 3 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Invalid move. Play again. inputs have to be non negative and 2 positions away"
        + "either by row or by columns\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n", out.toString());
  }

  //testing making a successful move then quitting after
  @Test
  public void testMoveandQuit() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    Readable in = new StringReader("6 4 4 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n", out.toString());
  }
  //testing quitting first even when we have a valid move in our input

  @Test
  public void testMoveandQuit2() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    Readable in = new StringReader("q 6 4 4 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n", out.toString());
  }

  //testing making multiple valid moves
  @Test
  public void testMultiMove() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    Readable in = new StringReader("6 4 4 4 5 2 5 4 3 3 5 3");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O _ _ O O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 30\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O _ O O O O\n"
        + "O O _ O O O O\n"
        + "O _ O O O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 29\n", out.toString());
  }

  //testing making invalid move in between valid moves
  @Test
  public void testValidInvalidMove() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    Readable in = new StringReader("6 4 4 4 2 3 3 3 5 2 5 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "Invalid move. Play again. inputs have to be non negative and 2 positions away"
        + "either by row or by columns\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O _ _ O O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 30\n", out.toString());
  }

  //testing valid moves and incorrect input
  @Test
  public void testValidMoveIncorrectInput() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    Readable in = new StringReader("6 4 4 4 abc -10 5 2 5 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "re-enter the value again \n"
        + "re-enter the value again \n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O _ _ O O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 30\n", out.toString());
  }

  //testing incorrect input and quitting the game
  @Test
  public void testIncorrectInputQuit() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    Readable in = new StringReader("0 abc -10 Q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "re-enter the value again \n"
        + "re-enter the value again \n"
        + "re-enter the value again \n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n", out.toString());
  }


  //testing mix of incorrect input, invalid moves, valid moves, and quitting
  @Test
  public void testMixInput() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    Readable in = new StringReader("6 4 4 4 abc -10 3 4 4 4 5 2 5 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "re-enter the value again \n"
        + "re-enter the value again \n"
        + "Invalid move. Play again. inputs have to be non negative and 2 positions away"
        + "either by row or by columns\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O _ _ O O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 30\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O _ _ O O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 30\n", out.toString());
  }

  //testing playGame with enough input to end the game
  @Test
  public void testPlayToEnd() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    Readable in = new StringReader("2 4 4 4 5 4 3 4 4 2 4 4 4 5 4 3 4 7 4 5 7 4 5 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    controller.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "    O O O\n"
        + "    O _ O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "    O O O\n"
        + "    O _ O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O _ O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 30\n"
        + "    O O O\n"
        + "    O _ O\n"
        + "O O O O O O O\n"
        + "O _ _ O O O O\n"
        + "O O O _ O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 29\n"
        + "    O O O\n"
        + "    O _ O\n"
        + "O O O O O O O\n"
        + "O _ O _ _ O O\n"
        + "O O O _ O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 28\n"
        + "    O O O\n"
        + "    O _ O\n"
        + "O O O O O O O\n"
        + "O _ O _ O _ _\n"
        + "O O O _ O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 27\n"
        + "Game over!\n"
        + "    O O O\n"
        + "    O _ O\n"
        + "O O O O O O O\n"
        + "O _ O _ O _ _\n"
        + "O O O O O O O\n"
        + "    O _ O\n"
        + "    O _ O\n"
        + "Score: 26\n", out.toString());
  }

}

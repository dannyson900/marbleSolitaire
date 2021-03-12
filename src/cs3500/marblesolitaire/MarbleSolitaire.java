package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;
import java.io.StringReader;
import java.util.Scanner;

/**
 * represents an interactive program taking in inputs to represent our models.
 */
public final class MarbleSolitaire {

  /**
   * when the program runs, we should be able to use our controller, create our models by
   * the given list of strings and be able to play the game.
   * @param args commands that we input into our program
   */

  public static void main(String[] args) {
    MarbleSolitaireModel model;
    Appendable out = new StringBuilder();
    Readable read = new StringReader("");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(read,out);
    Scanner scan = new Scanner(System.in);
    String modelType = null;

    int hole1 = -1;
    int hole2 = -1;
    int size = -1;
    while (scan.hasNext()) {
      String next = scan.next();
      if (next.equals("triangular" ) || next.equals("english") || next.equals("european")) {
        modelType = next;
      }
      if (next.equals("-hole")) {
        hole1 = scan.nextInt();
        hole2 = scan.nextInt();
      }
      if (next.equals("-size")) {
        size = scan.nextInt();
      }
    }
    model = createModel(modelType, hole1, hole2, size);
    controller.playGame(model);
    System.out.println("Input commands inOrder to play the game");
    System.out.println(out.toString());
  }

  /**
   * Creates our model to implement in our controller's playGame method.
   * @param modelType the type of model we want to create
   * @param hole1 the row index where we want to place an emptySlot
   * @param hole2 the column index where we want to place an emptySlot
   * @param size the thickness, or dimensions of our board
   * @return a MarbleSolitaireModel
   */
  private static MarbleSolitaireModel createModel(String modelType,
      int hole1, int hole2, int size) {
    if (modelType.equals("triangular")) {
      return buildTriangularModel(hole1, hole2, size);
    }
    else if (modelType.equals("european")) {
      return buildEnglishModel(hole1, hole2, size);
    }
    else {
      return buildEuropeanModel(hole1, hole2, size);
    }
  }

  /**
   * Creates the European version of MarbleSolitaireModel.
   * @param hole1 the row index where we want to place an emptySlot
   * @param hole2 the column index where we want to place an emptySlot
   * @param size the thickness of our board
   * @return a MarbleSolitaireModel, constructed with these parameters
   */
  private static MarbleSolitaireModel buildEuropeanModel(int hole1, int hole2, int size) {
    if (hole1 == -1 && hole2 == -1 && size == -1) {
      return new EuropeanSolitaireModelImpl();
    }
    else if (size == -1) {
      return new EuropeanSolitaireModelImpl(hole1, hole2);
    }
    else if (hole1 == -1 || hole2 == -1) {
      return new EuropeanSolitaireModelImpl(size);
    }
    else {
      return new EuropeanSolitaireModelImpl(size, hole1, hole2);
    }

  }

  /**
   * Creates the English version of MarbleSolitaireModel.
   * @param hole1 the row index where we want to place an emptySlot
   * @param hole2 the column index where we want to place an emptySlot
   * @param size the thickness of our board
   * @return a MarbleSolitaireModel, constructed with these parameters
   */
  private static MarbleSolitaireModel buildEnglishModel(int hole1, int hole2, int size) {
    if (hole1 == -1 && hole2 == -1 && size == -1) {
      return new MarbleSolitaireModelImpl();
    }
    else if (size == -1) {
      return new MarbleSolitaireModelImpl(hole1, hole2);
    }
    else if (hole1 == -1 || hole2 == -1) {
      return new MarbleSolitaireModelImpl(size);
    }
    else {
      return new MarbleSolitaireModelImpl(size, hole1, hole2);
    }

  }

  /**
   * Creates the Triangular version of MarbleSolitaireModel.
   * @param hole1 the row index where we want to place an emptySlot
   * @param hole2 the column index where we want to place an emptySlot
   * @param size the dimensions of our board
   * @return a MarbleSolitaireModel, constructed with these parameters
   */
  private static MarbleSolitaireModel buildTriangularModel(int hole1, int hole2, int size) {
    if (hole1 == -1 && hole2 == -1 && size == -1) {
      return new TriangleSolitaireModelImpl();
    }
    else if (size == -1) {
      return new TriangleSolitaireModelImpl(hole1, hole2);
    }
    else if (hole1 == -1 || hole2 == -1
    ) {
      return new TriangleSolitaireModelImpl(size);
    }
    else {
      return new TriangleSolitaireModelImpl(size, hole1, hole2);
    }

  }
}

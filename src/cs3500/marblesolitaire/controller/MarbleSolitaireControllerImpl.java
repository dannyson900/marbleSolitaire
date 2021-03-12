package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * represents a MarbleSolitaireController with implemented methods.
 * this implementation will use its model to execute moves and accurately output the stage
 * of the current stage of the model.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private Readable rd;
  private Appendable ap;
  private Scanner scan;
  private ArrayList<Integer> list;

  /**
   * Constructs a MarbleSolitaireController.
   * @param rd a Readable that takes in the input
   * @param ap an Appendable that transmits the give input
   * @throws  IllegalArgumentException if any of the parameters are null
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Either your input or output are invalid");
    }
    this.ap = ap;
    this.scan = new Scanner(rd);
    this.list = new ArrayList<Integer>();
  }



  @Override
  public void playGame(MarbleSolitaireModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Error, model is null");
    }

    boolean gameStop = false;
    while (!(model.isGameOver())) {
      if (gameStop) {
        return;
      }
      try {
        //first operation
        ap.append(model.getGameState() + "\n");
        //second operation
        ap.append("Score: " + model.getScore() + "\n");
        if (!scan.hasNext()) {
          return;
        } else {
          //third operation
          if (consoleMoves(model, list, gameStop)) {
            return;
          }
        }
      } catch (Exception ioe) {
        throw new IllegalStateException("Error outputting", ioe);
      }
    }
  }


  /**
   * helper method for playGame: updates the model if need be, transmits specific messages,
   * depending on the input.
   * @param model Our MarbleSolitaireModel used to mutate, display the game state, and score
   * @param list A list of integers to use in our method move, once we have 4 valid inputs of int
   * @param gameStop A boolean to know at point we stop displaying the Procedure the hw mentions
   *                 used so we do not display the same output twice
   * @return a boolean to let us know in the playGame method where to stop.
   * @throws IOException if there is an error with transmitting the input
   */
  private boolean consoleMoves(MarbleSolitaireModel model, ArrayList<Integer> list,
      boolean gameStop) throws IOException {
    while (scan.hasNext()) {
      String console = scan.next();
      if (console.equals("q") || console.equals("Q")) {
        ap.append("Game quit!\n"
            + "State of game when quit:\n"
            + model.getGameState() + "\n"
            + "Score: " + model.getScore() + "\n");
        gameStop = true;
        return gameStop;
      }
      if ((!console.equals("q") && !console.equals("Q")) && (isInteger(console))) {
        ap.append("re-enter the value again \n");
      } else {
        list.add(Integer.valueOf(console));
        if (list.size() == 4) {
          int i = 0;
          try {
            model.move(list.get(0) - 1, list.get(1) - 1, list.get(2) - 1,
                list.get(3) - 1);
          } catch (IllegalArgumentException iae) {
            ap.append(
                "Invalid move. Play again. inputs have to be non negative and 2 positions away"
                    + "either by row or by columns\n");
          }
          while (i < 4) {
            list.remove(0);
            i++;
          }
          if (model.isGameOver()) {
            ap.append("Game over!\n");
            ap.append(model.getGameState() + "\n");
            ap.append("Score: " + model.getScore() + "\n");
            gameStop = true;
          }
          return gameStop;
        }
      }
    }
    return gameStop;
  }

  /**
   * Method to determine if the given string is an integer and is <= 0.
   * Used for when we call the next Part of our scanner, helps with the
   * restrictions of invalid inputs
   * @param s String that we will try to convert
   * @return A boolean of whether or not the string can be converted into an int, and is <= 0.
   */
  private boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
      return Integer.valueOf(s) <= 0;
    }
    catch (Exception e) {
      return true;
    }
  }
}


package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This interface represents the controller of MarbleSolitaireModel.
 * uses the model to execute moves made by the player
 * outputs the move made using the model's methods
 */
public interface MarbleSolitaireController {

  /**
   * Plays the game of MarbleSolitaire in form of a console.
   * @param model the model used to create the game
   * @throws IllegalArgumentException if the given model is null.
   * @throws IllegalStateException if the controller can not successfully receive input.
   * @throws IllegalStateException or transmit output.
   */
  void playGame(MarbleSolitaireModel model);

}

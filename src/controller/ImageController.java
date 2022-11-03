package controller;

import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Interface for a controller of image processing.
 */

public interface ImageController {

  /**
   * Starts the image processing from a series of commands inputted.
   *
   * @throws IOException              Throws error for scanner
   * @throws IllegalArgumentException Throws error for invalid inputs
   */

  public void start() throws IOException, IllegalArgumentException;

}

package model;

import java.util.List;

/**
 * Interface that represents an image.
 */

public interface ImageInterface<K> {

  /**
   * Grabs the pixel data from an image.
   *
   * @return The pixel data in the form of a list of type K
   */

  public List<K> getData();

  /**
   * Getter for the height of an image.
   *
   * @return int of the height
   */

  public int getHeight();

  /**
   * Getter for the width of an image.
   *
   * @return int for the width
   */

  public int getWidth();

  /**
   * Getter for the maxRGB value possible in the image.
   *
   * @return int for Max RGB value
   */

  public int getMaxRGB();

  /**
   * Getter to return the visibility of the image.
   *
   * @return Boolean for the visibility
   */

  public boolean getVisible();

  /**
   * Sets the visibility of an image.
   *
   * @param isVisible Boolean of what the visibility will be set to
   */

  public void setVisible(boolean isVisible);

}

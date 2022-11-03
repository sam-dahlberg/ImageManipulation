package model;

import java.util.List;

/**
 * This is the interface of the Image Creation Model. It is parameterized over the pixel type, i.e.
 * when you implement it, you can substitute K with your implementation of a pixel.
 */
public interface ImageCreationModel<K> {

  /**
   * creates an list of type K which represents an image.
   *
   * @return a list of type K that represents an image
   */
  public List<K> create();

  /**
   * Getter for the height of the model.
   *
   * @return the height
   */
  public int getHeight();

  /**
   * Getter for the width of the model.
   *
   * @return the width
   */
  public int getWidth();

  /**
   * Getter for the max value of the model.
   *
   * @return the max value
   */
  public int getMaxValue();

}

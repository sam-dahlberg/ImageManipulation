package model;


/**
 * This is the interface of the operations that can be applied to an image. Where type K represents
 * the Pixel type.
 */
public interface Operations<K> {

  /**
   * Applies the Operation to the given Image.
   *
   * @param image a 2D array of K that represents and image
   * @return a 2D array of K that represents the transformed image
   */
  public K[][] apply(K[][] image);

}

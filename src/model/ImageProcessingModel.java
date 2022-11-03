package model;


/**
 * This is the interface of the Image Processing Model. It is parameterized over the pixel type,
 * i.e. when you implement it, you can substitute K with your implementation of a pixel.
 */
public interface ImageProcessingModel<K> {


  /**
   * Implements an operation on the image.
   *
   * @param operation represent a transformation that can applied to and image.
   */
  public Image transform(Image image, Operations<K> operation);

}

package model;


/**
 * This is an implementation of FilteringOperations that represents the processes of blurring an
 * image.
 */
public class Blur extends FilteringOperations {

  private final double[][] kernel = {{.0625, .125, .0625},
      {.125, .25, .125},
      {.0625, .125, .0625}};

  @Override
  public Pixel[][] apply(Pixel[][] image) {
    return super.math(image, kernel);
  }
}

package model;


/**
 * This is an implementation of FilteringOperations that represents the processes of sharpening an
 * image.
 */
public class Sharpen extends FilteringOperations {

  private final double[][] kernel = {{-.125, -.125, -.125, -.125, -.125},
      {-.125, .25, .25, .25, -.125},
      {-.125, .25, 1, .25, -.125},
      {-.125, .25, .25, .25, -.125},
      {-.125, -.125, -.125, -.125, -.125}};

  @Override
  public Pixel[][] apply(Pixel[][] image) {
    return super.math(image, kernel);
  }
}

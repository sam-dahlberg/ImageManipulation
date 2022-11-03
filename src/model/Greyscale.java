package model;


/**
 * This is an implementation of ColorOperations that represents the processes of making an image
 * greyscale.
 */
public class Greyscale extends ColorOperations {

  private final double[][] kernel = {{.2126, .7152, .0722},
      {.2126, .7152, .0722},
      {.2126, .7152, .0722}};

  @Override
  public Pixel[][] apply(Pixel[][] image) {
    return super.math(image, kernel);
  }
}

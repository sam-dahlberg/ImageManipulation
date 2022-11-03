package model;


/**
 * This is an implementation of ColorOperations that represents the processes of making an image
 * sepia.
 */
public class Sepia extends ColorOperations {

  private final double[][] kernel = {{.393, .769, .189},
      {.349, .686, .168},
      {.272, .534, .131}};

  @Override
  public Pixel[][] apply(Pixel[][] image) {
    return super.math(image, kernel);
  }
}

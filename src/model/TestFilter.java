package model;


/**
 * Test filter for model testing.
 */
public class TestFilter extends FilteringOperations {

  private final double[][] kernel = {{0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0},
      {0, 0, .25, 0, 0},
      {0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0}};


  //This is just a handoff method to pass to the math class
  @Override
  public Pixel[][] apply(Pixel[][] image) {
    return super.math(image, kernel);
  }
}

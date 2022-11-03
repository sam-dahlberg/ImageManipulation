package model;


/**
 * Represents a pixel for an image. Will store rgb values along with the max value the pixel can
 * be.
 */
public class Pixel {

  private final int red;
  private final int green;
  private final int blue;
  private final int maxValue;


  /**
   * Constructor for Pixel. Takes in the rgb values as well as the max value of the pixel.
   *
   * @param red      represents the red color of a pixel
   * @param green    represents the green color of a pixel
   * @param blue     represents the blue color of a pixel
   * @param maxValue represents the max color a pixel rgb value can be
   * @throws IllegalArgumentException Throws if any values are incorrect
   */
  public Pixel(int red, int green, int blue, int maxValue) throws IllegalArgumentException {
    if (red < 0 || red > maxValue || green < 0 || green > maxValue || blue < 0 || blue > maxValue) {
      throw new IllegalArgumentException("Color out of range.");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;
    this.maxValue = maxValue;
  }

  /**
   * Method to convert the pixel into a string.
   *
   * @return returns the rgb values of a pixel as a string
   */
  public String toString() {
    return red + " " + green + " "
        + blue + "\t  ";
  }

  /**
   * Getter for the red value.
   *
   * @return returns the red value
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Getter for the green value.
   *
   * @return returns the green value
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Getter for the blue value.
   *
   * @return returns the blue value
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Getter for the maximum value.
   *
   * @return returns the maximum value
   */
  public int getMaxValue() {
    return this.maxValue;
  }


}

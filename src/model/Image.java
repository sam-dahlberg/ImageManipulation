package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an image, storing all information relevant to an image.
 */

public class Image implements ImageInterface<Pixel> {

  private final List<Pixel> data;
  private final int height;
  private final int width;
  private final int maxRGB;
  private boolean isVisible;

  /**
   * Image Constructor.
   *
   * @param data   list of pixel representing image
   * @param height height of the image
   * @param width  width of the image
   * @param maxRGB max value a pixel can be
   */
  public Image(List<Pixel> data, int height, int width, int maxRGB) {
    if (data == null || !validateImage(data)) {
      throw new IllegalArgumentException("Invalid list of Pixels");
    }

    this.data = new ArrayList<Pixel>(data);
    this.height = height;
    this.width = width;
    this.maxRGB = maxRGB;
    this.isVisible = true;
  }

  /**
   * Helper method to check if the pixel data of an image is valid.
   *
   * @param data The inputted pixel data
   * @return Boolean of whether the inputted data is valid
   */

  private Boolean validateImage(List<Pixel> data) {
    for (Pixel pixel : data) {
      if (pixel == null) {
        return false;
      }
    }
    return true;
  }

  public List<Pixel> getData() {
    return new ArrayList<Pixel>(data);
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public int getMaxRGB() {
    return maxRGB;
  }

  public boolean getVisible() {
    return isVisible;
  }

  public void setVisible(boolean isVisible) {
    this.isVisible = isVisible;
  }

}


